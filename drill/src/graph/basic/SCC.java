package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SCC implements IConnectedComponents {

	private IGraph g;
	private int groups;
	private int[] group;

	public SCC(IGraph g) {

		this.g = g;
		groups = 0;
		group = new int[g.V()];
		Arrays.fill(group, -1);

		IGraph rg = new Digraph(g.V());
		for (int v = 0; v < g.V(); v++) {
			for (int w : g.adj(v)) {
				rg.addEdge(w, v);
			}
		}

		for (int v : new TopologyOrder(rg).order()) {
			if (group[v] == -1) {
				dfsLoop(v);
				groups++;
			}
		}

		for (int i = 0; i < group.length; i++)
			System.out.print(group[i] + " ");
	}

	private void dfsLoop(int v) {
		group[v] = groups;
		for (int w : g.adj(v))
			if (group[w] == -1)
				dfsLoop(w);
	}

	@Override
	public int components() {
		return groups;
	}

	@Override
	public boolean isConnected(int v, int w) {
		return group[v] == group[w];
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/digraph1"));
		IGraph g = new Digraph(in);
		in.close();
		System.out.println(g);

		IConnectedComponents scc = new SCC(g);
	}

}
