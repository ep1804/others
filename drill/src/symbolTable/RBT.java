package symbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RBT<K extends Comparable<K>, V> implements ISymbolTable<K, V> {

	private class Node {
		public final K key;
		public V value;
		public Node left, right;
		public boolean isRed;
		public int size;

		public Node(K k, V v) {
			key = k;
			value = v;
			isRed = true;
			size = 1;
		}
	}
	
	private boolean isRed(Node n){
		if(n == null) return false;
		else if(n == root) return false;
		else return n.isRed;
	}
	
	private Node rotateLeft(Node h){
		if(! isRed(h.right)) throw new IllegalArgumentException();
		
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.isRed = h.isRed;
		h.isRed = true;
		
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	private Node rotateRight(Node h){
		if(! isRed(h.left)) throw new IllegalArgumentException();
		
		Node x = h.left;
		h.left = x.right;
		x.right = h;		
		x.isRed = h.isRed;
		h.isRed = true;
		
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		
		return x;
	}
	
	private void flipColors(Node h){
		if(isRed(h)) throw new IllegalArgumentException();
		if(! isRed(h.left)) throw new IllegalArgumentException();
		if(! isRed(h.right)) throw new IllegalArgumentException();
		
		h.left.isRed = false;
		h.right.isRed = false;
		h.isRed = true;		
	}

	private Node root;

	public RBT() {
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

		n.size = size(n.left) + size(n.right) + 1;
		
		// balance
		if(isRed(n.right) && !isRed(n.left) ) n = rotateLeft(n);
		if(isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
		if(isRed(n.left) && isRed(n.right)) flipColors(n);
		
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
		List<K> buf = new ArrayList<K>();
		keysLoop(root, buf);
		return buf;
	}

	private void keysLoop(Node n, List<K> buf) {
		if (n == null)
			return;

		keysLoop(n.left, buf);
		buf.add(n.key);
		keysLoop(n.right, buf);
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<V> values() {
		List<V> buf = new ArrayList<V>();
		valuesLoop(root, buf);
		return buf;
	}

	private void valuesLoop(Node n, List<V> buf) {
		if (n == null)
			return;

		valuesLoop(n.left, buf);
		buf.add(n.value);
		valuesLoop(n.right, buf);
	}

	@Override
	public K min() {
		Node n = min(root);
		if (n != null)
			return n.key;
		else
			return null;
	}

	private Node min(Node n) {
		if (n == null)
			return null;

		if (n.left == null)
			return n;
		else
			return min(n.left);
	}

	@Override
	public K max() {
		Node n = max(root);
		if (n != null)
			return n.key;
		else
			return null;
	}

	private Node max(Node n) {
		if (n == null)
			return null;

		if (n.right == null)
			return n;
		else
			return max(n.right);
	}

	@Override
	public K ceiling(K key) {
		Node n = ceiling(root, key);

		if (n != null)
			return n.key;
		else
			return null;
	}

	private Node ceiling(Node n, K key) {

		if (n == null)
			return null;

		int cmp = key.compareTo(n.key);

		if (cmp < 0) {
			Node n1 = ceiling(n.left, key);
			if (n1 == null)
				return n;
			else
				return n1;
		} else if (cmp > 0)
			return ceiling(n.right, key);
		else
			return n;
	}

	@Override
	public K floor(K key) {
		Node n = floor(root, key);

		if (n != null)
			return n.key;
		else
			return null;
	}

	private Node floor(Node n, K key) {
		if (n == null)
			return null;

		int cmp = key.compareTo(n.key);

		if (cmp < 0)
			return floor(n.left, key);
		else if (cmp > 0) {
			Node n1 = floor(n.right, key);
			if (n1 == null)
				return n;
			else
				return n1;
		} else
			return n;
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(Node n) {
		if (n == null)
			return 0;
		else
			return n.size;
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
			return size(n) - size(n.right) + rank(n.right, key);
		} else {
			return size(n) - size(n.right);
		}
	}

	@Override
	public Iterable<V> harvestValues(K lo, K hi) {
		List<Node> buf = new ArrayList<Node>();
		harvest(root, lo, hi, buf);
		List<V> res = new ArrayList<V>();
		for (Node n : buf)
			res.add(n.value);
		return res;
	}

	@Override
	public Iterable<K> harvestKeys(K lo, K hi) {
		List<Node> buf = new ArrayList<Node>();
		harvest(root, lo, hi, buf);
		List<K> res = new ArrayList<K>();
		for (Node n : buf)
			res.add(n.key);
		return res;
	}

	private void harvest(Node n, K lo, K hi, List<Node> buf) {
		if (n == null)
			return;

		int cmp1 = n.key.compareTo(lo);
		int cmp2 = n.key.compareTo(hi);

		if (cmp1 < 0) {
			harvest(n.right, lo, hi, buf);
		} else if (cmp1 == 0) {
			buf.add(n);
			harvest(n.right, lo, hi, buf);
		} else if (cmp1 > 0 && cmp2 < 0) {
			buf.add(n);
			harvest(n.left, lo, hi, buf);
			harvest(n.right, lo, hi, buf);
		} else if (cmp2 == 0 && lo != hi) {
			buf.add(n);
			harvest(n.left, lo, hi, buf);
		} else if (cmp2 > 0)
			harvest(n.left, lo, hi, buf);

	}

	@Override
	public String toString() {
		return toString(root);
	}
	
	private String toString(Node n){
		if(n == null) return ".";
		
		return "(" + toString(n.left) + n.key + "-" + n.value + toString(n.right) + ")";
	}

	public static void main(String[] args) throws FileNotFoundException {
		RBT<Integer, String> st = new RBT<Integer, String>();
		
		Scanner in = new Scanner(new File("input/symTab/sym1"));
		while(in.hasNextLine()){
			String[] ws = in.nextLine().trim().split("\\s+");
			st.put(Integer.parseInt(ws[0]), ws[1]);
		}
		in.close();
		
		System.out.println(st);
		
		System.out.println("keys:" + st.keys());
		System.out.println("values:" + st.values());
		
		System.out.println("size: " + st.size());
		System.out.println("min: " + st.min());
		System.out.println("min: " + st.max());

		for (int i = 0; i <= 17; i++)
			System.out.println(i + ":" + " get=" + st.get(i) + ", floor=" + st.floor(i) + ", ceiling=" + st.ceiling(i)
					+ ", rank=" + st.rank(i));

		System.out.println("harvest keys:");
		for (int i = 0; i < 17; i++) {
			int lo = i - 3;
			int hi = i + 3;
			System.out.println(lo + " ~ " + hi + ": " + st.harvestKeys(lo, hi));
		}

		System.out.println("harvest values:");
		for (int i = 0; i < 17; i++) {
			int lo = i - 3;
			int hi = i + 3;
			System.out.println(lo + " ~ " + hi + ": " + st.harvestValues(lo, hi));
		}

	}

}
