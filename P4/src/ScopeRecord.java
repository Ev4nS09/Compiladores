import Antlr.SolBaseListener;
import Antlr.SolBaseVisitor;
import Antlr.SolParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ScopeRecord extends SolBaseVisitor<ScopeTree>
{
    private ScopeTree currentParent;
    private ParseTreeProperty<ScopeTree> scopeCache;

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

    public ScopeRecord()
    {
        this.currentParent = null;
    }

    private void addVariables(SolParser.LineContext line)
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

    @Override
    public ScopeTree visitScope(SolParser.ScopeContext ctx)
    {
        if(ctx.loop() != null || ctx.if_() != null)
            return null;

        ScopeTree newScope = new ScopeTree(ctx, this.currentParent);

        for(SolParser.ScopeContext scope : ctx.blo)

        for(SolParser.LineContext line : ctx.block().line())
            if(line.localDeclaration() != null)
               this.addVariables(line);


    }
}
