public class InstructionArgument<T> extends Instruction{

    private final T argument;

    public InstructionArgument(OpCode instruction, T argument)
    {
        super(instruction);
        this.argument = argument;
    }

    public T getInstructionArguments()
    {
        return this.argument;
    }

    @Override
    public String toString(){
        return super.toString() + this.argument;
    }
}
