package graph.sp;

public class Edge implements IDirectedEdge {

	private final int from;
	private final int to;
	private final double weight;

	public Edge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(IDirectedEdge o) {
		double cmp = weight - o.weight();
		if(cmp < 0) return -1;
		else if(cmp > 0) return 1;
		else return 0;
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
		if(v == from) return to;
		else return from;
	}

	@Override
	public double weight() {
		return weight;
	}

	public String toString(){
		return new StringBuilder().append(from).append('-').append(to).append('-').append(weight).toString();
	}

}
