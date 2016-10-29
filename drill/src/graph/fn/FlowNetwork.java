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
		adj = (List<IFlowEdge>[]) new ArrayList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayList<IFlowEdge>();
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
		Scanner in = new Scanner(new File("input/graph/flowNetwork"));
		FlowNetwork g = new FlowNetwork(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] ws = line.trim().split("(\\s*->\\s*)|(\\s*/\\s*)|\\s+");
			int v = Integer.parseInt(ws[0]);
			int w = Integer.parseInt(ws[1]);
			double flow = Double.parseDouble(ws[2]);
			double capacity = Double.parseDouble(ws[3]);
			g.addEdge(new FlowEdge(v, w, flow, capacity));
		}
		in.close();

		System.out.println(g);

	}

}
