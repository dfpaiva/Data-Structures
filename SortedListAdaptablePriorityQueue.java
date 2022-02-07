import java.util.Comparator;

public class SortedListAdaptablePriorityQueue<K,V> extends SortedListPriorityQueue<K,V> implements AdaptablePriorityQueue<K,V>
{

  public SortedListAdaptablePriorityQueue()
  {
    super();
  }

  public SortedListAdaptablePriorityQueue(Comparator<K> comp)
  {
    super(comp);
  }

  public SortedListAdaptablePriorityQueue(PositionList<Entry<K,V>> list, Comparator<K> comp)
  {
    super(list, comp);
  }

  public Entry<K,V> insert (K k, V v) throws InvalidKeyException
  {
    checkKey(k);
    LocationAwareEntry<K,V> e = new LocationAwareEntry<K,V>(k,v);
    insertEntry(e);
    e.setLocation(actionPos);	// position of the new entry
    return e;
  }

  public Entry<K,V> remove(Entry<K,V> entry)
  {
    checkEntry(entry);
    LocationAwareEntry<K,V> e = (LocationAwareEntry<K,V>) entry;
    Position<Entry<K,V>> p = e.location();
    entries.remove(p);
    e.setLocation(null);
    return e;
  }

  public K replaceKey(Entry<K,V> entry, K k)
  {
    checkKey(k);
    checkEntry(entry);
    LocationAwareEntry<K,V> e = (LocationAwareEntry<K,V>) remove(entry);
    K old = e.setKey(k);
    insertEntry(e);
    e.setLocation(actionPos); // position of new entry
    return old;
  }

  public V replaceValue(Entry<K,V> e, V v)
  {
    checkEntry(e);
    V old = ((LocationAwareEntry<K,V>) e).setValue(v);
    return old;
  }

  protected void checkEntry(Entry e) throws InvalidEntryException
  {
    if(e == null || !(e instanceof LocationAwareEntry))
      throw new InvalidEntryException("invalid entry");
  }

  // below is inner class

  protected static class LocationAwareEntry<K,V> extends MyEntry<K,V> implements Entry<K,V>
  {
    private Position<Entry<K,V>> loc;

    public LocationAwareEntry(K k, V v)
    {
      super(k, v);
    }

    public LocationAwareEntry(K k, V v, Position<Entry<K,V>> p)
    {
      super(k, v);
      loc = p;
    }

    protected Position<Entry<K,V>> location()
    {
      return loc;
    }

    protected Position<Entry<K,V>> setLocation(Position<Entry<K,V>> p)
    {
      Position<Entry<K,V>> old = location();
      loc = p;
      return old;
    }

    protected K setKey(K key)
    {
      K old = getKey();
      k = key;
      return old;
    }

    protected V setValue(V val)
    {
      V old = getValue();
      v = val;
      return old;
    }
  }
}
