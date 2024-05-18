package solUtils;

public class Variable
{
    public String name;
    public Class<?> type;
    public int memoryValue;
    public boolean isGlobal;
    public boolean isFunctionArgument;

    public Variable(String name, Class<?> type, boolean isInitialized, int memoryValue, boolean isGlobal, boolean isFunctionArgument)
    {
        this.name = name;
        this.type = type;
        this.memoryValue = memoryValue;
        this.isGlobal = isGlobal;
        this.isFunctionArgument = isFunctionArgument;
    }

    public Variable(String name, Class<?> type, boolean isInitialized, boolean isFunctionArgument)
    {
        this(name, type, isInitialized, 0, true, isFunctionArgument);
    }

    public Variable(String name, Class<?> type, boolean isInitialized)
    {
       this(name, type, isInitialized, 0, true, false);
    }

    @Override
    public String toString()
    {
        return this.type.getName() + " " + this.name;
    }
}

