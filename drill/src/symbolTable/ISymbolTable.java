package symbolTable;

//Implementations: BST, RBT

public interface ISymbolTable<K extends Comparable<K>, V> {
	public void put(K key, V value);
	public V get(K key);
	public boolean contains(K key);
	public boolean isEmpty();	
	public Iterable<K> keys();

	public void delete(K key);
	
	public K min();
	public K max();
	public K ceiling(K key);
	public K floor(K key);
	
	public int size();
	public int rank(K key);
}