package com.lee.leetcode.pro0101_0125;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a string S and a string T, count the number of distinct subsequences of S which equals T.

 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

 Example 1:
 Input: S = "rabbbit", T = "rabbit"
 Output: 3
 Explanation:
 As shown below, there are 3 ways you can generate "rabbit" from S.
 (The caret symbol ^ means the chosen letters)
 rabbbit
 ^^^^ ^^
 rabbbit
 ^^ ^^^^
 rabbbit
 ^^^ ^^^

 Example 2:
 Input: S = "babgbag", T = "bag"
 Output: 5
 Explanation:
 As shown below, there are 5 ways you can generate "bag" from S.
 (The caret symbol ^ means the chosen letters)
 babgbag
 ^^ ^
 babgbag
 ^^    ^
 babgbag
 ^    ^^
 babgbag
 ^  ^^
 babgbag
 ^^^
 *
 */
public class Pro_0115_DistinctSubsequences {

    public static void main(String[] args) {
        String s = "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc";
        String t = "bcddceeeebecbc";    // 700531452
        int result = numDistinct(s, t);
        System.out.println(result);
    }

    public static int numDistinct(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if(sLen < tLen) {
            return 0;
        }else if(sLen == tLen) {
            if(sLen == 0) { return 1; }
            int i = 0;
            while(i < sLen) {
                if(s.charAt(i) != t.charAt(i)) { break; }
                i++;
            }
            return i == sLen ? 1 : 0;
        }else {
            if(tLen == 0) { return 1; }
//            return numDistinctRecursively(s, 0, sLen, t, 0, tLen);
            return numDistinct2(s, sLen, t, tLen);
        }
    }

    /** O(sLen ^ tLen) **/
    private static int numDistinctRecursively(String s, int sb, int sLen, String t, int tb, int tLen) {
        int end = sLen- tLen + tb;
        int count = 0;
        if(tb < tLen - 1) {
            char ch = t.charAt(tb);
            for(int i= sb; i<=end; i++) {
                if(s.charAt(i) == ch) {
                    count += numDistinctRecursively(s, i+1, sLen, t, tb+1, tLen);
                }
            }
        }else {
            char ch = t.charAt(tb);
            for(int i=sb; i<=end; i++) {
                if(s.charAt(i) == ch) {
                    count += 1;
                }
            }
        }
        return count;
    }

    /** O(tLen * sLen) **/
    private static int numDistinct2(String s, int sLen, String t, int tLen) {
        List<State> prev = new ArrayList<>();
        char ch = t.charAt(0);
        int end = sLen - tLen;
        for(int i=0; i<=end; i++) {
            if(s.charAt(i) == ch) {
                prev.add(new State(i, 1));
            }
        }
        for(int i=1; i<tLen; i++) {
            List<State> current = new ArrayList<>();
            int size = prev.size();
            int c = 0;
            end += 1;
            ch = t.charAt(i);
            for(int j=0; j<size; j++) {
                c += prev.get(j).count;
                int b = prev.get(j).index + 1;
                int e = (j == size - 1 ? end : prev.get(j+1).index);
                while(b <= e) {
                    if(s.charAt(b) == ch) {
                        current.add(new State(b, c));
                    }
                    b++;
                }
            }
            if(current.isEmpty()) {
                return 0;
            }else {
                prev = current;
            }
        }
        int size = prev.size();
        int count = 0;
        for(int i=0; i<size; i++) {
            count += prev.get(i).count;
        }
        return count;
    }

    private static class State {
        int index;
        int count;
        State(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }
}
