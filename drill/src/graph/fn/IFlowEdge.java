package graph.fn;

// Implementations: FlowEdge

public interface IFlowEdge{
	public int from();
	public int to();
	public int other(int v);
	public double capacity();
	public double flow();
	public double residualCapacityTo(int v);
	public void addResidualFlowTo(int v);
}
