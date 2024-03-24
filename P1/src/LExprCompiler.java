import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;

import antlr.*;


public class LExprCompiler {

    public void generateByteCode(LinkedList<Instruction> instructions, String outputFile) throws Exception {

        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));
        for (Instruction instruction : instructions) {
            outputStream.writeByte(instruction.getInstruction().ordinal());

            for (Integer argument : instruction.getInstructionArguments())
                outputStream.writeInt(argument);
        }
        outputStream.close();
    }

    public void compile(String inputFile, String outputFile) throws Exception{
        InputStream is = System.in;
        if (inputFile != null)
            is = new FileInputStream(inputFile);

        CharStream input = CharStreams.fromStream(is);
        LExprLexer lexer = new LExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LExprParser parser = new LExprParser(tokens);

        ParseTree tree = parser.s();
        ParseTreeWalker walker = new ParseTreeWalker();

        InstructionThree instructionThree = new InstructionThree();
        walker.walk(instructionThree, tree);

        generateByteCode(instructionThree.instructions, outputFile);

    }
}