package graph.mst;

public interface IWeightedGraph {
	public int V();
	public int E();
	public Iterable<IEdge> edges();
	public Iterable<IEdge> adj(int v);
	public void addEdge(IEdge e);
}
