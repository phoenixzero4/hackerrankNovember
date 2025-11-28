package com.hackerRank.javaBasic;

public class Solution {

	class Arithmetic <E>{
		
		public static int sum (Integer[] array) {
			
			int sum = 0;
			for(Integer i : array) {
				sum += i;
			}
			return sum;
		}
		
		public static String sum(String[] array) {
			String ans = "";
			for(String s: array) {
				ans += s;
			}
			return ans;
		}
	}
	public static void main(String[] args) {
		
		Integer[] a = { 1,2,3};
		String[] b = {"Phoenix", "Gloria"};
		
	
		System.out.println(Arithmetic.sum(a));
		System.out.println(Arithmetic.sum(b));
		}

	}


