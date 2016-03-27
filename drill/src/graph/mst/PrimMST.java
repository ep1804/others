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

	private boolean[] marked;
	private double[] distTo;
	private IEdge[] edgeTo;
	private List<IEdge> mst;
	private double totalWeight = 0;

	public PrimMST(IWeightedGraph g) {
		super(g);

		marked = new boolean[g.V()];
		distTo = new double[g.V()];
		edgeTo = new IEdge[g.V()];

		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		distTo[0] = 0.0;
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				double cmp = distTo[o1] - distTo[o2];
				if(cmp < 0) return -1;
				else if(cmp > 0) return 1;
				else return 0;
			}
		});
		q.add(0);
		
		while(!q.isEmpty()){
			int v = q.remove();
			marked[v] = true;
			
			for(IEdge e : g.adj(v)){
				int w = e.other(v);
				if(marked[w]) continue;
				
				if(distTo[w] > e.weight()){
					if(q.contains(w)) q.remove(w);
					distTo[w] = e.weight();
					edgeTo[w] = e;
					q.add(w);
				}
			}
		}
		
		mst = new ArrayList<IEdge>();
		for(int i=1; i<g.V();i++){
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
		Graph g = new Graph(in);
		in.close();
		System.out.println(g);

		PrimMST mst = new PrimMST(g);

		for (IEdge e : mst.inclusionOrder()) {
			System.out.print(e + " ");
		}
		System.out.println();

		System.out.println(mst.totalWeight());
	}

}
