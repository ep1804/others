package graph.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraSp extends ShortestPath {

	public DijkstraSp(IWeightedDigraph g, int start) {
		super(g, start);

		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				double cmp = distTo[o1] - distTo[o2];
				if (cmp < 0)
					return -1;
				else if (cmp > 0)
					return 1;
				else
					return 0;
			}
		});

		q.add(start);

		while (!q.isEmpty()) {
			int v = q.remove();

			for (IDirectedEdge e : g.adj(v)) {
				if (relax(e)) {
					int w = e.to();
					if (q.contains(w))
						q.remove(w);
					q.add(w);
				}
			}
		}

		for (int i = 0; i < g.V(); i++) {
			System.out.println(edgeTo[i]);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wdigraph"));
		Digraph g = new Digraph(in.nextInt());
		in.nextLine();
		while (in.hasNextLine()) {
			String[] ws = in.nextLine().trim().split("(\\s*->\\s*)|\\s+");
			g.addEdge(new Edge(Integer.parseInt(ws[0]), Integer.parseInt(ws[1]), Double.parseDouble(ws[2])));
		}
		in.close();

		System.out.println(g);

		new DijkstraSp(g, 0);

	}

}
