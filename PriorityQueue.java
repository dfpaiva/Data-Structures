public interface PriorityQueue<K, V>
{
	public Entry<K, V> insert(K k, V v) throws InvalidKeyException;
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException;

    public Entry<K, V> min() throws EmptyPriorityQueueException;

    public int size();
    public boolean isEmpty();
}