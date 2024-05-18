package SemanticChecker;

import Antlr.SolBaseListener;
import Antlr.SolParser;
import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import solUtils.*;
import java.util.HashMap;

public class VariableRecord extends SolBaseListener
{
    private ParseTreeProperty<HashMap<String, Variable>> scopeVariableCache;
    private HashMap<String, Function> functionCache;
    private ErrorLog errorLog;

    private Class<?> stringToClass(String type)
    {
        return switch (type)
        {
            case "int" -> int.class;
            case "real" -> double.class;
            case "string" -> String.class;
            case "bool" -> boolean.class;
            case "void" -> void.class;
            default -> null;
        };
    }

    public VariableRecord(HashMap<String, Function> functionCache, ErrorLog errorLog)
    {
        this.scopeVariableCache = new ParseTreeProperty<>();
        this.functionCache = functionCache;
        this.errorLog = errorLog;

        this.scopeVariableCache.put(null, new HashMap<>());
    }

    public VariableRecord()
    {
        this(new HashMap<>(), new ErrorLog());
    }

    private Variable getVariable(String variableName, SolParser.ScopeContext currentScope)
    {
        while(currentScope != null)
        {
            if(this.scopeVariableCache.get(currentScope)!= null && this.scopeVariableCache.get(currentScope).get(variableName) != null)
                break;

            currentScope = getScope(currentScope.parent);
        }

        return this.scopeVariableCache.get(currentScope).get(variableName);
    }

    private SolParser.ScopeContext getScope(RuleContext ctx)
    {
        while(ctx != null && !(ctx instanceof SolParser.ScopeContext))
            ctx = ctx.parent;

        return ctx != null ? (SolParser.ScopeContext) ctx : null;
    }

    @Override
    public void exitLabelExpression(SolParser.LabelExpressionContext ctx)
    {
        SolParser.ScopeContext currentScope = getScope(ctx);

        if (this.scopeVariableCache.get(currentScope).containsKey(ctx.LABEL().getText()))
            this.errorLog.throwError(ctx, "Utils.solUtils.Variable '" + ctx.LABEL().getText() + "' already exists");

        this.scopeVariableCache.get(currentScope).put(ctx.LABEL().getText(), new Variable(ctx.LABEL().getText()
                , stringToClass(((SolParser.DeclarationContext)ctx.parent).TYPE().getText())
                , ctx.expression() != null
        ));
    }

    @Override
    public void enterDeclaration(SolParser.DeclarationContext ctx)
    {
        SolParser.ScopeContext currentScope = getScope(ctx);

        if(this.scopeVariableCache.get(currentScope) == null)
            this.scopeVariableCache.put(currentScope, new HashMap<>());
    }

    @Override
    public void exitAffectation(SolParser.AffectationContext ctx)
    {
        SolParser.ScopeContext scope = getScope(ctx);

        if(getVariable(ctx.LABEL().getText(), scope) == null)
            this.errorLog.throwError(ctx, "Variable '" + ctx.LABEL().getText() + "' was not defined");
    }

    @Override
    public void enterFunction(SolParser.FunctionContext ctx)
    {
        HashMap<String, Variable> argumentDeclarations = new HashMap<>();

        int voidException = ctx.rtype.getText().equals("void") ? -1 : 0;

        for(int i = 1; i < ctx.LABEL().size(); i++)
        {
            if(argumentDeclarations.containsKey(ctx.LABEL(i).getText()))
                this.errorLog.throwError(ctx, "Argument '" + ctx.LABEL(i).getText() + "' was already defined");

            argumentDeclarations.put(ctx.LABEL(i).getText(), new Variable(
                    ctx.LABEL(i).getText(),
                    stringToClass(ctx.TYPE(i + voidException).getText()),
                    true,
                    0,
                    false,
                    true
            ));
        }


        this.scopeVariableCache.put(ctx.scope(), argumentDeclarations);
    }

    @Override
    public void exitLabel(SolParser.LabelContext ctx)
    {
        SolParser.ScopeContext scope = getScope(ctx);

        if(getVariable(ctx.LABEL().getText(), scope) == null)
            this.errorLog.throwError(ctx, "Variable '" + ctx.LABEL().getText() + "' was not defined.");
    }


    @Override
    public void exitFunctionCall(SolParser.FunctionCallContext ctx)
    {
        Function function = this.functionCache.get(ctx.LABEL().getText());


        if(function == null)
            this.errorLog.throwError(ctx, "Function '" + ctx.fname.getText() + "' does not exist.");
        else if(function.numberOfArgs() < ctx.expression().size())
            this.errorLog.throwError(ctx, "Too many arguments for function '" + function.name() + "'");
        else if(function.numberOfArgs() > ctx.expression().size())
            this.errorLog.throwError(ctx, "Too few arguments for function '" + function.name() + "'");


    }

    public ParseTreeProperty<HashMap<String, Variable>> getVariables(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        return this.scopeVariableCache;
    }
}
