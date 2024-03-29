import javax.swing.*;
import java.io.*;
import java.lang.classfile.FieldBuilder;
import java.nio.ByteBuffer;
import java.util.*;

public class VirtualMachine {

    private final static int TAB_SIZE = 30;

    private Stack<Object> stack;
    private Object[] globalMemory;
    private ByteCodeBuffer byteCodeBuffer;
    private final LinkedList<String> stackIterations;
    private final LinkedList<Instruction> instructions;

    private final boolean trace;

    private int instructionPointer;

    public VirtualMachine(boolean trace) throws IOException {
        this.byteCodeBuffer = null;
        this.stack = new Stack<>();
        this.trace = trace;

        this.globalMemory = new Object[0];
        this.stackIterations = new LinkedList<>();
        this.stackIterations.add("[]");

        this.instructions = new LinkedList<>();
        this.instructionPointer = 0;

    }

    public VirtualMachine() throws IOException {
        this(false);
    }

    private void doInstruction(OpCode instruction) throws Exception
    {
        if(this.trace)
        {
            System.out.println("                " + "Globals: " + Arrays.toString(this.globalMemory));
            System.out.println("                " +  "Stack: " + this.stack);
        }

        switch (instruction)
        {
            case iconst -> iconst(this.byteCodeBuffer.getInt());

            case dconst -> dconst(this.byteCodeBuffer.getDouble());

            case sconst -> sconst(this.byteCodeBuffer.getString());

            case jump -> jump(null);

            case jumpt -> jumpt(null);

            case jumpf -> jumpf(null);

            case galloc -> galloc(this.byteCodeBuffer.getInt());

            case gload -> gload(this.byteCodeBuffer.getInt());

            case gstore -> gstore(this.byteCodeBuffer.getInt());

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

        }

        if(this.trace)
        {
            System.out.println(this.instructionPointer + ": " + this.instructions.get(instructionPointer));
        }

        this.stackIterations.add(this.stack.toString());
        this.instructionPointer++;
    }

    public void execute(String byteCode) throws Exception
    {
        this.byteCodeBuffer = new ByteCodeBuffer(byteCode);
        generateInstructions();

        if(this.trace)
            trace();

        while(this.byteCodeBuffer.isAvailable())
        {
            OpCode instruction = OpCode.values()[this.byteCodeBuffer.getByte()];

            if(instruction == OpCode.halt)
                break;

            doInstruction(instruction);
        }

        reset();
    }

    private void reset()
    {
        this.globalMemory = new Object[0];
        this.stack.clear();

        this.stackIterations.clear();
        this.stackIterations.add("[]");

        this.instructions.clear();
        this.instructionPointer = 0;

    }

    private void iconst(int number)
    {
        this.stack.push(number);
    }
    private void dconst(double number)
    {
        this.stack.push(number);
    }
    private void sconst(String string)
    {
        this.stack.push(string);
    }
    private void tconst()
    {
        this.stack.push(true);
    }
    private void fconst()
    {
        this.stack.push(false);
    }
    private void jump(String tag)
    {
        //TODO ----------------------------------!?!---------------------------
    }
    private void jumpt(String tag)
    {
        //TODO ----------------------------------!?!---------------------------
    }
    private void jumpf(String tag)
    {
        //TODO ----------------------------------!?!---------------------------
    }
    private void galloc(int size)
    {
        this.globalMemory = new Object[size];
        for(int i = 0; i < size; i++)
            this.globalMemory[i] = "NIL";
    }
    private void gload(int address)
    {
        this.stack.push(this.globalMemory[address]);
    }
    private void gstore(int address)
    {
        this.globalMemory[address] = this.stack.pop();
    }

    private void iprint() {
        System.out.println(stack.pop());
    }
    private void iuminus()
    {
        stack.push(-(Integer)this.stack.pop());
    }
    private void iadd()
    {
        int right = (Integer)stack.pop();
        int left = (Integer)stack.pop();
        stack.push(left + right);
    }
    private void isub()
    {
        int right = (Integer)stack.pop();
        int left = (Integer)stack.pop();
        stack.push(left - right);
    }
    private void imult()
    {
        int right = (Integer) this.stack.pop();
        int left = (Integer) this.stack.pop();
        this.stack.push(left * right);
    }
    private void idiv()
    {
        int right = (Integer) this.stack.pop();
        int left = (Integer) this.stack.pop();

        if(right == 0)
            throw new ArithmeticException("Divisor mustn't be 0");

        this.stack.push(left / right);
    }
    private void imod()
    {
        int right = (Integer) this.stack.pop();
        int left = (Integer) this.stack.pop();

        if(right == 0)
            throw new ArithmeticException("Divisor mustn't be 0");

        this.stack.push(left % right);
    }
    private void ieq()
    {
        int right = (Integer) this.stack.pop();
        int left = (Integer) this.stack.pop();

        if (left == right)
            stack.push(true);
        else
            stack.push(false);
    }
    private void ineq()
    {
        int right = (Integer) this.stack.pop();
        int left = (Integer) this.stack.pop();

        if (left != right)
            stack.push(true);
        else
            stack.push(false);
    }
    private void ilt()
    {
        int right = (Integer) this.stack.pop();
        int left = (Integer) this.stack.pop();

        if (left < right)
            stack.push(true);
        else
            stack.push(false);

    }
    private void ileq() {
        int right = (Integer) this.stack.pop();
        int left = (Integer) this.stack.pop();

        if (left <= right)
            stack.push(true);
        else
            stack.push(false);


    }
    private void itod()
    {
        Double real = (Double)stack.pop();
        stack.push(real);

    }
    private void itos()
    {
        int inteiro = (Integer)stack.pop();
        stack.push(String.valueOf(inteiro));
    }
    private void dprint()
    {
        System.out.println(this.stack.pop());
    }
    private void duminus()
    {
        stack.push(-(Double)this.stack.pop());
    }

    private void dadd()
    {
        Double left = (Double)stack.pop();
        Double right = (Double)stack.pop();
        stack.push(left + right);
    }

    private void dsub()
    {
        Double right = (Double)stack.pop();
        Double left = (Double)stack.pop();
        stack.push(left - right);
    }

    private void dmult()
    {
        Double right = (Double)stack.pop();
        Double left = (Double)stack.pop();
        stack.push(left - right);
    }
    private void ddiv()
    {
        double right = (Double) this.stack.pop();
        double left = (Double) this.stack.pop();

        if(right == 0)
            throw new ArithmeticException("Divisor mustn't be 0");

        this.stack.push(left / right);

    }
    private void deq()
    {
        double right = (Double)stack.pop();
        double left = (Double)stack.pop();

        if (left == right)
            stack.push(true);
        else
            stack.push(false);
        ;
    }
    private void dneq()
    {
        double right = (Double)stack.pop();
        double left = (Double)stack.pop();
        if (left != right)
            stack.push(true);
        else
            stack.push(false);
    }
    private void dlt()
    {
        double right = (Double)stack.pop();
        double left = (Double)stack.pop();
        if (left < right)
            stack.push(true);
        else
            stack.push(false);
    }
    private void dleq()
    {
        double right = (Double)stack.pop();
        double left = (Double)stack.pop();

        if (left <= right)
            stack.push(true);
        else
            stack.push(false);
    }
    private void dtos()
    {
        Double real = (Double)stack.pop();
        stack.push(String.valueOf(real));
    }
    private void sprint()
    {
        char x = '"';
        System.out.println(((String)this.stack.pop()).replaceAll(Character.toString(x), ""));
    }
    private void sadd()
    {
        String right = (String)stack.pop();
        String left = (String)stack.pop();

        left = left.substring(0, left.length()-1);
        right = right.substring(1);


        this.stack.push((left.concat(right)));
    }
    private void seq()
    {
        String right = (String)stack.pop();
        String left = (String)stack.pop();

        if (left.equals(right))
            stack.push(true);
        else
            stack.push(false);
    }
    private void sneq()
    {
        String right = (String)stack.pop();
        String left = (String)stack.pop();

        if (!left.equals(right))
            stack.push(true);
        else
            stack.push(false);
    }
    private void bprint()
    {
        System.out.println(stack.pop());
    }

    private void beq()
    {
        Boolean right = (Boolean)stack.pop();
        Boolean left = (Boolean)stack.pop();

        if (left.equals(right))
            stack.push(true);
        else
            stack.push(false);
    }

    private void bneq()
    {
        Boolean right = (Boolean)stack.pop();
        Boolean left = (Boolean)stack.pop();

        if (!left.equals(right))
            stack.push(true);
        else
            stack.push(false);
    }

    private void btos()
    {
        if ((boolean) this.stack.pop())
            stack.push("true");

        else
            stack.push("false");

    }


    private void generateInstructions() throws IOException
    {
        while (this.byteCodeBuffer.isAvailable())
        {
            OpCode instruction = OpCode.values()[this.byteCodeBuffer.getByte()];

            if (instruction == OpCode.iconst)
            {
                this.instructions.add(new Instruction(instruction, this.byteCodeBuffer.getInt()));
            }

            else if (instruction == OpCode.dconst)
            {
                this.instructions.add(new Instruction(instruction, this.byteCodeBuffer.getDouble()));
            }

            else if(instruction == OpCode.sconst)
            {
                this.instructions.add(new Instruction(instruction, this.byteCodeBuffer.getString()));
            }

            else if(instruction == OpCode.galloc | instruction == OpCode.gload | instruction == OpCode.gstore)
            {
                this.instructions.add(new Instruction(instruction, this.byteCodeBuffer.getInt()));
            }

            else if(instruction == OpCode.jump | instruction == OpCode.jumpf | instruction == OpCode.jumpt)
            {
                this.instructions.add(new Instruction(instruction, this.byteCodeBuffer.getString()));
            }

            else
            {
                this.instructions.add(new Instruction(instruction));
            }
        }

        this.byteCodeBuffer.resetPointer();
    }

    private void trace() throws IOException
    {

        System.out.println("\nDisassembled instructions");
        for(int i = 0; i < instructions.size(); i++)
        {
            Instruction instruction = instructions.get(i);
            System.out.println(STR."\{i}: \{instruction.toString()}");
        }
        System.out.println("\nTrace while running the code");
        System.out.println("Execution starts at instruction 0\n");
    }
}
