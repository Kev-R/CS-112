package friends;

import java.util.ArrayList;
import structures.Queue;
import structures.Stack;

/**
 * A Friends class
 * 
 * @author Your name
 * @date Due Date
 * @period Class Period
 */
public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. null if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {		
		/** COMPLETE THIS METHOD **/
		return null; //QUIETS THE COMPILER
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an ArrayList of ArrayLists - each constituent ArrayList contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return ArrayList of cliques, where each clique is an ArrayList of names. 
	 * 				null if there is no student in the given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		/** COMPLETE THIS METHOD **/
		return null; //QUIETS THE COMPILER
	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return an ArrayList of names of all connectors. null if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		/** COMPLETE THIS METHOD **/
		return null; //QUIETS THE COMPILER	
	}
}

