import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

import antlr.*;

public class LExprCompiler {

    private final boolean asm;

    public LExprCompiler(boolean asm){
        this.asm = asm;
    }

    public LExprCompiler(){
        this(false);
    }

    private LExprParser generateParser(InputStream inputStream) throws Exception{

        LExprLexer lexer = new LExprLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        return new LExprParser(tokens);
    }

    private void generateByteCode(LinkedList<Instruction> instructions, String outputFile) throws Exception {

        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));
        for (Instruction instruction : instructions) {
            outputStream.writeByte(instruction.getInstruction().ordinal());

            for (Integer argument : instruction.getInstructionArguments())
                outputStream.writeInt(argument);
        }
        outputStream.close();
    }

    private void asm(LinkedList<Instruction> instructions, String outputFile){
        System.out.println("Generated code in assembly format");

        for(int i = 0; i < instructions.size(); i++)
            System.out.println(STR."\{i}: \{instructions.get(i).toString()}");

        System.out.println(STR."Saving the bytecodes to \{outputFile}");
    }

    public void compile(String inputFile, String outputFile) throws Exception
    {
        InputStream inputStream = inputFile == null ? System.in : new FileInputStream(inputFile);

        LExprParser parser = generateParser(inputStream);

        ParseTree tree = parser.s();
        ParseTreeWalker walker = new ParseTreeWalker();

        InstructionTree instructionTree = new InstructionTree();
        walker.walk(instructionTree, tree);

        generateByteCode(instructionTree.getInstructions(), outputFile);

        if(this.asm)
            asm(instructionTree.getInstructions(), outputFile);

    }
}