import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import Antlr.*;

public class InstructionTree extends TasmBaseListener
{

    protected final LinkedList<Instruction> instructions;
    protected final LinkedList<Instruction> constantPool;

    protected final HashMap<String, Integer> tagLine;
    protected final HashMap<String, Integer> waitList;
    protected final HashMap<Object, Integer> constantPoolCache;

    public InstructionTree()
    {
        this.instructions = new LinkedList<>();
        this.constantPool = new LinkedList<>();
        this.tagLine = new HashMap<>();
        this.waitList = new HashMap<>();
        this.constantPoolCache = new HashMap<>();
    }

    public void exitIconst(TasmParser.IconstContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.iconst, Integer.parseInt(ctx.INT().getText())));
    }

    public void exitDconst(TasmParser.DconstContext ctx)
    {
        Double number = Double.valueOf(ctx.DOUBLE().getText());

        if(!this.constantPoolCache.containsKey(number))
        {
            this.constantPool.add(new Instruction(OpCode.dconst, number));
            this.constantPoolCache.put(number, this.constantPool.size()-1);
        }

        this.instructions.add(new Instruction(OpCode.sconst, this.constantPoolCache.get(number)));
    }

    public void exitSconst(TasmParser.SconstContext ctx)
    {

        String string = ctx.STRING().getText();

        if(!this.constantPoolCache.containsKey(string))
        {
            this.constantPool.add(new Instruction(OpCode.sconst, string));
            this.constantPoolCache.put(string, this.constantPool.size()-1);
        }

        this.instructions.add(new Instruction(OpCode.sconst, this.constantPoolCache.get(string)));
    }

    public void exitTconst(TasmParser.TconstContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.tconst));
    }

    public void exitFconst(TasmParser.FconstContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.tconst));
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

    public void exitTagInstruction(TasmParser.TagInstructionContext ctx)
    {
        for(int i = 0; i < ctx.TAG().size(); i++)
        {
            String tag = ctx.TAG().get(i).toString();

            if (this.tagLine.containsKey(tag))
            {
                System.out.println("Label already defined");
                System.exit(1);
            }

            if (this.waitList.containsKey(tag))
            {
                int index = this.waitList.get(tag);
                Instruction newInstruction = new Instruction(this.instructions.get(index).getInstruction(), this.instructions.size() - 1);
                this.instructions.set(index, newInstruction);
            }

            this.tagLine.put(tag, this.instructions.size() - 1);
        }
    }

    public void exitTasm(TasmParser.TasmContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.halt));
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
