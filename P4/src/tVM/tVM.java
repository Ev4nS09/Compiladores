package tVM;

import ErrorHandler.ErrorLog;

import java.io.*;
import java.util.*;

import solUtils.*;
public class tVM
{
    private static final long  MEMORY_LIMIT = 83_000_000;


    private static final String PRINT_NIL_ERROR = "Can't print a NIL value";
    private static final String CAST_NIL_ERROR = "Can't cast a NIL value";
    private static final String ADDITION_NIL_ERROR = "Can't effectuate an addition with a NIL value";
    private static final String SUBTRACTION_NIL_ERROR = "Can't effectuate a subtraction with a NIL value";
    private static final String MULTIPLICATION_NIL_ERROR = "Can't effectuate a multiplication with a NIL value";
    private static final String DIVISION_NIL_ERROR = "Can't effectuate a division with a NIL value";
    private static final String MOD_NIL_ERROR = "Can't mod a NIL value";
    private static final String UNARY_NIL_ERROR = "Can't effectuate a unary operation with a NIL value";
    private static final String COMPARE_NIL_ERROR = "Can't compare a NIL value";


    private static final Value NIL = new Value(null);

    private final Stack<Value> stack;
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

        if(this.stack.size() + this.globalMemory.length > MEMORY_LIMIT)
            ErrorLog.fatalError("Virtual machine memory limit exceeded");

        this.instructionPointer++;

        OpCode instructionOpCode = instruction.getInstruction();
        Integer argument = instruction.hasArgument() ? instruction.getArgument().getInteger() : null;

        switch (instructionOpCode)
        {
            case OpCode.iconst -> iconst(argument);

            case OpCode.dconst -> dconst(argument);

            case OpCode.sconst -> sconst(argument);

            case OpCode.jump -> jump(argument);

            case OpCode.jumpt -> jumpt(argument);

            case OpCode.jumpf -> jumpf(argument);

            case OpCode.galloc -> galloc(argument);

            case OpCode.gload -> gload(argument);

            case OpCode.gstore -> gstore(argument);

            case OpCode.lalloc -> lalloc(argument);

            case OpCode.lload -> lload(argument);

            case OpCode.lstore -> lstore(argument);

            case OpCode.call -> call(argument);

            case OpCode.ret -> ret(argument);

            case OpCode.retval -> retval(argument);

            case OpCode.pop -> pop(argument);

            case OpCode.tconst -> tconst();

            case OpCode.fconst -> fconst();

            case OpCode.iprint -> iprint();

            case OpCode.iuminus -> iuminus();

            case OpCode.iadd -> iadd();

            case OpCode.isub -> isub();

            case OpCode.imult -> imult();

            case OpCode.idiv -> idiv();

            case OpCode.imod -> imod();

            case OpCode.ieq -> ieq();

            case OpCode.ineq -> ineq();

            case OpCode.ilt -> ilt();

            case OpCode.ileq -> ileq();

            case OpCode.itod -> itod();

            case OpCode.itos -> itos();

            case OpCode.dprint -> dprint();

            case OpCode.duminus -> duminus();

            case OpCode.dadd -> dadd();

            case OpCode.dsub -> dsub();

            case OpCode.dmult -> dmult();

            case OpCode.ddiv -> ddiv();

            case OpCode.deq -> deq();

            case OpCode.dneq -> dneq();

            case OpCode.dlt -> dlt();

            case OpCode.dleq -> dleq();

            case OpCode.dtos -> dtos();

            case OpCode.sprint -> sprint();

            case OpCode.sadd -> sadd();

            case OpCode.seq -> seq();

            case OpCode.sneq -> sneq();

            case OpCode.bprint -> bprint();

            case OpCode.beq -> beq();

            case OpCode.bneq -> bneq();

            case OpCode.btos -> btos();

            case OpCode.or -> or();

            case OpCode.and -> and();

            case OpCode.not -> not();

            case OpCode.halt -> halt();
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

    private boolean isNIL(Value value)
    {
        return value.equals(NIL);
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

        while(stack.size() > this.framePointer + 2)
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
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(PRINT_NIL_ERROR);

        System.out.println(stack.pop().getInteger());
    }

    private void iuminus()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(UNARY_NIL_ERROR);

        stack.push(new Value(-this.stack.pop().getInteger()));
    }

    private void iadd()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(ADDITION_NIL_ERROR);

        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();
        stack.push(new Value(left + right));
    }

    private void isub()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(SUBTRACTION_NIL_ERROR);

        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        stack.push(new Value(left - right));
    }

    private void imult()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(MULTIPLICATION_NIL_ERROR);

        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();
        this.stack.push(new Value(left * right));
    }

    private void idiv()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(DIVISION_NIL_ERROR);

        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if(right == 0)
            ErrorLog.fatalError("Divisor mustn't be 0");

        this.stack.push(new Value(left / right));
    }

    private void imod()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(MOD_NIL_ERROR);

        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if(right == 0)
            ErrorLog.fatalError("Divisor mustn't be 0");

        this.stack.push(new Value(left % right));
    }

    private void ieq()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if (left == right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void ineq()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);


        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if (left != right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void ilt()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);


        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if (left < right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void ileq()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        int right = this.stack.pop().getInteger();
        int left = this.stack.pop().getInteger();

        if (left <= right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void itod()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(CAST_NIL_ERROR);

        Double real = Double.valueOf(stack.pop().getInteger());
        stack.push(new Value(real));
    }

    private void itos()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(CAST_NIL_ERROR);

        int integer = stack.pop().getInteger();
        stack.push(new Value('"' + String.valueOf(integer) + '"'));
    }

    private void dprint()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        System.out.println(this.stack.pop().getDouble());
    }

    private void duminus()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(UNARY_NIL_ERROR);

        stack.push(new Value(-this.stack.pop().getDouble()));
    }

    private void dadd()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(ADDITION_NIL_ERROR);

        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        stack.push(new Value(left + right));
    }

    private void dsub()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(SUBTRACTION_NIL_ERROR);

        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        stack.push(new Value(left - right));
    }

    private void dmult()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(MULTIPLICATION_NIL_ERROR);

        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        stack.push(new Value(left * right));
    }

    private void ddiv()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(DIVISION_NIL_ERROR);

        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        if(right == 0)
            ErrorLog.fatalError("Divisor mustn't be 0");

        this.stack.push(new Value(left / right));
    }

    private void deq()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

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
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        if (left != right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void dlt()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        if (left < right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void dleq()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        double right = stack.pop().getDouble();
        double left = stack.pop().getDouble();

        if (left <= right)
            this.stack.push(new Value(true));
        else
            this.stack.push(new Value(false));
    }

    private void dtos()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(CAST_NIL_ERROR);

        Double real = this.stack.pop().getDouble();
        this.stack.push(new Value('"' + String.valueOf(real) + '"'));
    }


    private void sprint()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(PRINT_NIL_ERROR);

        System.out.println(this.stack.pop().getString().replaceAll("\"", ""));
    }

    private void sadd()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(ADDITION_NIL_ERROR);

        String right = stack.pop().getString();
        String left = stack.pop().getString();

        left = left.substring(0, left.length()-1);
        right = right.substring(1);

        this.stack.push(new Value(left.concat(right)));
    }

    private void seq()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        String right = stack.pop().getString();
        String left = stack.pop().getString();

        if (left.equals(right))
            this.stack.push(new Value(true));
        else
            this.stack.push(new Value(false));
    }

    private void sneq()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        String right = stack.pop().getString();
        String left = stack.pop().getString();

        if (!left.equals(right))
            this.stack.push(new Value(true));
        else
            this.stack.push(new Value(false));
    }

    private void bprint()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(PRINT_NIL_ERROR);

        System.out.println(this.stack.pop().getBoolean());
    }

    private void beq()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        boolean right = this.stack.pop().getBoolean();
        boolean left = this.stack.pop().getBoolean();

        if (left == right)
            stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void bneq()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        boolean right = this.stack.pop().getBoolean();
        boolean left = this.stack.pop().getBoolean();

        if (left != right)
            this.stack.push(new Value(true));
        else
            stack.push(new Value(false));
    }

    private void btos()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(CAST_NIL_ERROR);


        if (this.stack.pop().getBoolean())
            this.stack.push(new Value("\"true\""));

        else
            this.stack.push(new Value("\"false\""));

    }

    private void and()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        boolean right = this.stack.pop().getBoolean();
        boolean left = this.stack.pop().getBoolean();

        this.stack.push(new Value(left && right));
    }

    private void or()
    {
        if(isNIL(stack.peek()) || isNIL(stack.get(stack.size() - 2)))
            ErrorLog.fatalError(COMPARE_NIL_ERROR);

        boolean right = this.stack.pop().getBoolean();
        boolean left = this.stack.pop().getBoolean();

        this.stack.push(new Value(left || right));
    }

    private void not()
    {
        if(isNIL(stack.peek()))
            ErrorLog.fatalError(UNARY_NIL_ERROR);

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
            ErrorLog.fatalError("Too many Program arguments. tVM.tVM [OPTION] [FILE]");
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
