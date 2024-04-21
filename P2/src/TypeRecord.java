import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import Antlr.*;

public class TypeRecord extends SolBaseListener
{
    ParseTreeProperty<Class<?>> types;
    int programErrors;

    public TypeRecord()
    {
        this.types = new ParseTreeProperty<>();
        this.programErrors = 0;
    }

    @Override
    public void exitInstruction(SolParser.InstructionContext ctx)
    {
        this.types.put(ctx, this.types.get(ctx.expression()));
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

    public int getNumberOfErrors()
    {
        return this.programErrors;
    }

}
