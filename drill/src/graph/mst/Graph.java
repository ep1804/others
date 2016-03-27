package graph.mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Graph implements IWeightedGraph {

	private final int V;
	private Set<IEdge>[] adj;
	private Set<IEdge> edges;

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		adj = (Set<IEdge>[]) new HashSet[V];
		for (int v = 0; v < V; v++)
			adj[v] = new HashSet<IEdge>();
		edges = new HashSet<IEdge>();
	}

	public Graph(Scanner in) {
		this(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String[] ws = in.nextLine().trim().split("(\\s*-\\s*)|(\\s+)");
			Edge e = new Edge(Integer.parseInt(ws[0]), Integer.parseInt(ws[1]), Double.parseDouble(ws[2]));
			addEdge(e);
		}
	}

	@Override
	public int V() {
		return V;
	}

	@Override
	public int E() {
		return edges.size();
	}

	@Override
	public Iterable<IEdge> edges() {
		return edges;
	}

	@Override
	public Iterable<IEdge> adj(int v) {
		return adj[v];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vtxs: ").append(V);
		for (int v = 0; v < V; v++)
			sb.append('\n').append(v).append(": ").append(adj[v]);
		return sb.toString();
	}

	@Override
	public void addEdge(IEdge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		edges.add(e);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wungraph"));
		Graph g = new Graph(in);
		in.close();
		System.out.println(g);

	}

}
