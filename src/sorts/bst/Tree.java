package sorts.bst;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private Node root;
    private int count;

    public Node getRoot() {
        return root;
    }

    public int getCount() {
        return count;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add(int data) {
        var node = new Node(data);

        if(root == null)
        {
            root = node;
            count = 1;
            return;
        }

        root.add(data);
        count++;
    }

    public ArrayList preorder() {

        if(root == null)
        {
            return new ArrayList();
        }
        return preorder(root);
    }

    public ArrayList inorder() {

        if(root == null)
        {
            return new ArrayList();
        }
        return inorder(root);
    }
    public ArrayList postorder() {

        if(root == null)
        {
            return new ArrayList();
        }
        return postorder(root);
    }


    private ArrayList preorder(Node node) {
        var list = new ArrayList();

        if(node != null) {
            list.add(node.getData());

            if(node.getLeft() != null)
            {
                list.addAll(preorder(node.getLeft()));
            }
            if(node.getRight() != null)
            {
                list.addAll(preorder(node.getRight()));
            }
        }
        return list;
    }

    private ArrayList inorder(Node node) {

        var list = new ArrayList();

        if(node != null) {

            if(node.getLeft() != null)
            {
                list.addAll(inorder(node.getLeft()));
            }

            list.add(node.getData());

            if(node.getRight() != null)
            {
                list.addAll(inorder(node.getRight()));
            }
        }
        return list;
    }
    private ArrayList postorder(Node node) {
         var list = new ArrayList();

        if(node != null) {

            if(node.getLeft() != null)
            {
                list.addAll(inorder(node.getLeft()));
            }

            if(node.getRight() != null)
            {
                list.addAll(inorder(node.getRight()));
            }
            list.add(node.getData());
        }
        return list;
    }




}
