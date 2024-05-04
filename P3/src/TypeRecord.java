import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import Antlr.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class TypeRecord extends SolBaseListener
{
    private final ParseTreeProperty<Class<?>> types;
    private final HashMap<String, Label> labelCache;
    private int programErrors;

    private static class Label
    {
        String label;
        Class<?> labelType;
        boolean isInitialized;

        public Label(String label, Class<?> labelType, boolean isInitialized)
        {
            this.label = label;
            this.labelType = labelType;
            this.isInitialized = isInitialized;
        }
    }

    public TypeRecord()
    {
        this.types = new ParseTreeProperty<>();
        this.labelCache = new HashMap<>();
        this.programErrors = 0;
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

        ErrorHandler.invalidBreak();
        this.programErrors++;
    }

    @Override
    public void exitIf(SolParser.IfContext ctx)
    {
        if(this.types.get(ctx.expression()) != boolean.class)
        {
            ErrorHandler.incompatibleTypes(ctx, this.types.get(ctx.expression()).getName(), boolean.class.getName());
            this.programErrors++;
        }
    }

    @Override
    public void exitWhile(SolParser.WhileContext ctx)
    {
        if(this.types.get(ctx.expression()) != boolean.class)
        {
            ErrorHandler.incompatibleTypes(ctx, this.types.get(ctx.expression()).getName(), boolean.class.getName());
            this.programErrors++;
        }
    }

    @Override
    public void exitFor(SolParser.ForContext ctx)
    {
        if(this.types.get(ctx.expression()) != int.class && this.types.get(ctx.expression()) != double.class)
        {
            ErrorHandler.incompatibleTypes(ctx, this.types.get(ctx.expression()).getName(), double.class.getName());
            this.programErrors++;
        }
    }

    @Override
    public void exitAffectation(SolParser.AffectationContext ctx)
    {
        Class<?> labelType = this.labelCache.get(ctx.LABEL().getText()).labelType;
        Class<?> valueType = this.types.get(ctx.expression());

        if(!this.labelCache.containsKey(ctx.LABEL().getText()))
        {
            ErrorHandler.undefinedVariables(ctx, ctx.LABEL().getText());
            this.programErrors++;
        }

        else if(labelType != valueType)
        {
            ErrorHandler.incompatibleTypes(ctx, labelType.getName(), valueType.getName());
            this.programErrors++;
        }

        this.types.put(ctx, labelType);
        this.labelCache.get(ctx.LABEL().getText()).isInitialized = true;
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
            {
                ErrorHandler.incompatibleTypes(ctx, labelType, valueType.getName());
                this.programErrors++;
            }
            else if(this.labelCache.containsKey(label))
            {
                ErrorHandler.redefinedVariables(ctx, label);
                this.programErrors++;
            }

            this.labelCache.put(label, new Label(label, stringToClass(labelType), valueType != null));
        }
    }

    @Override
    public void exitInstruction(SolParser.InstructionContext ctx)
    {
        this.types.put(ctx, this.types.get(ctx.expression()));
    }

    @Override
    public void exitLable(SolParser.LableContext ctx)
    {
        if(!this.labelCache.containsKey(ctx.getText()))
        {
            ErrorHandler.undefinedVariables(ctx, ctx.getText());
            this.programErrors++;
        }
        else if(!this.labelCache.get(ctx.getText()).isInitialized)
        {
            ErrorHandler.undefinedVariables(ctx, ctx.getText());
            this.programErrors++;
        }
        this.types.put(ctx, this.labelCache.get(ctx.getText()).labelType);
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
        {
            ErrorHandler.unaryOperatorError(ctx, ctx.op.getText(), nodeType.getName());
            this.programErrors++;
        }

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
        {
            ErrorHandler.binaryOperatorError(ctx, ctx.op.getText(), leftNodeType.getName(), rightNodeType.getName());
            this.programErrors++;
        }

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
        {
            ErrorHandler.binaryOperatorError(ctx, ctx.op.getText(), leftNodeType.getName(), rightNodeType.getName());
            this.programErrors++;
        }

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
        {
            ErrorHandler.binaryOperatorError(ctx, ctx.op.getText(), leftNodeType.getName(), rightNodeType.getName());
            this.programErrors++;
        }

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
        {
            ErrorHandler.incomparableTypesError(ctx, leftNodeType.getName(), rightNodeType.getName());
            this.programErrors++;
        }


        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitAnd(SolParser.AndContext ctx)
    {
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        if(leftNodeType != boolean.class || rightNodeType != boolean.class)
        {
            ErrorHandler.binaryOperatorError(ctx, ctx.op.getText(), leftNodeType.getName(), rightNodeType.getName());
            this.programErrors++;
        }

        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitOr(SolParser.OrContext ctx)
    {
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        if(leftNodeType != boolean.class || rightNodeType != boolean.class)
        {
            ErrorHandler.binaryOperatorError(ctx, ctx.op.getText(), leftNodeType.getName(), rightNodeType.getName());
            this.programErrors++;
        }

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

    public int getGlobalMemorySize()
    {
        return this.labelCache.size();
    }

    public int getNumberOfErrors()
    {
        return this.programErrors;
    }

}
