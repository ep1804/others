package symbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BST<K extends Comparable<K>, V> implements ISymbolTable<K, V> {

	private class Node {
		public K key;
		public V value;
		public Node left;
		public Node right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node root;

	public BST() {

	}

	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
	}

	private Node put(Node x, K key, V value) {
		if (x == null)
			return new Node(key, value);

		int cmp = key.compareTo(x.key);

		if (cmp < 0)
			x.left = put(x.left, key, value);
		else if (cmp > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;

		return x;
	}

	@Override
	public V get(K key) {
		return get(root, key);
	}

	private V get(Node x, K key) {
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);

		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.value;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<K> keys() {
		List<K> ks = new ArrayList<K>();
		keys(ks, root);
		return ks;
	}

	private void keys(List<K> ks, Node x) {

		if (x == null)
			return;

		keys(ks, x.left);
		ks.add(x.key);
		keys(ks, x.right);
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub

	}

	@Override
	public K min() {

		return min(root);
	}

	private K min(Node x) {
		if (x == null)
			return null;

		if (x.left != null)
			return min(x.left);
		else
			return x.key;
	}

	@Override
	public K max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K ceiling(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K floor(K key) {

		return floor(root, key);
	}

	private K floor(Node x, K key) {

		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);

		if (cmp < 0) {
			return floor(x.left, key);

		} else if (cmp > 0) {
			K rc = floor(x.right, key);
			if (rc == null)
				return x.key;
			else
				return rc;

		} else {
			return key;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rank(K key) {

		return rank(root, key);
	}

	private int rank(Node x, K key) {

		return 0;
	}

	@Override
	public Iterable<V> harvestValues(K lo, K hi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<K> harvestKeys(K lo, K hi) {
		
		List<K> ks = new ArrayList<K>();		
		harvest(ks, root, lo, hi);
		
		return ks;
	}

	private void harvest(List<K> ks, Node x, K lo, K hi) {
		if(x == null)
			return;
		
		int cmp1 = x.key.compareTo(lo);
		int cmp2 = x.key.compareTo(hi);
		
		if(cmp1 < 0){
			harvest(ks, x.right, lo, hi);
		} else if( cmp2 > 0){
			harvest(ks, x.left, lo, hi);
		} else {
			ks.add(x.key);
			harvest(ks, x.left, lo, hi);
			harvest(ks, x.right, lo, hi);
		}
		
	}

	public static void main(String[] args) throws FileNotFoundException {

		ISymbolTable<String, Integer> st = new BST<String, Integer>();

		Scanner in = new Scanner(new File("input/symTab/sym1"));
		while (in.hasNextInt()) {
			int v = in.nextInt();
			String k = in.next();
			st.put(k, v);
		}
		in.close();

		System.out.println(st.get("E"));
		System.out.println(st.get("F"));
		System.out.println(st.min());
		System.out.println(st.keys());
		System.out.println(st.floor("G"));
		System.out.println(st.floor("H"));
		System.out.println(st.floor("I"));
		System.out.println(st.harvestKeys("F", "K"));
	}

}
