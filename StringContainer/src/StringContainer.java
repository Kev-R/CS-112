/**
 * @Name
 * @DueDate
 * @ClassPeriod
 *
 * A String Container... an introduction to a DataStructure and Exceptions
 */


public class StringContainer {
	
	private String[] array;
	
	/**
	 * Constructs a StringContainer object
	 * @param array
	 */
	public StringContainer(String[] array) {
		this.array = array;		
	}
	
	/**
	 * Add an item to the end of this.array
	 */
	public void add(String item) {
		// your code goes here
		
		if(array == null)
			this.array = new String[] {};
		
		add(item, array.length);
	}
	
	/**
	 * Add an item at a given index of this.array
	 * 
	 * Throws an IllegalArgumentException if the index < 0 or 
	 * 					index > array.length
	 */
	public void add(String item, int index) throws IllegalArgumentException {
		//your code goes here
		// if the array is null create a empty array
		if(array == null)
			this.array = new String[] {};
		
		
		if(index < 0 || index > array.length) 
			throw new IllegalArgumentException("index given is out of bounds of the array");
		

		
		String[] arr = new String[array.length + 1];
		
		for(int i = 0; i < arr.length; i++) {
			if (i < index)
                arr[i] = array[i];
            else if (i == index)
                arr[i] = item;
            else
                arr[i] = array[i - 1];
		}
		
		array = arr;
	}
	
	/**
	 * Removes a given item from this.array.
	 * 
	 * array remains unchanged if the item is not an element of this.array.
	 * @return true if a change was made, false otherwise
	 */
	public boolean remove(String item) throws NullPointerException {
		//your code goes here
		if(array == null)
			throw new NullPointerException("The array is null. Please make sure the array is not empty before using this method");
		
		if(contains(item)) {
			remove(indexOf(item));
			return true;
		}
		return false;
			
	}
	
	/**
	 * Removes an item from this.array at a given index
	 * 
	 * Throws an ArrayIndexOutOfBoundsException if the index is not valid
	 * @return true if a change was made, false otherwise
	 */
	public boolean remove(int index) throws ArrayIndexOutOfBoundsException, NullPointerException {
		if(array == null)
			throw new NullPointerException("The array is null. Please make sure the array is not empty before using this method");
		
		if(index < 0 || index >= array.length) 
			throw new ArrayIndexOutOfBoundsException("index given is out of bounds of the array");
		
		
		boolean bool = false; 
		
		String[] arr = new String[array.length -1];
		
		for(int i = 0; i < array.length; i++) {
			if (i < index)
                arr[i] = array[i];
            else if (i == index)
                bool = true;
            else
                arr[i-1] = array[i];
				
		}
		
		array = arr;
		return bool;
	}
	
	/**
	 * Determines if an element exists in this.array
	 * @return true if a element was found, false otherwise
	 */
	public boolean contains(String element) throws NullPointerException
	{
		if(array == null)
			throw new NullPointerException("The array is null. Please make sure the array is not empty before using this method");
		
		for(String string: array) {
			if(string.equals(element))
				return true;
		}
		return false;
	}
	
	/**
	 * returns the index of an element in this.array or 
	 * 					-1 if the element does not exist
	 */
	public int indexOf(String element) throws NullPointerException
	{
		if(array == null)
			throw new NullPointerException("The array is null. Please make sure the array is not empty before using this method");
		
		for(int i = 0; i < array.length; i++) {
			if(array[i].equals(element))
				return i;
		}
		return -1;
	}
	
	/**
	 * @return a String representation of this.array
	 */
	public String toString() throws NullPointerException
	{
		if(array == null)
			throw new NullPointerException("The array is null. Please make sure the array is not empty before using this method");
		
		if(array.length == 0) return "{}";
		
		String str = "{";
		int i = 0;
		for( ; i < array.length - 1; i++)
		{
			if(array[i] == null)
				str += String.format("%s,", array[i]);
			else
				str += String.format("\"%s\",", array[i]);
		}
		if(array[i] == null)
			str += String.format("%s}", array[i]);
		else
			str += String.format("\"%s\"}", array[i]);
		return str;
				
	}
}
