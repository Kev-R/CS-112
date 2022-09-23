

public class IntNodeRunner {
	static IntNode n = null;
	static IntNode o = new IntNode(2, null);
	static IntNode o1 = new IntNode(3,null);
	static IntNode two = new IntNode(3,new IntNode(5,null));
	static IntNode t1 = new IntNode(3,new IntNode(5,new IntNode(1,null)));
	static IntNode t2 = new IntNode(3,new IntNode(5,new IntNode(8,new IntNode(3,null))));
	static IntNode t3 = new IntNode(1,new IntNode(2,new IntNode(6,null)));

	public static void main(String[] args) {
		System.out.println("Add Before:");
		//System.out.println("Null List should return null\n"+IntNode.addBefore(n,3,1));
		System.out.println("List with 1 node as target should return 2 3\n"+IntNode.addBefore(o1, 3, 2));
		System.out.println("List with 1 node with no target should return 2\n"+IntNode.addBefore(o, 3, 2));
		System.out.println("List with multiple nodes should return 3 7 5 1\n"+IntNode.addBefore(t1, 5, 7));
		System.out.println("List with multiple nodes and target as last should return 3 7 5 7 1\n"+IntNode.addBefore(t1, 1, 7));
		System.out.println("List with multiple target nodes should return 2 3 5 8 3\n"+IntNode.addBefore(t2, 3, 2)+"\n");
		System.out.println("List with no target nodes should return 3 5 8 3\n"+IntNode.addBefore(t2, 9, 2)+"\n");

		System.out.println("Add Before Last:");
		System.out.println("Null List should return null\n"+IntNode.addBeforeLast(n, 0));
		System.out.println("List with 1 node as target should return 2 3\n"+IntNode.addBeforeLast(o1, 2)); o1 = new IntNode(3,null);
		System.out.println("List with multiple nodes should return 3 7 5 7 7 1\n"+IntNode.addBeforeLast(t1, 7)+"\n");
		System.out.println("List with two nodes should return 3 8 5\n"+IntNode.addBeforeLast(two, 8)+"\n"); two = new IntNode(3,new IntNode(5,null));

		System.out.println("Number of Occurances:");
		System.out.println("Null List should return 0\n" + IntNode.numberOfOccurrences(n,3));
		System.out.println("No targets in list should return 0\n"+IntNode.numberOfOccurrences(t1, -9));
		System.out.println("1 target in list of one node should return 1\n"+IntNode.numberOfOccurrences(o1, 3));
		System.out.println("0 targets in list of one node should return 0\n"+IntNode.numberOfOccurrences(o1, 9));
		System.out.println("List with multiple nodes and targets should return 3\n"+IntNode.numberOfOccurrences(t1, 7));
		System.out.println("List with multiple nodes and 1 target should return 1\n"+IntNode.numberOfOccurrences(t1, 1)+"\n"); t1 = new IntNode(3,new IntNode(5,new IntNode(1,null)));

		System.out.println("Delete Every Other:");
		IntNode.deleteEveryOther(n);
		System.out.println("Null List should return null\n"+n); n = null;
		IntNode.deleteEveryOther(o1);
		System.out.println("One node list returns 3\n" + o1); o1 = new IntNode(3,null);
		IntNode.deleteEveryOther(two);
		System.out.println("Two node list returns 3\n" + two); two = new IntNode(3,new IntNode(5,null));
		IntNode.deleteEveryOther(t1);
		System.out.println("Odd # List returns 3 1\n" + t1); t1 = new IntNode(3,new IntNode(5,new IntNode(5,new IntNode(1,new IntNode(3,null)))));
		IntNode.deleteEveryOther(t2);
		System.out.println("Even # List returns 3 8\n" + t2 + "\n"); t2 = new IntNode(3,new IntNode(5,new IntNode(8,new IntNode(9,null))));

		System.out.println("Delete All Occurances:");
		System.out.println("Null List should return null\n"+IntNode.deleteAllOccurrences(n, 0));
		System.out.println("One node list with target returns null\n"+IntNode.deleteAllOccurrences(o1, 3)); o1 = new IntNode(3,null);
		System.out.println("One node list without target returns 3\n"+IntNode.deleteAllOccurrences(o1, 0)); o1 = new IntNode(3,null);
		System.out.println("Multiple instances of target returns 5 5 1\n"+IntNode.deleteAllOccurrences(t1, 3)); t1 = new IntNode(3,new IntNode(5,new IntNode(5,new IntNode(3,null))));
		System.out.println("Multiple instances of target, at last returns 3 3\n"+IntNode.deleteAllOccurrences(t1, 5)); t1 = new IntNode(3,new IntNode(3,new IntNode(3,null)));
		System.out.println("All instances of target returns null\n"+IntNode.deleteAllOccurrences(t1, 3)+"\n"); t1 = new IntNode(3,new IntNode(5,new IntNode(6,null)));

		System.out.println("Common Elements:");
		System.out.println("Null List should return null\n"+IntNode.commonElements(n, o));
		System.out.println("List with 1 elem and 1 common returns 3\n"+IntNode.commonElements(t1, o1));
		System.out.println("List with 1 elem and 0 common returns null\n"+IntNode.commonElements(t1, o));
		System.out.println("List with many common returns 3 5\n"+IntNode.commonElements(t1, t2));
		System.out.println("Lists with no common returns null\n"+IntNode.commonElements(t3, t2));

	}
}
