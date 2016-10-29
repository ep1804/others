package graph.sp;

import java.io.File;
import java.io.FileNotFoundException;
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
				if(cmp > 0)
					return 1;
				else if(cmp < 0)
					return -1;
				else 
					return 0;
			}});
		
		q.add(start);
		
		while(! q.isEmpty()){
			int v = q.remove();
			
			for(IDirectedEdge e : g.adj(v)){
				if(relax(e)){
					int w = e.to();
					if(q.contains(w))
						q.remove(w);
					q.add(w);
				}
			}			
		}
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wdigraph"));
		WeightedDigraph g = new WeightedDigraph(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] ws = line.trim().split("(\\s*->\\s*)|\\s+");
			int v = Integer.parseInt(ws[0]);			
			int w = Integer.parseInt(ws[1]);
			double weight = Double.parseDouble(ws[2]);
			g.addEdge(new DirectedEdge(v, w, weight));
		}
		in.close();

		System.out.println(g);

		DijkstraSP sp = new DijkstraSP(g, 0);
		
		System.out.println(sp);
		
	}

}
