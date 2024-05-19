package solUtils;

public class Variable
{
    public String name;
    public Class<?> type;
    public int memoryValue;
    public boolean isGlobal;

    public Variable(String name, Class<?> type, int memoryValue, boolean isGlobal)
    {
        this.name = name;
        this.type = type;
        this.memoryValue = memoryValue;
        this.isGlobal = isGlobal;
    }

    public Variable(String name, Class<?> type)
    {
       this(name, type, 0, false);
    }

    @Override
    public String toString()
    {
        return this.type.getName() + " " + this.name;
    }
}

