import javax.xml.crypto.Data;
import java.io.*;
import java.math.BigInteger;
import java.util.Stack;

public class Main {

    Class<?> getInteger() throws ClassNotFoundException {
        return Class.forName("java.lang.Integer");
    }
    public void main(String[] args) throws Exception{
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("test.bc"));
        Instruction instruction = new Instruction(OpCode.iconst);
        Class<?> x = getInteger();

        System.out.println();
    }
}