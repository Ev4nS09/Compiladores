import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class ErrorHandler
{
    public static void throwError(String message)
    {
        System.err.println("Error: " + message);
        System.exit(1);
    }

    public static void binaryOperatorError(ParserRuleContext node, String binaryOperator, String type1, String type2)
    {
        System.err.println(new StringBuilder()
                .append("line ")
                .append(node.start.getLine())
                .append(". Invalid types for binary operator ")
                .append("'").append(binaryOperator).append("'")
                .append(". Types: ")
                .append(type1).append(" and ").append(type2));
    }

    public static void incomparableTypesError(ParserRuleContext node, String type1, String type2)
    {
        System.err.println(new StringBuilder()
                .append("line ")
                .append(node.start.getLine())
                .append(". Incomparable types: ")
                .append(type1).append(" and ").append(type2));
    }

    public static void unaryOperatorError(ParserRuleContext node, String unaryOperator, String type)
    {
        System.err.println(new StringBuilder()
                .append("line ")
                .append(node.start.getLine())
                .append(". Invalid type ").append(type).append(" for unary operator ")
                .append("'").append(unaryOperator).append("'"));
    }

    public static void incompatibleTypes(ParserRuleContext node, String type1, String type2)
    {
        System.err.println(new StringBuilder()
                .append("line ")
                .append(node.start.getLine())
                .append(". Incopatible types, ")
                .append(type1).append(" cannot be converted to ").append(type2)
        );
    }

    public static void redefinedVariables(ParserRuleContext node, String label)
    {
        System.err.println(new StringBuilder()
                .append("line ")
                .append(node.start.getLine())
                .append(". Variable ").append("'").append(label).append("'").append(" is already defined.")
        );
    }

    public static void undefinedVariables(ParserRuleContext node, String label)
    {
        System.err.println(new StringBuilder()
                .append("line ")
                .append(node.start.getLine())
                .append(". Variable ").append("'").append(label).append("'").append(" has not been defined")

        );
    }
}
