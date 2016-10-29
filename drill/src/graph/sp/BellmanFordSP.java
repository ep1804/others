package graph.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BellmanFordSP extends ShortestPath {

	public BellmanFordSP(IWeightedDigraph g, int start) {
		super(g, start);

		for (int i = 0; i < 10; i++) {
			for (int v = 0; v < g.V(); v++) {
				for (IDirectedEdge e : g.adj(v))
					relax(e);
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

		BellmanFordSP sp = new BellmanFordSP(g, 0);

		System.out.println(sp);

	}

}
