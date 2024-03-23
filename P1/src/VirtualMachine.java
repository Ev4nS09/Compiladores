import java.io.*;
import java.util.*;
import java.math.*;

public class VirtualMachine {

    private final Stack<Integer> stack;

    public VirtualMachine(){
        this.stack = new Stack<>();
    }

    public void execute(String file) throws Exception{

        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
        while(inputStream.available() > 0) {
            OpCode instruction = OpCode.values()[inputStream.readByte()];

            switch (instruction) {
                case iconst:
                    iconst(inputStream.readInt());
                    break;

                case iadd:
                    iadd();
                    break;

                case imult:
                    imult();
                    break;

                case idiv:
                    idiv();
                    break;

                case isub:
                    isub();
                    break;

                case iuminus:
                    iuminus();
                    break;

                case ipow:
                    ipow();
                    break;

                case iprint:
                    iprint();
                    break;
            }
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
