import java.io.*;
import java.util.*;
import java.math.*;

public class VirtualMachine {

    private Stack<Integer> stack;

    public VirtualMachine(){
        this.stack = new Stack<>();
    }

    public void execute(String file) throws Exception{
        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
        while(inputStream.available() > 0) {
            int currentByte = inputStream.read();
            OpCode instruction = OpCode.values()[currentByte];

            int left;
            int right;

            switch (instruction) {
                case OpCode.iconst:
                    stack.push(inputStream.readInt());
                    break;
                case OpCode.iadd:
                    stack.push(stack.pop() + stack.pop());
                    break;
                case OpCode.imult:
                    stack.push(stack.pop() * stack.pop());
                    break;
                case OpCode.idiv:
                    right = stack.pop();
                    left= stack.pop();
                    stack.push(left / right);
                    break;
                case OpCode.isub:
                    right = stack.pop();
                    left = stack.pop();
                    stack.push(left - right);
                    break;
                case OpCode.iuminus:
                    stack.push(-stack.pop());
                    break;
                case OpCode.ipow:
                    right = stack.pop();
                    left = stack.pop();
                    stack.push((int) Math.pow(left, right));
                    break;
                case OpCode.iprint:
                    System.out.println(stack.pop());
                    break;
            }
        }
        inputStream.close();
        stack.clear();
    }
}
