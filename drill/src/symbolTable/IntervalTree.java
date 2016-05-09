package symbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntervalTree<K extends Comparable<K>, V> implements IIntervalTree<K, V> {

	private class Node {
		public final K lo;
		public K hi;
		public K max;
		public V value;
		public Node left;
		public Node right;

		public Node(K lo, K hi, V value) {
			this.lo = lo;
			this.hi = hi;
			this.max = hi;
			this.value = value;
		}

		@Override
		public String toString() {
			return lo + "/" + hi + "/" + max;
		}
	}

	private Node root;

	public IntervalTree() {
		root = null;
	}

	@Override
	public void put(K lo, K hi, V value) {
		root = put(root, lo, hi, value);
	}

	private Node put(Node n, K lo, K hi, V value) {

		if (n == null)
			return new Node(lo, hi, value);

		int cmp = lo.compareTo(n.lo);

		if (cmp < 0) {
			n.left = put(n.left, lo, hi, value);
		} else if (cmp > 0) {
			n.right = put(n.right, lo, hi, value);
		} else {
			n.value = value;
		}
		if (hi.compareTo(n.max) > 0)
			n.max = hi;

		return n;
	}

	@Override
	public V get(K lo, K hi) {
		Node n = get(root, lo, hi);

		if (n == null)
			return null;
		return n.value;
	}

	private Node get(Node n, K lo, K hi) {
		if (n == null)
			return null;

		int cmp = lo.compareTo(n.lo);
		if (cmp < 0) {
			return get(n.left, lo, hi);
		} else if (cmp > 0) {
			return get(n.right, lo, hi);
		} else {
			if (n.hi == hi)
				return n;
			else
				return null;
		}
	}

	@Override
	public void delete(K lo, K hi) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<V> intersects(K lo, K hi) {

		if (lo.compareTo(hi) > 0)
			throw new IllegalArgumentException("lo > hi");

		List<V> acc = new ArrayList<V>();

		intersects(acc, root, lo, hi);
		return acc;
	}

	private void intersects(List<V> acc, Node n, K lo, K hi) {
		if (n == null)
			return;
		
		int cmp1 = hi.compareTo(n.lo);
		int cmp2 = lo.compareTo(n.max);

		if (!(cmp1 < 0 || lo.compareTo(n.hi) > 0))
			acc.add(n.value);

		if (cmp1 < 0)
			intersects(acc, n.left, lo, hi);
		else if (cmp2 <= 0) {
			intersects(acc, n.left, lo, hi);
			intersects(acc, n.right, lo, hi);
		}
	}

	@Override
	public String toString() {
		return toString(root);
	}

	private String toString(Node n) {
		if (n == null)
			return ".";
		return "(" + toString(n.left) + n + toString(n.right) + ")";
	}

	private static class Line {
		public final int from;
		public final int to;

		public Line(int v, int w) {
			this.from = Math.min(v, w);
			this.to = Math.max(v, w);
		}

		@Override
		public String toString() {
			return from + "-" + to;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		IntervalTree<Integer, Line> st = new IntervalTree<Integer, Line>();

		Scanner in = new Scanner(new File("input/symTab/interval"));
		int N = in.nextInt();
		for (int i = 0; i < N; i++) {
			Line l = new Line(in.nextInt(), in.nextInt());
			st.put(l.from, l.to, l);
		}
		in.close();

		System.out.println(st);
		System.out.println("get 4, 8: " + st.get(4, 8));
		System.out.println("get 17, 19: " + st.get(17, 19));
		System.out.println("get 15, 18: " + st.get(15, 18));
		System.out.println("get 7, 10: " + st.get(7, 10));
		System.out.println("get 7, 11: " + st.get(7, 11));
		System.out.println("get 6, 7: " + st.get(6, 7));
		System.out.println("get 20, 30: " + st.get(20, 30));
		
		System.out.println("intersects 21, 23: " + st.intersects(21, 23));

	}
}
