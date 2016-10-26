package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SCC implements IConnectedComponents {

	private Digraph g;
	private int[] group;
	private int nGroup;
	
	public SCC(Digraph g) {
		this.g = g;
		group = new int[g.V()];
		nGroup = 1;
		
		Digraph rg = new Digraph(g.V());
		for(int v=0; v<g.V(); v++){
			for(int w : g.adj(v)){
				rg.addEdge(w, v);
			}
		}
		
		Iterable<Integer> ord = new TopologyOrder(rg).order();
		
		for(int v : ord){
			if(group[v] == 0){
				dfsLoop(v);
				nGroup ++;
			}
		}		
	}

	private void dfsLoop(int v) {
		if(group[v] != 0) return;
		group[v] = nGroup;
		
		for(int w : g.adj(v)){			
			dfsLoop(w);
		}
		
	}

	@Override
	public int components() {		
		return nGroup;
	}

	@Override
	public boolean isConnected(int v, int w) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int v=0; v<g.V(); v++)
			sb.append(group[v]).append(" ");
		return sb.toString();
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/digraph1"));
		Digraph g = new Digraph(in.nextInt());
		while (in.hasNextInt())
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);

		SCC scc = new SCC(g);
		
		System.out.println(scc);
	}

}
