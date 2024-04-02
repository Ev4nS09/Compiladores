import java.util.HashMap;
import java.util.LinkedList;
import Antlr.*;

public class InstructionTree extends TasmBaseListener
{

    protected final LinkedList<Instruction> instructions;
    protected final LinkedList<Instruction> constantPool;

    protected final HashMap<String, Integer> tagLine;
    protected final HashMap<String, Integer> waitList;

    public InstructionTree()
    {
        this.instructions = new LinkedList<>();
        this.constantPool = new LinkedList<>();
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
            this.constantPool.add(new Instruction(OpCode.dconst, Double.parseDouble(number)));
            this.instructions.add(new Instruction(OpCode.dconst, this.constantPool.size() - 1));
        }
        else if(ctx.SCONST() != null)
        {
            this.constantPool.add(new Instruction(OpCode.sconst, ctx.STRING().getText()));
            this.instructions.add(new Instruction(OpCode.sconst, this.constantPool.size() - 1));
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

                if(this.tagLine.containsKey(tag))
                {
                    System.out.println("Label already defined");
                    System.exit(1);
                }

                if(this.waitList.containsKey(tag))
                {
                    int index = this.waitList.get(tag);
                    Instruction newInstruction = new Instruction(this.instructions.get(index).getInstruction(), this.instructions.size() - 1);
                    this.instructions.set(index, newInstruction);
                }

                this.tagLine.put(tag, this.instructions.size() - 1);
            }
        }
    }

    public LinkedList<Instruction> getInstructions()
    {
        return this.instructions;
    }

    public LinkedList<Instruction> getConstantPool()
    {
        return this.constantPool;
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
