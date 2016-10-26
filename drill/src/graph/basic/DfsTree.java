package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DfsTree extends Tree {

	private boolean[] visited;
	private int[] vtxTo;

	public DfsTree(IGraph g, int start) {
		super(g, start);

		visited = new boolean[g.V()];
		vtxTo = new int[g.V()];
		for (int v = 0; v < g.V(); v++)
			vtxTo[v] = v;

		dfsLoop(start);		
	}

	private void dfsLoop(int v) {
		if (visited[v])
			return;

		visited[v] = true;

		for (int w : g.adj(v)) {
			if (!visited[w]) {
				vtxTo[w] = v;
				dfsLoop(w);
			}
		}
	}

	@Override
	public int vertexTo(int v) {
		return vtxTo[v];
	}

	@Override
	public Iterable<Integer> pathTo(int v) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/ungraph1"));
		Graph g = new Graph(in.nextInt());
		while (in.hasNextInt())
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);

		DfsTree tree = new DfsTree(g, 0);

		System.out.println(tree);

	}

}
