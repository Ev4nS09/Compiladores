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
    private int instructionPointer;

    public VirtualMachine(boolean trace){
        this.stack = new Stack<>();
        this.trace = trace;

        this.stackIterations = new LinkedList<>();
        this.stackIterations.add("[]");

        this.instructions = new LinkedList<>();
        this.instructionPointer = 0;
    }

    public VirtualMachine(){
        this(false);
    }


    private void doInstruction(OpCode instruction, DataInputStream byteStream) throws Exception{

        switch (instruction) {

            case iconst -> iconst(byteStream.readInt());

            case iadd -> iadd();

            case imult -> imult();

            case idiv -> idiv();

            case isub -> isub();

            case iuminus -> iuminus();

            case ipow -> ipow();

            case iprint -> iprint();
        }
        this.stackIterations.add(this.stack.toString());

        if(instruction != OpCode.iconst)
            this.instructions.add(new Instruction(instruction));

        this.instructionPointer++;
    }

    public void execute(String byteCode) throws Exception{

        DataInputStream byteStream = new DataInputStream(new FileInputStream(byteCode));
        byteStream.mark(0);

        while(byteStream.available() > 0) {
            OpCode instruction = OpCode.values()[byteStream.readByte()];
            doInstruction(instruction, byteStream);
        }

        if(this.trace)
            trace();

        byteStream.close();
        stack.clear();
    }

    private void iconst(int integer){
        this.instructions.add(new Instruction(OpCode.iconst, new int[]{integer}));
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


    private void trace(){
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

        for(int i = 0; i < instructions.size(); i++) {
            String instructionString = instructions.get(i).toString();
            System.out.println(STR."\{i}: \{instructionString}\{" ".repeat(TAB_SIZE - instructionString.length())}Stack: \{stackIterations.get(i)}");
        }
    }
}
