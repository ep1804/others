package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DfsTree extends Tree {

	private int[] vtxTo;

	public DfsTree(IGraph g, int start) {
		super(g, start);

		vtxTo = new int[g.V()];
		for (int v = 0; v < g.V(); v++) {
			vtxTo[v] = v;
		}

		boolean[] marked = new boolean[g.V()];
		
		dfsLoop(start, marked);

	}

	private void dfsLoop(int v, boolean[] marked) {
		if(marked[v]) return;
		marked[v] = true;
		
		for(int w : g.adj(v)){
			if(! marked[w]){
				vtxTo[w] = v;
				dfsLoop(w, marked);
			}
		}
		
	}

	@Override
	public int vertexTo(int v) {
		return vtxTo[v];
	}

	@Override
	public Iterable<Integer> pathTo(int v) {
		List<Integer> path = new ArrayList<Integer>();
		path.add(v);
		
		while(vtxTo[v] != v){
			path.add(vtxTo[v]);
			v = vtxTo[v];
		}
		
		return path;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/ungraph1"));
		int n = in.nextInt();
		Graph g = new Graph(n);
		for (int i = 0; i < n; i++)
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);

		DfsTree tree = new DfsTree(g, 0);

		for (int v = 0; v < g.V(); v++) {
			System.out.println(v + " -> " + tree.vertexTo(v));
		}

		for (int v = 0; v < g.V(); v++) {
			System.out.println(v + ": " + tree.pathTo(v));
		}

	}

}
