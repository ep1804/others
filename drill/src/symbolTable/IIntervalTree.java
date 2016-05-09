package symbolTable;

public interface IIntervalTree<K extends Comparable<K>, V> {
	public void put(K lo, K hi, V value);
	public V get(K lo, K hi);
	public void delete(K lo, K hi);
	public Iterable<V> intersects(K lo, K hi);
}