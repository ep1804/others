package graph.fn;

// Extensions: FordFulkersonMF 

public abstract class MaxFlow {
	protected IFlowNetwork g;
	protected int s;
	protected int t;

	public MaxFlow(IFlowNetwork g, int s, int t) {
		this.g = g;
		this.s = s;
		this.t = t;
	}
	
	public abstract double maxFlow();
	public abstract Iterable<Integer> minCut();
}