import java.nio.ByteBuffer;

public class Client {
    public static void main(String[] args) throws Exception {

        String inputFile = null;

        boolean trace = false;
        boolean asm = false;

        for (String arg : args) {

            if (arg.equals("-trace") || arg.equals("-t"))
                trace = true;
            else if (arg.equals("-asm") || arg.equals("-a"))
                asm = true;
            else
                inputFile = arg;
        }

        String outputFile = inputFile != null ? STR."\{inputFile.split("\\.")[0]}.bc" : "output.bc";


        LExprCompiler compiler = new LExprCompiler(asm);
        VirtualMachine virtualMachine = new VirtualMachine(trace);

        compiler.compile(inputFile, outputFile);
        virtualMachine.execute(outputFile);

    }
}
