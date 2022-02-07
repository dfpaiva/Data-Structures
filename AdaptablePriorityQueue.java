public interface AdaptablePriorityQueue<K,V> extends PriorityQueue<K,V>
{
  public Entry<K,V> remove(Entry<K,V> e);
  public K replaceKey(Entry<K,V> e, K k) throws InvalidKeyException;
  public V replaceValue(Entry<K,V> e, V v);
}