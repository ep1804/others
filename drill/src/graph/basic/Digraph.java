package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Digraph implements IGraph {

	private final int V;
	private int E;
	private final Set<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Digraph(int V) {
		this.V = V;
		E = 0;
		adj = (Set<Integer>[]) new HashSet[V];
		for (int v = 0; v < V; v++)
			adj[v] = new HashSet<Integer>();
	}

	public Digraph(Scanner in) {
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
		Scanner in = new Scanner(new File("input/graph/digraph1"));
		IGraph g = new Digraph(in);
		in.close();

		System.out.println(g);

	}

}
