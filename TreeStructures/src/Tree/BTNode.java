package Tree;

public class BTNode<T extends Comparable<T>> {

    T data;
    BTNode left;     // pointer to the Left Child
    BTNode right;    // pointer to the Right Child

    public BTNode(){
        data = null;
        left = null;
        right = null;
    }



    // Creates a root
    public BTNode(T data){
        this.data = data;
        left = null;
        right = null;
    }




    public BTNode(T data, BTNode left, BTNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }



    // Insert always happens on a Leaf
    public void insert(T data, BTNode root){
        BTNode ptr = root;

        if(ptr.left == null && ptr.right == null){

            if(ptr.data.compareTo(data)<=0)
                ptr.right = new BTNode(data);
            if(ptr.data.compareTo(data)>0)
                ptr.left = new BTNode(data);

        } else if(ptr.data.compareTo(data) <= 0 && ptr.left != null){

            insert(data, ptr.left);

        } else if(ptr.right != null) {

            insert(data, ptr.right);

        }

        System.out.printf("%s: %s ----> Left: %s: %s\n", ptr, ptr.data, ptr.left, (ptr.left == null ? null : ptr.left.data));
        System.out.printf("%s: %s ----> Right: %s: %s\n\n", ptr,ptr.data, ptr.right, (ptr.right == null ? null : ptr.right.data));



    }

    static final int COUNT = 10;

    public void print2DUtil(BTNode root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.data + "\n");

        // Process left child
        print2DUtil(root.left, space);
    }

    // Wrapper over print2DUtil()
    public void print2D(BTNode root)
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }
    public void printInOrder(BTNode root) {
        if(root == null)
            return;

        printInOrder(root.left);
        System.out.printf("%s", root.data);
        printInOrder(root.right);

    }


    /*
     * Returns Root Node of the binary expression tree
     * @param infix expression
     * @return
     */
//    public static BTNode<String> buildTree(String infix){
//        return;
//    }
}
