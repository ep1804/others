package graph.fn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FordFulkersonMF extends MaxFlow {

	private double maxFlow;
	private List<Integer> minCut;
	
	public FordFulkersonMF(IFlowNetwork g, int s, int t) {
		super(g, s, t);
		
		double delta;
		maxFlow = 0;
		
		while((delta = augment()) > 0){
			maxFlow += delta;
		}
		
		minCut = new ArrayList<Integer>();
		
		List<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[g.V()];
		
		q.add(s);
		visited[s] = true;
		
		while(! q.isEmpty()){
			int v = q.remove(0);
			minCut.add(v);
			
			for(IFlowEdge e : g.adj(v)){
				int w = e.other(v);
				if(! visited[w] && e.residualCapacityTo(w) > 0){
					q.add(w);
					visited[w] = true;
				}
			}
		}
		
		Collections.sort(minCut);
	}

	private double augment() {
		
		// based on BFS path search, find augmenting path and augment
		List<Integer> q = new LinkedList<Integer>();
		
		boolean[] visited = new boolean[g.V()];
		IFlowEdge[] edgeTo = new FlowEdge[g.V()];
		
		q.add(s);
		visited[s] = true;
		
		while(! q.isEmpty()){		
			int v = q.remove(0);
			
			if(v == t){
				
				int vc = t; 								// vertex cursor
				double delta = Double.POSITIVE_INFINITY; 	// minimum residual capacity in the path
				
				while(vc != s){
					IFlowEdge e = edgeTo[vc];
					delta = Math.min(delta, e.residualCapacityTo(vc));
					vc = e.other(vc);
					
//					System.out.println(e);
				}
				
//				System.out.println(delta);
				
				vc = t;
				while(vc != s){
					IFlowEdge e = edgeTo[vc];
					e.addResidualFlowTo(vc, delta);
					vc = e.other(vc);
					
//					System.out.println(e);
				}
				
				return delta;
			}
			
			for(IFlowEdge e : g.adj(v)){
				int w = e.other(v);
				if(! visited[w] && e.residualCapacityTo(w) > 0){
					q.add(w);
					visited[w] = true;
					edgeTo[w] = e;
				}
			}
		}
		
		return 0;
		
	}

	@Override
	public double maxFlow() {
		return maxFlow;
	}

	@Override
	public Iterable<Integer> minCut() {
		return minCut;
	}

	public static void main(String[] args) throws FileNotFoundException {
		FlowNetwork g = new FlowNetwork(new Scanner(new File("input/graph/flowNetwork")));
		System.out.println(g);
		
		FordFulkersonMF mf = new FordFulkersonMF(g, 0, 7);
		
		System.out.println("Max Flow: " + mf.maxFlow());
		System.out.println("Min Cut: " + mf.minCut());
		
	}

}
