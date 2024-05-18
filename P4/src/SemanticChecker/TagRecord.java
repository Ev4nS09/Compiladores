package SemanticChecker;

import Antlr.TasmBaseListener;
import Antlr.TasmParser;
import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.util.HashMap;

import solUtils.*;
public class TagRecord extends TasmBaseListener
{
    private final HashMap<String, Integer> tagCache;
    private final ErrorLog errorLog;
    private int lineCounter;


    public TagRecord(ErrorLog errorLog)
    {
        this.tagCache = new HashMap<>();
        this.errorLog = errorLog;
        this.lineCounter = 0;
    }

    public TagRecord()
    {
        this(new ErrorLog());
    }

    @Override
    public void exitTagInstruction(TasmParser.TagInstructionContext ctx)
    {
        for(TerminalNode tag : ctx.TAG())
        {
            if(this.tagCache.containsKey(tag.getText()))
                this.errorLog.throwError(ctx, "Tag '" + tag.getText() + "' is already defined.");

            this.tagCache.put(tag.getText(), this.lineCounter - 1);
        }
    }

    @Override
    public void exitInstruction(TasmParser.InstructionContext ctx)
    {
        this.lineCounter++;
    }

    public HashMap<String, Integer> getTags(ParseTree tree) throws IOException
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        return this.tagCache;
    }


}
