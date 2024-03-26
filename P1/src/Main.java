import javax.xml.crypto.Data;
import java.io.*;
import java.math.BigInteger;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        double a = 123.1234;
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("test.bc"));
        outputStream.writeDouble(a);
        outputStream.close();
        DataInputStream inputStream = new DataInputStream(new FileInputStream("test.bc"));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        long x = 0;
        for (byte b : bytes)
            x = (x << 8) + (b & 0xFF);
        double result = Double.longBitsToDouble(x);
        System.out.println(result);

        Instruction instruction = new InstructionInteger<Byte>(OpCode.iconst, new Byte[]{100});
        System.out.println(instruction);

    }
}