package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CC implements IConnectedComponents {

	private int groups;
	private int[] group;

	public CC(Graph g) {

		group = new int[g.V()];

		groups = 1;
		for (int v = 0; v < g.V(); v++) {
			if (group[v] == 0) {
				markGroup(v, g);
				groups++;
			}
		}
		groups --;
	}

	private void markGroup(int v, Graph g) {
		if (group[v] != 0)
			return;
		group[v] = groups;
		for (int w : g.adj(v)) {
			markGroup(w, g);
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
		for (int i = 1; i < group.length; i++) {
			sb.append(" ").append(group[i]);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/ungraph1"));
		int V = in.nextInt();
		Graph g = new Graph(V);
		for (int v = 0; v < V; v++) {
			g.addEdge(in.nextInt(), in.nextInt());
		}
		in.close();

		System.out.println(g);

		CC cc = new CC(g);

		System.out.println(cc);

		System.out.println(cc.components());

		for (int v = 1; v < g.V(); v++) {
			System.out.println((v - 1) + " - " + v + ": " + cc.isConnected(v - 1, v));
		}

	}

}
