import java.util.Arrays;

public class Instruction {

    private final OpCode instruction;

    public Instruction(OpCode instruction){
        this.instruction = instruction;
    }


    public OpCode getInstruction(){
        return this.instruction;
    }

    @Override
    public String toString() {
        return this.instruction.name();
    }
}