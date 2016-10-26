package graph.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TopologyOrder extends Order {

	private boolean[] visited;
	private List<Integer> order;

	public TopologyOrder(IGraph g) {
		super(g);

		visited = new boolean[g.V()];
		order = new ArrayList<Integer>();

		for (int v = 0; v < g.V(); v++)
			if (!visited[v])
				traverse(v);

		Collections.reverse(order);
	}

	private void traverse(int v) {
		if (visited[v])
			return;
		visited[v] = true;

		for (int w : g.adj(v)) {
			if (!visited[w])
				traverse(w);
		}

		order.add(v);
	}

	@Override
	public Iterable<Integer> order() {		
		return order;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/graph/digraph2"));
		Digraph g = new Digraph(in.nextInt());
		while (in.hasNextInt())
			g.addEdge(in.nextInt(), in.nextInt());
		in.close();

		System.out.println(g);

		TopologyOrder ord = new TopologyOrder(g);
		
		System.out.println(ord.order());
	}

}
