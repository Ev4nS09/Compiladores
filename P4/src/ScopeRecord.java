import Antlr.SolBaseListener;
import Antlr.SolBaseVisitor;
import Antlr.SolParser;
import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;

/*public class ScopeRecord extends SolBaseVisitor<ScopeTree>
{
    private ScopeTree currentParent;
    private ParseTreeProperty<ScopeTree> scopeCache;
    private ErrorLog errorLog;

    private Class<?> stringToClass(String type)
    {
        return switch (type)
        {
            case "int" -> int.class;
            case "real" -> double.class;
            case "string" -> String.class;
            case "bool" -> boolean.class;
            default -> null;
        };
    }

    public ScopeRecord(ErrorLog errorLog)
    {
        this.currentParent = new ScopeTree(null);
        this.scopeCache = new ParseTreeProperty<>();
        this.errorLog = errorLog;
    }

    public ScopeRecord()
    {
        this(new ErrorLog());
    }

   /* private void addVariables(SolParser.LineContext line)
    {
        for(SolParser.LabelExpressionContext v : line.localDeclaration().labelExpression())
            this.currentParent.addVariable(new Variable(v.LABEL().getText(),
                    stringToClass(line.localDeclaration().TYPE().getText()),
                    v.expression() != null
                    ));
    }

    private SolParser.ScopeContext getScopeParent(SolParser.ScopeContext scopeNode)
    {
        RuleContext result = scopeNode.parent;

        while(result != null && !(result instanceof SolParser.ScopeContext))
            result = result.parent;

        return result != null ? (SolParser.ScopeContext) result : null;
    }

    @Override
    public ScopeTree visitSol(SolParser.SolContext ctx)
    {
        ScopeTree result = this.currentParent;

        for(int i = 0; i < ctx.declaration().size(); i++)
            visit(ctx.declaration().get(i));

        for(int i = 0; i < ctx.function().size(); i++)
            visit(ctx.function().get(i));

        this.scopeCache.put(ctx, this.currentParent);

        return result;
    }

    private SolParser.ScopeContext getScope(RuleContext ctx)
    {
        while(ctx != null && !(ctx instanceof SolParser.ScopeContext))
            ctx = ctx.parent;

        return ctx != null ? (SolParser.ScopeContext) ctx : null;
    }


    @Override
    public ScopeTree visitDeclaration(SolParser.DeclarationContext ctx)
    {
        for(SolParser.LabelExpressionContext variable : ctx.labelExpression())
            this.currentParent.addVariable(new Variable(variable.LABEL().getText(),
                    stringToClass(ctx.TYPE().getText()),
                    variable.expression() != null));

        return this.currentParent;
    }

    @Override
    public ScopeTree visitFunction(SolParser.FunctionContext ctx)
    {
        ScopeTree result = this.currentParent;

        visit(ctx.scope());

        return result;
    }

    @Override
    public ScopeTree visitIf(SolParser.IfContext ctx)
    {
        ScopeTree result = this.currentParent;

        if(ctx.line(0).scope() != null)
            visit(ctx.line(0).scope());

        if(ctx.line(1) != null && ctx.line(1).scope() != null)
            visit(ctx.line(1).scope());

        return result;
    }

    @Override
    public ScopeTree visitWhile(SolParser.WhileContext ctx)
    {
        ScopeTree result = null;

        if(ctx.line().scope() != null)
            result = visit(ctx.line().scope());

        return result;
    }

    @Override
    public ScopeTree visitFor(SolParser.ForContext ctx)
    {
        ScopeTree result = null;

        if(ctx.line().scope() != null)
            result = visit(ctx.line().scope());

        return result;
    }

    @Override
    public ScopeTree visitLine(SolParser.LineContext ctx)
    {
        ScopeTree result = null;

        if(ctx.scope() != null)
            result = visit(ctx.scope());
        else if(ctx.loop() != null)
            result = visit(ctx.loop());

        return result;
    }

    @Override
    public ScopeTree visitStatement(SolParser.StatementContext ctx)
    {
        ScopeTree result = null;

        if(ctx.line() != null)
            result = visit(ctx.line());
        else if(ctx.declaration() != null)
            result = visit(ctx.declaration());

        return result;
    }

    @Override
    public ScopeTree visitScope(SolParser.ScopeContext ctx)
    {
        ScopeTree oldParent = this.currentParent;
        this.currentParent = new ScopeTree(ctx, this.currentParent);


        for(SolParser.StatementContext statement : ctx.block().statement())
        {
            ScopeTree scope = visit(statement);

            if(scope != null)
                this.currentParent.addChild(scope);
        }

        oldParent.addChild(this.currentParent);
        ScopeTree result = this.currentParent;
        this.currentParent = oldParent;

        this.scopeCache.put(ctx, result);

        return result;
    }

    public ParseTreeProperty<ScopeTree> getScopes(ParseTree tree)
    {
        this.visit(tree);

        return this.scopeCache;
    }
}*/
