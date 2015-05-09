package symbolTable;

//Implementations: BST, LLRB

public interface ISymbolTable<Key, Value> {
	public void put(Key key, Value value);
	public Value get(Key key);
	public void delete(Key key);
	public boolean contains(Key key);
	public boolean isEmpty(Key key);
	public int size();
	public Iterable<Key> keys();
}