import java.util.*;

/**
 * Created by Mack Heller on 21/10/2016.
 * 
 */
public class RandomMST {
    public double totalWeight;
    public double largestEdge;
    public double randomMST(int n){
        List<Node> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        for(int i=0;i<n;i++){
            totalWeight = 0;
            ListIterator<Node> nodeIterator = nodes.listIterator();
            Node newNode = new Node();
            double smallestNewEdge = 1.1;
            while(nodeIterator.hasNext()){
                double randomWeight = Math.random();
                Node tempNode = nodeIterator.next();
                nodeIterator.set(tempNode.resetToDefaultValues());
                //if the new edge is larger then the smallest new edge and the largest of the current MST edges
                //then it cannot be in our solution
                if(!(randomWeight>largestEdge&&randomWeight>smallestNewEdge)) {
                    if(randomWeight<smallestNewEdge)
                        smallestNewEdge = randomWeight;
                    insertSorted(edges,new Edge(newNode, tempNode,randomWeight));
                    //edges.add(new Edge(newNode, tempNode,randomWeight));
                }
            }
            nodes.add(newNode);
           //Collections.sort(edges);
            edges = kruskal(edges.iterator(), nodes.size());
        }

        /* For testing only
        Iterator<Edge> edgeIterator = edges.iterator();
        while(edgeIterator.hasNext()) {
            System.out.println(edgeIterator.next().toString());
            }*/
        return totalWeight;
    }

    /**
     *
     * @param edgeIterator an iterator over a set of edges
     * @param numberOfNodes the number of nodes that are currently in the graph
     * @return a MST of the given graph
     */
    public List<Edge> kruskal(Iterator<Edge> edgeIterator, int numberOfNodes){
        List<Edge> edgesInMST = new ArrayList<>();
        while(edgeIterator.hasNext()) {
            Edge temp = edgeIterator.next();
            if(!(temp.getNode1().getHead().equals(temp.getNode2().getHead()))) {
                edgesInMST.add(temp);
                totalWeight = totalWeight + temp.getWeight();
                largestEdge = temp.getWeight();
                union(temp.getNode1(), temp.getNode2());
            }
            //if we already have our MST then we should quit
            if(temp.getNode1().getHead().getElementsInTree()==numberOfNodes)
                return edgesInMST;
        }
        return edgesInMST;
    }

    /**
     * Assume that n1 head does not already equal n2 head
     * @param n1 first node in edge
     * @param n2 second node in edge
     */
    public void union(Node n1, Node n2){
        if(n1.getHead().getRank()> n2.getHead().getRank()){
            n2.getHead().setHead(n1.getHead());
        }else {
            n1.getHead().setHead(n2.getHead());
            if(n1.getHead().getRank()== n2.getHead().getRank())
                n2.getHead().addOneToRank();
        }
    }
    //thought that inserting into a sorted list was faster then sorting whole list every time using list default sort
    //turns out to save almost no runtime as Java's sort method is very efficient when called on mostly sorted lists,
    //however because I was getting, on average, 10 seconds faster I left it in.
    public List<Edge> insertSorted(List<Edge> edges,Edge element){
        return insertSortedHelper(edges,element,0,edges.size());
    }
    public List<Edge> insertSortedHelper(List<Edge> edges,Edge element, int start, int end){
        int halfway = (int)Math.floor((end+start)/2);
        if(halfway == edges.size()){
            edges.add(element);
            return edges;
        }
        if(start==end) {
            if(element.getWeight() < edges.get(halfway).getWeight()) {
                edges.add(halfway, element);
            }else{
                edges.add(halfway + 1, element);
            }
            return edges;
        }
        if(element.getWeight() < edges.get(halfway).getWeight() ){
            return insertSortedHelper(edges,element,start,halfway);
        }
        else{
            return insertSortedHelper(edges,element,halfway+1,end);
        }
    }
}
