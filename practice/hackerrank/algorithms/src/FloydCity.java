import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FloydCity {

	private static class Edge{
		public final int from;
		public final int to;
		public double weight;
		
		public Edge(int from, int to, double weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		public int other(int v){
			if(v == this.from)
				return to;
			else
				return this.from;
		}
		
		@Override
		public boolean equals(Object obj){
			Edge that = (Edge)obj;
			if(from == that.from && to == that.to)
				return true;
			else
				return false;
		}
		
		public String toString(){
			return new StringBuilder().append(from).append('-').append(to).append('-').append(weight).toString();
		}
	}
	
	private static class Graph{
		public final int V;
		private List<Edge>[] adj;
		
		@SuppressWarnings("unchecked")
		public Graph(int V){
			this.V = V;
			adj = (List<Edge>[])new ArrayList[V];
			for(int v=0; v<V; v++)
				adj[v] = new ArrayList<Edge>();
		}
		
		public void addEdge(Edge e){
			if(adj[e.from].contains(e)){
				adj[e.from].remove(e);
			}
			adj[e.from].add(e);
		}
		
		
		public Iterable<Edge> adj(int v){
			return adj[v];
		}
		
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("Vtxs: ").append(V).append('\n');
			for(int v=0; v<V; v++)
				sb.append(v).append(": ").append(adj[v]).append('\n');
			return sb.toString();
		}
		
	}
	
	public static class SP{
		
		boolean[] reached;
		double[] distTo;
		
		public SP(Graph g, int start){

			reached = new boolean[g.V];
			distTo = new double[g.V];
			
			for(int i=0; i<g.V; i++)
				distTo[i] = Double.POSITIVE_INFINITY;
			
			PriorityQueue<Integer> q = new PriorityQueue<Integer>(g.V, new Comparator<Integer>(){
				@Override
				public int compare(Integer arg0, Integer arg1) {
					double cmp = distTo[arg0] - distTo[arg1];
					if(cmp < 0) return -1;
					else if(cmp > 0) return 1;
					else return 0;
				}});
			
			distTo[start] = 0;
			q.add(start);
			
			while(! q.isEmpty()){
				int a = q.remove();
				reached[a] = true;
				
				for(Edge e : g.adj(a)){
					int b = e.other(a);
					if(! reached[b]){
						if(distTo[b] > distTo[a] + e.weight){
							distTo[b] = distTo[a] + e.weight;
							if(q.contains(b)) q.remove(b);
							q.add(b);
						}
					}
				}
			}
		}
		
		public int distance(int dest){
			if(reached[dest])
				return (int) distTo[dest];
			else
				return -1;
		}
	}
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		// Build graph
		int N = in.nextInt();
		int M = in.nextInt();
		
		Graph g = new Graph(N);
		
		for(int i=0; i<M; i++){
			g.addEdge(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextDouble()));
		}
		
		//System.out.println(g);
		
		
		// Get shortest distance
		SP[] sp = new SP[N];
		
		int T = in.nextInt();
		for(int i=0; i<T; i++){
			int start = in.nextInt() - 1;
			int dest = in.nextInt() - 1;
			
			if(sp[start] == null) sp[start] = new SP(g, start);
			System.out.println(sp[start].distance(dest));			
		}
		
		in.close();
	}

}
