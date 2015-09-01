package com.lee.leetcode.pro0001_0025;

public class Pro_0008_atoi {

	public static void main(String[] args) {
		String str = "9223372036854775809";
		System.out.println(Long.MAX_VALUE);
		System.out.println(myAtoi(str));
	}

	public static int myAtoi(String str) {
        char[] array = str.toCharArray();
        int i = 0;
        boolean isNegative = false;
        int limit = -Integer.MAX_VALUE;
        for(; i<array.length; i++) {
        	char ch = array[i];
        	if(ch == ' ') { continue; }
        	if(ch == '-' || ch == '+') {
        		if(isNegative = (ch == '-')) { limit = Integer.MIN_VALUE; }
        		i++;
        		break;
        	}else {
        		break;
        	}
        }
        /** 溢出的处理 **/
        int result = 0;
        for(; i<array.length; i++) {
        	char ch = array[i];
        	if(ch >= '0' && ch <= '9') {
        		int digit = (ch - '0');
        		if(result < limit / 10) { result = limit; break; }
        		result *= 10;
        		if(result < limit + digit) { result = limit; break; }
        		result -= digit;
        	}else {
        		break;
        	}
        }
        return isNegative ? result : -result;
    }
}
