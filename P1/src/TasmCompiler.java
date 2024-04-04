
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;

import Antlr.*;

public class TasmCompiler {

    private TasmParser generateParser(InputStream inputStream) throws Exception{

        TasmLexer lexer = new TasmLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        return new TasmParser(tokens);
    }

    private void generateByteCode(LinkedList<Instruction> instructions, LinkedList<Instruction> constantPool, String outputFile) throws Exception
    {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));

        outputStream.writeInt(constantPool.size());

        //Generates Bytes for constant pool
        for(Instruction instruction : constantPool)
        {
            OpCode instructionOpCode = instruction.getInstruction();
            outputStream.writeByte(instructionOpCode.ordinal());

            if(instructionOpCode == OpCode.dconst)
            {
                outputStream.writeDouble((Double) instruction.getArgument());
            }

            else if(instructionOpCode == OpCode.sconst)
            {
                String argument = (String) instruction.getArgument();
                outputStream.writeInt(argument.length());
                outputStream.writeChars(argument);
            }
        }

        //Generates bytes for instructions
        for (Instruction instruction : instructions)
        {
            OpCode instructionOpCode = instruction.getInstruction();
            outputStream.writeByte(instructionOpCode.ordinal());

            if(instruction.hasArgument())
            {
                outputStream.writeInt((Integer) instruction.getArgument());
            }

        }

        outputStream.close();
    }

    public void compile(String inputFile, String outputFile) throws Exception
    {
        InputStream inputStream = inputFile == null ? System.in : new FileInputStream(inputFile);

        TasmParser parser = generateParser(inputStream);

        ParseTree tree = parser.tasm();
        ParseTreeWalker walker = new ParseTreeWalker();

        InstructionTree instructionTree = new InstructionTree();
        walker.walk(instructionTree, tree);

        this.generateByteCode(instructionTree.getInstructions(), instructionTree.getConstantPool(), outputFile);

    }
}