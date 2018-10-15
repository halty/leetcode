package com.lee.leetcode.pro0001_0025;

/**
 * 
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * 
 */
public class Pro_0020_validParentheses {

	public static void main(String[] args) {
		String s = "([)]";
		System.out.println(isValid(s));
	}

	public static boolean isValid(String s) {
        int len = s.length();
        if(len == 0) { return true; }
        char[] buf = new char[len];
        int p = -1;
        for(int i=0; i<len; i++) {
        	char ch = s.charAt(i);
        	switch(ch) {
        	case '(': buf[++p] = ')'; break;
        	case '[': buf[++p] = ']'; break;
        	case '{': buf[++p] = '}'; break;
        	case ')':
        	case ']':
        	case '}':
        		if(p >= 0 && buf[p--] == ch) break;
        	default: return false;
        	}
        }
        return p == -1;
    }
	
}
