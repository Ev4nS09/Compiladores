public class Client {
    public static void main(String[] args) throws Exception{
        String inputFile = args.length > 0 ? args[0] : null;
        String outputFile = "byteCodes/data.bin";

        LExprCompiler compiler = new LExprCompiler();
        VirtualMachine virtualMachine= new VirtualMachine();

        compiler.compile(inputFile, outputFile);
        virtualMachine.execute(outputFile);
    }
}
