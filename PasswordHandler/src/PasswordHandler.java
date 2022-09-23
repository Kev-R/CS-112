
import java.io.*;
import java.util.*;
/**
 * 
 * @author (Name)
 * @date (Due Date)
 * 
 * An Exploration of StringTokenizer and File handling.
 *
 */
public class PasswordHandler 
{
	private static String filename = "pwds.txt";
	private static File file = new File(filename);
	
	private static final String SPECIAL_CHARS = "!@#$%";
	public static void main(String[] args) throws Exception {
		Scanner IO = new Scanner(System.in);
		int option = 0;
		while(option != 5)
		{
			option = welcomePrompt(IO);
			switch(option){
			case 1: createPassword(IO); break;
			case 2: changePassword(IO); break;
			case 3: 
				System.out.print("Enter a password:");	
				if(isValid(IO.next()))
					System.out.println("Password is valid\n");
				else
					System.out.println("Password is not valid\n");
				break;
			case 4: 
				System.out.printf("\nSuggested Password: %s\n", suggestPassword()); break;
			}
		}
		IO.close();
	}

	private static int welcomePrompt(Scanner sc) {
		System.out.println("*/\\*Password Handler*/\\*");
		System.out.println("1. Create a password");
		System.out.println("2. Change your password");
		System.out.println("3. Check to see if a password is valid");
		System.out.println("4. Get a suggested password");
		System.out.println("5. Quit");
		System.out.print("Choose a number (1-5) ");
		String selection = sc.next();
		int num;
		try {
			num = Integer.parseInt(selection);
		}
		catch(NumberFormatException e) {
			System.out.println("\nSelection must be a number between 1 and 5\n");
			return welcomePrompt(sc);
		}
		if(num < 1 || num > 5) {
			System.out.println("\nSelection must be a number between 1 and 5\n");
			return welcomePrompt(sc);
		}
		return num;
	}



	public static void createPassword(Scanner s) throws IOException{
		//Your code goes here
		if(file.exists()) {
			System.out.println("\nPassword Already Exists\n");
		} else {			
			String input;
			do {
				System.out.print("Please Input Valid Password: ");
				input = s.next();
			} while(!isValid(input));
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(input);
			writer.close();
		}	
	}

	

	public static void changePassword(Scanner s)
	{
		//Your code goes here
		try {
			if(!file.exists()) {
				System.out.println("You must create a password before selecting to change it.");
			} else {
				
				Scanner fileScanner = new Scanner(file);
				ArrayList<String> arr = new ArrayList<String>();
				while(fileScanner.hasNext()) {
					arr.add(fileScanner.next());
					
				}
				
				String input;
				boolean passCheck;				
				do {
					passCheck = false;
					System.out.print("Please Input Current Password: ");
					input = s.next();
					
					for(String string: arr) {
						if(string.equals(input))
							passCheck = true;
					}
				} while(!isValid(input) || !passCheck);
				
				boolean redundant;
				do {
					redundant = false;
					System.out.print("Please Input New Valid Password: ");
					input = s.next();
					
					for(String string: arr) {
						if(string.equals(input))
							redundant = true;
					}
				} while(!isValid(input) || redundant);
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
				writer.write("\n"+input);
				writer.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static boolean isValid(String password)
	{
		//Your code goes here
//		if(password == null)
//			return false;
		
		if(password.length() <=10 && password.length() >= 6) {
			if(hasMoreTokens(password, SPECIAL_CHARS, true)) {
				if(hasMoreTokens(password, "0123456789", true)) {
					
					boolean capital, lower;
					capital = lower = false;
					
					for(int i = 0; i < password.length(); i++) {
						if(password.charAt(i) >= 'A' || password.charAt(i) <= 'Z') {
							capital = true;
							break;
						}
					}
					
					for(int i = 0; i < password.length(); i++) {
						if(password.charAt(i) >= 'a' || password.charAt(i) <= 'z') {
							lower = true;
							break;
						}
					}
					
					return capital && lower;
				}
			}
		}
		
		return false;
	}
	
	private static boolean hasMoreTokens(String password, String delim, boolean showDelim) {
		StringTokenizer str = new StringTokenizer(password, delim, showDelim);
		if(str.countTokens() > 1)
			return true;
		
		return false;
	}
	


	

	public static String suggestPassword()
	{
		//Your code goes here
		String string = "";
		string += randomCapital();
		string += randomLower();
		string += randomSpecial();
		string += randomNumber();
		
		for(int i = 0; i < (int)((Math.random() * 5)+2); i++) {
			switch((int)(Math.random() * 4)) {
			case 1: string += randomCapital();
					break;
			case 2: string += randomLower();
					break;
			case 3: string += randomSpecial();
					break;
			case 4:	string += randomNumber();
					break;
			}
		}
		if(isValid(string))
			return string;
		return suggestPassword();
	}
	
	
	
	
	private static char randomCapital() {
		int rand = (int)((Math.random() * 25)+65);
		return (char)rand;
	}
	private static char randomLower() {
		int rand = (int)((Math.random() * 25)+97);
		return (char)rand;
	}
	private static char randomSpecial() {
		char[] arr = {'!','@','#','$','%'};
		int rand = (int)(Math.random() * 5);
		return arr[rand];
	}
	private static String randomNumber() {
		return ""+(int)(Math.random() * 10);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
