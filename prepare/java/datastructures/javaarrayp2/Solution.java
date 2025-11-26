package prepare.java.datastructures.javaarrayp2;

import java.util.Scanner;

public class Solution {

    public static boolean canWin(int leap, int[] line, int n) {
        
        int cur = 0;
        
        boolean playing = true;
        while(playing) {
            if((cur + 1 < n  && line[cur+1]==1) && (cur + leap < n && line[cur+leap]==1)){
            //	System.out.println("cur: " + cur + "line[cur+1] " + line[cur+1] + " cur+1 " + cur+1);
            	return false;
            	              
            }
            else if(cur + leap >= n || cur + 1 >= n) return true;
            else if(line[cur + leap]==0) cur = cur +leap;
            else if(line[cur + 1]==0) cur++;
            else{
                return false;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        
        
        for(int j = 0; j < q; j++) {
        int n = in.nextInt();
        int leap = in.nextInt();
        int[] line = new int[n];
        for(int i = 0; i < n; i++) {
        	line[i] = in.nextInt();
        	System.err.println(line[i] + " ");
        }System.err.println();
       if(canWin(leap,line,n)) System.out.println("YES");
       else System.out.println("NO");
        }
    }

}
