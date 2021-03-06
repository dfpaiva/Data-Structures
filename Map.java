public interface Map<K,V>
{
	public int size();
	public boolean isEmpty();
	public V get(K key);
	public V put(K key, V value);
	public V remove(K key);
	public Iterable<K> keys();
	public Iterable<V> values();
}