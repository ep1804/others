import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BFS {

	public BFS() {
		// TODO Auto-generated constructor stub
	}
	
	private static class Graph{
		private final int V;
		private Set<Integer>[] adj;
		
		@SuppressWarnings("unchecked")
		public Graph(int V){
			this.V = V;
			adj = (Set<Integer>[])new HashSet[V];
			for(int v=0; v<V; v++)
				adj[v] = new HashSet<Integer>();
		}
		
		public void addEdge(int v, int w){
			adj[v].add(w);
			adj[w].add(v);
		}
		
		public Iterable<Integer> adj(int v){
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
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		for(int t=0; t<T; t++){
			int V = in.nextInt();
			
			BFS.Graph g = new BFS.Graph(V);
			
			int E = in.nextInt();
			for(int e=0; e<E; e++){
				int v = in.nextInt() - 1;
				int w = in.nextInt() - 1;
				g.addEdge(v, w);				
			}
			
			System.out.println(g);
			
			int start = in.nextInt() - 1;
			
			boolean[] marked = new boolean[V];
			int[] distTo = new int[V];
			Arrays.fill(distTo, -1);
			
			List<Integer> q = new LinkedList<Integer>();
			
			q.add(start);
			distTo[start] = 0;
			marked[start] = true;
			
			while(! q.isEmpty()){
				int v = q.remove(0);
				
				for(int w : g.adj(v)){
					if(! marked[w]){
						q.add(w);
						distTo[w] = distTo[v] + 6;
						marked[w] = true;
					}
				}
			}
			
			for(int v=0; v<V; v++){
				if(v != start)
					System.out.print(distTo[v] + " ");				
			}
			System.out.println();
			
		}
		
		in.close();
	}
}
