package graph.sp;

public class DirectedEdge implements IDirectedEdge {

	private int from;
	private int to;
	private double weight;
	
	public DirectedEdge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(IDirectedEdge o) {
		double cmp = weight - o.weight();
		if(cmp > 0)
			return 1;
		else if(cmp < 0)
			return -1;
		else
			return 0;
	}

	@Override
	public int from() {
		return from;
	}

	@Override
	public int to() {
		return to;
	}

	@Override
	public int other(int v) {
		if(v == from)
			return to;
		else
			return from;
	}

	@Override
	public double weight() {
		return weight;
	}
	
	@Override
	public String toString() {	
		return "" + from + "-" + to + "-" + weight;
	}

	public static void main(String[] args) {
		DirectedEdge e = new DirectedEdge(0, 1, 0.1);

		System.out.println(e);
	}

}
