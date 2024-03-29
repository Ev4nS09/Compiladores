import java.util.Arrays;

public class Instruction {

    private final OpCode instruction;
    private final Object argument;
    private final String[] labels;

    public Instruction(OpCode instruction, Object argument, String[] labels)
    {
        this.instruction = instruction;
        this.argument = argument;
        this.labels = labels;
    }

    public Instruction(OpCode instruction, Object argument)
    {
        this(instruction, argument, null);
    }

    public Instruction(OpCode instruction)
    {
        this(instruction, null, null);
    }


    public OpCode getInstruction()
    {
        return this.instruction;
    }

    public Object getArgument()
    {
        return this.argument;
    }

    public boolean hasArgument()
    {
        return this.argument != null;
    }

    public boolean hasLabels()
    {
        return this.labels != null && this.labels.length > 0;
    }

    @Override
    public String toString() {
        if(!hasArgument())
            return this.instruction.name();

        return this.instruction.name() + " " + this.argument.toString();
    }
}