package graph.fn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FordFulkersonMF extends MaxFlow {
	
	private double maxFlow;

	public FordFulkersonMF(IFlowNetwork g, int s, int t) {
		super(g, s, t);
		maxFlow = 0;

		while(augment());
		
		System.out.println(g);
	}

	private boolean augment() {

		boolean[] visited = new boolean[g.V()];
		IFlowEdge[] edgeTo = new IFlowEdge[g.V()];
		List<Integer> q = new ArrayList<Integer>();

		q.add(s);
		visited[s] = true;

		while (!q.isEmpty()) {
			int v = q.remove(0);

			for (IFlowEdge e : g.adj(v)) {
				int w = e.other(v);
				if (e.residualCapacityTo(w) > 0 && !visited[w]) {
					q.add(w);
					visited[w] = true;
					edgeTo[w] = e;
				}
			}

		}

		if (edgeTo[t] == null)
			return false;

		double min = Double.POSITIVE_INFINITY;
		for (int v = t; v != s; v = edgeTo[v].other(v)) {
			double delta = edgeTo[v].residualCapacityTo(v);
			if (delta < min)
				min = delta;
		}
		for (int v = t; v != s; v = edgeTo[v].other(v)) {
			edgeTo[v].addResidualFlowTo(v, min);
		}
		maxFlow += min;

		return true;
	}

	@Override
	public double maxFlow() {
		return maxFlow;
	}

	@Override
	public Iterable<Integer> minCut() {
		
		boolean[] visited = new boolean[g.V()];
		List<Integer> q = new ArrayList<Integer>();
		List<Integer> cut = new ArrayList<Integer>();
		
		q.add(s);
		visited[s]= true;
		cut.add(s);
		
		while(! q.isEmpty()){
			int v = q.remove(0);
			for(IFlowEdge e : g.adj(v)){
				int w = e.other(v);
				
				if(! visited[w] && e.residualCapacityTo(w) > 0){
					q.add(w);
					visited[w] = true;
					cut.add(w);
				}					
			}
		}
		
		return cut;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/flowNetwork"));
		FlowNetwork fn = new FlowNetwork(in);
		in.close();

		System.out.println(fn);

		FordFulkersonMF mf = new FordFulkersonMF(fn, 0, 7);
		
		System.out.println(mf.maxFlow());
		
		System.out.println(mf.minCut());

	}

}
