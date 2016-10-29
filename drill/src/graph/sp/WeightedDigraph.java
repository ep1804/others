package graph.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeightedDigraph implements IWeightedDigraph {

	private final int V;
	private List<IDirectedEdge> edges;
	private List<IDirectedEdge>[] adj;

	
	@SuppressWarnings("unchecked")
	public WeightedDigraph(int V) {
		this.V = V;
		edges = new ArrayList<IDirectedEdge>(); 
		adj = (List<IDirectedEdge>[]) new ArrayList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayList<IDirectedEdge>();
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
		sb.append("Vtxs: ").append(V).append('\n');
		for (int i = 0; i < V; i++)
			sb.append(i).append(": ").append(adj(i)).append('\n');

		return sb.toString();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wdigraph"));
		WeightedDigraph g = new WeightedDigraph(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] ws = line.trim().split("(\\s*->\\s*)|\\s+");
			int v = Integer.parseInt(ws[0]);			
			int w = Integer.parseInt(ws[1]);
			double weight = Double.parseDouble(ws[2]);
			g.addEdge(new DirectedEdge(v, w, weight));
		}
		in.close();

		System.out.println(g);
	}

}
