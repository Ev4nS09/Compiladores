
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;
import java.util.logging.Handler;

import Antlr.*;

public class solCompiler extends SolBaseVisitor<Void>
{

    private final LinkedList<Instruction> instructions;
    private final ConstantPool<Value> pool;
    private ParseTreeProperty<Class<?>> types;

    public solCompiler()
    {
        this.instructions = new LinkedList<>();
        this.pool = new ConstantPool<>();
        this.types = new ParseTreeProperty<>();
    }

    private Class<?> mergeTypes(Class<?> left, Class<?> right)
    {
        Class<?> mergedType = null;

        if(left == String.class || right == String.class)
            mergedType = String.class;

        else if(left == double.class || right == double.class)
            mergedType = double.class;

        else if(left == int.class || right == int.class)
            mergedType = int.class;

        else if(left == boolean.class || right == boolean.class)
            mergedType = boolean.class;

        return mergedType;
    }

    private void possibleConversion(Class<?> parentType, ParseTree child)
    {
        visit(child);

        OpCode code = null;
        Class<?> childType = this.types.get(child);

        if(parentType == String.class && childType == int.class)
            code = OpCode.itos;
        else if(parentType == String.class && childType == double.class)
            code = OpCode.dtos;
        else if(parentType == String.class && childType == boolean.class)
            code = OpCode.btos;
        else if(parentType == double.class && childType == int.class)
            code = OpCode.itod;

        if(code != null)
            this.instructions.add(new Instruction(code));
    }
    @Override
    public Void visitAddSub(SolParser.AddSubContext ctx)
    {
        Class<?> currentNodeType = this.types.get(ctx);

        possibleConversion(currentNodeType, ctx.expression(0)); //Left
        possibleConversion(currentNodeType, ctx.expression(1)); //Right

        if(currentNodeType == String.class)
            this.instructions.add(new Instruction(OpCode.sadd));

        else if(currentNodeType == int.class)
            this.instructions.add(new Instruction(ctx.op.getText().equals("+") ? OpCode.iadd : OpCode.isub));

        else if(currentNodeType == double.class)
            this.instructions.add(new Instruction(ctx.op.getText().equals("+") ? OpCode.dadd : OpCode.dsub));

        return null;
    }


    @Override
    public Void visitMultDivMod(SolParser.MultDivModContext ctx)
    {
        Class<?> currentNodeType = this.types.get(ctx);

        possibleConversion(currentNodeType, ctx.expression(0)); //Left
        possibleConversion(currentNodeType, ctx.expression(1)); //Right

        if(ctx.op.getText().equals("*"))
            this.instructions.add(new Instruction(currentNodeType == int.class ? OpCode.imult : OpCode.dmult));

        else if(ctx.op.getText().equals("/"))
            this.instructions.add(new Instruction(currentNodeType == int.class ? OpCode.idiv : OpCode.ddiv));

        else if(ctx.op.getText().equals("%"))
            this.instructions.add(new Instruction(OpCode.imod));

        return null;
    }

    @Override
    public Void visitUnary(SolParser.UnaryContext ctx)
    {
        Class<?> currentNodeType = this.types.get(ctx);

        possibleConversion(currentNodeType, ctx.expression());

        if(ctx.op.getText().equals("-"))
            this.instructions.add(new Instruction(currentNodeType == int.class ? OpCode.iuminus : OpCode.duminus));

        else if(ctx.op.getText().equals("not"))
            this.instructions.add(new Instruction(OpCode.not));

        return null;
    }

    @Override
    public Void visitAnd(SolParser.AndContext ctx)
    {
        Class<?> currentNodeType = this.types.get(ctx);

        visit(ctx.expression(0)); //Left
        visit(ctx.expression(1)); //Right

        this.instructions.add(new Instruction(OpCode.and));

        return null;
    }

    @Override
    public Void visitOr(SolParser.OrContext ctx)
    {
        Class<?> currentNodeType = this.types.get(ctx);

        visit(ctx.expression(0)); //Left
        visit(ctx.expression(1)); //Right

        this.instructions.add(new Instruction(OpCode.or));

        return null;
    }

    @Override
    public Void visitRelational(SolParser.RelationalContext ctx)
    {
        Class<?> mergedNodeType = mergeTypes(this.types.get(ctx.expression(0)), this.types.get(ctx.expression(1)));

        if(ctx.op.getText().equals("<"))
        {
            possibleConversion(mergedNodeType, ctx.expression(0)); //Left
            possibleConversion(mergedNodeType, ctx.expression(1)); //Right
            this.instructions.add(new Instruction(mergedNodeType == int.class ?  OpCode.ilt : OpCode.dlt));
        }
        else if(ctx.op.getText().equals("<="))
        {
            possibleConversion(mergedNodeType, ctx.expression(0)); //Left
            possibleConversion(mergedNodeType, ctx.expression(1)); //Right
            this.instructions.add(new Instruction(mergedNodeType == int.class ?  OpCode.ileq : OpCode.dleq));
        }
        else if(ctx.op.getText().equals(">"))
        {
            possibleConversion(mergedNodeType, ctx.expression(1)); //Right
            possibleConversion(mergedNodeType, ctx.expression(0)); //Left
            this.instructions.add(new Instruction(mergedNodeType == int.class ?  OpCode.ilt : OpCode.dlt));
        }
        else
        {
            possibleConversion(mergedNodeType, ctx.expression(1)); //Right
            possibleConversion(mergedNodeType, ctx.expression(0)); //Left
            this.instructions.add(new Instruction(mergedNodeType == int.class ?  OpCode.ileq : OpCode.dleq));
        }

        return null;
    }

    @Override
    public Void visitIguality(SolParser.IgualityContext ctx)
    {
        Class<?> mergedNodeType = mergeTypes(this.types.get(ctx.expression(0)), this.types.get(ctx.expression(1)));

        possibleConversion(mergedNodeType, ctx.expression(0)); //Left
        possibleConversion(mergedNodeType, ctx.expression(1)); //Right

        if(mergedNodeType == String.class)
            this.instructions.add(new Instruction(ctx.op.getText().equals("==") ? OpCode.seq : OpCode.sneq));

        else if(mergedNodeType == int.class)
            this.instructions.add(new Instruction(ctx.op.getText().equals("==") ? OpCode.ieq : OpCode.ineq));

        else if(mergedNodeType == double.class)
            this.instructions.add(new Instruction(ctx.op.getText().equals("==") ? OpCode.deq : OpCode.dneq));

        else if(mergedNodeType == boolean.class)
            this.instructions.add(new Instruction(ctx.op.getText().equals("==") ? OpCode.beq : OpCode.bneq));

        return null;
    }

    @Override
    public Void visitInt(SolParser.IntContext ctx)
    {
        int integer = Integer.parseInt(ctx.INT().getText());
        this.instructions.add(new Instruction(OpCode.iconst, new Value(integer)));

        return null;
    }

    @Override
    public Void visitDouble(SolParser.DoubleContext ctx)
    {
        Value real = new Value(Double.parseDouble(ctx.DOUBLE().getText()));

        this.pool.add(real);
        this.instructions.add(new Instruction(OpCode.dconst, new Value(this.pool.getPoolPosition(real))));

        return null;
    }

    @Override
    public Void visitString(SolParser.StringContext ctx)
    {
        Value string = new Value(ctx.STRING().getText());

        this.pool.add(string);
        this.instructions.add(new Instruction(OpCode.dconst, new Value(this.pool.getPoolPosition(string))));

        return null;
    }

    @Override
    public Void visitBool(SolParser.BoolContext ctx)
    {
        boolean bool = Boolean.parseBoolean(ctx.BOOL().getText());

        if(bool)
            this.instructions.add(new Instruction(OpCode.tconst));

        else
            this.instructions.add(new Instruction(OpCode.fconst));

        return null;
    }

    @Override
    public Void visitInstruction(SolParser.InstructionContext ctx)
    {
        visit(ctx.expression());

        Class<?> type = this.types.get(ctx);

        if(type == int.class)
            this.instructions.add(new Instruction(OpCode.iprint));

        else if(type == double.class)
            this.instructions.add(new Instruction(OpCode.dprint));

        else if(type == String.class)
            this.instructions.add(new Instruction(OpCode.sprint));

        else if(type == boolean.class)
            this.instructions.add(new Instruction(OpCode.bprint));

        return null;
    }


    private SolParser generateParser(InputStream inputStream) throws Exception{

        SolLexer lexer = new SolLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        return new SolParser(tokens);
    }

    private void generateByteCode(LinkedList<Instruction> instructions, LinkedList<Value> constantPool, String outputFile) throws Exception
    {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));

        outputStream.writeInt(constantPool.size());

        //Generates Bytes for constant pool
        for(Value value : constantPool)
        {
            if(value.getValueType() == Double.class)
            {
                outputStream.writeByte(OpCode.dconst.ordinal());
                outputStream.writeDouble(value.getDouble());
            }

            else if(value.getValueType() == String.class)
            {
                outputStream.writeByte(OpCode.sconst.ordinal());
                outputStream.writeInt(value.getString().length());
                outputStream.writeChars(value.getString());
            }
        }

        //Generates bytes for instructions
        for (Instruction instruction : instructions)
        {
            outputStream.writeByte(instruction.getInstruction().ordinal());

            if(instruction.hasArgument())
            {
                outputStream.writeInt(instruction.getArgument().getInteger());
            }

        }

        outputStream.writeByte(OpCode.halt.ordinal());
        outputStream.close();
    }

    private void asm()
    {
        LinkedList<Value> pool = this.pool.getValueList();

        System.out.println("Constant Pool");

        for(int i = 0; i < pool.size(); i++)
        {
            Value value = pool.get(i);
            System.out.println(i + ": " + value);
        }

        System.out.println("\nGenerated code in text format");
        for(int i = 0; i < instructions.size(); i++)
        {
            Instruction instruction = instructions.get(i);
            System.out.println(i + ": " + instruction);
        }
    }

    public void compile(String inputFile, String outputFile, boolean asm) throws Exception
    {
        InputStream inputStream = inputFile == null ? System.in : new FileInputStream(inputFile);
        SolParser parser = generateParser(inputStream);
        ParseTree tree = parser.sol();

        //Checks if there is syntax errors, if yes it exits the program
        if(parser.getNumberOfSyntaxErrors() > 0)
            System.exit(1);

        //Annotates and saves the types.
        TypeRecord typeRecord = new TypeRecord();
        this.types = typeRecord.getTypes(tree);

        //Iterate through the tree
        this.visit(tree);

        //Checks if type errors existed, if yes it exits the program
        if(typeRecord.getNumberOfErrors() > 0)
        {
            System.err.println(inputFile + " has " + typeRecord.getNumberOfErrors() + " type cheking errors");
            System.exit(1);
        }

        if(asm)
            asm();

        System.out.println("\nSaving the bytecodes to " + outputFile);

        //generates the bytecode file of the compiled program
        this.generateByteCode(this.instructions, this.pool.getValueList(), outputFile);
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
        if(args.length > 2)
        {
            ErrorHandler.throwError("Too many Program arguments. tVM [OPTION] [FILE]");
        }

        String inputFile = null;
        boolean asm = false;

        for (String arg : args)
        {

            if (arg.equals("-asm") || arg.equals("-a"))
                asm = true;
            else
                inputFile = arg;
        }

        if (inputFile == null)
        {
            inputFile = "inputs/input.sol";
            File file = new File(inputFile);
            FileWriter writer = new FileWriter(inputFile);
            writer.write(readInput());
            writer.close();
        }

        if(!new File(inputFile).exists())
        {
            ErrorHandler.throwError("File " + inputFile + " does not exist." );
        }

        if (!inputFile.split("\\.")[1].equals("sol"))
        {
            ErrorHandler.throwError("Invalid file extension, File must have the extension sol.");
        }

        String outputFile = inputFile.split("\\.")[0].concat(".tbc");

        solCompiler compiler = new solCompiler();
        compiler.compile(inputFile, outputFile, asm);

    }

}