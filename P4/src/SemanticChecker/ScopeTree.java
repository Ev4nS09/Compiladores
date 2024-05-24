package SemanticChecker;

import Antlr.SolParser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import solUtils.Variable;

import java.util.HashMap;

public class ScopeTree
{
    private final ParseTreeProperty<HashMap<String, Variable>> variableCache;

    public ScopeTree()
    {
        this.variableCache = new ParseTreeProperty<>();
        this.variableCache.put(null, new HashMap<>());
    }

    public SolParser.ScopeContext getScope(RuleContext ctx)
    {
        while(ctx != null && !(ctx instanceof SolParser.ScopeContext))
            ctx = ctx.parent;

        if(ctx != null && this.variableCache.get(ctx) == null)
            this.variableCache.put(ctx, new HashMap<>());

        return ctx != null ? (SolParser.ScopeContext) ctx : null;
    }



    public Variable getVariable(RuleContext ctx, String variableName)
    {
        SolParser.ScopeContext currentScope = getScope(ctx);

        while(currentScope != null)
        {
            if(this.variableCache.get(currentScope).get(variableName) != null)
                break;

            currentScope = getScope(currentScope.parent);
        }

        return this.variableCache.get(currentScope).get(variableName);
    }

    public void addVariable(RuleContext ctx, Variable variable)
    {
        this.variableCache.get(getScope(ctx)).put(variable.name, variable);
    }

    public boolean variableExists(RuleContext ctx, String variableName)
    {
        return this.getVariable(ctx, variableName) != null;
    }

    public boolean variableExistsInCurrentScope(RuleContext ctx, String variableName)
    {
        return this.variableCache.get(getScope(ctx)).containsKey(variableName);
    }
}
