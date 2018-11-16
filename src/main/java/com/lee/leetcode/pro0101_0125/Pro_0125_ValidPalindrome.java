package com.lee.leetcode.pro0101_0125;

/**
 *
 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 Note: For the purpose of this problem, we define empty string as valid palindrome.

 Example 1:
 Input: "A man, a plan, a canal: Panama"
 Output: true

 Example 2:
 Input: "race a car"
 Output: false
 *
 */
public class Pro_0125_ValidPalindrome {

    public static void main(String[] args) {
        String s = "aa";
//        boolean result = isPalindrome1(s);
        boolean result = isPalindrome2(s);
        System.out.println(result);
    }

    public static boolean isPalindrome1(String s) {
        if(s == null) { return false; }
        String r = filter(s);
        int len = r.length();
        if(len < 2) { return true; }
        int end = len / 2;
        for(int b=0,e=len-1; b<end; b++,e--) {
            if(r.charAt(b) != r.charAt(e)) {
                return false;
            }
        }
        return true;
    }

    private static String filter(String s) {
        StringBuilder buf = new StringBuilder();
        int len = s.length();
        for(int i=0; i<len; i++) {
            char ch = s.charAt(i);
            if(isAlphanumeric(ch)) {
                buf.append(toUpperCase(ch));
            }
        }
        return buf.toString();
    }

    private static boolean isAlphanumeric(char ch) {
        return (ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z') || (ch >='a' && ch <='z');
    }

    private static char toUpperCase(char ch) {
        return ch > 'Z' ? (char)(ch-32) : ch;
    }

    public static boolean isPalindrome2(String s) {
        int len = s.length();
        if(len < 2) {
            return true;
        }else {
            int b = 0, e = len - 1;
            while(true) {
                for(; b<e && !isAlphanumeric(s.charAt(b)); b++);
                for(; b<e && !isAlphanumeric(s.charAt(e)); e--);
                if(b >= e) { return true; }
                char cb = toUpperCase(s.charAt(b));
                char ce = toUpperCase(s.charAt(e));
                if(cb != ce) { return false; }
                b++;
                e--;
            }
        }
    }
}
