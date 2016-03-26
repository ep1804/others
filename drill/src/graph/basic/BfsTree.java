package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BfsTree extends Tree {

	private boolean[] done;
	private int[] vtxTo;

	public BfsTree(IGraph g, int start) {
		super(g, start);

		done = new boolean[g.V()];
		vtxTo = new int[g.V()];
		for (int v = 0; v < g.V(); v++)
			vtxTo[v] = v;

		for (int v = 0; v < g.V(); v++)
			if (!done[v])
				build(v);

	}

	private void build(int start) {
		List<Integer> q = new LinkedList<Integer>();

		q.add(start);
		done[start] = true;

		while (!q.isEmpty()) {
			int v = q.remove(0);
			for (int w : g.adj(v)) {
				if (!done[w]) {
					vtxTo[w] = v;
					q.add(w);
					done[w] = true;
				}
			}
		}
	}

	@Override
	public int vertexTo(int v) {
		return vtxTo[v];
	}

	@Override
	public Iterable<Integer> pathTo(int v) {		
		List<Integer> res = new LinkedList<Integer>();
		
		while(v != vtxTo[v]){
			res.add(0, v);
			v = vtxTo[v];
		}
		
		res.add(0, v);
		
		return res;
	}

	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = new Scanner(new File("input/graph/ungraph1"));
//		IGraph g = new Graph(in);
		Scanner in = new Scanner(new File("input/graph/digraph1"));
		IGraph g = new Digraph(in);
		in.close();

		System.out.println(g);

		BfsTree tree = new BfsTree(g, 0);
		for (int v = 0; v < g.V(); v++)
			System.out.println("vtx to " + v + ": " + tree.vertexTo(v));
		for (int v = 0; v < g.V(); v++)
			System.out.println("path to " + v + ": " + tree.pathTo(v));

	}

}
