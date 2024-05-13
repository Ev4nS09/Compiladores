import Antlr.SolParser;

import java.util.ArrayList;

public class ScopeTree
{
    private SolParser.ScopeContext scopeNode;
    private ScopeTree parent;
    private final ArrayList<ScopeTree> children;
    private final ArrayList<Variable> scopeVariables;


    public ScopeTree(SolParser.ScopeContext scopeNode, ScopeTree parent, ArrayList<ScopeTree> children)
    {
        this.scopeNode = scopeNode;
        this.parent = parent;
        this.children = children;
        this.scopeVariables = new ArrayList<>();
    }


    public ScopeTree(SolParser.ScopeContext scopeNode, ScopeTree parent)
    {
        this(scopeNode, parent, new ArrayList<>());
    }

    public ScopeTree(SolParser.ScopeContext scopeNode)
    {
        this(scopeNode, null, new ArrayList<>());
    }

    public void addChild(ScopeTree scope)
    {
        scope.parent = this;
        this.children.add(scope);
    }

    public ScopeTree getChild(int index)
    {
        return this.children.get(index);
    }

    public void addVariable(Variable variable)
    {
        this.scopeVariables.add(variable);
    }

    private Variable getVariableFromList(String variableName)
    {
        Variable result = null;

        for(Variable variable : this.scopeVariables)
        {
            if (variable.name().equals(variableName))
            {
                result = variable;
                break;
            }
        }

        return result;
    }

    public Variable getVariable(String variableName)
    {
        Variable result = null;
        ScopeTree currentNode = this;

        while(currentNode != null)
        {
            Variable v = this.getVariableFromList(variableName);
            if (v != null) {
                result = v;
                break;
            }
            currentNode = currentNode.parent;
        }

        return result;
    }




}
