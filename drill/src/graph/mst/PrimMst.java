package graph.mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimMst extends MinimumSpanningTree {

	private List<IEdge> mst;
	private double totalWeight;
	
	private boolean[] included;
	private IEdge[] edgeTo;
	private double[] distTo;

	public PrimMst(IWeightedGraph g, int start) {
		super(g);
		mst = new ArrayList<IEdge>();
		included = new boolean[g.V()];
		edgeTo = new IEdge[g.V()];
		distTo = new double[g.V()];
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);

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

		distTo[start] = 0;
		q.add(start);

		while (!q.isEmpty()) {
			int v = q.remove();
			included[v]= true;

			for (IEdge e : g.adj(v)) {
				int w = e.other(v);
				
				if(included[w]) continue;

				if (distTo[w] > e.weight()) {
					distTo[w] = e.weight();
					edgeTo[w] = e;
					if(q.contains(w))
						q.remove(w);
					q.add(w);
				}
			}
		}
		
		for(int i=0; i<g.V(); i++){
			if(i == start) continue;
			mst.add(edgeTo[i]);
			totalWeight += edgeTo[i].weight();
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

		PrimMst mst = new PrimMst(g, 0);
		System.out.println(mst.inclusionOrder());
		System.out.println(mst.totalWeight());
	}

}
