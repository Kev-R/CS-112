package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @Name Your Name
 * @Date Due Date
 * @Period Class Period
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextDouble(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** Your code goes here **/
		
		Node pointer1 = poly1;
		Node pointer2 = poly2;
		Node result = null;
		
		if(pointer1 == null)
			return pointer2;
		
		if(pointer2 == null)
			return pointer1;
		
		
		while(pointer1 != null && pointer2 != null) {
			double sumOfCoeff = 0.0;
			
			if(pointer1.term.degree == pointer2.term.degree) {
				
				sumOfCoeff = pointer1.term.coeff + pointer2.term.coeff;
				
				if(sumOfCoeff != 0) {
					result = addAtLast(result, sumOfCoeff, pointer1.term.degree);
				}
				
				pointer1 = pointer1.next;
				pointer2 = pointer2.next;
				
				
			} else if(pointer1.term.degree > pointer2.term.degree) {
				
				result = addAtLast(result, pointer2.term.coeff, pointer2.term.degree);
				pointer2 = pointer2.next;
				
			} else if(pointer2.term.degree > pointer1.term.degree) {
				
				result = addAtLast(result, pointer1.term.coeff, pointer1.term.degree);
				pointer1 = pointer1.next;
				
			}
		}

		// If there are entities left in polynomial 1 add them to the result linked list
		while(pointer1 != null) {
			result = addAtLast(result, pointer1.term.coeff, pointer1.term.degree);
			pointer1 = pointer1.next;
		}
	
		// If there are entities left in polynomial 2 add them to the result linked list
		while(pointer2 != null) {
			result = addAtLast(result, pointer2.term.coeff, pointer2.term.degree);
			pointer2 = pointer2.next;
		}
		
		return result; //Quiets the Compiler
	}
	
	private static Node addAtLast(Node header, double coeff, int degree) {
		
			
		Node ptr = header;
		
		if(ptr == null) {
			ptr = new Node(coeff, degree, null);
			return ptr;
		}
		
		while(ptr.next != null) {
			ptr = ptr.next;
		}
		
		ptr.next = new Node(coeff, degree, null);
		
		return header;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** Your code goes here **/

		Node ptr1 = poly1;
		Node ptr2 = poly2;
		Node result = null;
		
		double coeffResult = 0.0;
		int expoResult = 0;
		
		
		while(ptr1 != null) {
			
			ptr2 = poly2;
			while(ptr2 != null) {
			
				coeffResult = ptr1.term.coeff * ptr2.term.coeff;
				expoResult = ptr1.term.degree + ptr2.term.degree;
				
				result = addAtLast(result, coeffResult, expoResult);
				
				ptr2 = ptr2.next;
				
			}
			
			ptr1 = ptr1.next;
			
		}
		
		Node simplifiedResult = simplify(result);
		
		return simplifiedResult; //Quiets the compiler
	}
		
	private static Node simplify(Node front) {
		
		Node ptr = front;
		Node newList = null;
		double totalCoeff = 0;
		int currentDegree = 0;
		
		while(ptr.next != null) {
			ptr = ptr.next;
		}
		
		int highestDegree = ptr.term.degree;
		
		ptr = front;
		
		for(int i = highestDegree; i >= 0; --i) {
			
			ptr = front;
			totalCoeff = 0;
			
			while(ptr != null) {
				if(ptr.term.degree == currentDegree) {
					totalCoeff += ptr.term.coeff;
				}
				ptr = ptr.next;
			}
			
			
			if(totalCoeff != 0)
				newList = addAtLast(newList, totalCoeff, currentDegree);
			
			
			currentDegree++;
			
		}
		
		return newList;
		
	}
	
	
	
	
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static double evaluate(Node poly, double x) {
		/** Your code goes here **/
		double result = 0;
		
		while(poly != null) {
			result += (poly.term.coeff * (Math.pow(x, poly.term.degree)));
			poly = poly.next;
		}
		
		return result;
	}
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
