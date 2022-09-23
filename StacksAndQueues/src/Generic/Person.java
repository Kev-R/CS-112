package Generic;

public class Person implements Comparable<Person> {

	private String name;
	private int timeIn;
	
	public Person(String name, int timeIn) {
		this.name = name;
		this.timeIn = timeIn;
	}
	
	public String getName() {
		return name;
	}
	
	// If class implements comparable interface, it must have a compareTo method
	public int compareTo(Person other) {
		return this.timeIn = other.timeIn;
	}
	
	
	public String toString() {
		return name+" "+timeIn;
	}
}
