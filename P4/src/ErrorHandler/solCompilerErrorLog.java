package ErrorHandler;

import org.antlr.v4.runtime.ParserRuleContext;

public class solCompilerErrorLog extends ErrorLog
{
    public void binaryOperatorError(ParserRuleContext node, String binaryOperator, String type1, String type2)
    {
        String message = "Invalid types for binary operator '" + binaryOperator + "'. Types" + type1 + " and " + type2;

        super.throwError(node, message);
    }

    public void incomparableTypesError(ParserRuleContext node, String type1, String type2)
    {
        String message = "Incomparable types: " + type1 + " and " + type2;

        super.throwError(node, message);
    }

    public void unaryOperatorError(ParserRuleContext node, String unaryOperator, String type)
    {
        String message = "Invalid type " + type + " for unary operator '" + unaryOperator + "'";

        super.throwError(node, message);
    }

    public void incompatibleTypes(ParserRuleContext node, String type1, String type2)
    {
        String message = "Incopatible types, " + type2 + " cannot be converted to " + type1;

        super.throwError(node, message);
    }

    public void redefinedVariables(ParserRuleContext node, String label)
    {
        String message = "Variable '" + label + "' is already defined";

        super.throwError(node, message);
    }

    public void undefinedVariables(ParserRuleContext node, String label)
    {
        String message = "Variable '" + label + "'" + " has not been defined";

        super.throwError(node, message);
    }

    public void invalidBreak()
    {
        System.err.println("break outside a loop");
    }
}
