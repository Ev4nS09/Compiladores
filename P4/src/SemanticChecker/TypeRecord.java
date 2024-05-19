package SemanticChecker;

import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import Antlr.*;

import java.util.HashMap;

import org.stringtemplate.v4.compiler.STParser;
import solUtils.*;
public class TypeRecord extends SolBaseListener
{
    private final ParseTreeProperty<Class<?>> types;
    private final HashMap<String, Function> functionCache;
    private final ScopeTree scopeTree;
    private final ErrorLog errorLog;

    public TypeRecord(HashMap<String, Function> functionCache, ScopeTree scopeTree, ErrorLog errorLog)
    {
        this.types = new ParseTreeProperty<>();
        this.functionCache = functionCache;
        this.scopeTree = scopeTree;
        this.errorLog = errorLog;
    }

    public TypeRecord()
    {
        this(new HashMap<>(), new ScopeTree(), new ErrorLog());
    }

    public static String getSolType(String javaType)
    {
        if(javaType.equals(int.class.getName()))
            return "int";
        else if(javaType.equals(double.class.getName()))
            return "real";
        else if(javaType.equals(String.class.getName()))
            return "string";
        else if(javaType.equals(boolean.class.getName()))
            return "bool";
        else
            return "void";
    }

    public static String getSolType(Class<?> javaType)
    {
        if(javaType == int.class)
            return "int";
        else if(javaType == double.class)
            return "real";
        else if(javaType == String.class)
            return "string";
        else if(javaType == boolean.class)
            return "bool";
        else
            return "void";
    }

    public static Class<?> stringToClass(String type)
    {
        return switch (type)
        {
            case "int" -> int.class;
            case "real" -> double.class;
            case "string" -> String.class;
            case "bool" -> boolean.class;
            case "void" -> void.class;
            default -> null;
        };
    }

    @Override
    public void exitBreak(SolParser.BreakContext ctx)
    {
        ParserRuleContext parent = ctx.getParent();

        while (parent != null)
        {
            if(parent instanceof SolParser.LoopContext)
                return;

            parent = parent.getParent();
        }

        this.errorLog.throwError(ctx, "break outside loop.");
    }

    @Override
    public void exitReturn(SolParser.ReturnContext ctx)
    {
        Function function = Function.getCurrentFunction(ctx);

        boolean isExpressionNull = ctx.expression() == null;
        boolean isNumberConversionPossible = !isExpressionNull && function.returnType() == double.class && this.types.get(ctx.expression()) == int.class;
        boolean returnTypeEqualsFunctionType = !isExpressionNull && function.returnType() == this.types.get(ctx.expression());

        if(isExpressionNull && function.returnType() != void.class)
            this.errorLog.throwError(ctx, "Invalid return type 'void' for function '"
                    + function.name() + "' with return type '" + function.returnType() + "'");

        else if(!isNumberConversionPossible && !returnTypeEqualsFunctionType)
            this.errorLog.throwError(ctx, "Invalid return type '" + getSolType(this.types.get(ctx.expression())) +
                    "' for function '" + function.name() + "' with return type '" + getSolType(function.returnType()) + "'");
    }

    @Override
    public void exitInstruction(SolParser.InstructionContext ctx)
    {
        if(ctx.functionCall() != null && this.functionCache.get(ctx.functionCall().fname.getText()).returnType() != void.class)
            this.errorLog.throwError(ctx, "Value of '" + ctx.functionCall().fname.getText() + "' should be assign to a variable");
    }

    @Override
    public void exitIf(SolParser.IfContext ctx)
    {
        if(this.types.get(ctx.expression()) != boolean.class)
            this.errorLog.throwError(ctx,
                    "Incompatible types, " + getSolType(this.types.get(ctx.expression())) + " cannot be converted to bool");
    }

    @Override
    public void exitWhile(SolParser.WhileContext ctx)
    {
        if(this.types.get(ctx.expression()) != boolean.class)
            this.errorLog.throwError(ctx,
                    "Incompatible types, " + this.types.get(ctx.expression()).getName() + " cannot be converted to bool");

    }

    @Override
    public void exitFor(SolParser.ForContext ctx)
    {
        if(this.types.get(ctx.expression()) != int.class || this.types.get(ctx.affectation()) != int.class)
            this.errorLog.throwError(ctx,
                    "Incompatible types, " + getSolType(this.types.get(ctx.expression())) + " cannot be converted to int");

    }

    @Override
    public void exitAffectation(SolParser.AffectationContext ctx)
    {
        Class<?> labelType = this.scopeTree.getVariable(ctx, ctx.LABEL().getText()).type;
        Class<?> valueType = this.types.get(ctx.expression());

        if(!(labelType == double.class && valueType == int.class) && labelType != valueType)
            this.errorLog.throwError(ctx, "Incompatible types, " + getSolType(labelType) + " cannot be converted to " + getSolType(valueType));


        this.types.put(ctx, labelType);
    }

    @Override
    public void exitLabelExpression(SolParser.LabelExpressionContext ctx)
    {
        Class<?> labelType = stringToClass(((SolParser.DeclarationContext) ctx.parent).TYPE().getText());

        String label = ctx.LABEL().getText();
        Class<?> valueType = this.types.get(ctx.expression());

        boolean isValidConversion = labelType == double.class && valueType == int.class;

        if(valueType != null && !isValidConversion && labelType != valueType)
            this.errorLog.throwError(ctx, "Incopatible types, " + getSolType(valueType) + " cannot be converted to " + getSolType(labelType));

        this.types.put(ctx, labelType);
    }

    @Override
    public void exitPrint(SolParser.PrintContext ctx)
    {
        this.types.put(ctx, this.types.get(ctx.expression()));
    }

    @Override
    public void exitLabel(SolParser.LabelContext ctx)
    {
        Variable variable = this.scopeTree.getVariable(ctx, ctx.LABEL().getText());

        this.types.put(ctx, variable != null ? variable.type: null);
    }

    @Override
    public void exitFunctionCall(SolParser.FunctionCallContext ctx)
    {
        Function function = this.functionCache.get(ctx.fname.getText());

        if(ctx.expression().size() != function.numberOfArgs() || ctx.expression().size() != function.argumentTypes().size())
        {
            this.types.put(ctx, function.returnType());
            return;
        }

        for (int i = 0; i < ctx.expression().size(); i++)
        {
            boolean isNumberConversionPossible = this.types.get(ctx.expression(i)) == int.class && function.argumentTypes().get(i) == double.class;

            if (!isNumberConversionPossible && this.types.get(ctx.expression(i)) != function.argumentTypes().get(i))
                this.errorLog.throwError(ctx, "Invalid type '" + getSolType(this.types.get(ctx.expression(i))) +
                        "' for argument with type '" + getSolType(function.argumentTypes().get(i)) + "'");
        }


        this.types.put(ctx, function.returnType());
    }

    @Override
    public void exitFunctionCallExpression(SolParser.FunctionCallExpressionContext ctx)
    {
        this.types.put(ctx, this.types.get(ctx.functionCall()));
    }

    @Override
    public void exitLRParen(SolParser.LRParenContext ctx)
    {
        this.types.put(ctx, this.types.get(ctx.expression()));
    }

    @Override
    public void exitUnary(SolParser.UnaryContext ctx)
    {
        Class<?> nodeType = this.types.get(ctx.expression());

        boolean isNodeValidForSub = !ctx.op.getText().equals("-") || nodeType == double.class || nodeType == int.class;
        boolean isNodeValidForNot = !ctx.op.getText().equals("not") || nodeType == boolean.class;

        if(!(isNodeValidForSub && isNodeValidForNot))
            this.errorLog.throwError(ctx, "Invalid type " + getSolType(nodeType)+
                    " for unary operator '" + ctx.op.getText() + "'");


        this.types.put(ctx, this.types.get(ctx.expression()));
    }

    @Override
    public void exitMultDivMod(SolParser.MultDivModContext ctx)
    {
        Class<?> result = null;
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        boolean isLeftNodeIntOrDouble = leftNodeType == int.class || leftNodeType == double.class;
        boolean isRightNodeIntOrDouble = rightNodeType == int.class || rightNodeType == double.class;

        if(!isLeftNodeIntOrDouble || !isRightNodeIntOrDouble)
            this.errorLog.throwError(ctx,  "Invalid types for binary operator '" + ctx.op.getText() +
                    "'. Types" + getSolType(leftNodeType) + " and " + getSolType(rightNodeType));


        if(leftNodeType == double.class || rightNodeType == double.class)
            result = double.class;
        else if(leftNodeType == int.class || rightNodeType == int.class)
            result = int.class;

        this.types.put(ctx, result);
    }

    @Override
    public void exitAddSub(SolParser.AddSubContext ctx)
    {
        Class<?> result = null;
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        boolean isLeftAndRightNotString = leftNodeType != String.class && rightNodeType != String.class;
        boolean isLeftOrRightBoolean = leftNodeType == boolean.class || rightNodeType == boolean.class;

        if(isLeftAndRightNotString && isLeftOrRightBoolean)
            this.errorLog.throwError(ctx,  "Invalid types for binary operator '" + ctx.op.getText() +
                    "'. Types" + getSolType(leftNodeType) + " and " + getSolType(rightNodeType));


        if(leftNodeType == String.class || rightNodeType == String.class)
            result = String.class;
        else if(leftNodeType == double.class || rightNodeType == double.class)
            result = double.class;
        else if(leftNodeType == int.class || rightNodeType == int.class)
            result = int.class;

        this.types.put(ctx, result);
    }

    @Override
    public void exitRelational(SolParser.RelationalContext ctx)
    {
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        boolean isLeftOrRightString = leftNodeType == String.class || rightNodeType == String.class;
        boolean isLeftOrRightBoolean = leftNodeType == boolean.class || rightNodeType == boolean.class;

        if(isLeftOrRightString || isLeftOrRightBoolean)
            this.errorLog.throwError(ctx,  "Invalid types for binary operator '" + ctx.op.getText() +
                    "'. Types" + getSolType(leftNodeType) + " and " + getSolType(rightNodeType));

        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitIguality(SolParser.IgualityContext ctx)
    {
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        boolean leftNotEqualsRight = leftNodeType != rightNodeType;
        boolean isLeftNodeIntOrDouble = leftNodeType == int.class || leftNodeType == double.class;
        boolean isRightNodeIntOrDouble = rightNodeType == int.class || rightNodeType == double.class;
        boolean isRightAndLeftNumber = isLeftNodeIntOrDouble && isRightNodeIntOrDouble;

        if(leftNotEqualsRight && !(isRightAndLeftNumber))
            this.errorLog.throwError(ctx,  "Incopatible types, " + getSolType(rightNodeType)
                    + " cannot be converted to " + getSolType(leftNodeType));



        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitAnd(SolParser.AndContext ctx)
    {
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        if(leftNodeType != boolean.class || rightNodeType != boolean.class)
            this.errorLog.throwError(ctx,  "Invalid types for binary operator '" + ctx.op.getText() +
                    "'. Types" + getSolType(leftNodeType )+ " and " + getSolType(rightNodeType));


        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitOr(SolParser.OrContext ctx)
    {
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        if(leftNodeType != boolean.class || rightNodeType != boolean.class)
            this.errorLog.throwError(ctx,  "Invalid types for binary operator '" + ctx.op.getText() +
                    "'. Types" + getSolType(leftNodeType) + " and " + getSolType(rightNodeType));



        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitString(SolParser.StringContext ctx)
    {
        this.types.put(ctx, String.class);
    }

    @Override
    public void exitBool(SolParser.BoolContext ctx)
    {
        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitDouble(SolParser.DoubleContext ctx)
    {
        this.types.put(ctx, double.class);
    }

    @Override
    public void exitInt(SolParser.IntContext ctx)
    {
        this.types.put(ctx, int.class);
    }

    public ParseTreeProperty<Class<?>> getTypes(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        return this.types;
    }
}
