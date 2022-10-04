package sorts.bst;


public class Node {

    private int data;
    private Node left;
    private Node right;

    public Node(int data)
    {
        this.data = data;
    }

    public Node( int data, Node left, Node right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public void add(int data)
    {
        var node = new Node(data);

        if(data < this.data) {
            if (this.left == null) {
                this.setLeft(node);
            } else {
                left.add(data);
            }
        }
        else {
            if(this.right == null)
            {
                this.setRight(node);
            }
            else {
                right.add(data);
            }
        }
    }
    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
