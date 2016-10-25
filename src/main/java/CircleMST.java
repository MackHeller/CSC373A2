import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CircleMST {
	public double totalWeight;
    public double largestEdge;
	public double circleMST(int n){
	    List<CircleNode> nodes = new ArrayList<>();
        List<CircleEdge> edges = new ArrayList<>();
        for(int i=0;i<n;i++){
            totalWeight = 0;
            ListIterator<CircleNode> nodeIterator = nodes.listIterator();
            
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            while(((x * x) + (y * y)) > 1){
            	x = Math.random() * 2 - 1;
            	y = Math.random() * 2 - 1;
            }
            CircleNode newNode = new CircleNode(x, y);
            double smallestNewEdge = 2.1;
            while(nodeIterator.hasNext()){
                CircleNode tempNode = nodeIterator.next();
                
                double xdiff = newNode.getXcoord() - tempNode.getXcoord();
                double ydiff = newNode.getYcoord() - tempNode.getYcoord();
                double randomWeight = Math.sqrt(
                		(xdiff * xdiff) + (ydiff * ydiff));
                
                nodeIterator.set(tempNode.resetToDefaultValues());
                //if the new edge is larger then the smallest new edge and the largest of the current MST edges
                //then it cannot be in our solution
                if(!(randomWeight>largestEdge&&randomWeight>smallestNewEdge)) {
                    if(randomWeight<smallestNewEdge)
                        smallestNewEdge = randomWeight;
                    insertSorted(edges,new CircleEdge(newNode, tempNode,randomWeight));
                    //edges.add(new Edge(newNode, tempNode,randomWeight));
                }
            }
            nodes.add(newNode);
           //Collections.sort(edges);
            edges = kruskal(edges.iterator(), nodes.size());
        }

        // For testing only
        Iterator<CircleEdge> edgeIterator = edges.iterator();
        while(edgeIterator.hasNext()) {
            System.out.println(edgeIterator.next().toString());
            }
        return totalWeight;
    }

    /**
     *
     * @param iterator an iterator over a set of edges
     * @param numberOfNodes the number of nodes that are currently in the graph
     * @return a MST of the given graph
     */
    public List<CircleEdge> kruskal(Iterator<CircleEdge> iterator, int numberOfNodes){
        List<CircleEdge> edgesInMST = new ArrayList<>();
        while(iterator.hasNext()) {
            CircleEdge temp = iterator.next();
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
     * @param circleNode first node in edge
     * @param circleNode2 second node in edge
     */
    public void union(CircleNode circleNode, CircleNode circleNode2){
        if(circleNode.getHead().getRank()> circleNode2.getHead().getRank()){
            circleNode2.getHead().setHead(circleNode.getHead());
        }else {
            circleNode.getHead().setHead(circleNode2.getHead());
            if(circleNode.getHead().getRank()== circleNode2.getHead().getRank())
                circleNode2.getHead().addOneToRank();
        }
    }
    //thought that inserting into a sorted list was faster then sorting whole list every time using list default sort
    //turns out to save almost no runtime as Java's sort method is very efficient when called on mostly sorted lists,
    //however because I was getting, on average, 10 seconds faster I left it in.
    public List<CircleEdge> insertSorted(List<CircleEdge> edges,CircleEdge circleEdge){
        return insertSortedHelper(edges,circleEdge,0,edges.size());
    }
    public List<CircleEdge> insertSortedHelper(List<CircleEdge> edges,CircleEdge circleEdge, int start, int end){
        int halfway = (int)Math.floor((end+start)/2);
        if(halfway == edges.size()){
            edges.add(circleEdge);
            return edges;
        }
        if(start==end) {
            if(circleEdge.getWeight() < edges.get(halfway).getWeight()) {
                edges.add(halfway, circleEdge);
            }else{
                edges.add(halfway + 1, circleEdge);
            }
            return edges;
        }
        if(circleEdge.getWeight() < edges.get(halfway).getWeight() ){
            return insertSortedHelper(edges,circleEdge,start,halfway);
        }
        else{
            return insertSortedHelper(edges,circleEdge,halfway+1,end);
        }
    }
    
	public static void main(String[] args) {
		CircleMST circleMST = new CircleMST();
        for (String s: args) {
            System.out.println(circleMST.circleMST(Integer.valueOf(s)));
        }

	}

}
