import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TasmCompile
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
            inputFile = "inputs/input.tasm";
            File file = new File(inputFile);
            FileWriter writer = new FileWriter(inputFile);
            writer.write(readInput());
            writer.close();
        }

        if (!inputFile.split("\\.")[1].equals("tasm"))
        {
            Flaw.Error("Invalid file extension, File must have the extension tasm.");
        }

        String outputFile = inputFile.split("\\.")[0].concat(".tbc");

        tAssembler compiler = new tAssembler();
        compiler.compile(inputFile, outputFile);

        System.out.println("Program compiled successfully.");
    }
}
