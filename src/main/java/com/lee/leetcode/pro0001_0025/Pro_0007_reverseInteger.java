package com.lee.leetcode.pro0001_0025;

/*
 * 
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 * 
 */
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
