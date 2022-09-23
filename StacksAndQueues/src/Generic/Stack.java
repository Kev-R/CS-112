package Generic;
import java.util.*;

public class Stack<T> {

	Node<T> top = null;
	int size;
	
	
	public Stack() {
		top = null;
		size = 0;
	}
	
	
	public void push(T data) {
		top = new Node<T>(data, top.next);
		++size;
	}
	
	
	public T pop() {
		if(top == null) 
			throw new EmptyStackException();
		
		T data = top.data;
		top = top.next;
		--size;
		
		return data;
	}
	
	
	public boolean isEmpty() {
		return (size == 0 || top == null);
	}
	
	
	public int size() {
		return size;
	}
	
	
	public void clear() {
		top = null;
		size = 0;
	}
	
	
	public T peek() {
		if(top == null)
			throw new EmptyStackException();
		
		return top.data;
	}
	
	
}