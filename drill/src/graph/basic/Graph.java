package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph implements IGraph {

	private final int V;
	private List<Integer>[] adj;
	private int E;

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (List<Integer>[]) new ArrayList[V];
		for (int i = 0; i < V; i++)
			adj[i] = new ArrayList<Integer>();
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
		sb.append("Vtxs: ").append(V).append('\n');
		for (int i = 0; i < V; i++)
			sb.append(i).append(": ").append(adj(i)).append('\n');

		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/ungraph1"));
		Graph g = new Graph(in.nextInt());
		while (in.hasNextInt())
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);
	}

}
