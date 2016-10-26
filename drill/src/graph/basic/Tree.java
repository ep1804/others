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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("DFS Tree:\n");
		for(int v=0; v<g.V(); v++)
			sb.append(v).append(": ").append(vertexTo(v)).append('\n');
		return sb.toString();
	}
}