package trie;

public interface IStringST<V> {
	public void put(String key, V val);
	public V get(String key);
	public boolean contains(String key);	

}
