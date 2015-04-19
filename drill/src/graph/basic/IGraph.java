package graph.basic;

// Implementations: Graph, DiGraph

public interface IGraph {
	public int V();
	public int E();
	public Iterable<Integer> adj(int v);
	public void addEdge(int v, int w);
}