package graph.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph implements IGraph {

	private final int V;
	private List<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public Graph(int V){
		this.V = V;
		adj = (List<Integer>[])new ArrayList[V];
		for(int i=0; i<V; i++)
			adj[i] = new ArrayList<Integer>();
	}
	
	@Override
	public int V() {
		return V;
	}

	@Override
	public int E() {
		int sum = 0;
		for(int i=0; i<V; i++)
			sum += adj[i].size();
		return sum / 2;
	}

	@Override
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	@Override
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Vtxs: ").append(V).append('\n');
		for(int i=0; i<V; i++)
			sb.append(i).append(": ").append(adj[i]).append('\n');
		return sb.toString();
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int V = in.nextInt();
		Graph g = new Graph(V);
		for(int i=0; i<V; i++){
			g.addEdge(in.nextInt(), in.nextInt());
		}
		in.close();

		System.out.println(g);
	}

}
