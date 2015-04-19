package graph.mst;

// Extensions: KruskalMST, PrimMST

public abstract class MinimumSpanningTree {
	protected IWeightedGraph g;
	public MinimumSpanningTree(IWeightedGraph g){
		this.g = g;
	}
	public abstract Iterable<IEdge> inclusionOrder();
	public abstract double totalWeight();
}
