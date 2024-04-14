
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

import Antlr.*;

public class tAssembler
{
    private static class Listener extends TasmBaseListener
    {

        protected final LinkedList<Instruction> instructions;
        protected final LinkedList<Instruction> constantPool;

        protected final HashMap<String, Integer> tagCache;
        protected final HashMap<String, Integer> tagWaitList;
        protected final HashMap<Object, Integer> constantPoolCache;

        public Listener()
        {
            this.instructions = new LinkedList<>();
            this.constantPool = new LinkedList<>();
            this.constantPoolCache = new HashMap<>();
            this.tagCache = new HashMap<>();
            this.tagWaitList = new HashMap<>();
        }

        public void exitIconst(TasmParser.IconstContext ctx)
        {
            this.instructions.add(new Instruction(OpCode.iconst, new Value(Integer.parseInt(ctx.INT().getText()))));
        }

        public void exitDconst(TasmParser.DconstContext ctx)
        {
            Double number = Double.valueOf(ctx.DOUBLE() != null ? ctx.DOUBLE().getText() : ctx.INT().getText());

            if(!this.constantPoolCache.containsKey(number))
            {
                this.constantPool.add(new Instruction(OpCode.dconst, new Value(number)));
                this.constantPoolCache.put(number, this.constantPool.size()-1);
            }

            this.instructions.add(new Instruction(OpCode.dconst, new Value(this.constantPoolCache.get(number))));
        }


        public void exitSconst(TasmParser.SconstContext ctx)
        {

            String string = ctx.STRING().getText();

            if(!this.constantPoolCache.containsKey(string))
            {
                this.constantPool.add(new Instruction(OpCode.sconst, new Value(string)));
                this.constantPoolCache.put(string, this.constantPool.size()-1);
            }


            this.instructions.add(new Instruction(OpCode.sconst, new Value(this.constantPoolCache.get(string))));
        }

        public void exitTconst(TasmParser.TconstContext ctx)
        {
            this.instructions.add(new Instruction(OpCode.tconst));
        }


        public void exitFconst(TasmParser.FconstContext ctx)
        {
            this.instructions.add(new Instruction(OpCode.fconst));
        }

        public void exitGlobal(TasmParser.GlobalContext ctx)
        {
            this.instructions.add(new Instruction(OpCode.valueOf(ctx.alloc.getText()), new Value(Integer.parseInt(ctx.INT().getText()))));
        }

        public void exitConditions(TasmParser.ConditionsContext ctx)
        {
            this.instructions.add(new Instruction(OpCode.valueOf(ctx.cd.getText())));
        }

        public void exitChange(TasmParser.ChangeContext ctx)
        {
            this.instructions.add(new Instruction(OpCode.valueOf(ctx.change.getText())));
        }

        public void exitOperations(TasmParser.OperationsContext ctx)
        {
            this.instructions.add(new Instruction(OpCode.valueOf(ctx.op.getText())));
        }

        public void exitPrint(TasmParser.PrintContext ctx)
        {
            this.instructions.add(new Instruction(OpCode.valueOf(ctx.print.getText())));
        }

        public void exitJp(TasmParser.JpContext ctx)
        {
            Integer line = this.tagCache.get(ctx.tag.getText());

            if(line == null)
                this.tagWaitList.put(ctx.TAG().getText(), this.instructions.size());

            this.instructions.add(new Instruction(OpCode.valueOf(ctx.jp.getText()), new Value(line)));
        }

        public void exitTagInstruction(TasmParser.TagInstructionContext ctx)
        {
            for(int i = 0; i < ctx.TAG().size(); i++)
            {
                String tag = ctx.TAG().get(i).toString();

                if (this.tagCache.containsKey(tag))
                {
                    Flaw.Error("Label already defined");
                }

                if (this.tagWaitList.containsKey(tag))
                {
                    int index = this.tagWaitList.get(tag);
                    Instruction newInstruction = new Instruction(this.instructions.get(index).getInstruction(), new Value(this.instructions.size() - 1));
                    this.instructions.set(index, newInstruction);
                }

                this.tagCache.put(tag, this.instructions.size() - 1);
            }
        }

        public void exitTasm(TasmParser.TasmContext ctx)
        {
            this.instructions.add(new Instruction(OpCode.halt));
        }

        public LinkedList<Instruction> getInstructions()
        {
            return this.instructions;
        }

        public LinkedList<Instruction> getConstantPool()
        {
            return this.constantPool;
        }


        @Override
        public String toString()
        {
            StringBuilder result = new StringBuilder();

            for(int i = 0; i < this.instructions.size(); i++)
                result.append(i).append(": ").append(this.instructions.get(i).toString()).append('\n');

            return result.toString();
        }
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

        //Generates Bytes for constant pool
        for(Instruction instruction : constantPool)
        {
            OpCode instructionOpCode = instruction.getInstruction();
            outputStream.writeByte(instructionOpCode.ordinal());

            if(instructionOpCode == OpCode.dconst)
            {
                outputStream.writeDouble(instruction.getArgument().getDouble());
            }

            else if(instructionOpCode == OpCode.sconst)
            {
                String argument = instruction.getArgument().getString();
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
                outputStream.writeInt(instruction.getArgument().getInteger());
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

        if(parser.getNumberOfSyntaxErrors() > 0)
            System.exit(1);

        Listener listener = new Listener();
        walker.walk(listener, tree);

        this.generateByteCode(listener.getInstructions(), listener.getConstantPool(), outputFile);

    }
}