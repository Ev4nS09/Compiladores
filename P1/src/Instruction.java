import java.util.Arrays;

public class Instruction {

    private final OpCode instruction;
    private final int[] instructionArguments;
    private final int numberOfArguments;

    public Instruction(OpCode instruction, int[] instructionArguments){
        this.instruction = instruction;
        this.instructionArguments = instructionArguments;
        this.numberOfArguments = instructionArguments.length;
    }

    public Instruction(OpCode instruction){
        this(instruction, new int[0]);
    }

    public OpCode getInstruction(){
        return this.instruction;
    }

    public int[] getInstructionArguments(){
        return this.instructionArguments;
    }

    public int getNumberOfArguments(){
        return this.numberOfArguments;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.instruction.toString());

        for (int instructionArgument : this.instructionArguments)
            result.append(STR." \{instructionArgument}");

        return result.toString();
    }
}
