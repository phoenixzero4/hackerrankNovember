package prepare.java.datastructures.javalist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
	
		
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		List<Integer> list = new ArrayList<>();
		
		for( int i = 0; i < n; i++ ) {
			list.add(in.nextInt());
		}
		
		int q = in.nextInt();
		
		for(int i = 0; i < q; i++) {
			String op = in.next().trim();
			
			if(op.equals("Insert")) {
			int x = in.nextInt();
			int y = in.nextInt();
			list.add(x,y);
			}
			else if(op.equals("Delete")) {
				int x = in.nextInt();
				list.remove(x);
			}
			
			in.close();
		}
		
		for(int i : list) {
			System.out.print(i + " ");
			
		}
	}

}
