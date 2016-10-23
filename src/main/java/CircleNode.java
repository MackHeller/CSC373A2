
public class CircleNode{
	public double getXcoord() {
		return xcoord;
	}

	public double getYcoord() {
		return ycoord;
	}

	private double xcoord;
	private double ycoord;
	private int rank;
    private CircleNode head;
    private int elementsInTree;
	
	public CircleNode(double x, double y){
		resetToDefaultValues();
		this.xcoord = x;
		this.ycoord = y;
		
	}

	public void addOneToRank() {
        rank++;
    }

    public void setHead(CircleNode head) {
        this.head = head;
        head.setElementsInTree(head.getElementsInTree() + elementsInTree);
    }

    public int getRank() {
        return rank;
    }

    public CircleNode getHead() {
        if(head.equals(this)){
            return this;
        }else{
            return head.getHead();
        }
    }
    //resets the rank and head to default values
    public CircleNode resetToDefaultValues(){
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

