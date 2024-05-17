import ErrorHandler.ErrorLog;

import java.io.*;
import java.util.*;

public class tVM
{
    private static final Value NIL = new Value("NIL");

    private final Stack<Value> stack;
    private final Stack<Integer> localMemorySizeStack;
    private Value[] globalMemory;
    private ByteCodeBuffer byteCodeBuffer;
    private final LinkedList<Instruction> instructions;
    private final LinkedList<Instruction> constPool;

    private final boolean trace;

    private int instructionPointer;
    private int framePointer;

    public tVM(boolean trace)
    {
        this.byteCodeBuffer = null;
        this.stack = new Stack<>();
        this.localMemorySizeStack = new Stack<>();
        this.trace = trace;
        this.constPool = new LinkedList<>();

        this.globalMemory = new Value[0];

        this.instructions = new LinkedList<>();
        this.instructionPointer = 0;
        this.framePointer = 0;

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
            System.out.println("                " +  "FramePointer: " + this.framePointer);
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

            case lalloc -> lalloc(argument);

            case lload -> lload(argument);

            case lstore -> lstore(argument);

            case call -> call(argument);

            case ret -> ret(argument);

            case retval -> retval(argument);

            case pop -> pop(argument);

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

            case halt -> halt();
        }

    }

    public void execute(String byteCodeFile) throws Exception
    {
        this.byteCodeBuffer = new ByteCodeBuffer(byteCodeFile);
        this.generateInstructions();

        if(!this.instructions.contains(new Instruction(OpCode.halt)))
            ErrorLog.fatalError("Code doesn't halt.");

        while(this.instructionPointer < this.instructions.size())
        {
            Instruction instruction = this.instructions.get(this.instructionPointer);

            if(instruction.getInstruction() == OpCode.halt)
                break;

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
            newGlobal[i] = NIL;

        this.globalMemory = newGlobal;
    }

    private void gload(Integer address)
    {
        if(address >= this.globalMemory.length)
           ErrorLog.fatalError("Index out of bounds.");

        this.stack.push(this.globalMemory[address]);
    }

    private void gstore(Integer address)
    {
        if(address >= this.globalMemory.length)
            ErrorLog.fatalError("Index out of bounds.");

        this.globalMemory[address] = this.stack.pop();
    }

    private void lalloc(Integer size)
    {
        while(size-- > 0)
            this.stack.push(NIL);
    }

    private void lload(Integer address)
    {
        int position = this.framePointer + address;

        if(position < 0 || position > this.stack.size())
            ErrorLog.fatalError("Accessing memory out of bounds");

        this.stack.push(this.stack.get(this.framePointer + address));
    }

    private void lstore(Integer address)
    {
        this.stack.set(this.framePointer + address, this.stack.pop());
    }

    private void call(Integer line)
    {
        this.stack.push(new Value(this.framePointer));
        this.framePointer = this.stack.size() - 1;

        this.stack.push(new Value(this.instructionPointer));
        this.instructionPointer = line;
    }


    private void ret(Integer numberOfArgs)
    {
        while(stack.size() > this.framePointer + 2)
            this.stack.pop();

        this.instructionPointer = this.stack.pop().getInteger();
        this.framePointer = this.stack.pop().getInteger();

        this.pop(numberOfArgs);
    }

    private void retval(Integer numberOfArgs)
    {
        Value returnedValue = this.stack.pop();

        while(stack.size() > this.framePointer + 1)
            this.stack.pop();

        this.instructionPointer = this.stack.pop().getInteger();
        this.framePointer = this.stack.pop().getInteger();

        this.pop(numberOfArgs);
        this.stack.push(returnedValue);
    }

    private void pop(Integer size)
    {
        while(size-- > 0)
            this.stack.pop();
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
            ErrorLog.fatalError("Divisor mustn't be 0");

        this.stack.push(new Value(left / right));
    }

    private void imod()
    {
        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if(right == 0)
            ErrorLog.fatalError("Divisor mustn't be 0");

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
        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        stack.push(new Value(left + right));
    }

    private void dsub()
    {
        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        stack.push(new Value(left - right));
    }

    private void dmult()
    {
        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        stack.push(new Value(left * right));
    }

    private void ddiv()
    {
        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        if(right == 0)
            ErrorLog.fatalError("Divisor mustn't be 0");

        this.stack.push(new Value(left / right));
    }

    private void deq()
    {
        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        if (left == right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
        ;
    }

    private void dneq()
    {
        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        if (left != right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void dlt()
    {
        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        if (left < right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void dleq()
    {
        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

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

    private void halt()
    {
        System.exit(0);
    }

    private boolean opCodeHasArgument(OpCode instruction)
    {
        return instruction == OpCode.iconst || instruction == OpCode.dconst || instruction == OpCode.sconst ||
                instruction == OpCode.galloc || instruction == OpCode.gload || instruction == OpCode.gstore ||
                instruction == OpCode.lalloc || instruction == OpCode.lload || instruction == OpCode.lstore ||
                instruction == OpCode.call || instruction == OpCode.retval || instruction == OpCode.ret ||
                instruction == OpCode.jump || instruction == OpCode.jumpf || instruction == OpCode.jumpt ||
                instruction == OpCode.pop
                ;
    }


    private void generateInstructions() throws IOException
    {
        //Generates the constant pool from the bytecode file
        int constantPoolSize = this.byteCodeBuffer.getInt();
        while (constantPoolSize-- > 0)
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

        //Generates the instructions from the bytecode file
        while(this.byteCodeBuffer.isAvailable())
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

    private static String readInput()
    {
        String result = "";
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine())
        {
            result += scanner.nextLine() + '\n';
        }

        return result;
    }

    public static void main(String[] args) throws Exception
    {

        if(args.length > 2)
        {
            ErrorLog.fatalError("Too many Program arguments. tVM [OPTION] [FILE]");
        }

        String inputFile = null;
        boolean trace = false;

        for (String arg : args)
        {

            if (arg.equals("-trace") || arg.equals("-t"))
                trace = true;
            else
                inputFile = arg;
        }

        if (inputFile == null)
        {
            inputFile = "inputs/input.tbc";
            File file = new File(inputFile);
            FileWriter writer = new FileWriter(inputFile);
            writer.write(readInput());
            writer.close();
        }

        if(!new File(inputFile).exists())
        {
            ErrorLog.fatalError("File " + inputFile + " does not exist." );
        }

        if (!inputFile.split("\\.")[1].equals("tbc"))
        {
            ErrorLog.fatalError("Invalid file extension, File must have the extension tbc.");
        }

        tVM virtualMachine = new tVM(trace);
        virtualMachine.execute(inputFile);
    }
}
