import java.io.*;

public class Main {

    public void main(String[] args) throws Exception{
        TasmCompiler compiler = new TasmCompiler();
        compiler.compile("src/test.txt", "src/text.bc");
        System.out.println();
    }
}
