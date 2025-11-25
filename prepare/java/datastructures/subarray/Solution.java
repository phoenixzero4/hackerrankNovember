package prepare.java.datastructures.subarray;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		int[] a = new int[n]; // create the array
		int ans = 0, sum = 0;
		for (int i = 0; i < n; i++) { // iterate over the array

			a[i] = in.nextInt();
		}

		for (int i = 0; i < n; i++) {

			for (int j = i; j < n; j++) {

				sum += a[j];

				if (sum < 0)
					ans++;

			} // end inner j loop
			sum = 0;
		} // end outer i loop
		System.out.println(ans);

	}
}
