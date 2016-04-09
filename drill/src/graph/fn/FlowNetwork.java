package graph.fn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FlowNetwork implements IFlowNetwork {

	private final int V;
	private Set<IFlowEdge> edges;
	private Set<IFlowEdge>[] adj;

	@SuppressWarnings("unchecked")
	public FlowNetwork(int V) {
		this.V = V;
		edges = new HashSet<IFlowEdge>();
		adj = (Set<IFlowEdge>[]) new HashSet[V];
		for (int v = 0; v < V; v++)
			adj[v] = new HashSet<IFlowEdge>();
	}

	public FlowNetwork(Scanner in) {
		this(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String[] ws = in.nextLine().trim().split("(\\s*->\\s*)|(\\s*\\/\\s*)|\\s+");

			Edge e = new Edge(Integer.parseInt(ws[0]), Integer.parseInt(ws[1]), Double.parseDouble(ws[2]),
					Double.parseDouble(ws[3]));
			addEdge(e);
		}
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
		sb.append("Vtxs: ").append(V);
		for (int v = 0; v < V; v++)
			sb.append('\n').append(v).append(": ").append(adj[v]);

		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/flowNetwork"));
		FlowNetwork fn = new FlowNetwork(in);
		in.close();

		System.out.println(fn);

	}

}
