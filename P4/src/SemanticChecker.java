import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.Calendar;
import java.util.HashMap;

public class SemanticChecker
{
    private ErrorLog errorLog;
    private HashMap<String, Function> functions;
    private ParseTreeProperty<HashMap<String, Variable>> scopeVariables;
    private ParseTreeProperty<Class<?>> types;

    public SemanticChecker(ErrorLog errorLog)
    {
        this.errorLog = errorLog;
        this.functions = new HashMap<>();
        this.scopeVariables = new ParseTreeProperty<>();
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

        VariableRecord variableRecord = new VariableRecord(this.functions, this.errorLog);
        this.scopeVariables = variableRecord.getVariables(tree);

        TypeRecord typeRecord = new TypeRecord(this.functions, this.scopeVariables, this.errorLog);
        this.types = typeRecord.getTypes(tree);

    }

    public ParseTreeProperty<Class<?>> getTypes() {
        return types;
    }

    public HashMap<String, Function> getFunctions() {
        return functions;
    }

    public ParseTreeProperty<HashMap<String, Variable>> getScopeVariables() {
        return scopeVariables;
    }
}
