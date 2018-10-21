package com.lee.leetcode.pro0076_0100;

/**
 *
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 */
public class Pro_0091_DecodeWays {

    public static void main(String[] args) {
        String s = "40";
//        int result = numDecodings1(s);
        int result = numDecodings2(s);
        System.out.println(result);
    }

    public static int numDecodings1(String s) {
        int end = s.length() - 2;
        return numDecodingsRecursively(s, 0, end);
    }

    private static int numDecodingsRecursively(String s, int index, int end) {
        if(index < end) {
            int i = numOf(s, index);
            if(i == 0) {
                return 0;
            }else {
                int cnt = 0;
                int c = numDecodingsRecursively(s, index+1, end);
                if(c != -1) {
                    cnt += c;
                }
                int j = numOf(s, index+1);
                int v = i * 10 + j;
                if(v <= 26) {
                    c = numDecodingsRecursively(s, index+2, end);
                    if(c != -1) {
                        cnt += c;
                    }
                }
                return cnt;
            }
        }else {
            if(index == end) {
                int i = numOf(s, index);
                if(i == 0) {
                    return 0;
                }else {
                    int j = numOf(s, index+1);
                    int v = i * 10 + j;
                    int cnt = 0;
                    if(v <= 26) {
                        cnt += 1;
                    }
                    if(j > 0) {
                        cnt += 1;
                    }
                    return cnt;
                }
            }else {
                int v = numOf(s, index);
                return v > 0 ? 1 : 0;
            }
        }
    }

    public static int numDecodings2(String s) {
        if(numOf(s, 0) == 0) { return 0; }
        int len = s.length();
        int last = 1;
        int prev = 1;
        for(int k=1; k<len; k++) {
            int j = numOf(s, k);
            int i = numOf(s, k-1);
            int current = 0;
            if(i > 0 && (i*10+j) <= 26) {
                current += last;
            }
            if(j > 0) {
                current += prev;
            }
            if(current == 0) { return 0; }
            last = prev;
            prev = current;
        }
        return prev;
    }

    private static int numOf(String s, int index) {
        return s.charAt(index) - '0';
    }
}
