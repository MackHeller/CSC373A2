/**
 * Created by Mack Heller on 21/10/2016.
 */
public class Edge implements Comparable<Edge>{
    private Node node1;
    private Node node2;
    private double weight;

    public Edge(Node node1, Node node2, double weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
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
    public int compareTo(Edge otherEdge){
        if((weight - otherEdge.weight) < 0)
            return -1;
        return 1;
    }
}
