package com.lee.leetcode.pro0051_0075;

/*
 *
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5
 *
 */
public class Pro_0058_LengthOfLastWord {

    public static void main(String[] args) {
        String s = "Hello World";
        int result = lengthOfLastWord(s);
        System.out.println(result);
    }

    public static int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int length = 0;
        boolean isLastWord = false;
        for(int i=s.length()-1; i>=0; i--) {
            if(s.charAt(i) == ' ') {
                if(isLastWord) {
                    break;
                }
            }else {
                if(!isLastWord) {
                    isLastWord = true;
                }
                length += 1;
            }
        }
        return length;
    }
}
