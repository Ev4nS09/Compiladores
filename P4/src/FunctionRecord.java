import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.*;

import Antlr.*;
import org.antlr.v4.tool.Rule;

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

    @Override
    public void exitIf(SolParser.IfContext ctx)
    {
        boolean ifHasValidReturn = ctx.line(0).return_() != null || this.isReturnValidCache.get(ctx.line(0).scope());
        boolean elseHasValidReturn = ctx.line(1) != null && (ctx.line(1).return_() != null || this.isReturnValidCache.get(ctx.line(1).scope()));

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
            if(line.return_() != null || (line.scope() != null && this.isReturnValidCache.get(line.scope())))
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

        if(ctx.if_() != null)
            result = this.isReturnValidCache.get(ctx.if_());
        else if(ctx.block() != null)
            result = this.isReturnValidCache.get(ctx.block());
        else if(ctx.loop() != null)
            result = this.isReturnValidCache.get(ctx.loop());

        this.isReturnValidCache.put(ctx, result);
    }

    @Override
    public void exitFunction(SolParser.FunctionContext ctx)
    {
        boolean isValidReturn = false;

        for(SolParser.LineContext line : ctx.block().line())
        {
            if(line.return_() != null || (line.scope() != null && this.isReturnValidCache.get(line.scope())))
            {
                isValidReturn = true;
                break;
            }
        }

        if(!isValidReturn && !ctx.rtype.getText().equals("void"))
            this.errorLog.throwError(ctx, "Invalid returns");

        if(this.functionCache.containsKey(ctx.fname.getText()))
            this.errorLog.throwError(ctx, "Function '" + ctx.fname.getText() + "' already exists");

        if(ctx.fname.getText().equals("main") && stringToClass(ctx.rtype.getText()) != void.class)
            this.errorLog.throwError(ctx, "Invalid return type for main.");

        if(ctx.fname.getText().equals("main") && ctx.LABEL().size() > 1)
            this.errorLog.throwError(ctx, "Invalid number of arguments for main, main has no arguments.");


        this.functionCache.put(ctx.fname.getText(),
                new Function(ctx.fname.getText(), ctx.LABEL().size() - 1, stringToClass(ctx.rtype.getText())));
    }

    public HashMap<String, Function> getFunctions(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        Function mainFunction = new Function("main", 0, void.class);

        if(!this.functionCache.containsValue(mainFunction))
            this.errorLog.throwError(0, "", "Missing main() function.");

        return this.functionCache;
    }

    public ErrorLog getErrorLog()
    {
        return this.errorLog;
    }
}
