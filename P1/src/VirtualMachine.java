import java.io.*;
import java.lang.classfile.FieldBuilder;
import java.nio.ByteBuffer;
import java.util.*;

public class VirtualMachine {

    private final static int TAB_SIZE = 30;

    private Stack<Object> stack;
    private ArrayList<Object> constantPool;
    private ByteCodeBuffer byteCodeBuffer;
    private final LinkedList<String> stackIterations;
    private final LinkedList<Instruction> instructions;

    private final boolean trace;

    private int instructionPointer;

    public VirtualMachine(boolean trace) throws IOException {
        this.byteCodeBuffer = null;
        this.stack = new Stack<>();
        this.constantPool = new ArrayList<>();
        this.trace = trace;

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
            traceInstruction();

        switch (instruction)
        {
            case iconst -> iconst(1);

            case dconst -> dconst(1);

            case sconst -> sconst(1);

            case jump -> jump(1);

            case jumpt -> jumpt(1);

            case jumpf -> jumpf(1);

            case galloc -> galloc(1);

            case gload -> gload(1);

            case gstore -> gstore(1);

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

            case halt -> halt();

        }

        this.stackIterations.add(this.stack.toString());
        this.instructionPointer++;
    }

    public void execute(String byteCode) throws Exception
    {
        this.byteCodeBuffer = new ByteCodeBuffer(byteCode);

        if(this.trace)
            trace();

        while(this.byteCodeBuffer.isAvailable()) {
            OpCode instruction = OpCode.values()[this.byteCodeBuffer.getByte()];
            doInstruction(instruction);
        }

        reset();
    }

    private void reset()
    {
        this.stack.clear();
        this.stackIterations.clear();
        this.instructions.clear();
        this.instructionPointer = 0;
    }

    private void iconst(int n) {
        stack.push(n);
    }
    private void dconst(int n) {
        //TODO  ---------------------------------!?!---------------------------
    }
    private void sconst(int n) {
        //TODO  ---------------------------------!?!---------------------------
    }
    private void tconst() {
        //TODO  ---------------------------------!?!---------------------------
    }
    private void fconst() {
        //TODO  ---------------------------------!?!---------------------------
    }
    private void jump(int addr) {
        //TODO ----------------------------------!?!---------------------------
    }
    private void jumpt(int addr) {
        //TODO ----------------------------------!?!---------------------------
    }
    private void jumpf(int addr) {
        //TODO ----------------------------------!?!---------------------------
    }
    private void galloc(int n) {
        //TODO ----------------------------------!?!---------------------------
    }
    private void gload(int addr) {
        //TODO ----------------------------------!?!---------------------------
    }
    private void gstore(int addr) {
        //TODO ----------------------------------!?!---------------------------
    }

    private void iprint() {
        System.out.println((Integer)stack.pop());
    }
    private void iuminus() {
        int negativo= -(Integer)stack.pop();
        stack.push(negativo);
    }
    private void iadd() {
        int direita = (Integer)stack.pop();
        int esquerda = (Integer)stack.pop();
        int result = esquerda + direita;
        stack.push(result);
    }
    private void isub() {
        int direita = (Integer)stack.pop();
        int esquerda = (Integer)stack.pop();
        int result = esquerda - direita;
        stack.push(result);
    }
    private void imult() {
        int direita = (Integer)stack.pop();
        int esquerda = (Integer)stack.pop();
        int result = esquerda * direita;
        stack.push(result);
    }
    private void idiv() {
        int direita = (Integer)stack.pop();
        if(direita != 0) {
            int esquerda = (Integer)stack.pop();
            int result = esquerda / direita;
            stack.push(result);
        }
        else System.out.println("Divisor inválido");
    }
    private void imod() {
        int direita = (Integer)stack.pop();
        if(direita != 0) {
            int esquerda = (Integer)stack.pop();
            int result = esquerda % direita;
            stack.push(result);
        }
        else System.out.println("Divisor inválido");
    }
    private void ieq() {
        int direita = (Integer)stack.pop();
        int esquerda = (Integer)stack.pop();
        if (esquerda == direita)
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
        ;
    }
    private void ineq() {
        int direita = (Integer)stack.pop();
        int esquerda = (Integer)stack.pop();
        if (esquerda != direita)
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void ilt() {
        int direita = (Integer)stack.pop();
        int esquerda = (Integer)stack.pop();
        if (esquerda < direita)
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void ileq() {
        int direita = (Integer)stack.pop();
        int esquerda = (Integer)stack.pop();
        if (esquerda <= direita)
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void itod() {
        Double real = (Double)stack.pop();
        stack.push((Double)real);

    }
    private void itos() {
        int inteiro = (Integer)stack.pop();
        stack.push(String.valueOf(inteiro));
    }
    private void dprint() {
        System.out.println((Double)stack.pop());
    }
    private void duminus() {
        Double negativo = -(Double)stack.pop();
        stack.push((Double)negativo);
    }private void dadd() {
        Double direita = (Double)stack.pop();
        Double esquerda = (Double)stack.pop();
        Double result = esquerda + direita;
        stack.push(result);
    }
    private void dsub() {
        Double direita = (Double)stack.pop();
        Double esquerda = (Double)stack.pop();
        Double result = esquerda - direita;
        stack.push(result);
    }
    private void dmult() {
        Double direita = (Double)stack.pop();
        Double esquerda = (Double)stack.pop();
        Double result = esquerda * direita;
        stack.push(result);
    }
    private void ddiv() {
        Double direita = (Double)stack.pop();
        if(direita != 0) {
            Double esquerda = (Double)stack.pop();
            Double result = esquerda / direita;
            stack.push(result);
        }
        else System.out.println("Divisor inválido");
    }
    private void deq() {
        Double direita = (Double)stack.pop();
        Double esquerda = (Double)stack.pop();
        if (esquerda == direita)
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
        ;
    }
    private void dneq() {
        Double direita = (Double)stack.pop();
        Double esquerda = (Double)stack.pop();
        if (esquerda != direita)
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void dlt() {
        Double direita = (Double)stack.pop();
        Double esquerda = (Double)stack.pop();
        if (esquerda < direita)
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void dleq() {
        Double direita = (Double)stack.pop();
        Double esquerda = (Double)stack.pop();
        if (esquerda <= direita)
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void dtos() {
        Double real = (Double)stack.pop();
        stack.push(String.valueOf(real));
    }
    private void sprint() {
        System.out.println((String)stack.pop());
    }
    private void sadd() {
        String direta = (String)stack.pop();
        String esquerda = (String)stack.pop();
        esquerda.concat(direta);
    }
    private void seq() {
        String direta = (String)stack.pop();
        String esquerda = (String)stack.pop();
        if (esquerda.equals(direta))
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void sneq() {
        String direta = (String)stack.pop();
        String esquerda = (String)stack.pop();
        if (!esquerda.equals(direta))
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void bprint() {
        System.out.println((Boolean)stack.pop());
    }
    private void beq() {
        Boolean direta = (Boolean)stack.pop();
        Boolean esquerda = (Boolean)stack.pop();
        if (esquerda.equals(direta))
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void bneq() {
        Boolean direta = (Boolean)stack.pop();
        Boolean esquerda = (Boolean)stack.pop();
        if (!esquerda.equals(direta))
            stack.push((Boolean)true);
        else
            stack.push((Boolean)false);
    }
    private void btos() {
        Boolean booleano = (Boolean)stack.pop();
        if (booleano == true) {
            stack.push((String)"true");
        }
        else {
            stack.push((String)"false");
        }
    }
    private void halt() {
        System. exit(0);
    }


    private void generateInstructions() throws IOException
    {
        while (this.byteCodeBuffer.isAvailable()) {
            OpCode instruction = OpCode.values()[this.byteCodeBuffer.getByte()];

            if (instruction == OpCode.iconst)
                this.instructions.add(new Instruction(instruction, this.byteCodeBuffer.getInt()));

            else if (instruction == OpCode.dconst)
            {
                Double argument = this.byteCodeBuffer.getDouble();
                this.instructions.add(new Instruction(instruction, argument));
                this.constantPool.add(argument);
            }

            else if(instruction == OpCode.sconst)
            {
                String argument = this.byteCodeBuffer.getString();
                this.instructions.add(new Instruction(instruction, argument));
                this.constantPool.add(argument);
            }

            else if(instruction == OpCode.galloc | instruction == OpCode.gload | instruction == OpCode.gstore)
                this.instructions.add(new Instruction(instruction, this.byteCodeBuffer.getInt()));

            else if(instruction == OpCode.jump | instruction == OpCode.jumpf | instruction == OpCode.jumpt)
                this.instructions.add(new Instruction(instruction, this.byteCodeBuffer.getString()));

            else
                this.instructions.add(new Instruction(instruction));
        }
        this.byteCodeBuffer.resetPointer();
    }

    private void traceInstruction()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Instruction instruction = this.instructions.get(this.instructionPointer);

        System.out.println(stringBuilder
                .append(this.instructionPointer)
                .append(": ")
                .append(instruction)
                .append(" ".repeat(TAB_SIZE - instruction.toString().length()))
                .append("Stack: ")
                .append(stackIterations.get(this.instructionPointer))
        );
    }


    private void trace() throws IOException
    {
        generateInstructions();

        System.out.println("ByteCodes: ");
        System.out.print(this.byteCodeBuffer.toString());

        System.out.println("\nDisassembled instructions");
        for(int i = 0; i < instructions.size(); i++)
        {
            Instruction instruction = instructions.get(i);
            System.out.println(STR."\{i}: \{instruction.toString()}\{" ".repeat(TAB_SIZE - instruction.toString().length())}// \{instruction.toString()}");
        }
        System.out.println("\nTrace while running the code");
        System.out.println("Execution starts at instruction 0\n");
    }
}
