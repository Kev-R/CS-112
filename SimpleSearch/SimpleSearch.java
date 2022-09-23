package structures;

import java.util.ArrayList;
/**
 * SimpleSearch class to search an inverted index
 * 
 * @name your name
 * @date due date
 * @period class period
 */
public class SimpleSearch {
	
	/**
	 * Searches the InvertedIndex indexer for any and all keywords found in 
	 * 		the String query and returns an ArrayList of all subjects 
	 * 		related to the keywords in the String query. 
	 * If the query results in a failed search return null.
	 * @param query a String that includes one or more key words.
	 * @param indexer an Inverted Indexer object to be searched
	 * @return an ArrayList of values or "subjects" that relate to any and all 
	 * 		of the "key" words in the String query. Or null if the key words in
	 * 		query result in a failed search. ie. key NOT in the InvertedIndex
	 * PRECONDTION: query may include upper and lower case letter, period, 
	 * 		comma, semicolon, colon, apostrophe, question mark, exclamation, 
	 * 		and spaces. But no other characters.
	 * POSTCONDITION: the ArrayList returned by the method query does NOT
	 * 		contain any duplicate values.
	 */
    public static ArrayList<String> query(String query, Indexer indexer) 
    		throws NoMoreTokensException{
    	/*  Complete this method */

		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> toBeAdded = new ArrayList<String>();

		if(query.equals(null) || query.equals(""))
			return list;

		query = query.replaceAll("\\p{Punct}", " ");

		Tokenizer tokenizer = new Tokenizer(" ", query);



		list.addAll(indexer.getKeyValue(tokenizer.nextToken()).getValues());


		while(tokenizer.hasToken()){

			toBeAdded.addAll(indexer.getKeyValue(tokenizer.nextToken()).getValues());
			list = mergeWithoutDuplicates(list,toBeAdded);

		}

        return list;
    }

	private static ArrayList<String> mergeWithoutDuplicates(ArrayList<String> arr1, ArrayList<String> arr2){

		ArrayList<String> merged = new ArrayList<String>();
		merged.addAll(arr1);

		for(String s : arr2){
			if(!merged.contains(s)){
				merged.add(s);
			}
		}

		return merged;
	}

}



