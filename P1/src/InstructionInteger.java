public class InstructionInteger<T> extends Instruction{

    private final T[] instructionArguments;

    public InstructionInteger(OpCode instruction, T[] instructionArguments){
        super(instruction);
        this.instructionArguments = instructionArguments;
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder(super.toString());

        for(T argument : this.instructionArguments)
            stringBuilder.append(" ").append(argument.toString());

        return stringBuilder.toString();
    }
}
