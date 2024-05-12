import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import Antlr.*;

import java.util.HashMap;

public class TypeRecord extends SolBaseListener
{
    private final ParseTreeProperty<Class<?>> types;
    private final HashMap<String, Label> labelCache;
    private HashMap<String, Function> functionCache;
    private ErrorLog errorLog;
    private static class Label
    {
        String name;
        Class<?> type;
        boolean isInitialized;

        public Label(String name, Class<?> type, boolean isInitialized)
        {
            this.name = name;
            this.type = type;
            this.isInitialized = isInitialized;
        }
    }

    public TypeRecord(ErrorLog errorLog)
    {
        this.types = new ParseTreeProperty<>();
        this.labelCache = new HashMap<>();
        this.functionCache = new HashMap<>();
        this.errorLog = errorLog;
    }

    public TypeRecord()
    {
        this(new ErrorLog());
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
        Class<?> labelType = this.labelCache.get(ctx.LABEL().getText()).type;
        Class<?> valueType = this.types.get(ctx.expression());

        if(!this.labelCache.containsKey(ctx.LABEL().getText()))
            this.errorLog.throwError(ctx,
                    "Variable '" + ctx.LABEL().getText() + "'" + " has not been defined");


        else if(!(labelType == double.class && valueType == int.class) && labelType != valueType)
            this.errorLog.throwError(ctx, "Incompatible types, " + labelType.getName() + " cannot be converted to " + valueType.getName());


        this.types.put(ctx, labelType);
        this.labelCache.get(ctx.LABEL().getText()).isInitialized = true;
    }

    @Override
    public void exitLabelExpression(SolParser.LabelExpressionContext ctx)
    {
        this.types.put(ctx, ctx.expression() != null ? this.types.get(ctx.expression()) : null);
    }

    @Override
    public void exitLocalDeclaration(SolParser.LocalDeclarationContext ctx)
    {
        String labelType = ctx.TYPE().getText();
        for(int i = 0; i < ctx.labelExpression().size(); i++)
        {
            String label = ctx.labelExpression(i).LABEL().getText();
            Class<?> valueType = this.types.get(ctx.labelExpression(i));

            if(valueType != null && stringToClass(labelType) != valueType)
                this.errorLog.throwError(ctx, "Incopatible types, " + labelType + " cannot be converted to " + valueType.getName());

            else if(this.labelCache.containsKey(label))
                this.errorLog.throwError(ctx, "Variable '" + label + "' is already defined");


            this.labelCache.put(label, new Label(label, stringToClass(labelType), valueType != null));
        }
    }

    @Override
    public void exitGlobalDeclaration(SolParser.GlobalDeclarationContext ctx)
    {
    }

    @Override
    public void exitInstruction(SolParser.InstructionContext ctx)
    {
        this.types.put(ctx, this.types.get(ctx.expression()));
    }

    @Override
    public void exitLable(SolParser.LableContext ctx)
    {
        if(!this.labelCache.containsKey(ctx.getText()) || !this.labelCache.get(ctx.getText()).isInitialized)
            this.errorLog.throwError(ctx, "Variable '" + ctx.getText()+ "'" + " has not been defined");

        Label label = this.labelCache.get(ctx.getText());

        this.types.put(ctx, label != null ? label.type: null);
    }

    @Override
    public void exitFunctionCall(SolParser.FunctionCallContext ctx)
    {
        if(!this.functionCache.containsKey(ctx.fname.getText()))
            this.errorLog.throwError(ctx, "Function '" + ctx.fname.getText() + "' does not exist.");

        Function function = this.functionCache.get(ctx.fname.getText());

        if(function.numberOfArgs() < ctx.expression().size())
            this.errorLog.throwError(ctx, "Too many arguments for function '" + function.name() + "'");
        else if(function.numberOfArgs() > ctx.expression().size())
            this.errorLog.throwError(ctx, "Too few arguments for function '" + function.name() + "'");

        this.types.put(ctx, function.returnType());
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
        FunctionRecord functionRecord = new FunctionRecord();
        this.functionCache = functionRecord.getFunctions(tree);
        this.errorLog = functionRecord.getErrorLog();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        return this.types;
    }

    public int getGlobalMemorySize()
    {
        return this.labelCache.size();
    }

    public ErrorLog getErrorLog()
    {
        return this.errorLog;
    }

}
