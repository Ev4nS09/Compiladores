import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class SolCompile
{

    private static String readInput()
    {
        String result = "";
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine())
        {
            result += scanner.nextLine() + '\n';
        }

        return result;
    }

    public static void main(String[] args) throws Exception
    {
        if(args.length > 1)
        {
            Flaw.Error("Too many Program arguments. The arguments must the file you want to compile.");
        }

        String inputFile = null;

        if(args.length == 1)
        {
            inputFile = args[0];
        }

        if (inputFile == null)
        {
            inputFile = "inputs/input.sol";
            File file = new File(inputFile);
            FileWriter writer = new FileWriter(inputFile);
            writer.write(readInput());
            writer.close();
        }

        if (!inputFile.split("\\.")[1].equals("sol"))
        {
            Flaw.Error("Invalid file extension, File must have the extension sol.");
        }

        String outputFile = inputFile.split("\\.")[0].concat(".tbc");

        solCompiler compiler = new solCompiler();
        compiler.compile(inputFile, outputFile);

        System.out.println("Program compiled successfully.");
    }
}
