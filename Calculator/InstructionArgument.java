public class InstructionArgument extends Instruction{

    private final Object argument;

    public InstructionArgument(OpCode instruction, Object argument)
    {
        super(instruction);
        this.argument = argument;
    }

    public Object getInstructionArgument()
    {
        return this.argument;
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + this.argument;
    }
}
