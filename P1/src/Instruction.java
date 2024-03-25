import java.util.Arrays;

public class Instruction {

    private final OpCode instruction;
    private final int[] instructionArguments;

    public Instruction(OpCode instruction, int...instructionArguments){
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

    private String integerToBytes(int integer)
    {
        return new StringBuilder()
                .append(String.format("%02X", (integer >> 24) & 0xFF)).append(" ")
                .append(String.format("%02X", (integer >> 16) & 0xFF)).append(" ")
                .append(String.format("%02X", (integer >> 8) & 0xFF)).append(" ")
                .append(String.format("%02X", (integer) & 0xFF)).toString();
    }

    public String toStringBytes(){
        StringBuilder result = new StringBuilder(String.format("%02X", this.instruction.ordinal()));
        for(int instructionArgument : this.instructionArguments)
            result.append(" ").append(integerToBytes(instructionArgument));

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
