package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CC implements IConnectedComponents {

	private final IGraph g;
	private int[] group;
	private int groups;

	public CC(IGraph g) {
		this.g = g;
		group = new int[g.V()];
		Arrays.fill(group, -1);

		groups = 0;
		for (int v = 0; v < g.V(); v++) {
			if (group[v] == -1) {
				dfsLoop(v);				
			}
			groups ++;
		}

		if (g.V() > 0)
			groups++;
	}

	private void dfsLoop(int v) {
		group[v] = groups;
		for(int w : g.adj(v)){
			if(group[w] != groups){
				group[w] = groups;
				dfsLoop(w);
			}			
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

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/ungraph1"));
		IGraph g = new Graph(in);
		in.close();
		System.out.println(g);

		IConnectedComponents cc = new CC(g);
		System.out.println(cc.components());
		for (int v = 1; v < g.V(); v++)
			System.out.println(cc.isConnected(v - 1, v));

	}

}
