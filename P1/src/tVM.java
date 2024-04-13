import java.io.*;
import java.util.*;

public class tVM {

    private final Stack<Value> stack;
    private Value[] globalMemory;
    private ByteCodeBuffer byteCodeBuffer;
    private final LinkedList<Instruction> instructions;
    private final LinkedList<Instruction> constPool;

    private final boolean trace;

    private int instructionPointer;

    public tVM(boolean trace)
    {
        this.byteCodeBuffer = null;
        this.stack = new Stack<>();
        this.trace = trace;
        this.constPool = new LinkedList<>();

        this.globalMemory = new Value[0];

        this.instructions = new LinkedList<>();
        this.instructionPointer = 0;

    }

    public tVM()
    {
        this(false);
    }

    private void doInstruction(Instruction instruction) throws Exception
    {
        if(this.trace)
        {
            System.out.println("                " + "Globals: " + Arrays.toString(this.globalMemory));
            System.out.println("                " +  "Stack: " + this.stack);
            System.out.println(this.instructionPointer + ": " + this.instructions.get(instructionPointer));
        }

        this.instructionPointer++;

        OpCode instructionOpCode = instruction.getInstruction();
        Integer argument = instruction.hasArgument() ? instruction.getArgument().getInteger() : null;

        switch (instructionOpCode)
        {
            case iconst -> iconst(argument);

            case dconst -> dconst(argument);

            case sconst -> sconst(argument);

            case jump -> jump(argument);

            case jumpt -> jumpt(argument);

            case jumpf -> jumpf(argument);

            case galloc -> galloc(argument);

            case gload -> gload(argument);

            case gstore -> gstore(argument);

            case tconst -> tconst();

            case fconst -> fconst();

            case iprint -> iprint();

            case iuminus -> iuminus();

            case iadd -> iadd();

            case isub -> isub();

            case imult -> imult();

            case idiv -> idiv();

            case imod -> imod();

            case ieq -> ieq();

            case ineq -> ineq();

            case ilt -> ilt();

            case ileq -> ileq();

            case itod -> itod();

            case itos -> itos();

            case dprint -> dprint();

            case duminus -> duminus();

            case dadd -> dadd();

            case dsub -> dsub();

            case dmult -> dmult();

            case ddiv -> ddiv();

            case deq -> deq();

            case dneq -> dneq();

            case dlt -> dlt();

            case dleq -> dleq();

            case dtos -> dtos();

            case sprint -> sprint();

            case sadd -> sadd();

            case seq -> seq();

            case sneq -> sneq();

            case bprint -> bprint();

            case beq -> beq();

            case bneq -> bneq();

            case btos -> btos();

            case or -> or();

            case and -> and();

            case not -> not();

        }

    }

    public void execute(String byteCode) throws Exception
    {
        this.byteCodeBuffer = new ByteCodeBuffer(byteCode);
        this.generateInstructions();

        if(this.trace)
            trace();

        while(this.instructionPointer < this.instructions.size())
        {
            Instruction instruction = this.instructions.get(this.instructionPointer);
            doInstruction(instruction);
        }

        reset();
    }

    private void reset()
    {
        this.globalMemory = new Value[0];
        this.stack.clear();
        this.constPool.clear();

        this.instructions.clear();
        this.instructionPointer = 0;

    }

    private void iconst(Integer number)
    {
        this.stack.push(new Value(number));
    }

    private void dconst(Integer constantPoolPosition)
    {
        this.stack.push(this.constPool.get(constantPoolPosition).getArgument());
    }

    private void sconst(Integer constantPoolPosition)
    {
        this.stack.push(this.constPool.get(constantPoolPosition).getArgument());
    }

    private void tconst()
    {
        this.stack.push(new Value(true));
    }

    private void fconst()
    {
        this.stack.push(new Value(false));
    }

    private void jump(Integer line)
    {
        this.instructionPointer = line;
    }

    private void jumpt(Integer line)
    {

        boolean bool = this.stack.pop().getBoolean();

        if(bool)
            this.instructionPointer = line;
    }

    private void jumpf(Integer line)
    {
        boolean bool = this.stack.pop().getBoolean();

        if(!bool)
            this.instructionPointer = line;
    }

    private void galloc(Integer size)
    {
        int newGlobalSize = this.globalMemory.length + size;
        Value[] newGlobal = new Value[newGlobalSize];

        System.arraycopy(this.globalMemory, 0, newGlobal, 0, this.globalMemory.length);

        for(int i = this.globalMemory.length; i < newGlobalSize; i++)
        {
            newGlobal[i] = new Value("NIL");
        }

        this.globalMemory = newGlobal;

    }

    private void gload(Integer address)
    {
        if(address >= this.globalMemory.length)
            Flaw.Error("Index out of bounds.");

        this.stack.push(this.globalMemory[address]);
    }

    private void gstore(Integer address)
    {
        if(address >= this.globalMemory.length)
            Flaw.Error("Index out of bounds.");

        this.globalMemory[address] = this.stack.pop();
    }

    private void iprint()
    {
        System.out.println(stack.pop().getInteger());
    }

    private void iuminus()
    {
        stack.push(new Value(-this.stack.pop().getInteger()));
    }

    private void iadd()
    {
        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();
        stack.push(new Value(left + right));
    }

    private void isub()
    {
        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();
        stack.push(new Value(left - right));
    }

    private void imult()
    {
        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();
        this.stack.push(new Value(left * right));
    }

    private void idiv()
    {
        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if(right == 0)
            Flaw.Error("Divisor mustn't be 0");

        this.stack.push(new Value(left / right));
    }

    private void imod()
    {
        int right = this.stack.pop().getInteger();
        System.out.println("sda");
        int left = this.stack.pop().getInteger();

        if(right == 0)
            Flaw.Error("Divisor mustn't be 0");

        this.stack.push(new Value(left % right));
    }

    private void ieq()
    {
        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if (left == right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void ineq()
    {
        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if (left != right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void ilt()
    {
        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if (left < right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));

    }

    private void ileq()
    {
        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if (left <= right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));


    }

    private void itod()
    {
        Double real = Double.valueOf(stack.pop().getInteger());
        stack.push(new Value(real));

    }

    private void itos()
    {
        int integer = stack.pop().getInteger();
        stack.push(new Value('"' + String.valueOf(integer) + '"'));
    }

    private void dprint()
    {
        System.out.println(this.stack.pop().getDouble());
    }

    private void duminus()
    {
        stack.push(new Value(-this.stack.pop().getDouble()));
    }

    private void dadd()
    {
        double left = stack.pop().getDouble();
        double right = stack.pop().getDouble();

        stack.push(new Value(left + right));
    }

    private void dsub()
    {
        double left = stack.pop().getDouble();
        double right = stack.pop().getDouble();

        stack.push(new Value(left - right));
    }

    private void dmult()
    {
        double left = stack.pop().getDouble();
        double right = stack.pop().getDouble();

        stack.push(new Value(left * right));
    }

    private void ddiv()
    {
        double left = stack.pop().getDouble();
        double right = stack.pop().getDouble();

        if(right == 0)
            Flaw.Error("Divisor mustn't be 0");

        this.stack.push(new Value(left / right));
    }

    private void deq()
    {
        double left = stack.pop().getDouble();
        double right = stack.pop().getDouble();

        if (left == right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
        ;
    }

    private void dneq()
    {
        double left = stack.pop().getDouble();
        double right = stack.pop().getDouble();

        if (left != right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void dlt()
    {
        double left = stack.pop().getDouble();
        double right = stack.pop().getDouble();

        if (left < right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void dleq()
    {
        double left = stack.pop().getDouble();
        double right = stack.pop().getDouble();

        if (left <= right)
            this.stack.push(new Value(true));
        else
            this.stack.push(new Value(false));
    }

    private void dtos()
    {
        Double real = this.stack.pop().getDouble();
        this.stack.push(new Value('"' + String.valueOf(real) + '"'));
    }


    private void sprint()
    {
        System.out.println(this.stack.pop().getString().replaceAll("\"", ""));
    }

    private void sadd()
    {
        String right = stack.pop().getString();
        String left = stack.pop().getString();

        left = left.substring(0, left.length()-1);
        right = right.substring(1);


        this.stack.push(new Value(left.concat(right)));
    }

    private void seq()
    {
        String right = stack.pop().getString();
        String left = stack.pop().getString();

        if (left.equals(right))
            this.stack.push(new Value(true));
        else
            this.stack.push(new Value(false));
    }

    private void sneq()
    {
        String right = stack.pop().getString();
        String left = stack.pop().getString();

        if (!left.equals(right))
            this.stack.push(new Value(true));
        else
            this.stack.push(new Value(false));
    }

    private void bprint()
    {
        System.out.println(this.stack.pop().getBoolean());
    }

    private void beq()
    {
        boolean right = this.stack.pop().getBoolean();
        boolean left = this.stack.pop().getBoolean();

        if (left == right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void bneq()
    {
        boolean right = this.stack.pop().getBoolean();
        boolean left = this.stack.pop().getBoolean();

        if (left != right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void btos()
    {
        if (this.stack.pop().getBoolean())
            stack.push(new Value("\"true\""));

        else
            stack.push(new Value("\"false\""));

    }

    private void and()
    {
        boolean right = this.stack.pop().getBoolean();
        boolean left = this.stack.pop().getBoolean();

        this.stack.push(new Value(left && right));
    }

    private void or()
    {
        boolean right = this.stack.pop().getBoolean();
        boolean left = this.stack.pop().getBoolean();

        this.stack.push(new Value(left || right));
    }

    private void not()
    {
        boolean bool = this.stack.pop().getBoolean();

        this.stack.push(new Value(!bool));
    }

    private boolean opCodeHasArgument(OpCode instruction)
    {
        return instruction == OpCode.iconst || instruction == OpCode.dconst || instruction == OpCode.sconst ||
                instruction == OpCode.galloc || instruction == OpCode.gload || instruction == OpCode.gstore ||
                instruction == OpCode.jump || instruction == OpCode.jumpf || instruction == OpCode.jumpt;
    }


    private void generateInstructions() throws IOException
    {
        //Generates the constant pool
        int constantPoolSize = this.byteCodeBuffer.getInt();
        for(int i = 0; i < constantPoolSize; i++)
        {
            OpCode instruction = OpCode.values()[this.byteCodeBuffer.getByte()];

            if(instruction == OpCode.dconst)
            {
                this.constPool.add(new Instruction(OpCode.dconst, new Value(this.byteCodeBuffer.getDouble())));
            }

            else if(instruction == OpCode.sconst)
            {
                this.constPool.add(new Instruction(OpCode.sconst, new Value(this.byteCodeBuffer.getString())));
            }
        }

        //Generates the instructions
        while (this.byteCodeBuffer.isAvailable())
        {

            OpCode instruction = OpCode.values()[this.byteCodeBuffer.getByte()];

            if(this.opCodeHasArgument(instruction))
            {
                this.instructions.add(new Instruction(instruction, new Value(this.byteCodeBuffer.getInt())));
            }
            else
            {
                this.instructions.add(new Instruction(instruction));
            }
        }

        this.byteCodeBuffer.close();
    }

    private void trace() throws IOException
    {

        System.out.println("Constant Pool");

        for(int i = 0; i < constPool.size(); i++)
        {
            Instruction instruction = constPool.get(i);
            System.out.println(i + ": " + instruction.toString());
        }

        System.out.println("\nDisassembled instructions");
        for(int i = 0; i < instructions.size(); i++)
        {
            Instruction instruction = instructions.get(i);
            System.out.println(i + ": " + instruction);
        }
        System.out.println("\nTrace while running the code");
        System.out.println("Execution starts at instruction 0\n");
    }
}
