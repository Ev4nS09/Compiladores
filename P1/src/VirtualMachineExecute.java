import java.io.*;
import java.util.*;

public class VirtualMachineExecute
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
        if(args.length > 2)
        {
            Flaw.Error("Too many Program arguments. VirtualMachineExecute [OPTION] [FILE]");
        }

        String inputFile = null;
        boolean trace = false;

        for (String arg : args)
        {

            if (arg.equals("-trace") || arg.equals("-t"))
                trace = true;
            else
                inputFile = arg;
        }

        if (inputFile == null)
        {
            inputFile = "inputs/input.tbc";
            File file = new File(inputFile);
            FileWriter writer = new FileWriter(inputFile);
            writer.write(readInput());
            writer.close();
        }

        if (!inputFile.split("\\.")[1].equals("tbc"))
        {
            Flaw.Error("Invalid file extension, File must have the extension tbc.");
        }

        tVM virtualMachine = new tVM(trace);
        virtualMachine.execute(inputFile);
    }

}
