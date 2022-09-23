
public class RecursionPractice {

	static String[] words1 = {"escape", "meat", "mix", "ultra", "interesting", "wakeful", "fumbling", "worm", "point", "third"};
	static String[] words2 = {"potato", "linen", "point", "wakeful", "ultra", "precede", "meat", "amuse", "third"};
	static int[] numbers = {3, 5, 7, 10, 14, 26, 32, 33, 34, 36, 39, 40, 41, 50, 52, 55, 57, 61, 63, 65, 67, 75, 77, 83, 95};
	
	public static void main(String[] args) {
		
		System.out.println("Factorial");
		for(int i = 0; i <= 20; i++)
		{
			if(i == 13)
				System.out.println("Due to Integer boundaries the following is incorrect");
			System.out.printf("%d! = %d\n", i, factorial(i));
		}
		System.out.println();
		
		System.out.println("Sequential Search");
		System.out.printf("%-12s%10s\n", "word", "found?");
		for(String s: words2)
			System.out.printf("%-12s%10s\n", s, contains(words1, s));
		System.out.println();
		
		System.out.println("Binary Search");
		System.out.printf("%-12s%10s\n", "number", "found?");
		for(int i = 1; i < 100; i +=  1 + (int)(Math.random() * 4))
			System.out.printf("%-12s%10s\n", i, contains(numbers, i));
		System.out.println();

		System.out.println("Fibonacci Sequence");
		for(int n = 1; n < 20; n++)
			System.out.printf("%d ", fibonacci(n));
	}

	/**
	 * Factorial
	 * @return the factorial of an integer n using recursion
	 */
	public static int factorial(int n)
	{
		if(n == 0 || n == 1) return 1;

		int fact = n;
		while(n > 1)
		{
			fact = fact * --n;
		}
		return fact;
	}

	/**
	 * Sequential Search.
	 * @return true if target is in array, false otherwise
	 */
	public static boolean contains(String[] array, String target)
	{
		
		if(array.length == 0)
			return false;
		
		if(array[0].equals(target))
			return true;
		else {
			String[] subarray = new String[array.length - 1];
	        for(int i = 1; i < array.length; i++)
	            subarray[i-1] = array[i];
	        
	        return contains(subarray,target);
		}
			
		
		
//		for(String obj: array)
//			if(target.equals(obj))
//				return true;
//		return false;
	}
	
	/**
	 * Binary Search.
	 * PRECONDITION: array is sorted from smallest to largest
	 * @return true if target is in array, false otherwise
	 */
	public static boolean contains(int[] array, int target)
	{
//		if (array.length == 0) {
//			return false;
// 	    }
//		int low = 0;
//		int high = array.length-1;
//
//		while(low <= high ) {
//			int middle = (low + high) / 2;
//
//			if (target > array[middle] ){
//				low = middle + 1;
//			} 
//			else if (target < array[middle]){
//				high = middle - 1;
//			} 
//			else { // The element has been found
//				return true;
//			}
//		}
//		return false;
		
		
		if (array.length == 0)
			return false;
		

		int middle = ((array.length)/2);
		
		
		if(array[middle] == target)
			return true;
		
		else if(target < array[middle]) {
			int[] subarray = new int[middle];
	        for(int i = 0; i < middle; i++) {
	            subarray[i] = array[i];
	        }
			return contains(subarray, target);
		}
		
		else {
			int[] subarray = new int[middle];
	        for(int i = 0; i < middle; i++) {
	            subarray[i] = array[i+middle];
	        }
			return contains(subarray, target);
		}
			

	}
	
	/**
	 * Fibonacci
	 * @return the nth value in the Fibonacci sequence.
	 */
	public static int fibonacci(int n) {
		   
			if(n <= 1)
				return n;
			return fibonacci(n-1) + fibonacci(n-2);
			
		
		
//			if (n <= 2)
//		      return 1;
//		   int f1 = 1, f2 = 1;
//		   for (int i = 3; i <= n; i++) {
//		      int tmp = f1 + f2;
//		      f1 = f2;
//		      f2 = tmp;
//		   }
//		   return f2;
		}
}
