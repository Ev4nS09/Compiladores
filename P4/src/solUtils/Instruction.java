package solUtils;

import ErrorHandler.ErrorLog;

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

    public void addArgument(Value argument)
    {
        if(hasArgument())
            ErrorLog.fatalError("Utils.solUtils.Instruction already as an argument.");
        this.argument = argument;
    }

    public boolean equals(Object that)
    {
        if(this == that)
            return true;
        if(that == null)
            return false;
        if(this.getClass() != that.getClass())
            return false;

        Instruction thatInstruction = (Instruction) that;

        if(this.getInstruction() != thatInstruction.getInstruction())
            return false;


        if(this.hasArgument() && thatInstruction.hasArgument())
            return this.argument == thatInstruction.getArgument();
        else
            return true;

    }

    @Override
    public String toString()
    {
        if(!hasArgument())
            return this.instruction.name();

        return this.instruction.name() + " " + this.argument.toString();
    }
}