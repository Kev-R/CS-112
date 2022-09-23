package Tree;

public class Main {

    public static void main(String[] args) {
        BTNode root = new BTNode(3);
        root.printInOrder(root);
        System.out.println();

        System.out.println("--------------new--------------");
        root.insert(4, root);
        root.insert(2, root);
        root.insert(3, root);
        root.insert(5, root);
        root.insert(2, root);
        root.printInOrder(root);
    }


}
