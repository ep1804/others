package graph.sp;

// Implementations: WeightedDigraph 

public interface IWeightedDigraph {
	public int V();
	public int E();
	public Iterable<IDirectedEdge> edges();
	public Iterable<IDirectedEdge> adj(int v);
	public void addEdge(IDirectedEdge e);
}
