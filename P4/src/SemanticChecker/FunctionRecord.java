package SemanticChecker;

import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.*;

import Antlr.*;

import java.lang.foreign.MemorySegment;
import java.util.ArrayList;
import java.util.HashMap;
import solUtils.*;

public class FunctionRecord extends SolBaseListener
{
    private final HashMap<String, Function> functionCache;
    private final ParseTreeProperty<Boolean> hasReturnCache;
    private final ErrorLog errorLog;

    public FunctionRecord(ErrorLog errorLog)
    {
        this.functionCache = new HashMap<>();
        this.hasReturnCache = new ParseTreeProperty<>();
        this.errorLog = errorLog;

        this.hasReturnCache.put(null, false);
    }

    public FunctionRecord()
    {
        this(new ErrorLog());
    }

    @Override
    public void exitIf(SolParser.IfContext ctx)
    {
        boolean ifConditionHasElse = ctx.instruction(1) != null;

        boolean ifHasReturn = ctx.instruction(0).return_() != null || this.hasReturnCache.get(ctx.instruction(0).scope());
        boolean elseHasReturn = ifConditionHasElse && (ctx.instruction(1).return_() != null || this.hasReturnCache.get(ctx.instruction(1).scope()));

        boolean result = ifHasReturn && elseHasReturn;

        this.hasReturnCache.put(ctx, result);
    }

    @Override
    public void exitFor(SolParser.ForContext ctx)
    {
        this.hasReturnCache.put(ctx, false);
    }

    @Override
    public void exitWhile(SolParser.WhileContext ctx)
    {
        this.hasReturnCache.put(ctx, false);
    }

    @Override
    public void exitBlock(SolParser.BlockContext ctx)
    {
        boolean result = false;

        for(SolParser.InstructionContext instruction : ctx.instruction())
        {
            boolean scopeHasReturn = this.hasReturnCache.get(instruction.scope());
            boolean ifHasReturn = this.hasReturnCache.get(instruction.if_());

            if(instruction.return_() != null || scopeHasReturn || ifHasReturn)
            {
                result = true;
                break;
            }
        }

        this.hasReturnCache.put(ctx, result);
    }

    @Override
    public void exitScope(SolParser.ScopeContext ctx)
    {
        this.hasReturnCache.put(ctx, this.hasReturnCache.get(ctx.block()));
    }


    @Override
    public void exitFunction(SolParser.FunctionContext ctx)
    {
        boolean hasValidReturn = this.hasReturnCache.get(ctx.scope());

        if(this.functionCache.containsKey(ctx.fname.getText()))
            this.errorLog.throwError(ctx, "Function '" + ctx.fname.getText() + "' already exists");
        if(!hasValidReturn && !ctx.rtype.getText().equals("void"))
            this.errorLog.throwError(ctx, "Function '" + ctx.fname.getText()  + "' may not return");
        if(ctx.fname.getText().equals("main") && TypeRecord.stringToClass(ctx.rtype.getText()) != void.class)
            this.errorLog.throwError(ctx, "Invalid return type for main.");
        if(ctx.fname.getText().equals("main") && ctx.LABEL().size() > 1)
            this.errorLog.throwError(ctx, "Invalid number of arguments for main, main has no arguments");

        this.functionCache.put(ctx.fname.getText(), Function.getCurrentFunction(ctx));
    }

    public HashMap<String, Function> getFunctions(ParseTree tree)
    {

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        Function mainFunction = new Function("main", 0, void.class, new ArrayList<>());

        if(!this.functionCache.containsKey("main"))
            this.errorLog.throwError(0, "", "Missing main() function");

        return this.functionCache;
    }

}
