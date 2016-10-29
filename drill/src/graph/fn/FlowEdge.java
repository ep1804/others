package graph.fn;

public class FlowEdge implements IFlowEdge {

	private final int from;
	private final int to;
	private double flow;
	private final double capacity;
	
	public FlowEdge(int from, int to, double flow, double capacity) {
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
		if(v == from)
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
		if(v == to)
			return capacity - flow;
		else
			return flow;
	}

	@Override
	public void addResidualFlowTo(int v, double delta) {
		if(v == to)
			flow += delta;
		else
			flow -= delta;
	}
	
	@Override
	public String toString() {
		return "" + from + "-" + to + "-" + flow + "-" + capacity;
	}

	public static void main(String[] args) {
		System.out.println(new FlowEdge(1, 2, 0.1, 0.2));

	}

}
