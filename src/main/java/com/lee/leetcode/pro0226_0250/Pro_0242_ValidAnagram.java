package com.lee.leetcode.pro0226_0250;

/**
 *
 Given two strings s and t , write a function to determine if t is an anagram of s.

 Example 1:
 Input: s = "anagram", t = "nagaram"
 Output: true

 Example 2:
 Input: s = "rat", t = "car"
 Output: false

 Note: You may assume the string contains only lowercase alphabets.

 Follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?
 *
 */
public class Pro_0242_ValidAnagram {

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        boolean result = isAnagram(s, t);
        System.out.println(result);
    }

    public static boolean isAnagram(String s, String t) {
        if(s == null) { return t == null; }
        if(t == null) { return false; }
        int sLen = s.length(), tLen = t.length();
        if(sLen != tLen) { return false; }
        if(sLen == 1) { return s.charAt(0) == t.charAt(0); }
        int[] sc = new int[26];
        int[] tc = new int[26];
        for(int i=0; i<sLen; i++) {
            sc[s.charAt(i)-'a']++;
        }
        for(int i=0; i<tLen; i++) {
            tc[t.charAt(i)-'a']++;
        }
        for(int i=0; i<sLen; i++) {
            if(sc[i] != tc[i]) {
                return false;
            }
        }
        return true;
    }
}
