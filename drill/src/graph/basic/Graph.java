package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Graph implements IGraph {

	private final int V;
	private int E;
	private Set<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(int vertices) {
		this.V = vertices;
		adj = (Set<Integer>[]) new HashSet[vertices];
		for (int v = 0; v < V; v++)
			adj[v] = new HashSet<Integer>();
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vtxs: ").append(V);
		for (int v = 0; v < V; v++)
			sb.append('\n').append(v).append(": ").append(adj[v]);
		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(new File("input/graph/ungraph1"));
		int n = in.nextInt();
		Graph g = new Graph(n);
		for(int i=0; i<n; i++)
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);
	}

}
