public class UnsortedListPriorityQueue<K,V> implements PriorityQueue<K,V>
{
	protected PositionList<Entry<K,V>> entries;
	protected Comparator<K> c;
	protected Position<Entry<K,V>> actionPos;

	public Entry<K,V> min() throws EmptyPriorityQueueException
	{
		if(entries.isEmpty())
			throw new EmptyPriorityQueueException();
		else
			return minPosition().element;
	}

	public Entry<K,V> insert(K k, V v) throws InvalidKeyException
	{
		checkKey(k);
		Entry<K,V> entry = MyEntry<...>

	}

	public Position<Entry<K,V>> minPosition()
	{
		if(entries.isEmpty())
			throw new EmptyPriorityQueueException();
		else
		{
			Position<Entry<K,V>> curr = entries.next(entries.first());
			Position<Entry<K,V>> min = entries.first();
			while(curr != null)
			{
				if(c.compare(minPosition().element().getKey(), curr.element().getKey()) > 0)
				{
					min = curr;
					curr = entries.next(curr);
				}
				else
					curr = entries.next(curr);
			}
		}
		return min;
	}
}