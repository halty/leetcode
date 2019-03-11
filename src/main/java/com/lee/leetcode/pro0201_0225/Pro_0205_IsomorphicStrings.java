package com.lee.leetcode.pro0201_0225;

import java.util.HashMap;
import java.util.Map;

/**
 *
 Given two strings s and t, determine if they are isomorphic.
 Two strings are isomorphic if the characters in s can be replaced to get t.
 All occurrences of a character must be replaced with another character while preserving the order of characters.
 No two characters may map to the same character but a character may map to itself.

 Example 1:
 Input: s = "egg", t = "add"
 Output: true

 Example 2:
 Input: s = "foo", t = "bar"
 Output: false

 Example 3:
 Input: s = "paper", t = "title"
 Output: true

 Note: You may assume both s and t have the same length.
 *
 */
public class Pro_0205_IsomorphicStrings {

    public static void main(String[] args) {
        String s = "foo";
        String t = "bar";
        boolean result = isIsomorphic(s, t);
        System.out.print(result);
    }

    public static boolean isIsomorphic(String s, String t) {
        int length = s.length();
        if(length <= 1) { return true; }
        Map<Character, Character> mapping = new HashMap<>();
        Map<Character, Character> invertedMapping = new HashMap<>();
        for(int i=0; i<length; i++) {
            char si = s.charAt(i);
            char ti = t.charAt(i);
            Character mi = mapping.get(si);
            if(mi == null) {
                Character imi = invertedMapping.get(ti);
                if(imi != null && imi != si) {
                    return false;   // two chars map to the same char
                }
                mapping.put(si, ti);
                invertedMapping.put(ti, si);
            }else if(mi != ti) {
                return false;
            }
        }
        return true;
    }
}
