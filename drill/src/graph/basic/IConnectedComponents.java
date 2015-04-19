package graph.basic;

// Implementations: CC, StrongCC

public interface IConnectedComponents {
	public int components();
	public boolean isConnected(int v, int w);
}
