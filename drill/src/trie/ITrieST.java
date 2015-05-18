package trie;

public interface ITrieST<Value> {
	public void put(String key, Value val);	
	public boolean contains(String key);	
	public Value get(String key);
}
