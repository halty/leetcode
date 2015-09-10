package com.lee.leetcode.pro0026_0050;

/*
 * 
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * 
 */
public class Pro_0028_implementStrstr {

	public static void main(String[] args) {
		String haystack = "12";
		String needle = "";
		System.out.println(strStr1(haystack, needle));
	}
	
	public static int strStr1(String haystack, String needle) {
		int pLen = haystack.length(), sLen = needle.length();
    	if(pLen < sLen) { return -1; }
    	if(sLen == 0) { return 0; }
    	if(sLen == 1) {
    		char ch = needle.charAt(0);
    		for(int i=0; i<pLen; i++) {
    			if(haystack.charAt(i) == ch) {
    				return i;
    			}
    		}
    		return -1;
    	}
    	
		int[] index = index(needle);
		for(int i=0, j=0; i<pLen;) {
			if(haystack.charAt(i) == needle.charAt(j)) {
				i++;
				j++;
				if(j == sLen) { return i-sLen; }
			}else {
				if(j == 0) {
					i++;
				}else {
					j = index[j];
				}
			}
		}
		return -1;
    }
	
	private static int[] index(String needle) {
		int len = needle.length();
		int[] index = new int[len];
		index[0] = -1; index[1] = 0;
		for(int i=2; i<len; i++) {
			int prev = i - 1;
			int k = index[prev];
			while(k >= 0 && needle.charAt(k) != needle.charAt(prev)) {
				k = index[k];
			}
			index[i] = k + 1;
		}
		
		return index;
	}

    public static int strStr(String haystack, String needle) {
    	int pLen = haystack.length(), sLen = needle.length();
    	if(pLen < sLen) { return -1; }
    	
    	int max = pLen - sLen;
    	for(int i=0; i<=max; i++) {
    		boolean match = true;
    		for(int j=0; j<sLen; j++) {
    			if(haystack.charAt(i+j) != needle.charAt(j)) {
    				match = false;
    				break;
    			}
    		}
    		if(match) { return i; }
    	}
        return -1;
    }
}
