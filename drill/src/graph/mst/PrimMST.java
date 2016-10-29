package graph.mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimMST extends MinimumSpanningTree {

	private List<IEdge> mst;
	private double totalWeight;

	private double[] distTo;
	private IEdge[] edgeTo;

	public PrimMST(IWeightedGraph g) {
		super(g);

		mst = new ArrayList<IEdge>();
		totalWeight = 0;

		distTo = new double[g.V()];
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		edgeTo = new IEdge[g.V()];

		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {

				double cmp = distTo[o1] - distTo[o2];
				if (cmp > 0)
					return 1;
				else if (cmp < 0)
					return -1;
				else
					return 0;
			}
		});

		distTo[0] = 0;
		q.add(0);

		while (!q.isEmpty()) {
			int v = q.remove();

			if (v != 0) {
				distTo[v] = 0;
				mst.add(edgeTo[v]);
				totalWeight += edgeTo[v].weight();
			}

			for (IEdge e : g.adj(v)) {
				if (relax(e)) {
					int w = e.other(v);
					if (distTo[w] > 0){
						if(q.contains(w))
							q.remove(w);
						q.add(w);
					}
						
				}
			}
			
			System.out.println("Added vertex: " + v);
			print(distTo);
		}
	}

	private void print(double[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(" " + a[i]);
		System.out.println("\n");
	}

	private boolean relax(IEdge e) {

		int v = e.either();
		int w = e.other(v);

		if (distTo[v] > distTo[w]) { // v is edge with smaller distance-to
			int tmp = v;
			v = w;
			w = tmp;
		}

		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			return true;
		} else
			return false;
	}

	@Override
	public Iterable<IEdge> inclusionOrder() {
		return mst;
	}

	@Override
	public double totalWeight() {
		return totalWeight;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wungraph"));
		WeightedGraph g = new WeightedGraph(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String[] ws = in.nextLine().trim().split("\\s+|-");
			int v = Integer.parseInt(ws[0]);
			int w = Integer.parseInt(ws[1]);
			double weight = Double.parseDouble(ws[2]);
			g.addEdge(new Edge(v, w, weight));
		}
		in.close();

		System.out.println(g);

		PrimMST mst = new PrimMST(g);

		System.out.println(mst.totalWeight());
		System.out.println(mst.inclusionOrder());

	}

}
