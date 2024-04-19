import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import Antlr.*;

public class TypeRecord extends SolBaseListener
{
    ParseTreeProperty<Class<?>> types;

    public TypeRecord()
    {
        this.types = new ParseTreeProperty<>();
    }

    private boolean isString(Class<?> type)
    {
        return type == String.class;
    }

    private boolean isBoolean(Class<?> type)
    {
        return type == boolean.class;
    }

    private boolean isDouble(Class<?> type)
    {
        return type == double.class;
    }

    private boolean isInt(Class<?> type)
    {
        return type == int.class;
    }

    private boolean isNumber(Class<?> type)
    {
        return isInt(type) || isDouble(type);
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
        this.types.put(ctx, this.types.get(ctx.expression()));
    }



    @Override
    public void exitMultDivMod(SolParser.MultDivModContext ctx)
    {
        Class<?> result = null;
        Class<?> leftNodeType = this.types.get(ctx.expression(0));
        Class<?> rightNodeType = this.types.get(ctx.expression(1));

        if(!isNumber(leftNodeType) || !isNumber(rightNodeType))
        {
            Flaw.Error("");
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

        boolean isLeftAndRightNotString = !isString(leftNodeType) && !isString(rightNodeType);
        boolean isLeftOrRightBoolean = isBoolean(leftNodeType) || isBoolean(rightNodeType);

        if(isLeftAndRightNotString && isLeftOrRightBoolean)
        {
            Flaw.Error("");
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
        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitIguality(SolParser.IgualityContext ctx)
    {
        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitAnd(SolParser.AndContext ctx)
    {
        this.types.put(ctx, boolean.class);
    }

    @Override
    public void exitOr(SolParser.OrContext ctx)
    {
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
