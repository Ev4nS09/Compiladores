import java.util.LinkedList;
import Antlr.*;
public class InstructionTree extends TasmBaseListener{

    private final Link:w
    edList<Instruction> instructions;

    public InstructionTree(){
        this.instructions = new LinkedList<>();
    }

    public void exitPrint(TasmParser.PrintContext ctx){
        if(ctx.print.getText().equals("iprint"))
            instructions.add(new Instruction(OpCode.iprint));
    }

    public LinkedList<Instruction> getInstructions(){
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
