import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;

import Antlr.*;

public class TagRecord extends TasmBaseListener
{
    private final HashMap<String, Integer> tagCache;
    private int lineCounter;
    private int programErrorCounter;

    public TagRecord()
    {
        this.tagCache = new HashMap<>();
        this.lineCounter = 0;
        this.programErrorCounter = 0;
    }

    @Override
    public void exitTagInstruction(TasmParser.TagInstructionContext ctx)
    {
        for(TerminalNode tag : ctx.TAG())
        {
            if(this.tagCache.containsKey(tag.getText()))
            {
                ErrorHandler.redefinedTag(ctx, tag.getText());
                this.programErrorCounter++;
            }

            this.tagCache.put(tag.getText(), this.lineCounter - 1);
        }
    }

    @Override
    public void exitInstruction(TasmParser.InstructionContext ctx)
    {
        this.lineCounter++;
    }

    public int getProgramErrors()
    {
        return this.programErrorCounter;
    }

    public HashMap<String, Integer> getTags(ParseTree tree) throws IOException
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        return this.tagCache;
    }


}
