import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import Antlr.*;

import java.util.HashMap;

public class TypeRecord extends SolBaseListener
{
    private final ParseTreeProperty<Class<?>> types;
    private HashMap<String, Function> functionCache;
    private ParseTreeProperty<HashMap<String, Variable>> variableScopeCache;
    private ErrorLog errorLog;

    public TypeRecord(HashMap<String, Function> functionCache, ParseTreeProperty<HashMap<String, Variable>> variableScopeCache, ErrorLog errorLog)
    {
        this.types = new ParseTreeProperty<>();
        this.functionCache = functionCache;
        this.variableScopeCache = variableScopeCache;
        this.errorLog = errorLog;
    }

    public TypeRecord()
    {
        this(new HashMap<>(), new ParseTreeProperty<>(), new ErrorLog());
    }

    private SolParser.FunctionContext getFunction(RuleContext ctx)
    {
        while(ctx != null && !(ctx instanceof SolParser.FunctionContext))
            ctx = ctx.parent;

        return ctx != null ? (SolParser.FunctionContext) ctx : null;
    }

    private SolParser.ScopeContext getScope(RuleContext ctx)
    {
        while(ctx != null && !(ctx instanceof SolParser.ScopeContext))
            ctx = ctx.parent;

        return ctx != null ? (SolParser.ScopeContext) ctx : null;
    }

    private Variable getVariable(String variableName, SolParser.ScopeContext currentScope)
    {
        Variable result = null;

        while(currentScope != null)
        {
            if(this.variableScopeCache.get(currentScope) != null && this.variableScopeCache.get(currentScope).get(variableName) != null)
                break;

            currentScope = getScope(currentScope.parent);
        }

        return this.variableScopeCache.get(currentScope).get(variableName);
    }

    private Class<?> stringToClass(String type)
    {
        return switch (type)
        {
            case "int" -> int.class;
            case "real" -> double.class;
            case "string" -> String.class;
            case "bool" -> boolean.class;
            default -> null;
        };
    }

    private boolean typeCheck(String type1, Class<?> type2) {
        return
                type2 == null ||
                        type1.equals("int") && type2 == int.class ||
                        type1.equals("real") && (type2 == double.class || type2 == int.class) ||
                        type1.equals("string") && type2 == String.class ||
                        type1.equals("bool") && type2 == boolean.class;
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
        Function function = this.functionCache.get(getFunction(ctx).fname.getText());

        if(ctx.expression() == null && function.returnType() != void.class)
            this.errorLog.throwError(ctx, "Invalid return type 'void' for function '"
                    + function.name() + "' with return type '" + function.returnType() + "'");
        else if(ctx.expression() != null && (function.returnType() != this.types.get(ctx.expression())))
            this.errorLog.throwError(ctx, "Invalid return type '" + this.types.get(ctx.expression()) +
                    "' for function '" + function.name() + "' with return type '" + function.returnType() + "'");
    }

    @Override
    public void exitLine(SolParser.LineContext ctx)
    {
        if(ctx.functionCall() != null && this.functionCache.get(ctx.functionCall().fname.getText()).returnType() != void.class)
            this.errorLog.throwError(ctx, "Value of '" + ctx.functionCall().fname.getText() + "' should be assign to a variable");
    }

    @Override
    public void exitIf(SolParser.IfContext ctx)
    {
        if(this.types.get(ctx.expression()) != boolean.class)
            this.errorLog.throwError(ctx,
                    "Incompatible types, " + this.types.get(ctx.expression()).getName() + " cannot be converted to " + boolean.class.getName());
    }

    @Override
    public void exitWhile(SolParser.WhileContext ctx)
    {
        if(this.types.get(ctx.expression()) != boolean.class)
            this.errorLog.throwError(ctx,
                    "Incompatible types, " + this.types.get(ctx.expression()).getName() + " cannot be converted to " + boolean.class.getName());

    }

    @Override
    public void exitFor(SolParser.ForContext ctx)
    {
        if(this.types.get(ctx.expression()) != int.class || this.types.get(ctx.affectation()) != int.class)
            this.errorLog.throwError(ctx,
                    "Incompatible types, " + this.types.get(ctx.expression()).getName() + " cannot be converted to " + int.class.getName());

    }

    @Override
    public void exitAffectation(SolParser.AffectationContext ctx)
    {
        Class<?> labelType = this.getVariable(ctx.LABEL().getText(), getScope(ctx)).type;
        Class<?> valueType = this.types.get(ctx.expression());

        if(!(labelType == double.class && valueType == int.class) && labelType != valueType)
            this.errorLog.throwError(ctx, "Incompatible types, " + labelType.getName() + " cannot be converted to " + valueType.getName());


        this.types.put(ctx, labelType);
    }

    @Override
    public void exitLabelExpression(SolParser.LabelExpressionContext ctx)
    {
        this.types.put(ctx, ctx.expression() != null ? this.types.get(ctx.expression()) : null);
    }

    @Override
    public void exitDeclaration(SolParser.DeclarationContext ctx)
    {
        String labelType = ctx.TYPE().getText();
        for(int i = 0; i < ctx.labelExpression().size(); i++)
        {
            String label = ctx.labelExpression(i).LABEL().getText();
            Class<?> valueType = this.types.get(ctx.labelExpression(i));

            if(valueType != null && stringToClass(labelType) != valueType)
                this.errorLog.throwError(ctx, "Incopatible types, " + labelType + " cannot be converted to " + valueType.getName());
        }
    }

    @Override
    public void exitInstruction(SolParser.InstructionContext ctx)
    {
        this.types.put(ctx, this.types.get(ctx.expression()));
    }

    @Override
    public void exitLabel(SolParser.LabelContext ctx)
    {
        Variable variable = this.getVariable(ctx.getText(), getScope(ctx));

        this.types.put(ctx, variable != null ? variable.type: null);
    }

    @Override
    public void exitFunctionCall(SolParser.FunctionCallContext ctx)
    {
        Function function = this.functionCache.get(ctx.fname.getText());

        if(ctx.expression().size() == function.numberOfArgs() && ctx.expression().size() == function.argumentTypes().size())
            for(int i = 0; i < ctx.expression().size(); i++)
                if(this.types.get(ctx.expression(i)) != function.argumentTypes().get(i))
                    this.errorLog.throwError(ctx, "Invalid type '" + this.types.get(ctx.expression(i)) +
                    "' for argument with type '" + function.argumentTypes().get(i));

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
            this.errorLog.throwError(ctx, "Invalid type " + nodeType.getName() +
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
                    "'. Types" + leftNodeType.getName() + " and " + rightNodeType.getName());


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
                    "'. Types" + leftNodeType.getName() + " and " + rightNodeType.getName());


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
                    "'. Types" + leftNodeType.getName() + " and " + rightNodeType.getName());

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
            this.errorLog.throwError(ctx,  "Incopatible types, " + rightNodeType.getName()
                    + " cannot be converted to " + leftNodeType.getName());



        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitAnd(SolParser.AndContext ctx)
    {
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        if(leftNodeType != boolean.class || rightNodeType != boolean.class)
            this.errorLog.throwError(ctx,  "Invalid types for binary operator '" + ctx.op.getText() +
                    "'. Types" + leftNodeType.getName() + " and " + rightNodeType.getName());


        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitOr(SolParser.OrContext ctx)
    {
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        if(leftNodeType != boolean.class || rightNodeType != boolean.class)
            this.errorLog.throwError(ctx,  "Invalid types for binary operator '" + ctx.op.getText() +
                    "'. Types" + leftNodeType.getName() + " and " + rightNodeType.getName());



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
