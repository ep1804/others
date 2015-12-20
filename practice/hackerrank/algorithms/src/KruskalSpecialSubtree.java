import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KruskalSpecialSubtree {

	private static class Edge{
		public final int v;
		public final int w;
		public final int weight;
		
		public Edge(int v, int w, int weight){
			this.v = (int) Math.min(v, w);
			this.w = (int) Math.max(v, w);
			this.weight = weight;
		}
		
		public int either(){
			return v;			
		}
		
		public int other(int v){
			if(v == this.v)
				return w;
			else
				return this.v;
		}
		
		public String toString(){
			return new StringBuilder().append(v).append('-').append(w).append('-').append(weight).toString();
		}
	}
	
	private static class Graph{
		public final int V;
		private List<Edge> edges;
		private List<Edge>[] adj;
		
		@SuppressWarnings("unchecked")
		public Graph(int V){
			this.V = V;
			edges = new ArrayList<Edge>();
			adj = (List<Edge>[])new ArrayList[V];
			for(int v=0; v<V; v++)
				adj[v] = new ArrayList<Edge>();
		}
		
		public void addEdge(Edge e){
			edges.add(e);
			int v = e.either();
			adj[v].add(e);
			adj[e.other(v)].add(e);
		}
		
		public Iterable<Edge> edges(){
			return edges;
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
	
	private static class Connection{
		private int V;
		private int[] parent;
		
		public Connection(int V){
			this.V = V;
			parent = new int[V];
			for(int i=0; i<V; i++)
				parent[i] = i;
		}
		
		public void connect(int v, int w){
			int rv = parent[v]; // root of v
			int rw = parent[w]; // root of w
			
			while(rv != parent[rv]) rv = parent[rv]; 
			while(rw != parent[rw]) rw = parent[rw]; 
			
			parent[rw] = rv;
		}
		
		public boolean isConnected(int v, int w){
			int rv = parent[v];
			int rw = parent[w];
			
			while(rv != parent[rv]) rv = parent[rv];
			while(rw != parent[rw]) rw = parent[rw];

			return rv == rw; 
		}
	}
	
	public static void main(String[] args) {
		
		// read input and build a weighted graph
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int M = in.nextInt();
		
		Graph g = new Graph(N);
		for(int i=0; i<M; i++)
			g.addEdge(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
		
		int S = in.nextInt() - 1;
		in.close();

		//System.out.println(g);
		
		// build the MST using Kruskal algorithm
		
		PriorityQueue<Edge> q = new PriorityQueue<Edge>(N, new Comparator<Edge>(){

			@Override
			public int compare(Edge o1, Edge o2) {
				int cmp = o1.weight - o2.weight;
				if(cmp < 0) return -1;
				else if(cmp > 0) return 1;
				else{
					cmp = o1.v + o1.w - o2.v - o2.w;
					if(cmp < 0) return -1;
					else if(cmp > 0) return 1;
					else return 0;
				}
			}});

		q.addAll(g.edges);
		
		Connection con = new Connection(N);
		List<Edge> mst = new ArrayList<Edge>();
		
		while(! q.isEmpty()){
			Edge e = q.remove();
			int v = e.either();
			int w = e.other(v);
			
			if(! con.isConnected(v, w)){
				mst.add(e);
				con.connect(v, w);
			}
		}
		
		//System.out.println(mst);
		
		// Sum the weight of edges in MST
		
		int sum = 0;
		for(Edge e : mst){
			sum += e.weight;
		}
		
		System.out.println(mst.size());
		System.out.println(sum);
	}

}
