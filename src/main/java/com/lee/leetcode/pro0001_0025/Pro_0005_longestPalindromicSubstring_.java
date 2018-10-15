package com.lee.leetcode.pro0001_0025;

import java.util.Arrays;

/**
 * 
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * 
 */
public class Pro_0005_longestPalindromicSubstring_ {

	public static void main(String[] args) {
		String s = "aaaa";
		System.out.println(longestPalindrome2(s));
	}

	/** 时间：O(n^2)；空间：O(1) **/
	public static String longestPalindrome(String s) {
        int length = s.length();
        if(length <= 1) { return s; }

        int start = 0, end = 0, max = 0;
		for(int i=1; i<length; i++) {
			// 偶对称
			int maxStep = Math.min(i, length-i);
			int left = i - 1, right = i;
			for(int step = 0; step<maxStep; step++, left--, right++) {
				if(s.charAt(left) != s.charAt(right)) {
					break;
				}
			}
			int len = right-1-left;
			if(max < len) {
				max = len;
				start = left + 1;
				end = right - 1;
			}
			
			// 奇对称
			maxStep = Math.min(i, length-i-1);
			left = i - 1;
			right = i + 1;
			for(int step = 0; step<maxStep; step++, left--, right++) {
				if(s.charAt(left) != s.charAt(right)) {
					break;
				}
			}
			len = right-1-left;
			if(max < len) {
				max = len;
				start = left + 1;
				end = right - 1;
			}
		}
		return s.substring(start, end+1);
    }
	
	/** DP 时间：最坏O(n^2)；空间：O(n) **/
	public static String longestPalindrome1(String s) {
		int length = s.length();
        if(length <= 1) { return s; }
        
        int start = 0, end = 0, len = 0;
        int[] startArray = new int[length];
        Arrays.fill(startArray, -1);
        if(s.charAt(0) == s.charAt(1)) {
        	startArray[0] = 0;
        	start = 0;
        	end = 1;
        	len = 2;
        }
        for(int i=2; i<length; i++) {
        	int j=0, left = startArray[j];
        	int k = 0;
        	char ch = s.charAt(i);
        	while(left != -1) {
        		if(left > 0 && s.charAt(left-1) == ch) {
        			startArray[k++] = left-1;
        		}
        		left = startArray[++j];
        	}
        	if((left=i-2) >= 0 && s.charAt(left) == ch) {
        		startArray[k++] = left;
        	}
        	if((left=i-1) >= 0 && s.charAt(left) == ch) {
        		startArray[k++] = left;
        	}
        	startArray[k] = -1;
        	if((left = startArray[0]) != -1 && len < (j=i-left+1)) {
        		len = j;
        		start = left;
        		end = i;
        	}
        }
        
        return len == 0 ? s : s.substring(start, end+1);
	}
	
	/** 时间：O(n)；空间：O(n) <a href=http://blog.csdn.net/ggggiqnypgjg/article/details/6645824>Manacher算法</a> **/
	public static String longestPalindrome2(String s) {
		int length = s.length();
        if(length <= 1) { return s; }
        
		char seperator = 0;
		int expandLen = 2 * length + 1;
		char[] expandStr = new char[expandLen];
		for(int index = 0, i=0; i<length; i++) {
			expandStr[index++] = seperator;
			expandStr[index++] = s.charAt(i);
		}
		expandStr[expandLen-1] = seperator;
		
		// calculate index
		int[] lenArray = new int[expandLen];
		int id = 0, max = 1;
		lenArray[id] = max;
		int expandEnd = expandLen - 1;
		for(int i=1; i<expandEnd; i++) {
			lenArray[i] = (i < max ? Math.min(max - i, lenArray[2*id-i]) : 1);
			int curMax = i + lenArray[i];
			for(int left = 2*i - curMax;
				curMax<expandLen && left>=0 && expandStr[left] == expandStr[curMax];
				curMax++, left--);
			lenArray[i] = curMax - i;
			if(curMax > max) {
				max = curMax;
				id = i;
			}
			if(curMax == expandLen) { break; }
		}
		
		// calculate longest
		int pos = 0, maxLen = lenArray[0];
		for(int i=1; i<expandLen; i++) {
			if(lenArray[i] > maxLen) {
				maxLen = lenArray[i];
				pos = i;
			}
		}
		int maxSubLen = maxLen - 1, start = pos / 2 - maxSubLen / 2;
		return s.substring(start, start+maxSubLen);
	}
}
