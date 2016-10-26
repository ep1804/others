package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Digraph implements IGraph {

	private final int V;
	private int E;

	private List<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Digraph(int V) {
		this.V = V;
		adj = (List<Integer>[]) new ArrayList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayList<Integer>();
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vtxs: ").append(V).append('\n');
		for (int i = 0; i < V; i++)
			sb.append(i).append(": ").append(adj(i)).append('\n');

		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/digraph1"));
		Digraph g = new Digraph(in.nextInt());
		while (in.hasNextInt())
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);

	}

}
