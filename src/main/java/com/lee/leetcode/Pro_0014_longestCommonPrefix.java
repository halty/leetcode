package com.lee.leetcode;

public class Pro_0014_longestCommonPrefix {

	public static void main(String[] args) {
		String[] strs = new String[] {"a"};
		System.out.println(longestCommonPrefix(strs));
	}
	
	public static String longestCommonPrefix(String[] strs) {
        int mLen = Integer.MAX_VALUE;
        String minStr = "";
        if(strs.length == 0) {
        	mLen = 0;
        }else {
        	if(strs.length == 1) { return strs[0]; }
        	for(String str : strs) {
        		if(str.length() < mLen) { minStr = str; }
        	}
        	mLen = minStr.length();
        }
        if(mLen == 0) { return ""; }
        
        int i = 0;
        boolean match = true;
        for(; i<mLen && match; i++) {
        	char ch = minStr.charAt(i);
        	for(String str : strs) {
        		if(str.charAt(i) != ch) {
        			match = false;
        			break;
        		}
        	}
        }
        
		return minStr.substring(0, i);
    }

}
