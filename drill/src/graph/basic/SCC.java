package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SCC implements IConnectedComponents {

	private int groups;
	private int[] group;
	private List<Integer> rpo;

	public SCC(IGraph g) {

		IGraph rg = new Digraph(g.V());
		for (int v = 0; v < g.V(); v++) {
			for (int w : g.adj(v)) {
				rg.addEdge(w, v);
			}
		}
		System.out.println(rg);

		rpo = new ArrayList<Integer>();
		boolean[] marked = new boolean[g.V()];

		for (int v = 0; v < rg.V(); v++)
			if (!marked[v])
				buildPostOrder(rg, v, marked);

		Collections.reverse(rpo);

		System.out.println(rpo);

		group = new int[g.V()];

		groups = 1;
		for (int v : rpo) {
			if (group[v] == 0) {
				markGroup(g, v);
				groups++;
			}
		}
		groups--;
	}

	private void buildPostOrder(IGraph g, int v, boolean[] marked) {
		marked[v] = true;

		for (int w : g.adj(v)) {
			if (!marked[w])
				buildPostOrder(g, w, marked);
		}

		rpo.add(v);
	}

	private void markGroup(IGraph g, int v) {
		group[v] = groups;

		for (int w : g.adj(v)) {
			if (group[w] == 0)
				markGroup(g, w);
		}
	}

	@Override
	public int components() {
		return groups;
	}

	@Override
	public boolean isConnected(int v, int w) {
		return group[v] == group[w];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(group[0]);
		for (int i = 1; i < group.length; i++)
			sb.append(' ').append(group[i]);
		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/digraph1"));
		int V = in.nextInt();
		Digraph g = new Digraph(V);
		while (in.hasNextInt())
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);

		SCC scc = new SCC(g);

		System.out.println(scc);
	}

}
