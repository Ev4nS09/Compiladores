import java.io.*;
import java.util.*;

public class Main {

    public void main(String[] args) throws Exception
    {
        TasmCompiler compiler = new TasmCompiler();
        VirtualMachine vm = new VirtualMachine(true);
        compiler.compile("src/test.txt", "src/text.bc");
        vm.execute("src/text.bc");
    }
}
