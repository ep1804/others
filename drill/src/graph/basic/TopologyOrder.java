package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TopologyOrder extends Order {

	private boolean[] marked;
	private List<Integer> ord; 

	public TopologyOrder(IGraph g) {
		super(g);

		marked = new boolean[g.V()];
		
		ord = new ArrayList<Integer>();
		
		for (int v = 0; v < g.V(); v++)
			if (!marked[v])
				dfsLoop(v);
		
		Collections.reverse(ord);
	}

	private void dfsLoop(int v) {		
		marked[v] = true;
		for(int w : g.adj(v)){
			if(! marked[w])
				dfsLoop(w);
		}
		ord.add(v);
	}

	@Override
	public Iterable<Integer> order() {
		return ord;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/digraph2"));
		IGraph g = new Digraph(in);
		in.close();
		System.out.println(g);

		TopologyOrder ord = new TopologyOrder(g);
		System.out.println(ord.order());
	}

}
