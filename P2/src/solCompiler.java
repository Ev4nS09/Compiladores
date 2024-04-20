
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;

import Antlr.*;

public class solCompiler
{
    private static class Visitor extends SolBaseVisitor<Class<?>>
    {
        protected final LinkedList<Instruction> instructions;
        protected final ConstantPool pool;
        protected final ParseTreeProperty<Class<?>> types;

        public Visitor(ParseTreeProperty<Class<?>> types)
        {
            this.instructions = new LinkedList<>();
            this.pool = new ConstantPool();
            this.types = types;
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

        private OpCode stringConversion(Class<?> type)
        {
            OpCode result = null;

            if(type == int.class)
                result = OpCode.itos;
            else if(type == double.class)
                result = OpCode.dtos;
            else if(type == boolean.class)
                result = OpCode.btos;

            return result;
        }

        private OpCode doubleConversion(Class<?> type)
        {
            OpCode result = null;

            if(type == int.class)
                result = OpCode.itod;

            return result;
        }


        private void possibleConversion(Class<?> parentType, ParseTree child)
        {
            visit(child);

            OpCode code = null;
            Class<?> childType = this.types.get(child);

            if(parentType == String.class)
                code = stringConversion(childType);
            else if(parentType == double.class)
                code = doubleConversion(childType);

            if(code != null)
                this.instructions.add(new Instruction(code));
        }
        @Override
        public Class<?> visitAddSub(SolParser.AddSubContext ctx)
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
        public Class<?> visitMultDivMod(SolParser.MultDivModContext ctx)
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
        public Class<?> visitUnary(SolParser.UnaryContext ctx)
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
        public Class<?> visitAnd(SolParser.AndContext ctx)
        {
            Class<?> currentNodeType = this.types.get(ctx);

            possibleConversion(currentNodeType, ctx.expression(0)); //Left
            possibleConversion(currentNodeType, ctx.expression(1)); //Right

            this.instructions.add(new Instruction(OpCode.and));

            return boolean.class;
        }

        @Override
        public Class<?> visitOr(SolParser.OrContext ctx)
        {
            Class<?> currentNodeType = this.types.get(ctx);

            possibleConversion(currentNodeType, ctx.expression(0)); //Left
            possibleConversion(currentNodeType, ctx.expression(1)); //Right

            this.instructions.add(new Instruction(OpCode.or));

            return boolean.class;
        }

        @Override
        public Class<?> visitRelational(SolParser.RelationalContext ctx)
        {
            Class<?> currentNodeType = mergeTypes(this.types.get(ctx.expression(0)), this.types.get(ctx.expression(1)));

            possibleConversion(currentNodeType, ctx.expression(0)); //Left
            possibleConversion(currentNodeType, ctx.expression(1)); //Right

            if(ctx.op.getText().equals("<"))
            {
                this.instructions.add(new Instruction(currentNodeType == int.class ?  OpCode.ilt : OpCode.dlt));
            }
            else if(ctx.op.getText().equals("<="))
            {
                this.instructions.add(new Instruction(currentNodeType == int.class ?  OpCode.ileq : OpCode.dleq));
            }
            else if(ctx.op.getText().equals(">"))
            {
                this.instructions.add(new Instruction(currentNodeType == int.class ?  OpCode.ileq : OpCode.dleq));
                this.instructions.add(new Instruction(OpCode.not));
            }
            else
            {
                this.instructions.add(new Instruction(currentNodeType == int.class ?  OpCode.ilt : OpCode.dlt));
                this.instructions.add(new Instruction(OpCode.not));
            }

            return null;
        }

        @Override
        public Class<?> visitIguality(SolParser.IgualityContext ctx)
        {
            Class<?> currentNodeType = mergeTypes(this.types.get(ctx.expression(0)), this.types.get(ctx.expression(1)));

            possibleConversion(currentNodeType, ctx.expression(0)); //Left
            possibleConversion(currentNodeType, ctx.expression(1)); //Right

            if(currentNodeType == String.class)
                this.instructions.add(new Instruction(ctx.op.getText().equals("==") ? OpCode.seq : OpCode.sneq));
            else if(currentNodeType == int.class)
                this.instructions.add(new Instruction(ctx.op.getText().equals("==") ? OpCode.ieq : OpCode.ineq));
            else if(currentNodeType == double.class)
                this.instructions.add(new Instruction(ctx.op.getText().equals("==") ? OpCode.deq : OpCode.dneq));
            else if(currentNodeType == boolean.class)
                this.instructions.add(new Instruction(ctx.op.getText().equals("==") ? OpCode.beq : OpCode.bneq));

            return null;
        }

        @Override
        public Class<?> visitInt(SolParser.IntContext ctx)
        {
            int integer = Integer.parseInt(ctx.INT().getText());
            this.instructions.add(new Instruction(OpCode.iconst, new Value(integer)));

            return null;
        }

        @Override
        public Class<?> visitDouble(SolParser.DoubleContext ctx)
        {
            Value real = new Value(Double.parseDouble(ctx.DOUBLE().getText()));

            this.pool.add(real);
            this.instructions.add(new Instruction(OpCode.dconst, new Value(this.pool.getPoolPosition(real))));

            return null;
        }

        @Override
        public Class<?> visitString(SolParser.StringContext ctx)
        {
            Value string = new Value(ctx.STRING().getText());

            this.pool.add(string);
            this.instructions.add(new Instruction(OpCode.dconst, new Value(this.pool.getPoolPosition(string))));

            return null;
        }

        @Override
        public Class<?> visitBool(SolParser.BoolContext ctx)
        {
            boolean bool = Boolean.parseBoolean(ctx.BOOL().getText());

            if(bool)
                this.instructions.add(new Instruction(OpCode.tconst));

            else
                this.instructions.add(new Instruction(OpCode.fconst));

            return null;
        }

        @Override
        public Class<?> visitInstruction(SolParser.InstructionContext ctx)
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

        @Override
        public String toString()
        {
            StringBuilder result = new StringBuilder();

            for(int i = 0; i < this.instructions.size(); i++)
                result.append(i).append(": ").append(this.instructions.get(i).toString()).append('\n');

            return result.toString();
        }
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

    public void compile(String inputFile, String outputFile) throws Exception
    {
        InputStream inputStream = inputFile == null ? System.in : new FileInputStream(inputFile);

        SolParser parser = generateParser(inputStream);

        ParseTree tree = parser.sol();

        if(parser.getNumberOfSyntaxErrors() > 0)
            System.exit(1);


        ParseTreeProperty<Class<?>> types = new TypeRecord().getTypes(tree);

        Visitor visitor = new Visitor(types);
        visitor.visit(tree);

        System.out.println(visitor.instructions);

        this.generateByteCode(visitor.instructions, visitor.pool.getValueList(), outputFile);

    }
}