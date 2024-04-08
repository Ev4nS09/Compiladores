import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.FileSystemException;
import java.util.Scanner;

public class Main
{

    private String readInput()
    {
        String result = "";
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine())
        {
            result += scanner.nextLine() + '\n';
        }

        return result;
    }

    public void main(String[] args) throws Exception
    {
        String inputFile = null;
        boolean trace = false;

        for (String arg : args) {

            if (arg.equals("-trace") || arg.equals("-t"))
                trace = true;
            else
                inputFile = arg;
        }

        if(inputFile == null)
        {
            inputFile = "inputs/input.tasm";
            File file = new File(inputFile);
            FileWriter writer = new FileWriter(inputFile);
            writer.write(readInput());
            writer.close();
        }

        if(!inputFile.split("\\.")[1].equals("tasm"))
        {
            Flaw.Error("Invalid file extension, File must have the extension tasm.");
        }

        String outputFile = inputFile.split("\\.")[0].concat(".tbc");


        tAssembler compiler = new tAssembler();
        tVM tVM = new tVM(trace);

        compiler.compile(inputFile, outputFile);
        tVM.execute(outputFile);

    }
}
