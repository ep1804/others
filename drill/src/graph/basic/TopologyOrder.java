package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TopologyOrder extends Order {

	private List<Integer> order;

	public TopologyOrder(IGraph g) {
		super(g);
		boolean[] marked = new boolean[g.V()];
		order = new ArrayList<Integer>();

		for (int v = 0; v < g.V(); v++)
			if (!marked[v])
				dfsLoop(v, marked);
		
		Collections.reverse(order);
	}

	private void dfsLoop(int v, boolean[] marked) {
		if (marked[v])
			return;
		marked[v] = true;

		for (int w : g.adj(v)) {
			if (!marked[w])
				dfsLoop(w, marked);
		}

		order.add(v);
	}

	@Override
	public Iterable<Integer> order() {
		return order;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/digraph2"));
		int V = in.nextInt();
		Digraph g = new Digraph(V);
		while (in.hasNextInt())
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);

		TopologyOrder ord = new TopologyOrder(g);
		System.out.println(ord.order());

	}

}
