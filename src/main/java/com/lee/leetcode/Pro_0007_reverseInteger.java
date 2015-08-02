package com.lee.leetcode;

public class Pro_0007_reverseInteger {

	public static void main(String[] args) {
		System.out.println(reverse(123));
	}

	public static int reverse(int x) {
        int result = 0;
        boolean isNegative = false;
        int limit = -Integer.MAX_VALUE;
        if(x < 0) {
        	isNegative = true;
        	x = -x;
        	limit = Integer.MIN_VALUE;
        }
        
        while(x != 0) {
        	if(result < limit / 10) {
        		result = 0;
        		break;
        	}
        	result *= 10;
        	int digit = x % 10;
        	if(result < limit + digit) {
        		result = 0;
        		break;
        	}
        	result -= digit;
        	x = x / 10;
        }
        return isNegative ? result : -result;
    }
}
