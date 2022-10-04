package sorts.bst;

public class Main {

    public static void main(String[] args)
    {
         var tree = new Tree();
         tree.add(5);
         tree.add(3);
         tree.add(7);
         tree.add(1);
         tree.add(2);
         tree.add(8);
         tree.add(6);
         tree.add(9);

       for (var i : tree.inorder())
       {
           System.out.println(Integer.parseInt(i.toString()));
       }
    }

}

