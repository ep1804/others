import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimSpecialSubtree {

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

		// build MST(minimum spanning tree) using prim's algorithm (eager version)
		int[] distTo = new int[N];
		boolean[] visited = new boolean[N];
		
		for(int i=0; i<N; i++)
			distTo[i] = Integer.MAX_VALUE;

		PriorityQueue<Integer> q = new PriorityQueue<Integer>(N, new Comparator<Integer>(){

			@Override
			public int compare(Integer arg0, Integer arg1) {
				int cmp = distTo[arg0] - distTo[arg1];
				
				if(cmp < 0) return -1;
				else if(cmp > 0) return 1;
				else return 0;
			}});
		
		distTo[S] = 0;
		q.add(S);
		
		while(! q.isEmpty()){
			int v = q.remove();
			visited[v] = true;
			
			for(Edge e : g.adj(v)){
				int w = e.other(v);
				
				if(! visited[w]){
					if(distTo[w] > e.weight){
						distTo[w] = e.weight;
						if(q.contains(w)) q.remove(w);
						q.add(w);
					}					
				}
			}
		}
		
		// sum the edge weights in the MST
		int sum = 0;
		for(int i=0; i<N; i++){
			sum += distTo[i];
		}
		
		System.out.println(sum);

	}

}
