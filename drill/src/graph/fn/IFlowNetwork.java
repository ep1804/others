package graph.fn;

// Implementations: FlowNetwork

public interface IFlowNetwork {
	public int V();
	public int E();
	public Iterable<IFlowEdge> edges();
	public Iterable<IFlowEdge> adj(int v);
	public void addEdge(int v, int w);

}
