package graph.fn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlowNetwork implements IFlowNetwork {

	private final int V;
	private List<IFlowEdge> edges;
	private List<IFlowEdge>[] adj;
	
	@SuppressWarnings("unchecked")
	public FlowNetwork(int V) {
		this.V = V;
		edges = new ArrayList<IFlowEdge>();
		adj = (List<IFlowEdge>[])new ArrayList[V];
		for(int v=0; v<V; v++)
			adj[v] = new ArrayList<IFlowEdge>();
	}
	
	public FlowNetwork(Scanner in){		
		this(in.nextInt());
		in.nextLine();
		while(in.hasNextLine()){
			String[] ws = in.nextLine().trim().split("\\s*/\\s*|(\\s*->\\s*)|(\\s+)");
			addEdge(new FlowEdge(Integer.parseInt(ws[0]), Integer.parseInt(ws[1]), Double.parseDouble(ws[2]), Double.parseDouble(ws[3])));
		}
		
		in.close();
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
	public Iterable<IFlowEdge> edges() {
		return edges;
	}

	@Override
	public Iterable<IFlowEdge> adj(int v) {
		return adj[v];
	}

	@Override
	public void addEdge(IFlowEdge e) {
		edges.add(e);
		adj[e.from()].add(e);
		adj[e.to()].add(e);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Vtxs: ").append(V).append('\n');
		for(int i=0; i<V; i++)
			sb.append(i).append(": ").append(adj[i]).append('\n');
		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		FlowNetwork g = new FlowNetwork(new Scanner(new File("input/graph/flowNetwork")));
		System.out.println(g);
	}
	
}
