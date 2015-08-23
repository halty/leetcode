package com.lee.leetcode;

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
