import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraSP {

	public DijkstraSP() {
	}

	public static class Edge implements Comparable<Edge>{
		private final int v;
		private final int w;
		private final int weight;
		
		public Edge(int v, int w, int weight){
			this.v = Math.min(v, w);
			this.w = Math.max(v, w);
			this.weight = weight;
		}
		
		public int either(){
			return v;
		}
		
		public int other(int v){
			if(v == this.v) return w;
			else return this.v;
		}
		
		public int weight(){
			return weight;
		}
		
		public String toString(){
			return new StringBuilder().append(v).append('-').append(w).append('-').append(weight).toString();
		}
		
		@Override
		public boolean equals(Object that){
			if(this.v == ((Edge)that).v && this.w == ((Edge)that).w)
				return true;
			else
				return false;
		}
		
		@Override
		public int compareTo(Edge e) {
			int cmp = this.weight - e.weight;
			if(cmp < 0) return -1;
			else if(cmp > 0) return 1;
			else return 0;
		}
	}
	
	public static class Graph{
		private final int V;
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
			
			int v = e.either();
			int w = e.other(v);
			
			if(edges.contains(e)){
				Edge e0 = edges.get(edges.indexOf(e));				
				if(e.compareTo(e0) > 0)
					return;
				edges.remove(e);
				adj[v].remove(e);
				adj[w].remove(e);				
			}

			edges.add(e);
			adj[v].add(e);
			adj[w].add(e);
		}
		
		public int V(){
			return V;
		}
		
		public int E(){
			return edges.size();
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
		private Graph g;
		private int start;
		private boolean[] done;
		private int[] distTo;
		
		public SP(Graph g, int start){
			this.g = g;
			this.start = start;
			
			done = new boolean[g.V];
			distTo = new int[g.V()];
			
			Arrays.fill(distTo, Integer.MAX_VALUE);
			
			PriorityQueue<Integer> q = new PriorityQueue<Integer>(g.V(), new Comparator<Integer>(){

				@Override
				public int compare(Integer v1, Integer v2) {
					int cmp = distTo[v1] - distTo[v2];
					if(cmp < 0) return -1;
					else if(cmp > 0) return 1;
					else return 0;
				}});
			
			
			distTo[start] = 0;
			done[start] = true;
			q.add(start);
			
			while(! q.isEmpty()){
				int v = q.remove();
				done[v] = true;
				
				for(Edge e : g.adj(v)){
					int w = e.other(v);
					
					if(! done[w]){
						if(distTo[w] > distTo[v] + e.weight()){
							distTo[w] = distTo[v] + e.weight();
						}
						
						if(q.contains(w)) q.remove(w);					
						q.add(w);
					}
				}
			}
		}
		
		public String toString(){
			StringBuilder sb = new StringBuilder();			
			for(int v=0; v<g.V(); v++)
				if(v != start){
					if(done[v])
						sb.append(distTo[v]).append(' ');
					else
						sb.append(-1).append(' ');
				}
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++){
			int V = in.nextInt();
			
			Graph g = new Graph(V);
			
//			g.addEdge(new Edge(1, 2, 10));
//			g.addEdge(new Edge(2, 3, 10));
//			System.out.println(g);
//			
//			g.addEdge(new Edge(1, 2, 7));
//			g.addEdge(new Edge(2, 3, 13));
//			System.out.println(g);
			
			int E = in.nextInt();
			
			for(int e=0; e<E; e++){
				int v = in.nextInt() - 1;
				int w = in.nextInt() - 1;
				int weight = in.nextInt();
				
				g.addEdge(new Edge(v, w, weight));
			}
			
			//System.out.println(g);
			
			int S = in.nextInt() - 1;
			
			SP sp = new SP(g, S);
			
			System.out.println(sp);
		}
		
		
		in.close();
	}

}
