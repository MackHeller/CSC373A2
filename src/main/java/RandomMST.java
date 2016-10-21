import java.util.*;

/**
 * Created by Mack Heller on 21/10/2016.
 */
public class RandomMST {
    public static double randomMST(int n){
        List<Node> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        for(int i=0;i<n;i++){
            ListIterator<Node> nodeIterator = nodes.listIterator();
            Node newNode = new Node();
            while(nodeIterator.hasNext()){
                Node tempNode = nodeIterator.next();
                nodeIterator.set(tempNode.resetToDefaultValues());
                edges.add(new Edge(newNode,tempNode,Math.random()));
            }
            nodes.add(newNode);
            edges = kruskal(edges);
        }
        Iterator<Edge> edgeIterator = edges.iterator();
        double totalWeight= 0;
        while(edgeIterator.hasNext()) {
            //for testing
            Edge temp =edgeIterator.next();
            System.out.println(temp.toString());

            totalWeight = totalWeight + temp.getWeight();
        }
        return totalWeight;
    }
    public static List<Edge> kruskal(List<Edge> edges){
        List<Edge> edgesInMST = new ArrayList<>();
        Collections.sort(edges);
        Iterator<Edge> edgeIterator = edges.iterator();
        while(edgeIterator.hasNext()) {
            Edge temp = edgeIterator.next();
            if(!(temp.getNode1().getHead().equals(temp.getNode2().getHead()))){
                edgesInMST.add(temp);
                union(temp.getNode1(),temp.getNode2());
            }else{
                //for testing
                System.out.println("Remove: "+temp.toString());
            }
        }
        return edgesInMST;
    }

    /**
     * Assume that n1 head does not already equal n2 head
     * @param n1
     * @param n2
     */
    public static void union(Node n1, Node n2){
        if(n1.getHead().getRank()> n2.getHead().getRank()){
            n2.getHead().setHead(n1.getHead());
        }else {
            n1.getHead().setHead(n2.getHead());
            if(n1.getHead().getRank()== n2.getHead().getRank())
                n2.getHead().addOneToRank();
        }
    }

    public static void main (String arg[]){
        System.out.println(randomMST(10));
    }
}
