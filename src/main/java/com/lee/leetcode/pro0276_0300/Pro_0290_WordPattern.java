package com.lee.leetcode.pro0276_0300;

import java.util.*;

/**
 *
 Given a pattern and a string str, find if str follows the same pattern.
 Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

 Example 1:
 Input: pattern = "abba", str = "dog cat cat dog"
 Output: true

 Example 2:
 Input:pattern = "abba", str = "dog cat cat fish"
 Output: false

 Example 3:
 Input: pattern = "aaaa", str = "dog cat cat dog"
 Output: false

 Example 4:
 Input: pattern = "abba", str = "dog dog dog dog"
 Output: false

 Notes:
    You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 *
 */
public class Pro_0290_WordPattern {

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog dog dog dog";
        boolean isMatched = wordPattern(pattern, str);
        System.out.println(isMatched);
    }

    public static boolean wordPattern(String pattern, String str) {
        int[] patternIndex = parsePattern(pattern);
        List<Integer> strIndex = parseStr(str);
        int patternLen = patternIndex.length, strLen = strIndex.size();
        if(patternLen != strLen) { return false; }
        for(int i=0; i<patternLen; i++) {
            if(patternIndex[i] != strIndex.get(i)) {
                return false;
            }
        }
        return true;
    }

    private static int[] parsePattern(String pattern) {
        int len = pattern.length();
        if(len == 0) { return new int[0]; }
        int[] patternIndex = new int[len];
        int[] map = new int[26];
        int index = 1;
        for(int i=0; i<len; i++) {
            char ch = pattern.charAt(i);
            int existedIndex = map[ch-'a'];
            if(existedIndex == 0) {
                map[ch-'a'] = index;
                patternIndex[i] = index;
                index++;
            }else {
                patternIndex[i] = existedIndex;
            }
        }
        return patternIndex;
    }

    private static List<Integer> parseStr(String str) {
        char[] chars = str.toCharArray();
        if(chars.length == 0) { return Collections.emptyList(); }
        List<Integer> indexList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int begin = 0, end = 0, index = 1;
        while(true) {
            for(; end<chars.length && chars[end] != ' '; end++);
            String s = new String(chars, begin, end-begin);
            Integer existedIndex = map.get(s);
            if(existedIndex == null) {
                map.put(s, index);
                indexList.add(index);
                index++;
            }else {
                indexList.add(existedIndex);
            }
            if(end == chars.length) { break; }
            begin = end = end + 1;
        }
        return indexList;
    }
}
