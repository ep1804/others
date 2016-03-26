package graph.basic;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Graph implements IGraph {

	private final int V;
	private int E;
	private Set<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		adj = (Set<Integer>[]) new HashSet[V];
		for (int v = 0; v < V; v++)
			adj[v] = new HashSet<Integer>();
	}

	public Graph(Scanner in) {
		this(in.nextInt());

		while (in.hasNextInt()) {
			addEdge(in.nextInt(), in.nextInt());
		}
	}

	@Override
	public int V() {
		return V;
	}

	@Override
	public int E() {
		return E;
	}

	@Override
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	@Override
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vtxs: ").append(V);
		sb.append('\n').append("Edges: ").append(E);
		for (int v = 0; v < V; v++)
			sb.append('\n').append(v).append(": ").append(adj[v]);
		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/ungraph1"));
		Graph g = new Graph(in);
		in.close();
		System.out.println(g);
	}

}
