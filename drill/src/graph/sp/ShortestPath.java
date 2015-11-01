package graph.sp;

import java.util.Arrays;

public abstract class ShortestPath {
	protected IWeightedDigraph g;
	protected int start;
	
	protected double[] distTo;
	protected IDirectedEdge[] edgeTo;

	public ShortestPath(IWeightedDigraph g, int start) {
		this.g = g;
		this.start = start;
		
		distTo = new double[g.V()];
		edgeTo = new IDirectedEdge[g.V()];
		
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		distTo[start] = 0;
	}
	
	public double distTo(int v){
		return distTo[v];
	}
	
	public IDirectedEdge edgeTo(int v){
		return edgeTo[v];
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<g.V(); i++){
			sb.append(i).append(": ");
			sb.append("distTo: ").append(distTo(i)).append(", edgeTo: ");
			if(edgeTo(i) != null)
				sb.append(edgeTo(i)).append('\n');
			else
				sb.append("NA\n");
		}
		
		return sb.toString();
	}
	
	protected boolean relax(IDirectedEdge e){
		return false;
	}
}
