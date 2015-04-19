package graph.sp;

// Implementations: DirectedEdge

public interface IDirectedEdge extends Comparable<IDirectedEdge>{
	public int from();
	public int to();
	public int other(int v);
	public double weight();
}
