import java.io.*;
import java.lang.classfile.FieldBuilder;
import java.nio.ByteBuffer;
import java.util.*;

public class VirtualMachine {

    private final static int TAB_SIZE = 30;

    private final Stack<Integer> stack;
    private final LinkedList<String> stackIterations;
    private final LinkedList<Instruction> instructions;

    private final boolean trace;

    private DataInputStream bytes;
    private int instructionPointer;

    public VirtualMachine(boolean trace) throws IOException {
        this.stack = new Stack<>();
        this.trace = trace;

        this.stackIterations = new LinkedList<>();
        this.stackIterations.add("[]");

        this.instructions = new LinkedList<>();
        this.instructionPointer = 0;

    }


    public VirtualMachine() throws IOException {
        this(false);
    }

    private void generateInstructions() throws IOException {
        this.bytes.mark(this.bytes.available());
        while(this.bytes.available() > 0){
            OpCode instruction = OpCode.values()[this.bytes.readByte()];

            if(instruction == OpCode.iconst)
                this.instructions.add(new Instruction(instruction, this.bytes.readInt()));
            else
                this.instructions.add(new Instruction(instruction));
        }
        this.bytes.reset();
    }


    private void doInstruction(OpCode instruction) throws Exception{

        switch (instruction) {

            case iconst -> iconst();

            case iadd -> iadd();

            case imult -> imult();

            case idiv -> idiv();

            case isub -> isub();

            case iuminus -> iuminus();

            case ipow -> ipow();

            case iprint -> iprint();
        }

        if(this.trace)
            traceInstruction();

        this.stackIterations.add(this.stack.toString());
        this.instructionPointer++;
    }

    public void execute(String byteCode) throws Exception{

        this.bytes = new DataInputStream(new FileInputStream(byteCode));

        if(this.trace){
           generateInstructions();
           trace();
        }

        while(this.bytes.available() > 0) {
            OpCode instruction = OpCode.values()[this.bytes.readByte()];
            doInstruction(instruction);
        }

        this.bytes.close();
        this.stack.clear();
        this.instructionPointer = 0;

    }

    private void iconst() throws Exception {
        int integer = this.bytes.readInt();
        this.instructions.add(new Instruction(OpCode.iconst, integer));
        this.stack.push(integer);
    }

    private void iadd(){
        int right = this.stack.pop();
        int left = this.stack.pop();
        this.stack.push(left + right);
    }

    private void imult(){
        int right = this.stack.pop();
        int left = this.stack.pop();
        this.stack.push(left * right);
    }

    private void idiv() throws Exception{
        int right = this.stack.pop();
        int left = this.stack.pop();

        if(right == 0)
            throw new ArithmeticException("Divisor must not be 0");

        this.stack.push(left / right);
    }

    private void isub() {
        int right = this.stack.pop();
        int left = this.stack.pop();
        this.stack.push(left - right);
    }

    private void iuminus(){
        this.stack.push(-this.stack.pop());
    }

    private void ipow(){
        int right = this.stack.pop();
        int left = this.stack.pop();
        this.stack.push((int) Math.pow(left, right));
    }

    private void iprint(){
        System.out.println(this.stack.pop());
    }

    private void traceInstruction(){
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


    private void trace() {

        System.out.println("ByteCodes: ");
        for(Instruction instruction : instructions)
            System.out.print(STR."\{instruction.toStringBytes()} ");

        System.out.println("\nDisassembled instructions");
        for(int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            System.out.println(STR."\{i}: \{instruction.toString()}\{" ".repeat(TAB_SIZE - instruction.toString().length())}// \{instruction.toStringBytes()}");

        }
        System.out.println("\nTrace while running the code");
        System.out.println("Execution starts at instruction 0\n");
    }
}
