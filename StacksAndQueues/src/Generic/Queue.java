package Generic;

import java.util.NoSuchElementException;

public class Queue <T>{

	Node <T> tail;
	int size;
	
	public Queue() {
		tail = null;
		size = 0;
	}
	
	public void enqueue(T data) {
		
		if(tail == null) {
			tail = new Node<T>(data, null);
			tail.next = tail;
		} else {
			tail = new Node<T>(data, tail.next);	// insert after the tail
			tail = tail.next;	// advance the tail pointer to the new tail
		}
		
		++size;
		
	}
	
	
	// Remove the head
	public T dequeue() throws NoSuchElementException {
		T data = tail.next.data;	// Get info from head node
		
		if(isEmpty())	// empty queue
			throw new NoSuchElementException("Queue is Empty");
		
		if(tail.next == tail)	// If there is only one item in the queue, make queue null
			tail = null;
		else
			tail.next = tail.next.next;		// skip the reference to head by one
		
		--size;
		return data;	// return head node data
	}
	
	
	public boolean isEmpty() {
		return tail == null || size == 0;
	}
	
	
	public int size() {
		return size;
	}
	
	
	public void clear() {
		tail = null;
		size = 0;
	}
	
	public T peek() {
		if(isEmpty())
			return null;	// or throw exception
		
		return tail.next.data;
	}
	
	
	public void print() {
		
		Node<T> pointer = tail.next;
		
		while(pointer != tail) {
			System.out.print(pointer.data + "-->>");
			pointer = pointer.next;
		}
		
		
		
	}
	
}












