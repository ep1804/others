package graph.mst;

// Implementations: Edge

public interface IEdge extends Comparable<IEdge>{
	public int either();
	public int other(int v);
	public double weight();
}