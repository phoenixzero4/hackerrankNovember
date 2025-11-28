package prepare.java.datastructures.javaarrayp2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Solution {

	public static boolean canWin(int leap, int[] game) {
	
		boolean[] seen = new boolean[game.length];
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(0);
		
		while(!stack.isEmpty()) {
			int i = stack.pop();
			
			if(i < 0) continue;
			
			if(i >= game.length) return true;
			
			if(seen[i]) continue;
			
			if(game[i] == 1) continue;
			
			seen[i] = true;
			
			stack.push(i + leap);
			stack.push(i+1);;
			stack.push(i-1);
		}
		return false;
		
	}
	
   public static void main(String[] args) {
	   
	   Scanner in = new Scanner(System.in);
	   int q = in.nextInt();
	   
	   while(q-- > 0) {
		   int n = in.nextInt();
		   int leap = in.nextInt();
		   int[] game = new int[n];
		   
		   for(int i = 0; i < n; i++) {
			   game[i] = in.nextInt();
		   }
		   
		   System.out.println( (canWin(leap, game) == true) ? "YES" : "NO");
	   }
	   in.close();
        
     
        
    }

}
