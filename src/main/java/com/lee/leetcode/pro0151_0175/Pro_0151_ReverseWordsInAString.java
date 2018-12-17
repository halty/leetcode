package com.lee.leetcode.pro0151_0175;

/**
 *
 Given an input string, reverse the string word by word.

 Example:
 Input: "the sky is blue",
 Output: "blue is sky the".

 Note:
   1. A word is defined as a sequence of non-space characters.
   2. Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
   3. You need to reduce multiple spaces between two words to a single space in the reversed string.

 Follow up: For C programmers, try to solve it in-place in O(1) space.
 *
 */
public class Pro_0151_ReverseWordsInAString {

    public static void main(String[] args) {
        String s = "the sky is blue";
//        String r = reverseWords(s);
        String r = reverseWords1(s);
        System.out.println(r);
    }

    public static String reverseWords(String s) {
        if(s == null || s.isEmpty()) { return s; }
        char[] source = s.toCharArray();
        int len = source.length;
        char[] dest = new char[len];
        int di = 0, si = len - 1;
        while(true) {
            if(source[si] == ' ') {
                for(si-=1; si>=0 && source[si]==' '; si--);
                if(si < 0) { break; }
            }else {
                int e = si + 1;
                for(si-=1; si>=0 && source[si]!=' '; si--);
                int b = si + 1;
                int count =  e - b;
                if(di > 0) { dest[di++] = ' '; }
                System.arraycopy(source, b, dest, di, count);
                di += count;
                if(si < 0) { break; }
            }
        }
        return new String(dest, 0, di);
    }

    public static String reverseWords1(String s) {
        if(s == null || s.isEmpty()) { return s; }
        char[] array = s.toCharArray();
        int len = array.length, p = 0, i = 0;
        while(true) {
            for(; i<len && array[i]==' '; i++);
            if(i == len) { break; }
            int b = i;
            for(; i<len && array[i]!=' '; i++);
            int e = i - 1;
            if(p > 0) { array[p++] = ' '; }
            for(; p<b && e>=b; p++,e--) { array[p] = array[e]; }
            if(p == b) {
                int next = e + 1;
                for(; p<e; p++,e--) {
                    char tmp = array[p];
                    array[p] = array[e];
                    array[e] = tmp;
                }
                p = next;
            }
            if(i == len) { break; }
        }
        for(int b=0,e=p-1; b<e; b++,e--) {
            char tmp = array[b];
            array[b] = array[e];
            array[e] = tmp;
        }
        return new String(array, 0, p);
    }
}
