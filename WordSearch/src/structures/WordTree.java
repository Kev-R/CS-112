package structures;

/**
 * A Binary Search Tree of WordNodes.
 * 
 * @author Your Name
 * @date Due Date
 * @period Class Period
 */
public class WordTree {
	
	WordNode root;
	int numWords;
	
	public WordTree(){
		root = null;
		numWords = 0;
	}
	
	/**
	 * If word doesn't exist in this word tree, it should be inserted in
	 * 		the appropriate place based on Binary Search.  If words already
	 * 		exists in this tree, the quantity should be updated to reflect 
	 * 		the appropriate number of times word appears in the text
	 * @param word a word to be inserted into this Binary Search Tree
	 * @return the quantity, or number of times that word has been added to the list
	 */
	public int insertWord(String word) {
		//complete this method
		
		root = insertHelper(word, root);
		return getWordQuantity(word, root);
		
	}
	
	
	
	private WordNode insertHelper(String word, WordNode root) {
	
		if(root == null) {
			return new WordNode(word,null,null);
		}
		
		if(word.compareTo(root.word) < 0)
			root.left = insertHelper(word, root.left);
		else if(word.compareTo(root.word) > 0)
			root.right = insertHelper(word, root.right);
		else
			root.updateQuantity();
		
		return root;
		
	}
	
	// private helper method to get quantity of a word in tree
	private int getWordQuantity(String word, WordNode root) {
			
		if(word.compareTo(root.word) == 0)
			return root.getQuantity();
		
		if(word.compareTo(root.word) < 0)
			return getWordQuantity(word, root.left);
			
		if(word.compareTo(root.word) > 0)
			return getWordQuantity(word, root.right);
			
		return 0;
	}
		
	
	/**
	 * Performs a search in this word tree for the word.  Determines and returns
	 *      the depth of the word in the tree.  The root is at depth 0, a child of
	 *      the root is at depth 1, etc... If the word is not in this word tree, -1
	 *      is returned to reflect that the word does not exist in the tree.
	 * 
	 * @param word a word to be searched for in this tree
	 * @return the depth of the word in the tree, or -1 if word is not found.
	 */
	public int wordSearch(String word) {
		//complete this method
		return searchHelper(root, word, 0);		
		 
	}

	
	private int searchHelper(WordNode root, String word, int depth) {
		
		if(root == null)
			return -1;
		
		if(word.compareTo(root.word) == 0) 
			return depth;
		
		
		if(word.compareTo(root.word) < 0) 
			return searchHelper(root.left, word, ++depth);
		else if(word.compareTo(root.word) > 0) 
			return searchHelper(root.right, word, ++depth);
		
			
		return -1;
		
	}
	
	
	
	
}
