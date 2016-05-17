package graph.mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KruskalMst extends MinimumSpanningTree {

	private List<IEdge> mst;
	private double totalWeight;

	public KruskalMst(IWeightedGraph g) {
		super(g);

		mst = new ArrayList<IEdge>();

		PriorityQueue<IEdge> q = new PriorityQueue<IEdge>();
		q.addAll((Collection<? extends IEdge>) g.edges());

		Conn con = new Conn(g.V());

		while (!q.isEmpty()) {
			IEdge e = q.remove();
			int v = e.either();
			int w = e.other(v);
			if (!con.isConnected(v, w)) {
				mst.add(e);
				totalWeight += e.weight();
				con.connect(v, w);
			}

			System.out.println("Checking " + e);
		}

	}

	@Override
	public Iterable<IEdge> inclusionOrder() {
		return mst;
	}

	@Override
	public double totalWeight() {
		return totalWeight;
	}

	private class Conn {

		private int[] parent;

		private int root(int v) {
			int res = parent[v];
			while (parent[res] != res)
				res = parent[res];
			return res;
		}

		public Conn(int V) {
			parent = new int[V];
			for (int i = 0; i < V; i++)
				parent[i] = i;
		}

		public void connect(int v, int w) {
			parent[root(v)] = root(w);

		}

		public boolean isConnected(int v, int w) {
			return root(v) == root(w);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wungraph"));
		Graph g = new Graph(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String[] ws = in.nextLine().trim().split("\\s+|(\\s*-\\s*)");
			g.addEdge(new Edge(Integer.parseInt(ws[0]), Integer.parseInt(ws[1]), Double.parseDouble(ws[2])));
		}
		in.close();

		System.out.println(g);

		KruskalMst mst = new KruskalMst(g);
		System.out.println(mst.inclusionOrder());
		System.out.println(mst.totalWeight());

	}

}
