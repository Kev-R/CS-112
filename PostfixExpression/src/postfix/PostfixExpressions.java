package postfix;

public class PostfixExpressions {

	/**
	 * Creates a postfix expression based on an infix expression
	 * @param infix a mathematical expression
	 * PRECONDITION: Numeric values are single digits and Variables are single letters
	 * @return a postfix expression
	 * 
	 * Examples:
	 * a+b 		-> ab+
	 * (a+b)*c 	-> ab+c*
	 * 3+a-b+5 	-> 3a+b-5+
	 * 2+3*4 	-> 234*+
	 * (2-3+a)*(A+B*x)	-> 23-a+ABx*+*
	 */
	public static String toPostfix(String infix) {

		char[] arr = infix.toCharArray();

		String result = "";
		Stack stack = new Stack();

		final int number_start = 48; // 0
		final int number_end = 57; // 9

		final int capL_start = 65; // A
		final int capL_end = 90; // Z

		final int lowerL_start = 97; // a
		final int lowerL_end = 122; // z


		for(char c: arr){
			if(c >= number_start && c <= number_end || c >= capL_start && c <= capL_end
					|| c >= lowerL_start && c <= lowerL_end) {

				result += c;

			} else if (c == 37 || c == 42 || c == 43 || c == 45 || c == 47){

				while (!stack.isEmpty() && (precedence((char)stack.peek()) >= precedence(c)))  {
					result += stack.pop();
;				}
				stack.push(c);

			} else if(c == 40) {

				stack.push('(');

			} else if(c == 41) {

				while(!(stack.peek().equals('('))) {
					result += stack.pop();
				}
				stack.pop();

			}
		}

		while(!stack.isEmpty()){
			result += stack.peek();
			stack.pop();
		}

		return result;

	}


	private static int precedence(char operator) {

		switch(operator) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
			case '%':
				return 2;
			default:
				return 0;
		}
	}
	
	/**
	 * Implement a method to evaluate a postfix expression. 
	 * The expression is a string which contains either single‐digit 
	 * numbers (0‐‐9), or the operators +, ‐, *, /, and %, and nothing else. 
	 * 
	 * @param postfix A postfix expression
	 * PRECONDITION: the postfix expression contains single-digit numbers (0-9), or
	 * 		the operators +, -, *, /, %, and nothing else.
	 * @return the value of the expression evaluated as a double
	 * 
	 * Examples:
	 * 	2		-> 2.0
	 * 	23+		-> 5.0
	 * 	234+*	-> 14.0
	 * 	234-*5/	-> -0.4
	 */
	public static double evaluatePostfix(String postfix) {

		char[] arr = postfix.toCharArray();
		Stack stack = new Stack();

		final int number_start = 48; // 0
		final int number_end = 57; // 9

		double firstNum;
		double secondNum;

		if(postfix.length() == 1)
			return Double.parseDouble(postfix);

		for(char c: arr){

			firstNum = 0;
			secondNum = 0;

			if(c >= number_start && c <= number_end ) {
				stack.push(c);
			} else if (c == 37 || c == 42 || c == 43 || c == 45 || c == 47){


				secondNum = Double.parseDouble(""+stack.pop());
				firstNum = Double.parseDouble(""+stack.pop());

				switch(c){
					case '+':
						stack.push(firstNum+secondNum);
						break;
					case '-':
						stack.push(firstNum-secondNum);
						break;
					case '*':
						stack.push(firstNum*secondNum);
						break;
					case '/':
						stack.push(firstNum/secondNum);
						break;
					case '%':
						stack.push(firstNum%secondNum);
						break;
					default:
						break;
				}

			}

		}

		return Double.parseDouble(""+stack.peek());
	}
	
	
	public static void main(String[] args) {

		final String RESET = "\u001B[0m";
		final String BLACK = "\u001B[30m";
		final String RED = "\u001B[31m";
		final String GREEN = "\u001B[32m";
		final String YELLOW = "\u001B[33m";
		final String BLUE = "\u001B[34m";
		final String PURPLE = "\u001B[35m";
		final String CYAN = "\u001B[36m";
		final String WHITE = "\u001B[37m";
		String test = "";

		System.out.println("\\\\ -------- Testing toPostfix Method -------- \\\\");

		test = toPostfix("a+b").equals("ab+") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 0:%s a+b -> ab+ :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("a+b"));

		test = toPostfix("(a+b)*c").equals("ab+c*") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 1:%s (a+b)*c -> ab+c* :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("(a+b)*c"));

		test = toPostfix("3+a-b+5").equals("3a+b-5+") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 2:%s 3+a-b+5 -> 3a+b-5+ :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("3+a-b+5"));

		test = toPostfix("2+3*4").equals("234*+") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 3:%s 2+3*4 -> 234*+ :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("2+3*4"));

		test = toPostfix("(2+3)*(4+5)").equals("23+45+*") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 4:%s (2+3)*(4+5) -> 23+45+* :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("(2+3)*(4+5)"));

		test = toPostfix("2+3*4+5").equals("234*+5+") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 5:%s 2+3*4+5 -> 234*+5+ :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("2+3*4+5"));

		test = toPostfix("2*3+7/3").equals("23*73/+") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 6:%s 2*3+7/3 -> 23*73/+ :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("2*3+7/3"));

		test = toPostfix("(2-3+a)*(A+B*x)").equals("23-a+ABx*+*") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 7:%s (2-3+a)*(A+B*x) -> 23-a+ABx*+* :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("(2-3+a)*(A+B*x)"));

		test = toPostfix("A*B/(C+D)").equals("AB*CD+/") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 8:%s A*B/(C+D) -> AB*CD+/ :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("A*B/(C+D)"));

		test = toPostfix("(A-B)*(C/D)").equals("AB-CD/*") ? "\u001B[32mpass\u001B[0m" :  "\u001B[31mfail\u001B[0m";
		System.out.printf("\n%sCase 9:%s (A-B)*(C/D) -> AB-CD/* :: %s\n        Program output: %s\n", YELLOW, RESET, test, toPostfix("(A-B)*(C/D)"));


//		System.out.printf("\n\u001B[33mCase 0:\u001B[0m a+b -> ab+ :: %s\n        Program output: %s\n", toPostfix("a+b").equals("ab+"), toPostfix("a+b"));
//		System.out.printf("\n\u001B[33mCase 1:\u001B[0m (a+b)*c -> ab+c* :: %b\n        Program output: %s\n", toPostfix("(a+b)*c").equals("ab+c*"), toPostfix("(a+b)*c"));
//		System.out.printf("\n\u001B[33mCase 2:\u001B[0m 3+a-b+5 -> 3a+b-5+ :: %b\n        Program output: %s\n", toPostfix("3+a-b+5").equals("3a+b-5+"), toPostfix("3+a-b+5"));
//		System.out.printf("\n\u001B[33mCase 3:\u001B[0m 2+3*4 -> 234*+ :: %b\n        Program output: %s\n", toPostfix("2+3*4").equals("234*+"), toPostfix("2+3*4"));
//		System.out.printf("\n\u001B[33mCase 4:\u001B[0m (2+3)*(4+5) -> 23+45+* :: %b\n        Program output: %s\n", toPostfix("(2+3)*(4+5)").equals("23+45+*"), toPostfix("(2+3)*(4+5)"));
//		System.out.printf("\n\u001B[33mCase 5:\u001B[0m 2+3*4+5 -> 234*+5+ :: %b\n        Program output: %s\n", toPostfix("2+3*4+5").equals("234*+5+"), toPostfix("2+3*4+5"));
//		System.out.printf("\n\u001B[33mCase 6:\u001B[0m 2*3+7/3 -> 23*73/+ :: %b\n        Program output: %s\n", toPostfix("2*3+7/3").equals("23*73/+"), toPostfix("2*3+7/3"));
//		System.out.printf("\n\u001B[33mCase 7:\u001B[0m (2-3+a)*(A+B*x) -> 23-a+ABx*+* :: %b\n        Program output: %s\n", toPostfix("(2-3+a)*(A+B*x)").equals("23-a+ABx*+*"), toPostfix("(2-3+a)*(A+B*x)"));
//		System.out.printf("\n\u001B[33mCase 8:\u001B[0m A*B/(C+D) -> AB*CD+/ :: %b\n        Program output: %s\n", toPostfix("A*B/(C+D)").equals("AB*CD+/"), toPostfix("A*B/(C+D)"));
//		System.out.printf("\n\u001B[33mCase 9:\u001B[0m (A-B)*(C/D) -> AB-CD/* :: %b\n        Program output: %s\n", toPostfix("(A-B)*(C/D)").equals("AB-CD/*"), toPostfix("(A-B)*(C/D)"));
		//Create additional test cases

		System.out.println();
		System.out.println("\\\\ -------- Testing evaluatePostfix -------- \\\\");
		System.out.println("\n\u001B[33mCase 0:\u001B[0m 2 evaluates to 2.0 \n        It became " + evaluatePostfix("2"));
		System.out.println("\n\u001B[33mCase 1:\u001B[0m 23+ evaluates to 5.0\n        It became " + evaluatePostfix("23+"));
		System.out.println("\n\u001B[33mCase 2:\u001B[0m 234+* evaluates to 14.0\n        It became " + evaluatePostfix("234+*"));
		System.out.println("\n\u001B[33mCase 3:\u001B[0m 234-*5/ evaluates to -0.4\n        It became " + evaluatePostfix("234-*5/"));
		System.out.println("\n\u001B[33mCase 4:\u001B[0m 234*+ evaluates to 14.0\n        It became " + evaluatePostfix("234*+"));
		System.out.println("\n\u001B[33mCase 5:\u001B[0m 34*25*+ evaluates to 22.0\n        It became " + evaluatePostfix("34*25*+"));
		System.out.println("\n\u001B[33mCase 6:\u001B[0m 23+45+* evaluates to 45.0\n        It became " + evaluatePostfix("23+45+*"));
		System.out.println("\n\u001B[33mCase 7:\u001B[0m 234*+5+ evaluates to 19.0\n        It became " + evaluatePostfix("234*+5+"));
		System.out.println("\n\u001B[33mCase 8:\u001B[0m 23*73/+ evaluates to 8.333333333333334\n        It became " + evaluatePostfix("23*73/+"));
		System.out.println("\n\u001B[33mCase 9:\u001B[0m 452*+5+ evaluates to 19.0\n        It became " + evaluatePostfix("452*+5+"));
		//Create additional test cases

	}

}
