package graph.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TopologySP extends ShortestPath {

	private boolean[] marked;
	private List<Integer> order;

	public TopologySP(IWeightedDigraph g, int start) {
		super(g, start);

		marked = new boolean[g.V()];
		order = new LinkedList<Integer>();

		dfsLoop(start);
		Collections.reverse(order);

		for (int v : order) {
			for (IDirectedEdge e : g.adj(v)) {
				relax(e);
			}
		}
	}

	private void dfsLoop(int v) {
		marked[v] = true;
		for (IDirectedEdge e : g.adj(v)) {
			int w = e.to();
			if (!marked[w])
				dfsLoop(w);
		}
		order.add(v);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wdigraph"));
		Graph g = new Graph(in);
		in.close();
		System.out.println(g);

		TopologySP sp = new TopologySP(g, 0);
		System.out.println();
		System.out.println(sp);
	}

}
