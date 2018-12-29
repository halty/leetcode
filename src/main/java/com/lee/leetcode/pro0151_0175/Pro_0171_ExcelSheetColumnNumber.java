package com.lee.leetcode.pro0151_0175;

/**
 *
 Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:
     A -> 1
     B -> 2
     C -> 3
     ...
     Z -> 26
     AA -> 27
     AB -> 28
     ...

 Example 1:
 Input: "A"
 Output: 1

 Example 2:
 Input: "AB"
 Output: 28

 Example 3:
 Input: "ZY"
 Output: 701
 *
 */
public class Pro_0171_ExcelSheetColumnNumber {

    public static void main(String[] args) {
        String s = "ZY";
        int num = titleToNumber(s);
        System.out.println(num);
    }

    public static int titleToNumber(String s) {
        int num = 0;
        for(int i=s.length()-1,base=1; i>=0; i--,base*=26) {
            int k = s.charAt(i) - 'A' + 1;
            num += k * base;
        }
        return num;
    }
}
