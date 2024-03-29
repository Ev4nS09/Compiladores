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
        this.instructions.add(new Instruction(OpCode.valueOf(ctx.jp.getText()), ctx.TAG().getText()));
    }

    public void exitLine(TasmParser.LineContext ctx)
    {
        if(ctx.HALT() != null)
            this.instructions.add(new Instruction(OpCode.halt));
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
