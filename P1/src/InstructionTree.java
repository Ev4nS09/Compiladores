import java.util.HashMap;
import java.util.LinkedList;
import Antlr.*;
public class InstructionTree extends TasmBaseListener
{

    protected final HashMap<String, Integer> tagLine;
    protected final LinkedList<Instruction> instructions;

    protected final HashMap<String, Integer> waitList;

    public InstructionTree()
    {
        this.instructions = new LinkedList<>();
        this.tagLine = new HashMap<>();
        this.waitList = new HashMap<>();
    }

    public void exitConst(TasmParser.ConstContext ctx)
    {
        if(ctx.ICONST() != null)
        {
            this.instructions.add(new Instruction(OpCode.iconst, Integer.parseInt(ctx.INT().getText())));
        }
        else if(ctx.DCONST() != null)
        {
            String number = ctx.INT() != null ? ctx.INT().getText() : ctx.DOUBLE().getText();
            this.instructions.add(new Instruction(OpCode.dconst, Double.parseDouble(number)));
        }
        else if(ctx.SCONST() != null)
        {
            this.instructions.add(new Instruction(OpCode.sconst, ctx.STRING().getText()));
        }
        else if(ctx.TCONST() != null)
        {
            this.instructions.add(new Instruction(OpCode.tconst));
        }
        else if(ctx.FCONST() != null)
        {
            this.instructions.add(new Instruction(OpCode.fconst));
        }
    }

    public void exitGlobal(TasmParser.GlobalContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.valueOf(ctx.alloc.getText()), Integer.parseInt(ctx.INT().getText())));
    }

    public void exitConditions(TasmParser.ConditionsContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.valueOf(ctx.cd.getText())));
    }

    public void exitChange(TasmParser.ChangeContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.valueOf(ctx.change.getText())));
    }

    public void exitOperations(TasmParser.OperationsContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.valueOf(ctx.op.getText())));
    }

    public void exitPrint(TasmParser.PrintContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.valueOf(ctx.print.getText())));
    }

    public void exitJp(TasmParser.JpContext ctx)
    {
        Integer line = this.tagLine.get(ctx.tag.getText());

        if(line == null)
            this.waitList.put(ctx.TAG().getText(), this.instructions.size());

        this.instructions.add(new Instruction(OpCode.valueOf(ctx.jp.getText()), line));
    }

    public void exitLine(TasmParser.LineContext ctx)
    {
        if(ctx.HALT() != null)
            this.instructions.add(new Instruction(OpCode.halt));

        //💀
        if(ctx.TAG() != null)
        {
            for(int i = 0; i < ctx.TAG().size(); i++)
            {
                String tag = ctx.TAG().get(i).toString();
                this.tagLine.put(tag, this.instructions.size() - 1);

                if(this.waitList.containsKey(tag))
                {
                    int index = this.waitList.get(tag);
                    Instruction newInstruction = new Instruction(this.instructions.get(index).getInstruction(), this.instructions.size() - 1);
                    this.instructions.set(index, newInstruction);
                }
            }
        }
    }

    public LinkedList<Instruction> getInstructions()
    {
        return this.instructions;
    }


    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < this.instructions.size(); i++)
            result.append(i).append(": ").append(this.instructions.get(i).toString()).append('\n');

        return result.toString();
    }
}
