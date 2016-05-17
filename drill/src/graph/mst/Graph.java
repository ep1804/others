package graph.mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph implements IWeightedGraph {

	private final int V;
	private List<IEdge> edges;
	private List<IEdge>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		edges = new ArrayList<IEdge>();
		adj = (List<IEdge>[]) new ArrayList[V];
		for(int i=0; i<V; i++)
			adj[i] = new ArrayList<IEdge>();
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
	public void addEdge(IEdge e) {
		edges.add(e);
		int v = e.either();
		adj[v].add(e);
		adj[e.other(v)].add(e);
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
		Scanner in = new Scanner(new File("input/graph/wungraph"));
		Graph g = new Graph(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String[] ws = in.nextLine().trim().split("\\s+|(\\s*-\\s*)");
			g.addEdge(new Edge(Integer.parseInt(ws[0]), Integer.parseInt(ws[1]), 
					Double.parseDouble(ws[2])));
		}
		in.close();
		
		System.out.println(g);

	}

}
