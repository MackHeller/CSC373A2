
public class CircleEdge implements Comparable<CircleEdge>{
	private CircleNode node1;
    private CircleNode node2;
    private double weight;
	
	public CircleEdge(CircleNode node1, CircleNode node2, double weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }
	public void setNode1(CircleNode node1) {
        this.node1 = node1;
    }

    public void setNode2(CircleNode node2) {
        this.node2 = node2;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public CircleNode getNode1() {
        return node1;
    }

    public CircleNode getNode2() {
        return node2;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString(){
        return "Node1: "+ node1+" Node2: "+ node2+" Weight: "+ weight;
    }

    @Override
    public int compareTo(CircleEdge otherEdge){
        if((weight - otherEdge.weight) < 0)
            return -1;
        return 1;
    }

}
