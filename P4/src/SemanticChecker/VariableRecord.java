package SemanticChecker;

import Antlr.SolBaseListener;
import Antlr.SolParser;
import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import solUtils.*;


public class VariableRecord extends SolBaseListener
{

    private final ScopeTree scopeTree;
    private final ErrorLog errorLog;

    public VariableRecord(ErrorLog errorLog)
    {
        this.scopeTree = new ScopeTree();
        this.errorLog = errorLog;
    }

    public VariableRecord()
    {
        this(new ErrorLog());
    }

    @Override
    public void exitLabelExpression(SolParser.LabelExpressionContext ctx)
    {
        if(this.scopeTree.variableExistsInCurrentScope(ctx, ctx.LABEL().getText()))
            this.errorLog.throwError(ctx, "Variable '" + ctx.LABEL().getText() + "' already exists");


        this.scopeTree.addVariable(ctx, new Variable(ctx.LABEL().getText()
                , TypeRecord.stringToClass(((SolParser.DeclarationContext)ctx.parent).TYPE().getText())
        ));
    }

    @Override
    public void exitAffectation(SolParser.AffectationContext ctx)
    {
        if(!this.scopeTree.variableExists(ctx, ctx.LABEL().getText()))
            this.errorLog.throwError(ctx, "Variable '" + ctx.LABEL().getText() + "' was not defined");
    }

    @Override
    public void enterFunction(SolParser.FunctionContext ctx)
    {
        int voidException = ctx.rtype.getText().equals("void") ? -1 : 0;

        for(int i = 1; i < ctx.LABEL().size(); i++)
        {
            if(this.scopeTree.variableExistsInCurrentScope(ctx.scope(), ctx.LABEL(i).getText()))
                this.errorLog.throwError(ctx, "Function Argument '" + ctx.LABEL(i).getText() + "' was already defined");

            this.scopeTree.addVariable(ctx, new Variable(
                    ctx.LABEL(i).getText(),
                    TypeRecord.stringToClass(ctx.TYPE(i + voidException).getText())
            ));
        }
    }

    @Override
    public void exitLabel(SolParser.LabelContext ctx)
    {
        if(!this.scopeTree.variableExists(ctx, ctx.LABEL().getText()))
            this.errorLog.throwError(ctx, "Variable '" + ctx.LABEL().getText() + "' was not defined.");
    }


    @Override
    public void exitFunctionCall(SolParser.FunctionCallContext ctx)
    {
        Function function = Function.getFunction(ctx, ctx.fname.getText());

        if(function == null)
            this.errorLog.throwError(ctx, "Function '" + ctx.fname.getText() + "' does not exist.");
        else if(function.numberOfArgs() < ctx.expression().size())
            this.errorLog.throwError(ctx, "Too many arguments for function '" + function.name() + "'");
        else if(function.numberOfArgs() > ctx.expression().size())
            this.errorLog.throwError(ctx, "Too few arguments for function '" + function.name() + "'");
    }

    public ScopeTree getScopeTree(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        return this.scopeTree;
    }
}
