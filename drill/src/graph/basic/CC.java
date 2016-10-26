package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CC implements IConnectedComponents {

	private final IGraph g;

	private int[] group;
	private int nGroup; 

	public CC(IGraph g) {
		this.g = g;
		group = new int[g.V()];
		nGroup = 1;

		for(int v=0; v<g.V(); v++){
			if(group[v] == 0){
				dfsLoop(v);
				
				nGroup ++;
			}
			
		}
	}
	
	private void dfsLoop(int v){
		if(group[v] != 0) return;
		group[v] = nGroup;
		
		for(int w : g.adj(v)){
			if(group[w] == 0){ 
				dfsLoop(w);
			}
		}
	}

	@Override
	public int components() {
		return nGroup;
	}

	@Override
	public boolean isConnected(int v, int w) {
		return group[v] == group[w];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int v=0; v<g.V(); v++)
			sb.append(group[v]).append(" ");
		return sb.toString();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/ungraph1"));
		Graph g = new Graph(in.nextInt());
		while (in.hasNextInt())
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);

		CC cc = new CC(g);
		
		System.out.println(cc);
	}

}
