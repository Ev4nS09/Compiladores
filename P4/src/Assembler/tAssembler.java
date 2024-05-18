package Assembler;

import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;

import Antlr.*;
import solUtils.ConstantPool;
import solUtils.Instruction;
import solUtils.OpCode;
import solUtils.Value;
import SemanticChecker.TagRecord;

public class tAssembler extends TasmBaseListener
{
    private final LinkedList<Instruction> instructions;
    private final ConstantPool<Value> constantPool;
    private HashMap<String, Integer> tagCache;
    private ErrorLog errorLog;

    public tAssembler()
    {
        this.instructions = new LinkedList<>();
        this.constantPool = new ConstantPool<>();
        this.tagCache = new HashMap<>();
        this.errorLog = new ErrorLog();
    }


    public void exitIconst(TasmParser.IconstContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.iconst, new Value(Integer.parseInt(ctx.INT().getText()))));
    }

    public void exitDconst(TasmParser.DconstContext ctx)
    {
        Double number = Double.valueOf(ctx.DOUBLE() != null ? ctx.DOUBLE().getText() : ctx.INT().getText());
        this.constantPool.add(new Value(number));

        Value poolPositionValue = new Value(this.constantPool.getPoolPosition(new Value(number)));
        this.instructions.add(new Instruction(OpCode.dconst, poolPositionValue));
    }


    public void exitSconst(TasmParser.SconstContext ctx)
    {
        String string = ctx.STRING().getText();
        this.constantPool.add(new Value(string));

        Value poolPositionValue = new Value(this.constantPool.getPoolPosition(new Value(string)));
        this.instructions.add(new Instruction(OpCode.sconst, poolPositionValue));
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
        this.instructions.add(new Instruction(OpCode.valueOf(ctx.alloc.getText()),
                                new Value(Integer.parseInt(ctx.INT().getText())))
        );
    }

    @Override
    public void exitLocal(TasmParser.LocalContext ctx)
    {
        int argument = Integer.parseInt(ctx.INT().getText());

        if(argument < 0 && ctx.alloc.getText().equals("lalloc"))
            this.errorLog.throwError(ctx, " Cannot allocate negative memory");

        this.instructions.add(new Instruction(OpCode.valueOf(ctx.alloc.getText()), new Value(argument)));
    }

    @Override
    public void exitCall(TasmParser.CallContext ctx)
    {
        if(!this.tagCache.containsKey(ctx.TAG().getText()))
            this.errorLog.throwError(ctx, "Tag '" + ctx.TAG().getText() + "' has not been defined.");


        Integer line = this.tagCache.get(ctx.TAG().getText());
        this.instructions.add(new Instruction(OpCode.call, new Value(line)));
    }

    @Override
    public void exitReturn(TasmParser.ReturnContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.valueOf(ctx.ret.getText()),
                new Value(Integer.parseInt(ctx.INT().getText())))
        );
    }

    @Override
    public void exitPop(TasmParser.PopContext ctx)
    {
        this.instructions.add(new Instruction(OpCode.pop, new Value(Integer.parseInt(ctx.INT().getText()))));
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
            this.errorLog.throwError(ctx, "Tag '" + ctx.TAG().getText() + "' has not been defined.");
        else
            this.instructions.add(new Instruction(OpCode.valueOf(ctx.jp.getText()), new Value(line)));
    }

    @Override
    public void exitInstruction(TasmParser.InstructionContext ctx)
    {
        if(ctx.HALT() != null)
            this.instructions.add(new Instruction(OpCode.halt));
    }


      @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < this.instructions.size(); i++)
            result.append(i).append(": ").append(this.instructions.get(i).toString()).append('\n');

        return result.toString();
    }

    private TasmParser generateParser(InputStream inputStream) throws Exception{

        TasmLexer lexer = new TasmLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        return new TasmParser(tokens);
    }

    private void generateByteCode(LinkedList<Instruction> instructions, LinkedList<Value> constantPool, String outputFile) throws Exception
    {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));

        outputStream.writeInt(constantPool.size());

        //Generates Bytes for constant pool
        for(Value value: constantPool)
        {
            OpCode instructionOpCode = value.getValueType() == Double.class ? OpCode.dconst : OpCode.sconst;
            outputStream.writeByte(instructionOpCode.ordinal());

            if(instructionOpCode == OpCode.dconst)
            {
                outputStream.writeDouble(value.getDouble());
            }

            else if(instructionOpCode == OpCode.sconst)
            {
                String argument = value.getString();
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

        TagRecord tagRecord = new TagRecord(this.errorLog);

        this.tagCache = tagRecord.getTags(tree);
        walker.walk(this, tree);

        if(this.errorLog.getNumberOfErrors() > 0)
            ErrorLog.fatalError(inputFile + " has " + this.errorLog.getNumberOfErrors() + " type cheking errors");

        this.generateByteCode(this.instructions, this.constantPool.getValueList(), outputFile);
    }

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
           ErrorLog.fatalError("Too many Program arguments. The arguments must the file you want to compile.");
        }

        String inputFile = null;

        if(args.length == 1)
        {
            inputFile = args[0];
        }

        if (inputFile == null)
        {
            inputFile = "inputs/input.tasm";
            FileWriter writer = new FileWriter(inputFile);
            writer.write(readInput());
            writer.close();
        }

        if (!inputFile.split("\\.")[1].equals("tasm"))
        {
            ErrorLog.fatalError("Invalid file extension, File must have the extension tasm.");
        }

        String outputFile = inputFile.split("\\.")[0].concat(".tbc");

        tAssembler compiler = new tAssembler();
        compiler.compile(inputFile, outputFile);


        System.out.println("Program compiled successfully.");
    }


}