import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

import Antlr.*;

import javax.swing.text.StyledEditorKit;

public class LExprCompiler {

    private final boolean asm;

    public LExprCompiler(boolean asm){
        this.asm = asm;
    }

    public LExprCompiler(){
        this(false);
    }

    private TasmParser generateParser(InputStream inputStream) throws Exception{

        TasmLexer lexer = new TasmLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        return new TasmParser(tokens);
    }

    private void generateByteCode(LinkedList<Instruction> instructions, String outputFile) throws Exception {

        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));
        for (Instruction instruction : instructions)
        {
            OpCode instructionOpCode = instruction.getInstruction();
            outputStream.writeByte(instructionOpCode.ordinal());

            if(instructionOpCode.name().charAt(0) == 'i')
                outputStream.writeInt(((InstructionArgument<Integer>) instruction).getInstructionArguments());
            else if(instructionOpCode.name().charAt(0) == 'd')
                outputStream.writeDouble(((InstructionArgument<Double>) instruction).getInstructionArguments());
            else if(instructionOpCode.name().charAt(0) == 's')
                outputStream.writeChars(((InstructionArgument<String>) instruction).getInstructionArguments());
            else if(instructionOpCode.name().charAt(0) == 'b')
                outputStream.writeBoolean(((InstructionArgument<Boolean>) instruction).getInstructionArguments());
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