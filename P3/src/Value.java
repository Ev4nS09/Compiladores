import java.util.Arrays;

public class Value
{
    private Object value;
    private Class<?> type;

    public Value(Object value)
    {
        Class<?>[] VALID_TYPES = {Integer.class, Double.class, String.class, Boolean.class};

        for(Class<?> type : VALID_TYPES)
        {
            if(type.isInstance(value))
            {
                this.value = value;
                this.type = type;
            }
        }

        if(this.value == null)
            ErrorHandler.throwError("Invalid Type, the valid types are: ".concat(Arrays.toString(VALID_TYPES)));
    }

    public Object getObject()
    {
        return this.value;
    }

    public Integer getInteger()
    {
        if(!(this.value instanceof Integer))
            ErrorHandler.throwError("Couldn't cast to Integer, because the value is not an instance of Integer");

        return (Integer) this.value;
    }

    public Double getDouble()
    {
        if(!(this.value instanceof Double))
            ErrorHandler.throwError("Couldn't cast to Double, because the value is not an instance of Double");

        return (Double) this.value;
    }

    public String getString()
    {
        if(!(this.value instanceof String))
            ErrorHandler.throwError("Couldn't cast to String, because the value is not an instance of String");

        return (String) this.value;
    }

    public Boolean getBoolean()
    {
        if(!(this.value instanceof Boolean))
            ErrorHandler.throwError("Couldn't cast to Boolean, because the value is not an instance of Boolean");

        return (Boolean) this.value;
    }

    public Class<?> getValueType()
    {
        return this.type;
    }

    public boolean isType(Class<?> thatType)
    {
        return this.type == thatType;
    }

    @Override
    public int hashCode()
    {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object that)
    {
        if(this == that)
            return true;
        if(that == null)
            return false;
        if(this.getClass() != that.getClass())
            return false;

        return this.value.equals(((Value)that).value);
    }

    @Override
    public String toString()
    {
        return this.value.toString();
    }
}
