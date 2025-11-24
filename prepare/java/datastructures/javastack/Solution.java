package prepare.java.datastructures.javastack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 *  @author phoenix </br>
 * <b> class Solution </b> 
 * </br>
 * <p> Find if brackets are balanced using a Stack data structure </p>
 * 
 *
 * 
 */
public class Solution {

	static Stack<String> stack = new Stack<>();
	

	public static void main(String[] args) throws Exception{
		
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		while((line = b.readLine()) != null) {
					
			System.out.println(isBalanced(line) ? "true" : "false");
		}
		
	}
	/**
	 * The isBalanced method accepts a String and returns a true / false depending on if the 
	 * brackets in the string are 'balanced'
	 * @param String
	 * @return boolean
	 */
	
	private static boolean isBalanced(String line) {
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			
			if( c=='(' || c=='{' || c=='[') {
				stack.push(c);
			}else if( c==')' || c=='}' || c==']') {
				if(stack.isEmpty()) return false;
				
				char o = stack.pop();
				if(!matches(o,c)) return false;
			}
		}
		return stack.isEmpty();
	} // end isBalanced
	
	private static boolean matches(char o, char c) {
		return ( o== '(' && c == ')' || o=='{' && c=='}' || o=='[' && c==']');
	} // end matches
	
} // end class
