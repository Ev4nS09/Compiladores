import java.io.*;
import java.util.*;

public class tVM {

    private final Stack<Object> stack;
    private Object[] globalMemory;
    private ByteCodeBuffer byteCodeBuffer;
    private final LinkedList<Instruction> instructions;
    private final LinkedList<Instruction> constPool;

    private final boolean trace;

    private int instructionPointer;

    public tVM(boolean trace) throws IOException {
        this.byteCodeBuffer = null;
        this.stack = new Stack<>();
        this.trace = trace;
        this.constPool = new LinkedList<>();

        this.globalMemory = new Object[0];

        this.instructions = new LinkedList<>();
        this.instructionPointer = 0;

    }

    public tVM() throws IOException {
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

        OpCode instructionOpCOde = instruction.getInstruction();
        Integer argument = instruction.hasArgument() ? (Integer) instruction.getArgument() : null;

        switch (instruction.getInstruction())
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
        this.globalMemory = new Object[0];
        this.stack.clear();
        this.constPool.clear();

        this.instructions.clear();
        this.instructionPointer = 0;

    }

    private String verifyObejctTypeString(Object object)
    {
        if(!(object instanceof String))
            Flaw.Error("Invalid type for the instruction " +
                    this.instructions.get(this.instructionPointer-1) + "The valid type must be a String");

        return (String) object;
    }

    private Integer verifyObejctTypeInteger(Object object)
    {
        if(!(object instanceof Integer))
            Flaw.Error("Invalid type for the instruction " +
                    this.instructions.get(this.instructionPointer-1) + "The valid type must be an Integer");

        return (Integer) object;
    }

    private Double verifyObejctTypeDouble(Object object)
    {
        if(!(object instanceof Double))
            Flaw.Error("Invalid type for the instruction " +
                    this.instructions.get(this.instructionPointer-1) + "The valid type must be a Double");

        return (Double) object;

    }


    private Boolean verifyObejctTypeBoolean(Object object)
    {
        if(!(object instanceof Boolean))
            Flaw.Error("Invalid type for the instruction " +
                    this.instructions.get(this.instructionPointer-1) + "The valid type must be a Boolean");

        return (Boolean) object;
    }


    private void iconst(Integer number)
    {
        this.stack.push(number);
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
        this.stack.push(true);
    }

    private void fconst()
    {
        this.stack.push(false);
    }

    private void jump(Integer line)
    {
        this.instructionPointer = line;
    }

    private void jumpt(Integer line)
    {

        boolean bool = this.verifyObejctTypeBoolean(this.stack.pop());

        if(bool)
            this.instructionPointer = line;
    }

    private void jumpf(Integer line)
    {
        boolean bool = this.verifyObejctTypeBoolean(this.stack.pop());

        if(!bool)
            this.instructionPointer = line;
    }

    private void galloc(Integer size)
    {
        int newGlobalSize = this.globalMemory.length + size;
        Object[] newGlobal = new Object[newGlobalSize];

        System.arraycopy(this.globalMemory, 0, newGlobal, 0, this.globalMemory.length);

        for(int i = this.globalMemory.length; i < newGlobalSize; i++)
        {
            newGlobal[i] = "NIL";
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
        System.out.println(this.verifyObejctTypeInteger(stack.pop()));
    }

    private void iuminus()
    {
        stack.push(-this.verifyObejctTypeInteger(this.stack.pop()));
    }

    private void iadd()
    {
        int right = this.verifyObejctTypeInteger(stack.pop());
        int left = this.verifyObejctTypeInteger(stack.pop());
        stack.push(left + right);
    }

    private void isub()
    {
        int right = this.verifyObejctTypeInteger(stack.pop());
        int left = this.verifyObejctTypeInteger(stack.pop());
        stack.push(left - right);
    }

    private void imult()
    {
        int right = this.verifyObejctTypeInteger(this.stack.pop());
        int left = this.verifyObejctTypeInteger(this.stack.pop());
        this.stack.push(left * right);
    }

    private void idiv()
    {
        int right = this.verifyObejctTypeInteger(this.stack.pop());
        int left = this.verifyObejctTypeInteger(this.stack.pop());

        if(right == 0)
            Flaw.Error("Divisor mustn't be 0");

        this.stack.push(left / right);
    }

    private void imod()
    {
        int right = this.verifyObejctTypeInteger(this.stack.pop());
        int left = this.verifyObejctTypeInteger(this.stack.pop());

        if(right == 0)
            Flaw.Error("Divisor mustn't be 0");

        this.stack.push(left % right);
    }

    private void ieq()
    {
        int right = this.verifyObejctTypeInteger(this.stack.pop());
        int left = this.verifyObejctTypeInteger(this.stack.pop());

        if (left == right)
            stack.push(true);
        else
            stack.push(false);
    }

    private void ineq()
    {
        int right = this.verifyObejctTypeInteger(this.stack.pop());
        int left = this.verifyObejctTypeInteger(this.stack.pop());

        if (left != right)
            stack.push(true);
        else
            stack.push(false);
    }

    private void ilt()
    {
        int right = this.verifyObejctTypeInteger(this.stack.pop());
        int left = this.verifyObejctTypeInteger(this.stack.pop());

        if (left < right)
            stack.push(true);
        else
            stack.push(false);

    }

    private void ileq()
    {
        int right = this.verifyObejctTypeInteger(this.stack.pop());
        int left = this.verifyObejctTypeInteger(this.stack.pop());

        if (left <= right)
            stack.push(true);
        else
            stack.push(false);


    }

    private void itod()
    {
        Double real = Double.valueOf(this.verifyObejctTypeInteger(stack.pop()));
        stack.push(real);

    }

    private void itos()
    {
        int integer = this.verifyObejctTypeInteger(stack.pop());
        stack.push('"' + String.valueOf(integer) + '"');
    }

    private void dprint()
    {
        System.out.println(this.verifyObejctTypeDouble(this.stack.pop()));
    }

    private void duminus()
    {
        stack.push(-this.verifyObejctTypeDouble(this.stack.pop()));
    }

    private void dadd()
    {
        Double left = this.verifyObejctTypeDouble(stack.pop());
        Double right = this.verifyObejctTypeDouble(stack.pop());
        stack.push(left + right);
    }

    private void dsub()
    {
        Double right = this.verifyObejctTypeDouble(stack.pop());
        Double left = this.verifyObejctTypeDouble(stack.pop());
        stack.push(left - right);
    }

    private void dmult()
    {
        Double right = this.verifyObejctTypeDouble(stack.pop());
        Double left = this.verifyObejctTypeDouble(stack.pop());
        stack.push(left * right);
    }

    private void ddiv()
    {
        double right = this.verifyObejctTypeDouble(this.stack.pop());
        double left = this.verifyObejctTypeDouble(this.stack.pop());

        if(right == 0)
            Flaw.Error("Divisor mustn't be 0");

        this.stack.push(left / right);

    }

    private void deq()
    {
        double right = this.verifyObejctTypeDouble(stack.pop());
        double left = this.verifyObejctTypeDouble(stack.pop());

        if (left == right)
            stack.push(true);
        else
            stack.push(false);
        ;
    }

    private void dneq()
    {
        double right = this.verifyObejctTypeDouble(stack.pop());
        double left = this.verifyObejctTypeDouble(stack.pop());
        if (left != right)
            stack.push(true);
        else
            stack.push(false);
    }

    private void dlt()
    {
        double right = this.verifyObejctTypeDouble(stack.pop());
        double left = this.verifyObejctTypeDouble(stack.pop());
        if (left < right)
            stack.push(true);
        else
            stack.push(false);
    }

    private void dleq()
    {
        double right = this.verifyObejctTypeDouble(stack.pop());
        double left = this.verifyObejctTypeDouble(stack.pop());

        if (left <= right)
            stack.push(true);
        else
            stack.push(false);
    }

    private void dtos()
    {
        Double real = this.verifyObejctTypeDouble(stack.pop());
        stack.push('"' + String.valueOf(real) + '"');
    }


    private void sprint()
    {
        System.out.println((this.verifyObejctTypeString(this.stack.pop())).replaceAll("\"", ""));
    }

    private void sadd()
    {
        String right = this.verifyObejctTypeString(stack.pop());
        String left = this.verifyObejctTypeString(stack.pop());

        left = left.substring(0, left.length()-1);
        right = right.substring(1);


        this.stack.push((left.concat(right)));
    }

    private void seq()
    {
        String right = this.verifyObejctTypeString(this.stack.pop());
        String left = this.verifyObejctTypeString(this.stack.pop());

        if (left.equals(right))
            this.stack.push(true);
        else
            this.stack.push(false);
    }

    private void sneq()
    {
        String right = this.verifyObejctTypeString(this.stack.pop());
        String left = this.verifyObejctTypeString(this.stack.pop());

        if (!left.equals(right))
            this.stack.push(true);
        else
            this.stack.push(false);
    }

    private void bprint()
    {
        System.out.println(this.verifyObejctTypeBoolean(this.stack.pop()));
    }

    private void beq()
    {
        Boolean right = this.verifyObejctTypeBoolean(stack.pop());
        Boolean left = this.verifyObejctTypeBoolean(stack.pop());

        if (left.equals(right))
            stack.push(true);
        else
            stack.push(false);
    }

    private void bneq()
    {
        Boolean right = this.verifyObejctTypeBoolean(stack.pop());
        Boolean left = this.verifyObejctTypeBoolean(stack.pop());

        if (!left.equals(right))
            stack.push(true);
        else
            stack.push(false);
    }

    private void btos()
    {
        if (this.verifyObejctTypeBoolean(this.stack.pop()))
            stack.push("\"true\"");

        else
            stack.push("\"false\"");

    }

    private void and()
    {
        boolean right = this.verifyObejctTypeBoolean(this.stack.pop());
        boolean left = this.verifyObejctTypeBoolean(this.stack.pop());

        this.stack.push(left && right);
    }

    private void or()
    {
        boolean right = this.verifyObejctTypeBoolean(this.stack.pop());
        boolean left = this.verifyObejctTypeBoolean(this.stack.pop());

        this.stack.push(left || right);
    }

    private void not()
    {
        boolean bool = this.verifyObejctTypeBoolean(this.stack.pop());

        this.stack.push(!bool);
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
                this.constPool.add(new Instruction(OpCode.dconst, this.byteCodeBuffer.getDouble()));
            }

            else if(instruction == OpCode.sconst)
            {
                this.constPool.add(new Instruction(OpCode.sconst, this.byteCodeBuffer.getString()));
            }
        }

        //Generates the instructions
        while (this.byteCodeBuffer.isAvailable())
        {

            OpCode instruction = OpCode.values()[this.byteCodeBuffer.getByte()];

            if(this.opCodeHasArgument(instruction))
            {
                this.instructions.add(new Instruction(instruction, this.byteCodeBuffer.getInt()));
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
