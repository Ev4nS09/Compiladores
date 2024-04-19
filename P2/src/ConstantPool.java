import java.util.*;

public class ConstantPool
{
    LinkedList<Value> pool;
    HashMap<Value, Integer> cache;

    public ConstantPool()
    {
        this.pool = new LinkedList<>();
        this.cache = new HashMap<>();
    }

    public int add(Value value)
    {
        if(!this.cache.containsKey(value))
        {
            this.cache.put(value, this.pool.size());
            this.pool.add(value);
        }

        return this.cache.get(value);
    }

    public Value get(int index)
    {
        return this.pool.get(index);
    }

    public int getPoolPosition(Value value)
    {
        return this.cache.get(value);
    }

    public LinkedList<Value> getValueList()
    {
        return this.pool;
    }

    @Override
    public String toString()
    {
        return this.pool.toString();
    }

}
