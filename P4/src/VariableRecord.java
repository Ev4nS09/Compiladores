import Antlr.SolBaseListener;
import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashMap;

public class VariableRecord extends SolBaseListener
{
    private record Variable(String name, Class<?> type, boolean isInitialized){}
    private HashMap<String, Variable> variableCache;
    private ErrorLog errorLog;

    public VariableRecord(ErrorLog errorLog)
    {
        this.variableCache =  new HashMap<>();
        this.errorLog = errorLog;
    }

    public VariableRecord()
    {
        this(new ErrorLog());
    }


    public HashMap<String, Variable> getVariables(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);


        return this.variableCache;
    }


}
