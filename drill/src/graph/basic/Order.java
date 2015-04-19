package graph.basic;

// Extensions: TopologicalOrder

public abstract class Order {
	protected IGraph g;
	public Order(IGraph g){
		this.g = g;
	}
	public abstract Iterable<Integer> order();
}
