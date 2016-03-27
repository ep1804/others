package graph.mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class KruskalMST extends MinimumSpanningTree {

	private List<IEdge> mst;
	private double totalWeight;

	public KruskalMST(IWeightedGraph g) {
		super(g);

		IEdge[] es = new IEdge[g.E()];
		int i = 0;
		for (IEdge e : g.edges()) {
			es[i] = e;
			i++;
		}

		Arrays.sort(es, new Comparator<IEdge>() {
			@Override
			public int compare(IEdge o1, IEdge o2) {
				double cmp = o1.weight() - o2.weight();
				if (cmp < 0)
					return -1;
				else if (cmp > 0)
					return 1;
				else
					return 0;
			}
		});

		mst = new LinkedList<IEdge>();

		UF uf = new UF(g.V());

		for (i = 0; i < g.E(); i++) {
			IEdge e = es[i];
			int v = e.either();
			int w = e.other(v);
			if (!uf.isConnected(v, w)) {
				uf.connect(v, w);
				mst.add(e);
				totalWeight += e.weight();
			}
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

	private class UF {
		private int[] parent;

		public UF(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++)
				parent[i] = i;
		}

		public void connect(int v, int w) {
			while (parent[v] != v)
				v = parent[v];
			while (parent[w] != w)
				w = parent[w];
			parent[v] = w;
		}

		public boolean isConnected(int v, int w) {
			while (parent[v] != v)
				v = parent[v];
			while (parent[w] != w)
				w = parent[w];
			return v == w;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wungraph"));
		Graph g = new Graph(in);
		in.close();
		System.out.println(g);

		KruskalMST mst = new KruskalMST(g);

		for (IEdge e : mst.inclusionOrder()) {
			System.out.print(e + " ");
		}
		System.out.println();

		System.out.println(mst.totalWeight());
	}

}
