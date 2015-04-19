package graph.sp;

public abstract class ShortestPath {
	protected IWeightedDigraph g;
	protected int start;

	public ShortestPath(IWeightedDigraph g, int start) {
		this.g = g;
		this.start = start;
	}
	
	public abstract double distTo(int v);
	public abstract IDirectedEdge edgeTo(int v);
	protected abstract boolean relax(IDirectedEdge e); // hint
}
