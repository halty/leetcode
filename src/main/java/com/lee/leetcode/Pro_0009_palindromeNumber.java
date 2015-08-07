package com.lee.leetcode;

public class Pro_0009_palindromeNumber {

	public static void main(String[] args) {
		int x = +0;
		System.out.println(isPalindrome(x));
	}

	public static boolean isPalindrome(int x) {
		if(x < 0) { return false; }
        long a = x, b = 0;
        while(x != 0) {
        	b = 10*b + x%10;
        	x /= 10;
        }
        return a == b;
    }
}
