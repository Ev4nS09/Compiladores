import java.util.LinkedList;
import Antlr.*;
public class InstructionTree extends TasmBaseListener{

    protected final LinkedList<Instruction> instructions;

    public InstructionTree()
    {
        this.instructions = new LinkedList<>();
    }

    public void exitConst(TasmParser.ConstContext ctx)
    {
        System.out.println(ctx.ICONST().getText());
    }

    public void exitPrint(TasmParser.PrintContext ctx)
    {
        if(ctx.print.getText().equals("iprint"))
            instructions.add(new Instruction(OpCode.iprint));

        else if(ctx.print.getText().equals("dprint"))
            instructions.add(new Instruction(OpCode.dprint));

        else if(ctx.print.getText().equals("sprint"))
            instructions.add(new Instruction(OpCode.sprint));

        else if(ctx.print.getText().equals("bprint"))
            instructions.add(new Instruction(OpCode.bprint));
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
