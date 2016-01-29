package graph.sp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph implements IWeightedDigraph {

	private int V;
	private List<IDirectedEdge>[] adj;
	private List<IDirectedEdge> edges;
	
	
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		edges = new ArrayList<IDirectedEdge>();
		adj = (List<IDirectedEdge>[]) new ArrayList[V];
		for(int i=0; i<V; i++)
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
	
	public String toString(){
		StringBuilder sb = new StringBuilder();		
		sb.append("Vtxs: ").append(V).append('\n');
		for(int v=0; v<V; v++)
			sb.append(v).append(": ").append(adj[v]).append('\n');		
		return sb.toString();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int V = in.nextInt();
		Graph g = new Graph(V);
		
		while(in.hasNextInt()){
			g.addEdge(new Edge(in.nextInt(), in.nextInt(), in.nextDouble()));
		}
		in.close();

		System.out.println(g);

	}

}
