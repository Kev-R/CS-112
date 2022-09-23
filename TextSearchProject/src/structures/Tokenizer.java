package structures;
import java.util.ArrayList;
/**
 * A String Tokenizer class
 * 
 * @author Your name
 * @date Due Date
 * @period Class Period
 */
public class Tokenizer {
	private ArrayList<String> tokens;

	/**
	 * Constructor for the Tokenizer object.  A tokenizer takes in a String and
	 * delimiters and splits the String into tokens based on the demlimiters.
	 * The individual tokens should be stored in the tokens arraylist in the
	 * order for which they exist in the String passed by parameter.
	 *
	 * @param delims a String of character for which string should be split
	 * @param string the String to be split into tokens
	 */
	public Tokenizer(String delims, String string) {
		/* Complete this method */

		tokens = new ArrayList<String>();


		if(delims.equals(null) || string.equals(null) || string.length() == 0)
			return;

		if(delims.length() == 0){
			tokens.add(string);
		} else {
			String firstLetterDelim = delims.substring(0, 1);
			String word = "";

			for (int i = 0; i < string.length(); ) {

				if (string.substring(i, i + 1).equals(firstLetterDelim)) {

					if (string.substring(i, i + delims.length()).equals(delims)) {
						i += delims.length();
						if(!(word.length() == 0))
							tokens.add(word);
						word = "";
					} else {
						word += string.substring(i, i + 1);
						i++;
					}
				} else {
					word += string.substring(i, i + 1);
					i++;
				}
			}
			tokens.add(word);
		}
	}




	/**
	 * Returns one word at a time from the String that was split
	 * 		into tokens
	 * @return the first token from the tokens list or
	 * 			null if the list is empty
	 */
	public String nextToken () throws NoMoreTokensException {
		if (this.hasToken())
			return tokens.remove(0);
		throw new NoMoreTokensException();
	}

	/**
	 * @return true if more tokens exist
	 */
	public boolean hasToken () {
		if(tokens == null || tokens.size() == 0)
			return false;

		return tokens.size() > 0;
	}

}

