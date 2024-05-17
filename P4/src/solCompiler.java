import Antlr.SolBaseVisitor;
import Antlr.SolLexer;
import Antlr.SolParser;
import ErrorHandler.ErrorLog;
import com.sun.source.tree.PackageTree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class solCompiler extends SolBaseVisitor<Void>
{

    private final LinkedList<Instruction> instructions;
    private final ConstantPool<Value> pool;
    private ParseTreeProperty<HashMap<String, Variable>> scopeVariableCache;
    private ParseTreeProperty<Class<?>> types;
    private HashMap<String, Function> functionCache;
    private HashMap<String, Integer> functionPosition;
    private HashMap<String, Integer> getFunctionPositionWaitList;
    private int globalMemoryPointer;
    private int localMemoryPointer;


    public solCompiler()
    {
        this.instructions = new LinkedList<>();
        this.pool = new ConstantPool<>();
        this.scopeVariableCache = new ParseTreeProperty<>();
        this.types = new ParseTreeProperty<>();
        this.functionCache = new HashMap<>();
        this.functionPosition = new HashMap<>();
        this.getFunctionPositionWaitList = new HashMap<>();
        this.globalMemoryPointer = 0;
        this.localMemoryPointer = 2;
    }

    private SolParser.ScopeContext getScope(RuleContext ctx)
    {
        while(ctx != null && !(ctx instanceof SolParser.ScopeContext))
            ctx = ctx.parent;

        return ctx != null ? (SolParser.ScopeContext) ctx : null;
    }

    private boolean isGlobal(RuleContext currentNode)
    {
        while(currentNode != null && !(currentNode instanceof SolParser.ScopeContext))
            currentNode = currentNode.parent;

        return currentNode == null;
    }

    private OpCode returnGlobalAllocCode(boolean isGlobal)
    {
        return isGlobal ? OpCode.galloc : OpCode.lalloc;
    }

    private OpCode returnGlobalLoadCode(boolean isGlobal)
    {
        return isGlobal ? OpCode.gload : OpCode.lload;
    }

    private OpCode returnGlobalStoreCode(boolean isGlobal)
    {
        return isGlobal ? OpCode.gstore : OpCode.lstore;
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

    private void possibleConversionWithTypes(Class<?> parentType, Class<?> childType)
    {
        OpCode code = null;

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

    private void possibleConversion(Class<?> parentType, ParseTree child)
    {
        visit(child);
        Class<?> childType = this.types.get(child);
        possibleConversionWithTypes(parentType, childType);
    }

    @Override
    public Void visitSol(SolParser.SolContext ctx)
    {
        if(!ctx.declaration().isEmpty())
            this.instructions.add(new Instruction(OpCode.galloc, new Value(ctx.declaration().size())));

        for(SolParser.DeclarationContext declaration : ctx.declaration())
            visit(declaration);

        for(SolParser.FunctionContext function : ctx.function())
            visit(function);

        return null;
    }

    @Override
    public Void visitBreak(SolParser.BreakContext ctx)
    {
        this.instructions.add(null);

        return null;
    }

    @Override
    public Void visitFor(SolParser.ForContext ctx)
    {
        Variable affectationValue = this.scopeVariableCache.get(ctx).get(ctx.affectation().LABEL().getText());
        OpCode store = returnGlobalStoreCode(affectationValue.isGlobal);
        OpCode load = returnGlobalLoadCode(affectationValue.isGlobal);

        Integer affectationLabelPosition = affectationValue.memoryValue;

        visit(ctx.affectation());
        int beginLoop = this.instructions.size();

        //The following code just creates an expression just like a while but just uses the symbol '<' per example
        // for i = 1 to 10 equals -> while i < 10
        this.instructions.add(new Instruction(load, new Value(affectationLabelPosition)));
        visit(ctx.expression());
        this.instructions.add(new Instruction(OpCode.ileq));

        int jumpInstructionIndex = this.instructions.size();
        this.instructions.add(null);

        //The following code just sums 1 to the affection variable
        visit(ctx.line());
        this.instructions.add(new Instruction(OpCode.iconst, new Value(1)));
        this.instructions.add(new Instruction(load, new Value(affectationLabelPosition)));
        this.instructions.add(new Instruction(OpCode.iadd));
        this.instructions.add(new Instruction(store, new Value(affectationLabelPosition)));

        //the following code just sets the jumps, if we are at the end of the loop we go to the beginning
        //if the affection equals the expression we leave the loop
        this.instructions.add(new Instruction(OpCode.jump, new Value(beginLoop)));
        this.instructions.set(jumpInstructionIndex, new Instruction(OpCode.jumpf, new Value(this.instructions.size())));

        //Searches for positions to put a jump that was the beak
        for(int i = beginLoop; i < this.instructions.size(); i++)
            if(this.instructions.get(i) == null)
                this.instructions.set(i, new Instruction(OpCode.jump, new Value(this.instructions.size())));


        return null;
    }

    @Override
    public Void visitWhile(SolParser.WhileContext ctx)
    {
        int beginLoop = this.instructions.size();
        visit(ctx.expression());

        int jumpInstructionIndex = this.instructions.size();
        this.instructions.add(null);

        visit(ctx.line());
        this.instructions.add(new Instruction(OpCode.jump, new Value(beginLoop)));
        this.instructions.set(jumpInstructionIndex, new Instruction(OpCode.jumpf, new Value(this.instructions.size())));

        //Searches for positions to put a jump that was the beak
        for(int i = beginLoop; i < this.instructions.size(); i++)
            if(this.instructions.get(i) == null)
                this.instructions.set(i, new Instruction(OpCode.jump, new Value(this.instructions.size())));

        return null;
    }

    @Override
    public Void visitIf(SolParser.IfContext ctx)
    {
        visit(ctx.expression());
        int jumpInstructionIndex = this.instructions.size();
        this.instructions.add(null);

        visit(ctx.line(0));
        int afterIfIndex =this.instructions.size();
        this.instructions.set(jumpInstructionIndex, new Instruction(OpCode.jumpf, new Value(afterIfIndex)));

        //else exists
        if(ctx.line().size() > 1)
        {
            this.instructions.set(jumpInstructionIndex, new Instruction(OpCode.jumpf, new Value(afterIfIndex+1)));
            this.instructions.add(null);
            visit(ctx.line(1));
            int afterElseIndex = this.instructions.size();

            this.instructions.set(afterIfIndex, new Instruction(OpCode.jump, new Value(afterElseIndex)));
        }

        return null;
    }

    @Override
    public Void visitScope(SolParser.ScopeContext ctx)
    {
       visit(ctx.block());

       if(!(ctx.parent instanceof SolParser.FunctionContext))
       {
           this.instructions.add(new Instruction(OpCode.pop, new Value(ctx.block().declaration().size())));
           this.localMemoryPointer -= ctx.block().declaration().size();
       }

       return null;
    }

    @Override
    public Void visitBlock(SolParser.BlockContext ctx)
    {
        if(!ctx.declaration().isEmpty())
            this.instructions.add(new Instruction(OpCode.lalloc, new Value(ctx.declaration().size())));

        for(int i = 0; i < ctx.declaration().size(); i++)
            visit(ctx.declaration(i));

        for(int i = 0; i < ctx.line().size(); i++)
            visit(ctx.line(i));

        return null;
    }

    @Override
    public Void visitAffectation(SolParser.AffectationContext ctx)
    {
        boolean isGlobal = isGlobal(ctx);

        possibleConversion(this.types.get(ctx), ctx.expression());
        this.instructions.add(new Instruction(returnGlobalStoreCode(isGlobal), new Value(
                this.scopeVariableCache.get(getScope(ctx)).get(ctx.LABEL().getText()).memoryValue
                )));

        return null;
    }

    @Override
    public Void visitLabelExpression(SolParser.LabelExpressionContext ctx)
    {
       boolean isGlobal = isGlobal(ctx);
       int pointer = isGlobal ? this.globalMemoryPointer : this.localMemoryPointer;

        if(ctx.expression() != null)
        {
            visit(ctx.expression());
            this.instructions.add(new Instruction(returnGlobalStoreCode(isGlobal), new Value(pointer)));
        }

        System.out.println(ctx.LABEL().getText());

        this.scopeVariableCache.get(getScope(ctx)).get(ctx.LABEL().getText()).memoryValue = pointer;
        this.scopeVariableCache.get(getScope(ctx)).get(ctx.LABEL().getText()).isGlobal = isGlobal;

        if(isGlobal)
            this.globalMemoryPointer++;
        else
            this.localMemoryPointer++;

        return null;
    }


    @Override
    public Void visitDeclaration(SolParser.DeclarationContext ctx)
    {
        for(int i = 0; i < ctx.labelExpression().size(); i++)
           visit(ctx.labelExpression(i));

        return null;
    }

    @Override
    public Void visitFunction(SolParser.FunctionContext ctx)
    {
        for(int i = 1; i < ctx.LABEL().size(); i++)
            this.scopeVariableCache.get(ctx.scope()).get(ctx.LABEL(i).getText()).memoryValue = -(ctx.LABEL().size()  - i);

        if(this.getFunctionPositionWaitList.get(ctx.fname.getText()) != null)
            this.instructions.set(this.getFunctionPositionWaitList.get(ctx.fname.getText()), new Instruction(OpCode.call, new Value(this.instructions.size())));

        this.functionPosition.put(ctx.fname.getText(), this.instructions.size());
        visit(ctx.scope());

        Function thisFunction = this.functionCache.get(ctx.fname.getText());

        if(thisFunction.returnType() == void.class)
            this.instructions.add(new Instruction(OpCode.ret, new Value(thisFunction.numberOfArgs())));
        else
            this.instructions.add(new Instruction(OpCode.retval, new Value((thisFunction.numberOfArgs()))));





        return null;
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
        else if(ctx.op.getText().equals(">="))
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
    public Void visitLabel(SolParser.LabelContext ctx)
    {
        Variable labelVariable = this.scopeVariableCache.get(getScope(ctx)).get(ctx.LABEL().getText());

        this.instructions.add(new Instruction(
                returnGlobalLoadCode(labelVariable.isGlobal), new Value(labelVariable.memoryValue))
        );

        return null;
    }

    @Override
    public Void visitFunctionCall(SolParser.FunctionCallContext ctx)
    {
        for(SolParser.ExpressionContext expression : ctx.expression())
            visit(expression);

        Integer functionPosition = this.functionPosition.get(ctx.fname.getText());

        if(functionPosition == null)
            this.getFunctionPositionWaitList.put(ctx.fname.getText(), this.instructions.size());

        this.instructions.add(new Instruction(OpCode.call, new Value(functionPosition != null ? functionPosition : -1)));

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
        this.instructions.add(new Instruction(OpCode.sconst, new Value(this.pool.getPoolPosition(string))));

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

        ErrorLog errorLog = new ErrorLog();

        //Annotates and saves the types.
        SemanticChecker semanticChecker = new SemanticChecker(errorLog);
        semanticChecker.semanticCheckTree(tree);

        this.types = semanticChecker.getTypes();
        this.scopeVariableCache = semanticChecker.getScopeVariables();
        this.functionCache = semanticChecker.getFunctions();

        //Checks if type errors existed, if yes it exits the program
        if(errorLog.getNumberOfErrors() > 0)
        {
            System.err.println(inputFile + " has " + errorLog.getNumberOfErrors() + " type checking errors");
            System.exit(1);
        }


        this.instructions.add(new Instruction(OpCode.call, new Value(-1)));
        this.instructions.add(new Instruction(OpCode.halt));
        //Iterate through the tree and creates the instructions
        this.visit(tree);

        this.instructions.set(0, new Instruction(OpCode.call, new Value(this.functionPosition.get("main"))));

        System.out.println(instructions);

        if(asm)
            asm();
        System.out.println("\nSaving the bytecodes to " + outputFile);


        //generates the bytecode file of the compiled program
        this.generateByteCode(this.instructions, this.pool.getValueList(), outputFile);

        new tasmGenerator(this.instructions, this.pool, inputFile.split("\\.")[0] + ".tasm");
    }

    private static String readInput()
    {
        String result = "";
        Scanner scanner = new Scanner(System.in)
        ;

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
            ErrorLog.fatalError("Too many Program arguments. tVM [OPTION] [FILE]");
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
            new ErrorLog().fatalError("File " + inputFile + " does not exist." );
        }

        if (!inputFile.split("\\.")[1].equals("sol"))
        {
            new ErrorLog().fatalError("Invalid file extension, File must have the extension sol.");
        }

        String outputFile = inputFile.split("\\.")[0].concat(".tbc");
        solCompiler compiler = new solCompiler();
        compiler.compile(inputFile, outputFile, asm);
    }

}