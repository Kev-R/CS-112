package friends;

import java.io.*;
import java.util.*;

// Testing client for Friends
public class FriendsApp {

	public static void main (String[] args) {
	
		// Update p1 and p2 to refer to people on Graph g
		// sam and sergei are from sampleGraph.txt
		String filename = "sampleGraph.txt";
		String p1 = "sam";
		String p2 = "sergei";
		
		System.out.printf("Testing shortestChain from %s to %s on graph %s.\n", p1, p2, filename);

		try {
			Graph g = new Graph(new Scanner(new File(filename)));
			
			ArrayList<String> shortestChain = Friends.shortestChain(g, p1, p2);

			// Testing Friends.shortestChain
			if(shortestChain != null) {
				System.out.println("Shortest chain from " + p1 + " to " + p2);
				System.out.println(shortestChain);
			}
			else {
				System.out.println("No chain exists from " + p1 + " to " + p2);
			}
			// ADD a test for Friends.cliques() here

			// ADD a test for Friends.connectors() here
			
		} 
		catch (FileNotFoundException e) {
			System.out.println(filename + " not found");
		}
	}
}
