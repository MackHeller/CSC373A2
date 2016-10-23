/**
 * Created by Mack Heller on 21/10/2016.
 */
public class Node {
    private int rank;
    private Node head;
    private int elementsInTree;

    public Node() {
        resetToDefaultValues();
    }

    public void addOneToRank() {
        rank++;
    }

    public void setHead(Node head) {
        this.head = head;
        head.setElementsInTree(head.getElementsInTree() + elementsInTree);
    }

    public int getRank() {
        return rank;
    }

    public Node getHead() {
        if(head.equals(this)){
            return this;
        }else{
            return head.getHead();
        }
    }
    //resets the rank and head to default values
    public Node resetToDefaultValues(){
        rank = 0;
        head = this;
        elementsInTree = 1;
        return this;
    }
    public int getElementsInTree() {
        return elementsInTree;
    }

    public void setElementsInTree(int elementsInTree) {
        this.elementsInTree = elementsInTree;
    }
}
