package symbolTable;

import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements ISymbolTable<K, V> {
	
	@SuppressWarnings("hiding")
	private class Node<K, V>{
		public final K key;
		public V value;
		public Node<K, V> left;
		public Node<K, V> right;
		public int count;
		
		public Node(K k, V v){
			key = k;
			value = v;
			left = null;
			right = null;
			count = 1;
		}
	}
	
	private Node<K, V> root;

	public BST() {		
		root = null;
	}
	
	private Node<K, V> put(K key, V value, Node<K, V> node){
		if(node == null){
			node = new Node<K, V>(key, value);
		}
		else if(key.compareTo(node.key) < 0) node.left = put(key, value, node.left);
		else if(key.compareTo(node.key) > 0) node.right = put(key, value, node.right);
		else node.value = value;
		
		int newCount = 1;
		if(node.left != null) newCount += node.left.count;
		if(node.right != null) newCount += node.right.count;
		node.count = newCount;
		
		return node;
	}

	@Override
	public void put(K key, V value) {
		root = put(key, value, root);
	}
	
	private Node<K, V> get(K key, Node<K, V> node){
		if(node == null) return null;
		else if(key.compareTo(node.key) < 0) return get(key, node.left);
		else if(key.compareTo(node.key) > 0) return get(key, node.right);
		else return node;
	}

	@Override
	public V get(K key) {
		Node<K, V> n = get(key, root);
		if(n == null) return null;
		else return n.value;
	}

	@Override
	public boolean contains(K key) {
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}
	
	private void collectLoop(Node<K,V> n, List<K> buf){
		if(n == null) return;
		collectLoop(n.left, buf);
		buf.add(n.key);
		collectLoop(n.right, buf);
	}

	@Override
	public Iterable<K> keys() {
		List<K> ks = new ArrayList<K>();
		collectLoop(root, ks);
		return ks;
	}

	@Override
	public void delete(K key) {
		// this is tombstone method. TODO Hibbard deletion
		Node<K, V> n = get(key, root);
		if(n != null) n.value = null;
	}
	
	private K min(Node<K, V> n, K mi){
		if(n == null) return mi;
		else return min(n.left, n.key);
	}

	@Override
	public K min() {
		return min(root, null);
	}

	private K max(Node<K, V> n, K ma){
		if(n == null) return ma;
		else return max(n.right, n.key);
	}
	
	@Override
	public K max() {
		return max(root, null);
	}
	
	private Node<K, V> ceil(Node<K, V> n, K key, Node<K, V> ceil){
		if(n == null) return ceil;
		else if(n.key.compareTo(key) < 0) return ceil(n.right, key, ceil);
		else if(n.key.compareTo(key) > 0) return ceil(n.left, key, n);
		else return n;		
	}

	@Override
	public K ceiling(K key) {
		Node<K,V> cl = ceil(root, key, null);
		if(cl == null) return null;
		else return cl.key;
	}

	private Node<K, V> floor(Node<K, V> n, K key, Node<K, V> fl){
		if(n == null) return fl;
		else if(n.key.compareTo(key) < 0) return floor(n.right, key, n);
		else if(n.key.compareTo(key) > 0) return floor(n.left, key, fl);
		else return n;
	}
	
	@Override
	public K floor(K key) {
		Node<K,V> fl = floor(root, key, null);
		if(fl == null) return null;
		else return fl.key;
	}

	@Override
	public int size() {
		if(root == null) return 0;
		else return root.count;
	}
	
	// FIXME incorrect due to tombstone delete
	private int rank(Node<K, V> n, K key, int rk){
		if(n == null) return rk;
		else if(n.key.compareTo(key) < 0){
			int cnt;
			if(n.right == null) cnt = n.count;
			else cnt = n.count - n.right.count;			 
			return rank(n.right, key, rk + cnt);
		}else if(n.key.compareTo(key) > 0) return rank(n.left, key, rk);
		else{
			int cnt;
			if(n.right == null) cnt = n.count;
			else cnt = n.count - n.right.count;
			return rk + cnt;
		}
	}
	
	// FIXME incorrect due to tombstone delete
	@Override
	public int rank(K key) {
		return rank(root, key, 0);
	}
	
	private void harvest(Node<K,V> n, K lo, K hi, List<Node<K,V>> buf){
		if(n == null) return;
		if(hi.compareTo(lo) < 0) return;
				
		int cmp1 = n.key.compareTo(lo);
		int cmp2 = n.key.compareTo(hi);
		
		if(cmp1 < 0) harvest(n.right, lo, hi, buf);
		else if(cmp1 == 0){
			buf.add(n);
			harvest(n.right, lo, hi, buf);			
		}else if(cmp1 > 0 && cmp2 < 0){
			buf.add(n);
			harvest(n.left, lo, hi, buf);
			harvest(n.right, lo, hi, buf);
		}else if(cmp2 == 0){
			buf.add(n);
			harvest(n.left, lo, hi, buf);
		}else{
			harvest(n.left, lo, hi, buf);
		}
	}
	
	@Override
	public Iterable<V> harvestValues(K lo, K hi) {
		List<Node<K, V>> buf = new ArrayList<Node<K,V>>();
		
		harvest(root, lo, hi, buf);
		
		List<V> res = new ArrayList<V>();
		for(Node<K,V> n : buf)
			if(n.value != null)
				res.add(n.value);
		return res;
	}

	@Override
	public Iterable<K> harvestKeys(K lo, K hi) {
		List<Node<K, V>> buf = new ArrayList<Node<K,V>>();
		
		harvest(root, lo, hi, buf);
		
		List<K> res = new ArrayList<K>();
		for(Node<K,V> n : buf)
			if(n.value != null)
				res.add(n.key);
		return res;
	}
	
	private void printLoop(Node<K,V> n, StringBuilder sb){
		if(n == null){
			sb.append(".");
			return;
		}
		sb.append('(');
		printLoop(n.left, sb);
		sb.append(n.key + "-" + n.value);
		printLoop(n.right, sb);
		sb.append(')');
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		printLoop(root, sb);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		BST<Integer, String> tree = new BST<Integer, String>();
		tree.put(12, "S");
		tree.put(4, "E");
		tree.put(14, "X");
		tree.put(0, "A");
		tree.put(10, "R");		
		tree.put(2, "C");
		tree.put(6, "H");
		tree.put(8, "M");
		
		System.out.println(tree);		
		System.out.println(tree.get(1));
		System.out.println(tree.get(4));
		
		System.out.println(tree.contains(2));
		System.out.println(tree.contains(8));
		
		System.out.println(tree.isEmpty());
		
		System.out.println(tree.size());
		
		System.out.println(tree.min());
		System.out.println(tree.max());
		
		System.out.println(tree.keys());
		
		System.out.println(tree.ceiling(4));
		System.out.println(tree.ceiling(5));
		System.out.println(tree.ceiling(6));
		System.out.println(tree.ceiling(7));
		System.out.println(tree.ceiling(8));
		System.out.println(tree.ceiling(9));
		System.out.println(tree.ceiling(10));
		System.out.println(tree.ceiling(11));
		
		System.out.println(tree.floor(4));
		System.out.println(tree.floor(5));
		System.out.println(tree.floor(6));
		System.out.println(tree.floor(7));
		System.out.println(tree.floor(8));
		System.out.println(tree.floor(9));
		System.out.println(tree.floor(10));
		System.out.println(tree.floor(11));
		
		System.out.println(tree.rank(4));
		System.out.println(tree.rank(5));
		System.out.println(tree.rank(6));
		System.out.println(tree.rank(7));
		System.out.println(tree.rank(8));
		System.out.println(tree.rank(9));
		System.out.println(tree.rank(10));
		System.out.println(tree.rank(11));
		
		System.out.println(tree.harvestKeys(2, 7));
		System.out.println(tree.harvestKeys(3, 7));
		System.out.println(tree.harvestKeys(3, 10));
		
		System.out.println(tree.harvestValues(2, 7));
		System.out.println(tree.harvestValues(3, 7));
		System.out.println(tree.harvestValues(3, 10));
	}
}
