
public class Main {

	public static void main(String args[]) {
		System.out.println("//-----------Regular Tester----------//");
		containerTester();
		
		System.out.println("\n\n//-----------null Tester----------//");
		nullContainerTester();
		
	}
	
	public static void nullContainerTester() {
		StringContainer container = new StringContainer(null);
		
		try {
			//Breaks code and throws exception
			//container.remove("WORD");
			
			container.add("WORD");
		
			System.out.println(container.toString());
			container.add("WORD2", 1);
		
			System.out.println(container.toString());
			container.remove("WORD");
		
			System.out.println(container.toString());
			container.remove(0);
		
			System.out.println(container.toString());
		} catch(Exception e) {
			System.out.println("Something went wrong. Please make sure your code is not passing in any null or invalid arugements and is logically correct");
			e.printStackTrace();
		}
		
	}
	
	public static void containerTester() {
		String[] stringArray = {"Jelly", "Who", "What", "Where", "How", "Why"};
		StringContainer container = new StringContainer(stringArray);
		
		try {
			container.add("WORD");
		
			
			System.out.println(container.toString());
			container.add("WORD2", 1);
			
			
			System.out.println(container.toString());
			container.remove("WORD2");
			
			
			System.out.println(container.toString());
			container.remove(0);
			
	
			System.out.println(container.toString());
		} catch(Exception e) {
			System.out.println("Something went wrong. Please make sure your code is not passing in any null or invalid arugements and is logically correct");
			e.printStackTrace();
		}
	}
	
}
