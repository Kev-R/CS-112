package lists;


public class DLL {

	public static class Node<T> {
		T data;
		Node<T> prev;
		Node<T> next;
		
		Node(T data) {
			this.data = data;
			prev = this;
			next = this;
		}
	}
	
	/**
	 * Print each item in the list. O(n)
	 * 
	 * @param tail Reference to the last node to be printed
	 */
	public static <T> void traverse(Node<T> tail) {
		Node<T> crnt = tail;
		do {
			crnt = crnt.next;
			System.out.println(crnt.data);
		} while (crnt != tail);
	}
	
	
	/**
	 * Insert an element at the front of the list. O(1)
	 * 
	 * @param data  the new element
	 * @param tail  reference to the end of the list
	 */
	public static <T> void insertFront(T data, Node<T> tail) {
		if (tail == null) throw new IllegalArgumentException("empty list");

		Node<T> tmp = new Node<T>(data);
		tmp.next = tail.next;
		tmp.next.prev = tmp;
		tail.next = tmp;
		tmp.prev = tail;
	}
	
	/**
	 * Insert an element at the back of the list. O(1)
	 * 
	 * @param data  the new element
	 * @param tail  reference to the end of the list
	 * @return  reference to the new end of the list 
	 */
	public static <T> Node<T> insertBack(T data, Node<T> tail) {
		Node<T> new_tail = new Node<T>(data);
		
		if (tail != null) {
			new_tail.next = tail.next;
			tail.next.prev = new_tail;
			
			tail.next = new_tail;
			new_tail.prev = tail;
		}
		
		return new_tail;
	}
	
	/**
	 * Remove the first element of the list. O(1)

	 * @param tail  last node in the list
	 * @return   tail, or null if the list contained only one element
	 */
	public static <T> Node<T> deleteFront(Node<T> tail) {
		if (tail == null) throw new IllegalArgumentException("empty list");
		
		if (tail.next == tail) return null;
		
		Node<T> head = tail.next.next;
		head.prev = tail;
		tail.next = head;
		
		return tail;
	}
	
	/**
	 * Remove the last element of the list. O(1)
	 * 
	 * @param tail  last node in the list
	 * @return  the new last node in the list
	 */
	public static <T> Node<T> deleteBack(Node<T> tail) {
		if (tail == null) throw new IllegalArgumentException("empty list");
		
		if (tail.next == tail) return null;

		Node<T> head = tail.next;
		tail = tail.prev;
		tail.next = head;
		head.prev = tail;
		
		return tail;
	}
	
	/**
	 * Combine two lists into a single list. O(1)
	 * 
	 * Note: after merging, traversing second will print the elements of first
	 * followed by those in second, and vice versa.
	 * 
	 * @param first
	 * @param second
	 */
	public static <T> void merge(Node<T> first, Node<T> second) {
		Node<T> first_head = first.next;
		Node<T> second_head = second.next;
		
		second.next = first_head;
		first_head.prev = second;
		
		first.next = second_head;
		second_head.prev = first;
	}
	
	/**
	 * Remove this node from the list containing it. O(1)
	 * 
	 * @param node  node to remove
	 */
	public static <T> void unlink(Node<T> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		node.next = node;
		node.prev = node;
	}
	
	/**
	 * Remove the given element from the list. O(n)
	 * 
	 * @param target  element value to remove
	 * @param tail    reference to end of the list
	 * @return        the tail after deleting
	 */
	public static <T> Node<T> delete(T target, Node<T> tail) {
		if (tail == null) throw new IllegalArgumentException("empty list");
		
		Node<T> crnt = tail;
		do {
			if (crnt.data.equals(target)) {
				if (crnt == crnt.next) {
					return null;
				}

				// unlink(crnt)
				crnt.prev.next = crnt.next;
				crnt.next.prev = crnt.prev;
				if (crnt == tail) {
					return crnt.next;
				} else {
					return tail;
				}
			}
			crnt = crnt.next;
		} while (crnt != tail);
		
		throw new IllegalArgumentException("target not present");
	}

	public static void main(String[] args) {
		Node<Integer> t = new Node<>(6);
		insertFront(5, t);
		insertFront(4, t);
		insertFront(3, t);
		traverse(t);

		System.out.println("--");
		//t = delete(3, t);
		//traverse(t);
		
		Node<Integer> t2 = new Node<>(2);
		insertFront(1, t2);
		merge(t, t2);
		traverse(t);
		
		
	}

}