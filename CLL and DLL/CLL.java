package lists;

public class CLL {


	/**
	 * Print each element in the list. O(n)
	 * 
	 * @param tail  node containing the final element
	 */
	public static <T> void traverse(Node<T> tail) {
		if (tail == null) {
			return;
		}

		Node<T> crnt = tail;
		do {
			crnt = crnt.next;
			System.out.println(crnt.data);
		} while (crnt != tail);
	}
	
	/**
	 * Combine two lists. O(1)
	 * 
	 * Note: after merging traversing second will print the elments of first,
	 * followed by the elements of second, and vice versa.
	 * 
	 * @param first
	 * @param second
	 */
	public static <T> void merge(Node<T> first, Node<T> second) {
		Node<T> first_head = first.next;
		first.next = second.next;
		second.next = first_head;
	}
	
	/**
	 * Add an element to the front of the list. O(1)
	 * 
	 * @param data  element to add
	 * @param tail  reference to end of the list
	 */
	public static <T> void insertFront(T data, Node<T> tail) {
		if (tail == null) throw new IllegalArgumentException("empty list");
		
		Node<T> tmp = new Node<T>(data);
		tmp.next = tail.next;
		tail.next = tmp;
	}
	
	/**
	 * Add an element to the end of the list. O(1)
	 * 
	 * @param data  element to add
	 * @param tail  reference to the end of the list
	 * @return      reference to the new end of the list
	 */
	public static <T> Node<T> insertBack(T data, Node<T> tail) {
		Node<T> new_tail = new Node<T>(data);

		if (tail != null) {
			new_tail.next = tail.next;
			tail.next = new_tail;
		}
		
		return new_tail;
	}
	
	/**
	 * Remove the first element from the list. O(1)
	 * 
	 * @param tail  reference to the end of the list
	 * @return   either tail, or null
	 */
	
	public static <T> Node<T> deleteFront(Node<T> tail) {
		if (tail.next == tail) return null;
		tail.next = tail.next.next;
		return tail;
	}
	
	/** 
	 * Delete the last element from the list. O(n)
	 * 
	 * @param tail
	 * @return  the new last element, or null
	 */
	public static <T> Node<T> deleteBack(Node<T> tail) {
		if (tail.next == tail) return null;

		Node<T> prev = tail;
		Node<T> crnt = tail.next;
		while (crnt != tail) {
			prev = crnt;
			crnt = crnt.next;
		}
		
		prev.next = crnt.next;
		return prev;
	}
	
	public static <T> Node<T> delete(T target, Node<T> tail) {
		if (tail == null) throw new IllegalArgumentException("empty list");
		
		if (tail == tail.next) {
			if (tail.data.equals(target)) {
				return null;
			}
			throw new IllegalArgumentException("not present");
		}
		
		Node<T> prev = tail;
		Node<T> crnt = tail.next;
		do {
			if (crnt.data.equals(target)) {
				prev.next = crnt.next;
				if (crnt == tail) {
					return prev;
				} else {
					return tail;
				}
			}
			prev = crnt;
			crnt = crnt.next;
		} while (prev != tail);
		
		throw new IllegalArgumentException("not present");
	}

	
	public static void main(String[] args) {
		Node<Integer> t = new Node<>(6);
		insertFront(5, t);
		insertFront(4, t);
		insertFront(3, t);
		traverse(t);

		System.out.println("--");
		t = delete(5, t);
		traverse(t);
		
	}
	
}