package SemanticChecker;

import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.HashMap;
import solUtils.*;

public class SemanticChecker
{
    private ErrorLog errorLog;
    private HashMap<String, Function> functions;
    private ScopeTree scopeTree;
    private ParseTreeProperty<Class<?>> types;

    public SemanticChecker(ErrorLog errorLog)
    {
        this.errorLog = errorLog;
        this.functions = new HashMap<>();
        this.scopeTree = new ScopeTree();
        this.types = new ParseTreeProperty<>();
    }

    public SemanticChecker()
    {
        this(new ErrorLog());
    }

    public void semanticCheckTree(ParseTree tree)
    {
        FunctionRecord functionRecord = new FunctionRecord(this.errorLog);
        this.functions = functionRecord.getFunctions(tree);

        VariableRecord variableRecord = new VariableRecord(this.errorLog);
        this.scopeTree = variableRecord.getScopeTree(tree);

        TypeRecord typeRecord = new TypeRecord(this.functions, this.scopeTree, this.errorLog);
        this.types = typeRecord.getTypes(tree);

    }

    public ParseTreeProperty<Class<?>> getTypes() {
        return this.types;
    }

    public HashMap<String, Function> getFunctions() {
        return this.functions;
    }

    public ScopeTree getScopeTree() {
        return this.scopeTree;
    }
}
