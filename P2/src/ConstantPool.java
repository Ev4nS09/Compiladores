import java.util.*;

public class ConstantPool<T>
{
    LinkedList<T> pool;
    HashMap<T, Integer> cache;

    public ConstantPool()
    {
        this.pool = new LinkedList<>();
        this.cache = new HashMap<>();
    }

    public void add(T value)
    {
        if(!this.cache.containsKey(value))
        {
            this.cache.put(value, this.pool.size());
            this.pool.add(value);
        }
    }

    public int getPoolPosition(T value)
    {
        return this.cache.get(value);
    }

    public LinkedList<T> getValueList()
    {
        return this.pool;
    }

    @Override
    public String toString()
    {
        return this.pool.toString();
    }

}
