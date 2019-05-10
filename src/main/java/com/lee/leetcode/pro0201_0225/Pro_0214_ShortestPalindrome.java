package com.lee.leetcode.pro0201_0225;

import java.util.Arrays;

/**
 *
 Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it.
 Find and return the shortest palindrome you can find by performing this transformation.

 Example 1:
 Input: "aacecaaa"
 Output: "aaacecaaa"

 Example 2:
 Input: "abcd"
 Output: "dcbabcd"
 *
 */
public class Pro_0214_ShortestPalindrome {

    public static void main(String[] args) {
        String s = "a";
        String p = shortestPalindrome(s);
        System.out.println(p);
        p = shortestPalindrome1(s);
        System.out.println(p);
        /*int[] array = calcPrefix(s);
        System.out.println(Arrays.toString(array));*/
    }

    public static String shortestPalindrome(String s) {
        int len = s.length();
        if(len == 0) { return s; }
        int p = len - 1;
        while(p > 0) {
            int b = 0;
            int e = p;
            while(b < e) {
                if(s.charAt(b) == s.charAt(e)) {
                    b++;
                    e--;
                }else {
                    break;
                }
            }
            if(b >= e) { break; }
            p--;
        }
        if(p == len - 1) {
            return s;
        }else {
            StringBuilder buf = new StringBuilder(len + len-1-p);
            for(int i=len-1; i>p; i--) {
                buf.append(s.charAt(i));
            }
            buf.append(s);
            return buf.toString();
        }
    }

    public static String shortestPalindrome1(String s) {
        int len = s.length();
        switch(len) {
            case 0:
            case 1: return s;
            case 2: return s.charAt(0) == s.charAt(1) ? s : s.substring(1) + s;
            case 3:
                char ch0 = s.charAt(0);
                if(ch0 == s.charAt(2)) {
                    return s;
                }else if(ch0 == s.charAt(1)) {
                    return s.substring(2) + s;
                }else {
                    StringBuilder buf = new StringBuilder(len + 2);
                    buf.append(s.charAt(2)).append(s.charAt(1)).append(s);
                    return buf.toString();
                }
        }
        int[] prefix = calcPrefix(s);
        int e = len - 1;
        int b = 0;
        while(b < e) {
            char chb = s.charAt(b);
            char che = s.charAt(e);
            if(chb == che) {
                b++;
                e--;
            }else {
                if(b == 0) {
                    e--;
                    continue;
                }
                int p = prefix[b-1];
                for(; p >= 0 && che != s.charAt(p+1); p = prefix[p]);
                if(p < 0) {
                    b = 0;
                }else {
                    b = p + 2;
                    e--;
                }
            }
        }
        int p = (b == e ? 2*b+1 : 2*b);
        if(p == len) { return s; }
        StringBuilder buf = new StringBuilder(len + len-p);
        for(int i=len-1; i>=p; i--) {
            buf.append(s.charAt(i));
        }
        buf.append(s);
        return buf.toString();
    }

    private static int[] calcPrefix(String s) {
        int len = s.length();
        len = (len >> 1) + (len & 1);
        char first = s.charAt(0);
        int[] prefix = new int[len];
        prefix[0] = -1;
        prefix[1] = (s.charAt(1) == first ? 0 : -1);
        for(int i=2; i<len; i++) {
            char ch = s.charAt(i);
            int idx = prefix[i-1];
            while(idx >= 0) {
                int next = idx + 1;
                if(ch == s.charAt(next)) {
                    prefix[i] = next;
                    break;
                }
                idx = prefix[idx];
            }
            if(idx < 0) {
                prefix[i] = (ch == first ? 0 : -1);
            }
        }
        return prefix;
    }
}
