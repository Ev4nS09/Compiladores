
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;

import Antlr.*;

public class TasmCompiler {

    private final boolean asm;

    public TasmCompiler(boolean asm){
        this.asm = asm;
    }

    public TasmCompiler(){
        this(false);
    }

    private TasmParser generateParser(InputStream inputStream) throws Exception{

        TasmLexer lexer = new TasmLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        return new TasmParser(tokens);
    }

    private void generateByteCode(LinkedList<Instruction> instructions, LinkedList<Instruction> constantPool, String outputFile) throws Exception
    {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));

        outputStream.writeInt(constantPool.size());

        for(Instruction instruction : constantPool)
        {
            OpCode instructionOpCode = instruction.getInstruction();
            outputStream.writeByte(instructionOpCode.ordinal());

            if(instructionOpCode == OpCode.dconst)
                outputStream.writeDouble((Double) instruction.getArgument());

            else if(instructionOpCode == OpCode.sconst)
            {
                String argument = (String) instruction.getArgument();
                outputStream.writeInt(argument.length());
                outputStream.writeChars(argument);
            }
        }

        for (Instruction instruction : instructions)
        {
            OpCode instructionOpCode = instruction.getInstruction();
            outputStream.writeByte(instructionOpCode.ordinal());


            if(instructionOpCode == OpCode.iconst)
                outputStream.writeInt((Integer) instruction.getArgument());

            else if(instructionOpCode == OpCode.dconst)
                outputStream.writeInt((Integer) instruction.getArgument());

            else if(instructionOpCode == OpCode.sconst)
            {
                outputStream.writeInt((Integer) instruction.getArgument());
            }

            else if(instructionOpCode == OpCode.galloc || instructionOpCode == OpCode.gload || instructionOpCode == OpCode.gstore)
                outputStream.writeInt((Integer) instruction.getArgument());

            else if(instructionOpCode == OpCode.jump || instructionOpCode == OpCode.jumpf || instructionOpCode == OpCode.jumpt)
            {
                Integer argument = (Integer) instruction.getArgument();
                outputStream.writeInt(argument);
            }
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

        TasmParser parser = generateParser(inputStream);

        ParseTree tree = parser.tasm();
        ParseTreeWalker walker = new ParseTreeWalker();

        InstructionTree instructionTree = new InstructionTree();
        walker.walk(instructionTree, tree);

        generateByteCode(instructionTree.getInstructions(), instructionTree.getConstantPool(), outputFile);

        if(this.asm)
            asm(instructionTree.getInstructions(), outputFile);

    }
}