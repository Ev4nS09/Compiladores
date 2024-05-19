package solUtils;

import Antlr.SolParser;
import SemanticChecker.ScopeTree;
import SemanticChecker.TypeRecord;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;

public record Function(String name, int numberOfArgs, Class<?> returnType, ArrayList<Class<?>> argumentTypes)
{
    public static SolParser.FunctionContext getFunctionContext(RuleContext ctx)
    {
        while(ctx != null && !(ctx instanceof SolParser.FunctionContext))
            ctx = ctx.parent;

        return ctx != null ? (SolParser.FunctionContext) ctx : null;
    }

    private static ArrayList<Class<?>> getFunctionTypes(SolParser.FunctionContext ctx)
    {
        ArrayList<Class<?>> result = new ArrayList<>();

        for(int i = ctx.rtype.getText().equals("void") ? 0 : 1; i < ctx.TYPE().size(); i++)
            result.add(TypeRecord.stringToClass(ctx.TYPE(i).getText()));

        return result;
    }

    public static Function getCurrentFunction(RuleContext ctx)
    {
        SolParser.FunctionContext functionCtx = getFunctionContext(ctx);

        if(ctx == null)
            return null;
        else
            return new Function(functionCtx.fname.getText(),
                    functionCtx.LABEL().size() - 1,
                    TypeRecord.stringToClass(functionCtx.rtype.getText()),
                    getFunctionTypes(functionCtx));
    }

    public static Function getFunction(RuleContext ctx, String functionName)
    {
        while(!(ctx instanceof SolParser.SolContext solContext))
            ctx = ctx.parent;

        Function result = null;

        for(SolParser.FunctionContext function : solContext.function())
            if(function.fname.getText().equals(functionName))
                result = getCurrentFunction(function);

        return result;
    }
}
