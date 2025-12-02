package prepare.java.oop.inheritance2;

class Arithmetic {

	public int add( int a, int b ) {
		return a + b;
	}
}

class Adder extends Arithmetic {

}

public class Solution {

	public static void main( String[] args ) {
		Adder a = new Adder();
		System.out.println("My class is " + a.getClass().getSuperclass().getName());
		System.out.println(a.add(10, 32) + " " + a.add(10, 3) + " " + a.add(1, 10) + "\n");

	}
}
