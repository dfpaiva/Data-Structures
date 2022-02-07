import java.util.Comparator;

public class SortedListPriorityQueue<K,V> implements PriorityQueue<K,V>
{
	protected PositionList<Entry<K,V>> entries;
  	protected Comparator<K> c;
  	protected Position<Entry<K,V>> actionPos;

  	protected static class MyEntry<K,V> implements Entry<K,V>
  	{
    	protected K k;
    	protected V v;

    	public MyEntry(K key, V value)
    	{
    	  k = key;
    	  v = value;
    	}

    	public K getKey()
    	{
			return k;
		}
    	public V getValue()
    	{
			return v;
		}

    	public String toString()
    	{
			return "(" + k  + "," + v + ")";
		}
  	}

  	public SortedListPriorityQueue()
  	{
  	  entries = new NodePositionList<Entry<K,V>>();
  	  c = new DefaultComparator<K>();
  	}

  	public SortedListPriorityQueue (Comparator<K> comp)
  	{
  	  entries = new NodePositionList<Entry<K,V>>();
  	  c = comp;
  	}

  	public SortedListPriorityQueue (PositionList<Entry<K,V>> list, Comparator<K> comp)
  	{
  	  entries = list;
  	  c = comp;
  	}

  	public void setComparator(Comparator<K> comp) throws IllegalStateException
  	{
  	  if(!isEmpty())
  	    throw new IllegalStateException("Priority queue is not empty");
  	  c = comp;
  	}

  	public int size()
  	{
		return entries.size();
  	}

  	public boolean isEmpty()
  	{
		return entries.isEmpty();
	}

  	public Entry<K,V> min() throws EmptyPriorityQueueException
  	{
  	  if (entries.isEmpty())
  	    throw new EmptyPriorityQueueException("priority queue is empty");
  	  else
  	    return entries.first().element();
  	}

  	public Entry<K,V> insert (K k, V v) throws InvalidKeyException
  	{
  	  checkKey(k);
  	  Entry<K,V> entry = new MyEntry<K,V>(k, v);
  	  insertEntry(entry);
  	  return entry;
  	}

  	protected void insertEntry(Entry<K,V> e)
  	{
  	  if (entries.isEmpty())
  	  {
  	    entries.addFirst(e);
  	    actionPos = entries.first();
  	  }
  	  else if (c.compare(e.getKey(), entries.last().element().getKey()) > 0)
  	  {
  	    entries.addLast(e);
  	    actionPos = entries.last();
  	  }
  	  else
  	  {
  	    Position<Entry<K,V>> curr = entries.first();
  	    while (c.compare(e.getKey(), curr.element().getKey())> 0)
  	    {
			curr = entries.next(curr);
  	    }
  	    entries.addBefore(curr, e);
  	    actionPos = entries.prev(curr);
  	  }
  	}

  	public Entry<K,V> removeMin() throws EmptyPriorityQueueException
  	{
  	  if (entries.isEmpty())
  	    throw new EmptyPriorityQueueException("priority queue is empty");
  	  else
  	    return entries.remove(entries.first());
  	}

  	protected boolean checkKey(K key) throws InvalidKeyException
  	{
  	  boolean result;
  	  try {
  	    result = (c.compare(key,key)==0);
  	  } catch (ClassCastException e)
  	    {
			throw new InvalidKeyException("key cannot be compared");
  	    }
  	  return result;
  	}

  	public String toString()
  	{
  	  return entries.toString();
  	}


}