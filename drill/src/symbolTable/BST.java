package symbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BST<K extends Comparable<K>, V> implements ISymbolTable<K, V> {

	private class Node {
		public final K key;
		public V value;
		public Node left;
		public Node right;
		public int size;

		public Node(K k, V v) {
			key = k;
			value = v;
			size = 1;
		}
	}

	private Node root;

	public BST() {
		root = null;
	}

	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
	}

	private Node put(Node n, K key, V value) {
		if (n == null)
			return new Node(key, value);

		int cmp = key.compareTo(n.key);

		if (cmp < 0)
			n.left = put(n.left, key, value);
		else if (cmp > 0)
			n.right = put(n.right, key, value);
		else
			n.value = value;

		n.size = size(n);

		return n;
	}

	@Override
	public V get(K key) {
		Node n = get(root, key);

		if (n == null)
			return null;
		else
			return n.value;
	}

	private Node get(Node n, K key) {
		if (n == null)
			return null;

		int cmp = key.compareTo(n.key);

		if (cmp < 0)
			return get(n.left, key);
		else if (cmp > 0)
			return get(n.right, key);
		else
			return n;
	}

	@Override
	public boolean contains(K key) {
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Iterable<K> keys() {
		List<K> acc = new ArrayList<K>();

		keysLoop(root, acc);
		return acc;
	}

	private void keysLoop(Node n, List<K> acc) {
		if (n == null)
			return;
		keysLoop(n.left, acc);
		acc.add(n.key);
		keysLoop(n.right, acc);
	}

	@Override
	public Iterable<V> values() {
		List<V> acc = new ArrayList<V>();

		valuesLoop(root, acc);
		return acc;
	}

	private void valuesLoop(Node n, List<V> acc) {
		if (n == null)
			return;
		valuesLoop(n.left, acc);
		acc.add(n.value);
		valuesLoop(n.right, acc);
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub
	}

	@Override
	public K min() {
		Node n = min(root);

		if (n == null)
			return null;
		else
			return n.key;
	}

	private Node min(Node n) {
		if (n == null)
			return null;
		if (n.left != null)
			return min(n.left);
		return n;
	}

	@Override
	public K max() {
		Node n = max(root);

		if (n == null)
			return null;
		else
			return n.key;
	}

	private Node max(Node n) {
		if (n == null)
			return null;
		if (n.right != null)
			return max(n.right);
		return n;
	}

	@Override
	public K ceiling(K key) {
		Node n = ceiling(root, key);

		if (n == null)
			return null;
		else
			return n.key;
	}

	private Node ceiling(Node n, K key) {
		if (n == null)
			return null;

		int cmp = key.compareTo(n.key);
		if (cmp < 0) {
			Node cl = ceiling(n.left, key);
			if (cl == null)
				return n;
			else
				return cl;
		} else if (cmp > 0) {
			return ceiling(n.right, key);
		} else {
			return n;
		}
	}

	@Override
	public K floor(K key) {
		Node n = floor(root, key);
		
		if(n == null) return null;
		else return n.key;
	}

	private Node floor(Node n, K key) {
		if(n == null) return null;
		
		int cmp = key.compareTo(n.key);
		if (cmp < 0) {
			return floor(n.left, key);
		} else if (cmp > 0) {
			Node fl = floor(n.right, key);
			if(fl == null) return n;
			else return fl;
		} else {
			return n;
		}
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(Node n) {
		if (n == null)
			return 0;
		else
			return size(n.left) + size(n.right) + 1;
	}

	@Override
	public int rank(K key) {
		return rank(root, key);
	}

	private int rank(Node n, K key) {
		if (n == null)
			return 1;

		int cmp = key.compareTo(n.key);

		if (cmp < 0) {
			return rank(n.left, key);
		} else if (cmp > 0) {
			return size(n.left) + 1 + rank(n.right, key);
		} else
			return size(n.left) + 1;
	}

	@Override
	public Iterable<V> harvestValues(K lo, K hi) {
		List<Node> ns = new ArrayList<Node>();
		harvest(root, ns, lo, hi);
		List<V> vs = new ArrayList<V>();
		for(Node n : ns)
			vs.add(n.value);
		return vs;
	}

	@Override
	public Iterable<K> harvestKeys(K lo, K hi) {
		List<Node> ns = new ArrayList<Node>();
		harvest(root, ns, lo, hi);
		List<K> ks = new ArrayList<K>();
		for(Node n : ns)
			ks.add(n.key);
		return ks;
	}
	
	private void harvest(Node n, List<Node> buf, K lo, K hi){
		if(n == null) return;
		
		int cmp1 = n.key.compareTo(lo);
		int cmp2 = n.key.compareTo(hi);
		
		if(cmp1 < 0){
			harvest(n.right, buf, lo, hi);
		}else if(cmp1 == 0){
			buf.add(n);
			harvest(n.right, buf, lo, hi);
		}else if(cmp1 > 0 && cmp2 < 0){
			buf.add(n);
			harvest(n.right, buf, lo, hi);
			harvest(n.left, buf, lo, hi);
		}else if(cmp2 == 0){
			buf.add(n);
			harvest(n.left, buf, lo, hi);
		}else{ // cmp2 > 0
			harvest(n.left, buf, lo, hi);
		}
	}

	@Override
	public String toString() {
		return toString(root);
	}

	private String toString(Node n) {
		if (n == null)
			return ".";
		return "(" + toString(n.left) + " " + n.key + "-" + n.value + " " + toString(n.right) + ")";
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/symTab/sym1"));
		BST<Integer, String> tree = new BST<Integer, String>();
		while (in.hasNextInt()) {
			tree.put(in.nextInt(), in.next());
		}
		in.close();

		System.out.println(tree);
		System.out.println("size: " + tree.size());
		for (int i = 0; i < 17; i++) {
			System.out.println(i + " get:" + tree.get(i)
			+ ", rank:" + tree.rank(i) + ", floor:" + tree.floor(i)
			+ ", ceil:" + tree.ceiling(i));
		}

		System.out.println("keys: " + tree.keys());
		System.out.println("values: " + tree.values());
		System.out.println("min: " + tree.min());
		System.out.println("max: " + tree.max());
		
		for (int i = 0; i < 12; i++) {
			System.out.println("harvest " + i + "~" + (i + 5) + ": " + tree.harvestKeys(i, i+5));
		}

	}
}
