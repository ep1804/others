package graph.basic;

import java.util.Arrays;
import java.util.Scanner;

public class DfsTree extends Tree {

	public DfsTree(Graph g, int start){
		super(g, start);
		
		marked = new boolean[g.V()];
		Arrays.fill(marked, false);
		
		dfsLoop(start);
	}
	
	private boolean[] marked;
	
	private void dfsLoop(int v){
		marked[v] = true;
		System.out.print(v + " ");
			
		for(int w : g.adj(v)){
			if(! marked[w]){
				dfsLoop(w); 
			}
		}
	}
	
	@Override
	public int vertexTo(int v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Integer> pathTo(int v) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int V = in.nextInt();
		Graph g = new Graph(V);
		for(int i=0; i<V; i++){
			g.addEdge(in.nextInt(), in.nextInt());
		}
		in.close();
		
		System.out.println(g);

		DfsTree tree = new DfsTree(g, 0);

	}

}
