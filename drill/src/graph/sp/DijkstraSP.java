package graph.sp;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraSP extends ShortestPath {

	public DijkstraSP(IWeightedDigraph g, int start) {
		super(g, start);
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				double cmp = distTo[o1] - distTo[o2];
				if(cmp < 0) return -1;
				else if(cmp > 0) return 1;
				else return 0;
			}}); 
		
		q.add(start);
		
		while(!q.isEmpty()){
			int v = q.remove();
			for(IDirectedEdge e : g.adj(v)){
				int w = e.to();
				if(relax(e)){
					if(q.contains(w))
						q.remove(w);
				}
				q.add(w);
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int V = in.nextInt();
		Graph g = new Graph(V);
		
		while(in.hasNextInt()){
			g.addEdge(new Edge(in.nextInt(), in.nextInt(), in.nextDouble()));
		}
		in.close();

		System.out.println(g);
		
		DijkstraSP sp = new DijkstraSP(g, 0);
		
		System.out.println(sp);

	}

}
