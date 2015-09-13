package com.lee.leetcode.pro0026_0050;

/*
 * 
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 *
 */
public class Pro_0032_longestValidParentheses {

	public static void main(String[] args) {
		// String s = "()";	// 2
		// String s = "()()";	// 4
		// String s = "(())";	// 4
		// String s = "()(())";	// 6
		// String s = "(()(()))";	// 8
		// String s = "(()()(()";	// 4
		// String s = "(()()(((()";	// 4
		String s = "(()(((()";	// 4
		System.out.println(longestValidParentheses1(s));
	}
	
	public static int longestValidParentheses1(String s) {
		int len = s.length();
		if(len < 2) { return 0; }
		
		int maxValidLen = 0;
		int[] iStack = new int[len]; int ip = 0;
		Pair[] pStack = new Pair[len / 2 + 1]; int pp = 0;
		for(int i=0; i<len; i++) {
			char ch = s.charAt(i);
			switch(ch) {
			case '(':
				iStack[ip++] = i;
				break;
			case ')':
				if(ip == 0) {
					int localMaxValidLen = maxValidLen(pStack, pp);
					if(localMaxValidLen > maxValidLen) { maxValidLen = localMaxValidLen; }
					pp = 0;
				}else {
					int end = i;
					int begin = iStack[--ip];
					pp = addPair(pStack, pp, begin, end);
				}
				break;
			}
		}
		return Math.max(maxValidLen(pStack, pp), maxValidLen);
    }
	
	private static int maxValidLen(Pair[] pStack, int pp) {
		if(pp == 0) { return 0; }
		int maxValidLen = 0;
		for(int i=0; i<pp; i++) {
			Pair pair = pStack[i];
			int validLen = pair.end - pair.begin + 1;
			if(validLen > maxValidLen) { maxValidLen = validLen; }
		}
		return maxValidLen;
	}
	
	private static int addPair(Pair[] pStack, int pp, int newBegin, int newEnd) {
		pStack[pp++] = new Pair(newBegin, newEnd);
		while(pp > 1) {
			int top = pp - 1;
			Pair first = pStack[top];
			Pair second = pStack[top-1];
			if(first.begin == second.end + 1) {
				second.end = first.end;
				pp--;
			}else if(first.begin + 1 == second.begin && second.end + 1 == first.end) {
				pStack[top-1] = first;
				pp--;
			}else {
				break;
			}
		}
		
		return pp;
	}
	
	private static class Pair {
		int begin;
		int end;
		Pair(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
	}

	public static int longestValidParentheses(String s) {
		int len = s.length();
		if(len < 2) { return 0; }
		
		int maxSubLen = 0;
		int end = len - 1;
		for(int i=0; i<end; i++) {
			if(s.charAt(i) == ')') { continue; }
			for(int j=i+1; j<len; j+=2) {
				if(s.charAt(j) == ')' && isValid(s, i, j)) {
					int subLen = j-i+1;
					if(subLen > maxSubLen) {
						maxSubLen = subLen;
					}
				}
			}
		}
		return maxSubLen;
    }

	private static boolean isValid(String s, int begin, int end) {
		int p = 0;
		for(int i=begin; i<=end; i++) {
			char ch = s.charAt(i);
			switch(ch) {
			case '(': ++p; break;
			case ')':
				if(--p == -1) { return false; }
			}
		}
		return p == 0;
	}
}
