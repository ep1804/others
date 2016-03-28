package graph.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BellmanFordSP extends ShortestPath {

	public BellmanFordSP(IWeightedDigraph g, int start) {
		super(g, start);

		for (int v = 0; v < g.V(); v++) {
			for (IDirectedEdge e : g.edges()) {
				relax(e);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/wdigraph"));
		Graph g = new Graph(in);
		in.close();
		System.out.println(g);

		BellmanFordSP sp = new BellmanFordSP(g, 0);
		System.out.println();
		System.out.println(sp);

	}

}
