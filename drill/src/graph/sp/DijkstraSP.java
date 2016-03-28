package graph.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraSP extends ShortestPath {


	public DijkstraSP(IWeightedDigraph g, int start) {
		super(g, start);
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(start);
		
		while(!q.isEmpty()){
			int v = q.remove();
			
			for(IDirectedEdge e: g.adj(v)){
				if(relax(e)){
					int w = e.to();
					if(q.contains(w)) q.remove(w);
					q.add(w);
				}
			}
		}
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wdigraph"));
		Graph g = new Graph(in);
		in.close();
		System.out.println(g);

		DijkstraSP sp = new DijkstraSP(g, 0);
		System.out.println();
		System.out.println(sp);
	}
}
