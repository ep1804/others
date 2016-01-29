package graph.basic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BfsTree extends Tree {

	public BfsTree(IGraph g, int start) {
		super(g, start);
		
		List<Integer> q = new LinkedList<Integer>();
		
		boolean[] marked = new boolean[g.V()];
		Arrays.fill(marked, false);
		
		q.add(start);
		marked[start] = true;
		
		while(! q.isEmpty()){
			int v = q.remove(0);
			System.out.print(v + " ");
			
			for(int w : g.adj(v)){
				if(! marked[w]){
					q.add(w);
					marked[w] = true;
				}
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

		BfsTree tree = new BfsTree(g, 0);

	}

}
