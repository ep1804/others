package graph.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Digraph implements IWeightedDigraph {

	private final int V;
	private List<IDirectedEdge> edges;
	private List<IDirectedEdge>[] adj;

	@SuppressWarnings("unchecked")
	public Digraph(int V) {
		this.V = V;
		edges = new ArrayList<IDirectedEdge>();
		adj = (List<IDirectedEdge>[]) new ArrayList[V];
		for (int i = 0; i < V; i++)
			adj[i] = new ArrayList<IDirectedEdge>();
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
		for (int i = 0; i < V; i++)
			sb.append('\n').append(i).append(": ").append(adj[i]);
		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wdigraph"));
		Digraph g = new Digraph(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String[] ws = in.nextLine().trim().split("(\\s*->\\s*)|\\s+");
			g.addEdge(new Edge(Integer.parseInt(ws[0]), Integer.parseInt(ws[1]), Double.parseDouble(ws[2])));
		}
		in.close();
		
		System.out.println(g);

	}

}
