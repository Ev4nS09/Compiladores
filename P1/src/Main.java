import java.io.*;
import java.util.*;

public class Main {

    public void main(String[] args) throws Exception
    {
        TasmCompiler compiler = new TasmCompiler();
         compiler.compile("src/test.txt", "src/text.bc");
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("src/double.bc"));
        DataInputStream inputStream = new DataInputStream(new FileInputStream("src/double.bc"));
        outputStream.writeDouble(3.23);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        for(byte b : bytes)
            System.out.println(b);

        ByteCodeBuffer byteCodeBuffer = new ByteCodeBuffer(bytes);
        System.out.println(byteCodeBuffer.getDouble());

        Stack<Object> stack = new Stack<>();
        stack.add(1);
        stack.add("Ola");

    }
}
