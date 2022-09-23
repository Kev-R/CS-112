package structures;
import java.io.*;
import java.util.Scanner;

/**
 * Indexer class to create a HashTable of KeyValues
 * 
 * @name your name
 * @date due date
 * @period class period
 */
public class Indexer {
	private HashTable table;
	
	public Indexer(String filename) throws IOException, NoMoreTokensException {
		table = new HashTable();
		buildInvertedIndex(filename);
	}
	
	/**
	 * Fills a HashTable of KeyValue objects to act as the storage unit for
	 * 		this InvertedIndex.  The HashTable and KeyValues are built using
	 * 		Strings from the File filename passed by parameter.
	 * @param filename the name of a txt file to be parsed into KeyValue objects
	 * 		and indexed into a HashTable
	 */
	private void buildInvertedIndex(String filename) 
			throws IOException, NoMoreTokensException{
		/* Complete this method */

		Scanner scan = new Scanner(new File(filename));

		while(scan.hasNextLine()){
			String line = scan.nextLine();
			Tokenizer tokenizer = new Tokenizer(" ", line);
			String subj = removeLastCharacter(tokenizer.nextToken());
			KeyValue current;
			while(tokenizer.hasToken()){
				current = new KeyValue(tokenizer.nextToken());

				if(current.equals(table.lookUpKey(current.hashCode()%table.size(), current.getKey())))
					table.lookUpKey(current.hashCode() % table.size(), current.getKey()).addValue(subj);
				else {
					table.insertKeyValue(current.hashCode() % table.size(), current);
					current.addValue(subj);
				}
			}


		}

	}


	private String removeLastCharacter(String s){
		return s.substring(0, s.length()-1);
	}
	
	/**
	 * Returns the KeyValue object in the HashTable of this Indexer
	 * 		with the key of key. If no such object exists, return null.
	 * @param key the key of a KeyValue object
	 * @return the KeyValue object for which key exist, null if no such
	 * 		object exists in the HashTable of this Indexer.
	 */
	public KeyValue getKeyValue(String key) {
		/* Complete this method */
		int index = index(key);
		return table.lookUpKey(index, key);

	}
    
	/**
	 * Determines the index at which String key should be found/located 
	 * 		in HashTable for this Indexer as calculated with the hashCode 
	 * 		method of the KeyValue class and the current size of the 
	 * 		HashTable attribute of this Indexer object.
	 * 
	 * @param key - A String of the key for a KeyValue object
	 * @return a valid index in the HashTable attribute of this Indexer
	 * 		object as calculated with the KeyValue hashCode and the size of
	 * 		the HashTable.
	 */
	public int index(String key){
		/* Complete this method */
	    return Math.abs((new KeyValue(key).hashCode()%table.size())); //quiets the compiler
	}
	
    /**
     * @return a String representation of this InvertedIndex...which is being
     * 		stored using a HashTable
     */
    public String toString() {
    	return table.toString();
    }
}
