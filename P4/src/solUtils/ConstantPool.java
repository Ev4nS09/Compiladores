package solUtils;

import java.util.*;

public class ConstantPool<T>
{
    private final LinkedList<T> pool;
    private final HashMap<T, Integer> cache;

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

    public T getPoolValue(int position)
    {
        return this.pool.get(position);
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
