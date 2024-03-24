import java.util.Arrays;

public class Instruction {

    private final OpCode instruction;
    private final int[] instructionArguments;

    public Instruction(OpCode instruction, int[] instructionArguments){
        this.instruction = instruction;
        this.instructionArguments = instructionArguments;
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

    private String integerToByte(int integer){
        return STR."\{(integer >> 24) & 0xFF} \{(integer >> 16) & 0xFF} \{(integer >> 8) & 0xFF} \{integer & 0xFF} ";
    }

    public String toStringBytes(){
        StringBuilder result = new StringBuilder(String.valueOf(this.instruction.ordinal()));
        for(int instructionArgument : this.instructionArguments)
            result.append(" ").append(integerToByte(instructionArgument));

        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.instruction.toString());

        for (int instructionArgument : this.instructionArguments)
            result.append(STR." \{instructionArgument}");

        return result.toString();
    }
}
