package graph.mst;

import java.util.Arrays;

public class Edge implements IEdge {

	private int v;
	private int w;
	private double weight;

	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	@Override
	public int compareTo(IEdge that) {
		double cmp = this.weight - that.weight();
		if (cmp > 0)
			return 1;
		else if (cmp < 0)
			return -1;
		else
			return 0;
	}

	@Override
	public int either() {
		return v;
	}

	@Override
	public int other(int x) {
		if (x == v)
			return w;
		else
			return v;
	}

	@Override
	public double weight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return v + "-" + w + "-" + weight;
	}

	public static void main(String[] args) {
		Edge e = new Edge(1, 2, 0.1);
		System.out.println(e);
		
		IEdge[] es = {new Edge(7, 2, 0.1), new Edge(1, 3, 0.5), new Edge(2, 4, 0.3)};
		
		Arrays.sort(es);
		for(int i=0; i<es.length; i++)
			System.out.print(" " + es[i]);
	}

}
