import java.util.LinkedList;
import antlr.*;
public class InstructionTree extends LExprBaseListener{

    private final LinkedList<Instruction<Integer>> instructions;

    public InstructionTree(){
        this.instructions = new LinkedList<>();
    }

    public void exitPow(LExprParser.PowContext ctx) {
        this.instructions.add(new Instruction(OpCode.ipow));
    }

    public void exitMultDiv(LExprParser.MultDivContext ctx) {
        if(ctx.op.getText().equals("*"))
            this.instructions.add(new Instruction(OpCode.imult));
        else
            this.instructions.add(new Instruction(OpCode.idiv));

    }

    public void exitAddSub(LExprParser.AddSubContext ctx) {
        if(ctx.op.getText().equals("+"))
            instructions.add(new Instruction(OpCode.iadd));
        else
            instructions.add(new Instruction(OpCode.isub));
    }

    public void exitNegation(LExprParser.NegationContext ctx) {
        instructions.add(new Instruction(OpCode.iuminus));
    }

    public void exitDouble(LExprParser.DoubleContext ctx) {
        instructions.add(new Instruction(OpCode.iconst, new int[]{Integer.parseInt(ctx.getText())}));
    }

    public void exitInstruction(LExprParser.InstructionContext ctx){
        if(ctx.ins.getText().equals("print"))
            instructions.add(new Instruction(OpCode.iprint));
    }

    public LinkedList<Instruction<Integer>> getInstructions(){
        return this.instructions;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < this.instructions.size(); i++)
            result.append(i).append(": ").append(this.instructions.get(i).toString()).append('\n');

        return result.toString();
    }
}
