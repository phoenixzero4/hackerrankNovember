package interviews.reversestring;

public class ReverseString {

	public static void main(String[] args) {
		String s = "Hello World";
		
		char[] a = s.toCharArray();
		for(int i = 0, j = a.length-1; i < a.length; i++, j--)
			a[i] = s.charAt(j);
		
		System.out.println(s);
		System.out.println(a);
		
	}

}
