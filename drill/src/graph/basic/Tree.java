package graph.basic;

// Extensions: DfsTree, BfsTree 

public abstract class Tree {
	protected IGraph g;
	protected int start;
	public Tree(IGraph g, int start){
		this.g = g;
		this.start = start;
	}
	public abstract int vertexTo(int v);
	public abstract Iterable<Integer> pathTo(int v);
}