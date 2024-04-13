import java.util.Arrays;

public class Instruction {

    private final OpCode instruction;
    private Value argument;

    public Instruction(OpCode instruction, Value argument)
    {
        this.instruction = instruction;
        this.argument = argument;
    }

    public Instruction(OpCode instruction)
    {
        this(instruction, null);
    }


    public OpCode getInstruction()
    {
        return this.instruction;
    }

    public Value getArgument()
    {
        return this.argument;
    }

    public boolean hasArgument()
    {
        return this.argument != null;
    }

    public void addArgument(Value argument) throws Exception
    {
        if(hasArgument())
            Flaw.Error("Instruction already as an argument.");

        this.argument = argument;
    }

    @Override
    public String toString()
    {
        if(!hasArgument())
            return this.instruction.name();

        return this.instruction.name() + " " + this.argument.toString();
    }
}