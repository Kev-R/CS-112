package postfix;

import java.util.NoSuchElementException;


/**
 * This class implements a generic LIFO stack.
 * @param <T> The type of objects to be stored in this stack.
 */
public class Stack<T> {
	
	/** Items in this stack, stored in a generic linked list. */
	Node<T> top;
	
	/** Initializes this stack to empty. */
	public Stack() {
		top = null;
	}
	
	/**
	 *  Pushes a given item on this stack by adding to the top.
	 * @param item Item to be pushed.
	 */
	public void push(T item) {
		top = new Node<T>(item, top);
	}


	/**
	 * Pops from this stack by deleting and returning the item at the top.
	 * @return Popped item.
	 * @throws NoSuchElementException If this stack is empty.
	 */
	public T pop() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T data = top.data;
		top = top.next;
		return data;
	}


	/**
	 * Returns the number of items in this stack.
	 * @return Number of items in this stack.
	 */
	public int size() {
		int count = 0;
		
		for(Node<T> pointer = top; pointer != null; pointer = pointer.next) 
			++count;

		return count;
	}



	/**
	 * Tells whether this stack is empty or not.
	 * @return True if this stack is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return top == null;
	}
	
	// Empties this stack by removing all items.
	public void clear() {
		top = null;
	}
	
	/**
	 * Returns the item at the top of this stack, without removal from list
	 * @return Item at the top of this stack, null if stack is empty.
	 */
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return top.data;
	}
	
	/** @return a String representation of this Stack */
	public String toString() {
		String string = "Stack: ";
		for (Node<T> ptr = top; ptr != null; ptr = ptr.next) {
			string += "| " + ptr.data + " |\n";
		}
		return string;
	}
	
	/** Node class used for the Linked list storage for this Stack */
	@SuppressWarnings("hiding")
	class Node<T>{
		T data;
		Node<T> next;
		
		public Node(T data, Node<T> next)
		{
			this.data = data;
			this.next = next;
		}
	}
}
