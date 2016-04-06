package graph.fn;

public class Edge implements IFlowEdge {

	private final int from;
	private final int to;
	private final double capacity;
	private double flow;

	public Edge(int from, int to, double flow, double capacity) {
		this.from = from;
		this.to = to;
		this.flow = flow;
		this.capacity = capacity;
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
		if (v == from)
			return to;
		else
			return from;
	}

	@Override
	public double capacity() {
		return capacity;
	}

	@Override
	public double flow() {
		return flow;
	}

	@Override
	public double residualCapacityTo(int v) {
		if (v == to)
			return capacity - flow;
		else
			return flow;
	}

	@Override
	public void addResidualFlowTo(int v, double delta) {
		if (v == to) {
			if (capacity >= flow + delta) {
				flow += delta;
			} else
				throw new IllegalArgumentException();
		} else {
			if (flow >= delta) {
				flow -= delta;
			} else
				throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return from + "-" + to + "-" + flow + "-" + capacity;
	}

	public static void main(String[] args) {
		Edge e = new Edge(1, 2, 0.3, 0.7);
		System.out.println(e);
		
		System.out.println(e.residualCapacityTo(2));
		System.out.println(e.residualCapacityTo(1));
		
		e.addResidualFlowTo(1, 0.2);
		System.out.println(e);

		e.addResidualFlowTo(1, 0.2);
		System.out.println(e);
	}

}
