import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.*;

import Antlr.*;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionRecord extends SolBaseListener
{
    private final HashMap<String, Function> functionCache;
    private final ParseTreeProperty<Boolean> isReturnValidCache;
    private final ErrorLog errorLog;

    public FunctionRecord(ErrorLog errorLog)
    {
        this.functionCache = new HashMap<>();
        this.isReturnValidCache = new ParseTreeProperty<>();
        this.errorLog = errorLog;
    }

    public FunctionRecord()
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
            case "void" -> void.class;
            default -> null;
        };
    }

    private SolParser.ScopeContext getScope(RuleContext ctx)
    {
        while(ctx != null && !(ctx instanceof SolParser.ScopeContext))
            ctx = ctx.parent;

        return ctx != null ? (SolParser.ScopeContext) ctx : null;
    }

    @Override
    public void exitIf(SolParser.IfContext ctx)
    {
        boolean ifHasValidReturn = ctx.line(0).return_() != null || (ctx.line(0).scope() != null && this.isReturnValidCache.get(ctx.line(0).scope()));
        boolean elseHasValidReturn = ctx.line(1) != null && (ctx.line(1).return_() != null || (ctx.line(0).scope() != null && this.isReturnValidCache.get(ctx.line(1).scope())));

        boolean result = ifHasValidReturn && elseHasValidReturn;



        this.isReturnValidCache.put(ctx, result);
    }

    @Override
    public void exitFor(SolParser.ForContext ctx)
    {
        this.isReturnValidCache.put(ctx, false);
    }

    @Override
    public void exitWhile(SolParser.WhileContext ctx)
    {
        this.isReturnValidCache.put(ctx, false);
    }

    @Override
    public void exitBlock(SolParser.BlockContext ctx)
    {
        boolean result = false;

        for(SolParser.LineContext line : ctx.line())
        {
            if(line == null)
                continue;

            boolean scopeIsValid = (line.scope() != null && this.isReturnValidCache.get(line.scope()));
            boolean ifIsValid = (line.if_() != null && this.isReturnValidCache.get(line.if_()));

            if(line.return_() != null || scopeIsValid || ifIsValid)
            {
                result = true;
                break;
            }
        }

        this.isReturnValidCache.put(ctx, result);
    }

    @Override
    public void exitScope(SolParser.ScopeContext ctx)
    {
        boolean result = false;

        if(ctx.block() != null)
            result = this.isReturnValidCache.get(ctx.block());

        this.isReturnValidCache.put(ctx, result);
    }


    @Override
    public void exitFunction(SolParser.FunctionContext ctx)
    {
        boolean isValidReturn = false;

        for(SolParser.LineContext line : ctx.scope().block().line())
        {
            if(this.isReturnValidCache.get(ctx.scope()) != null && this.isReturnValidCache.get(ctx.scope()))
            {
                isValidReturn = true;
                break;
            }

            if(line == null)
                continue;

            if(line.return_() != null || (line.scope() != null && this.isReturnValidCache.get(line.scope())))
            {
                isValidReturn = true;
                break;
            }
        }

        if(!isValidReturn && !ctx.rtype.getText().equals("void"))
            this.errorLog.throwError(ctx, "Invalid returns");

        if(ctx.fname.getText().equals("main") && stringToClass(ctx.rtype.getText()) != void.class)
            this.errorLog.throwError(ctx, "Invalid return type for main.");

        if(ctx.fname.getText().equals("main") && ctx.LABEL().size() > 1)
            this.errorLog.throwError(ctx, "Invalid number of arguments for main, main has no arguments.");


        ArrayList<Class<?>> functionTypes = new ArrayList<>();

        for(int i = 1; i < ctx.TYPE().size(); i++)
            functionTypes.add(stringToClass(ctx.TYPE(i).getText()));

        this.functionCache.put(ctx.fname.getText(),
                new Function(ctx.fname.getText(), ctx.LABEL().size() - 1, stringToClass(ctx.rtype.getText())
                , functionTypes));


    }

    public HashMap<String, Function> getFunctions(ParseTree tree)
    {

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        Function mainFunction = new Function("main", 0, void.class, new ArrayList<>());

        if(!this.functionCache.containsKey("main"))
            this.errorLog.throwError(0, "", "Missing main() function.");

        return this.functionCache;
    }

}
