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
		if(v == from) return to;
		else return from;
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
		if(v == to) return capacity - flow;
		else return flow;
	}

	@Override
	public void addResidualFlowTo(int v, double delta) {
		if(v == to) flow += delta;
		else flow -= delta;
	}
	
	public String toString(){
		return new StringBuilder().append(from).append('-').append(to).append('_').append(flow).append('/').append(capacity).toString();
	}

	public static void main(String[] args) {
		FlowEdge e1 = new FlowEdge(1, 2, 0.1, 0.5);
		System.out.println(e1);
		e1.addResidualFlowTo(2, 0.2);
		System.out.println(e1);
		e1.addResidualFlowTo(1, 0.1);
		System.out.println(e1);
	}
}
