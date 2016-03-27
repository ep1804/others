package graph.mst;

public class Edge implements IEdge {

	private final int v;
	private final int w;
	private final double weight;

	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	@Override
	public int compareTo(IEdge arg0) {
		double cmp = weight - arg0.weight();
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(v).append('-').append(w).append('-').append(weight);
		return sb.toString();
	}

	public static void main(String[] args) {
		Edge e = new Edge(1, 2, 0.1);
		System.out.println(e);
		System.out.println(e.other(1));
		System.out.println(e.other(2));
		

	}

}
