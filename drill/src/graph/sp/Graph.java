package graph.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Graph implements IWeightedDigraph {

	private final int V;
	private Set<IDirectedEdge> edges;
	private Set<IDirectedEdge>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		edges = new HashSet<IDirectedEdge>();
		adj = (Set<IDirectedEdge>[]) new HashSet[V];
		for (int v = 0; v < V; v++)
			adj[v] = new HashSet<IDirectedEdge>();
	}

	public Graph(Scanner in) {
		this(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String[] ws = in.nextLine().trim().split("(\\s*->\\s*)|\\s+");
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
	public Iterable<IDirectedEdge> edges() {
		return edges;
	}

	@Override
	public Iterable<IDirectedEdge> adj(int v) {
		return adj[v];
	}

	@Override
	public void addEdge(IDirectedEdge e) {
		edges.add(e);
		adj[e.from()].add(e);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vtxs: ").append(V);
		for (int v = 0; v < V; v++)
			sb.append('\n').append(v).append(": ").append(adj[v]);
		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wdigraph"));
		Graph g = new Graph(in);
		in.close();
		System.out.println(g);

	}

}
