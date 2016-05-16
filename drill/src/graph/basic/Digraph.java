package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Digraph implements IGraph {

	private final int V;
	private List<Integer>[] adj;
	private int size;

	@SuppressWarnings("unchecked")
	public Digraph(int V) {
		this.V = V;
		adj = (List<Integer>[]) new ArrayList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayList<Integer>();
		size = 0;
	}

	@Override
	public int V() {
		return V;
	}

	@Override
	public int E() {
		return size;
	}

	@Override
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	@Override
	public void addEdge(int v, int w) {
		adj[v].add(w);
		size++;
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
		Scanner in = new Scanner(new File("input/graph/digraph1"));
		int V = in.nextInt();
		Digraph g = new Digraph(V);
		while (in.hasNextInt())
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);

		BfsTree bt = new BfsTree(g, 0);

		System.out.println(bt.pathTo(4));
		System.out.println(bt.pathTo(3));

		DfsTree dt = new DfsTree(g, 0);
		System.out.println(dt.pathTo(4));
		System.out.println(dt.pathTo(3));

	}

}
