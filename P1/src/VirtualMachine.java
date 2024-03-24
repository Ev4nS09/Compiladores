import java.io.*;
import java.util.*;

public class VirtualMachine {

    private final Stack<Integer> stack;

    public VirtualMachine(){
        this.stack = new Stack<>();
    }

    private void doInstruction(OpCode instruction, DataInputStream inputStream) throws Exception{

        switch (instruction) {

            case iconst -> iconst(inputStream.readInt());

            case iadd -> iadd();

            case imult -> imult();

            case idiv -> idiv();

            case isub -> isub();

            case iuminus -> iuminus();

            case ipow -> ipow();

            case iprint -> iprint();
        }
    }

    public void execute(String file) throws Exception{

        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));

        while(inputStream.available() > 0) {
            OpCode instruction = OpCode.values()[inputStream.readByte()];
            doInstruction(instruction, inputStream);
        }

        inputStream.close();
        stack.clear();
    }

    private void iconst(int integer){
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
}
