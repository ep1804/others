package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BfsTree extends Tree {

	private int[] vtxTo;
	
	public BfsTree(IGraph g, int start) {
		super(g, start);
		
		boolean[] inQueue = new boolean[g.V()];
		vtxTo = new int[g.V()];
		for (int v = 0; v < g.V(); v++)
			vtxTo[v] = v;
		
		List<Integer> q = new ArrayList<Integer>();
		
		q.add(start);
		inQueue[start] = true;
		
		while(! q.isEmpty()){
			int v = q.remove(0);
			
			for(int w : g.adj(v)){
				if(! inQueue[w]){
					vtxTo[w] = v;
					
					q.add(w);
					inQueue[w] = true;
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

		BfsTree tree = new BfsTree(g, 0);

		System.out.println(tree);

	}

}
