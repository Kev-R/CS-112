package Generic;

import java.util.ArrayList;

public class Application {
	
	
	
	public static void main(String[] args) {
		
//		String[] samples = {"(...{...(...)...}...)"};
//		
//		for(String s: samples) {
//			System.out.println(s + " is mathcing? " + isParenthesisMatching(s));
//		}
		
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(new Person("Adrain", 1225));
		people.add(new Person("Arya", 1226));
		
		
		Queue<Person> line1 = new Queue<Person>();
		line1.enqueue(new Person("Gin", 1228));
		line1.enqueue(new Person("Alex Yao", 1227));
		line1.enqueue(new Person("John", 1226));
		
		
		Queue<Person> line2 = new Queue<Person>();
		line2.enqueue(people.remove(0));
		
		
		Queue<Person> merged = merge(line1, line2);
		
		System.out.println(line1.size);
		line1.print(); 
		
//		while(!merged.isEmpty())
//			System.out.println(merged.dequeue().getName());
		
		
	}
    
    
//    public static boolean isParenthesisMatching(String str){
//    	Stack<Character> stack = new Stack<Character>();
//    	
//    	for(int i = 0; i < str.length(); i++) {
//    		char ch1 = str.charAt(i);
//    		if(ch1 == '(' || ch1 == '{')
//    			stack.push(ch1);
//    		
//    		if (ch1 == ')' || ch1 == '}') {
//    			
//    		}
//    	}
//    	
//        return false;
//    }
    
    
    
    public static <T extends Comparable<T>> Queue<T> merge(Queue<T> line1, Queue<T> line2){
    	
    	Queue<T> newLine = new Queue<T>();
    	
    	while(!line1.isEmpty() && !line2.isEmpty()) {
    		
    		if(line1.peek().compareTo(line2.peek()) < 0)
    			newLine.enqueue(line1.dequeue());
    		else
    			newLine.enqueue(line2.dequeue());
    	
    	}
    	
    	while(!line1.isEmpty())
    		newLine.enqueue(line1.dequeue());
    	while(!line2.isEmpty())
    		newLine.enqueue(line2.dequeue());
    	
    	return newLine;
    	
    }
}
