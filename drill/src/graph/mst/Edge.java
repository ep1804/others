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
	public int compareTo(IEdge o) {
		double cmp = weight - o.weight();
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
	public int other(int v) {
		if(this.v == v)
			return w;
		else
			return this.v;
	}

	@Override
	public double weight() {
		return weight;
	}
	
	@Override
	public String toString() {	
		return "" + v + "-" + w + "-" + weight;
	}

	public static void main(String[] args) {
		Edge e = new Edge(0, 1, 0.1);

		System.out.println(e);
		

	}

}
