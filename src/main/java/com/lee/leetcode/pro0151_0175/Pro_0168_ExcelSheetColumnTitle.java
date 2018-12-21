package com.lee.leetcode.pro0151_0175;

/**
 *
 Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 For example:
     1 -> A
     2 -> B
     3 -> C
     ...
     26 -> Z
     27 -> AA
     28 -> AB
     ...

 Example 1:
 Input: 1
 Output: "A"

 Example 2:
 Input: 28
 Output: "AB"

 Example 3:
 Input: 701
 Output: "ZY"
 *
 */
public class Pro_0168_ExcelSheetColumnTitle {

    public static void main(String[] args) {
        int n = 52;
        String title = convertToTitle(n);
        System.out.println(title);
    }

    public static String convertToTitle(int n) {
        StringBuilder buf = new StringBuilder();
        char ch;
        while(n > 26) {
            int r = n % 26;
            if(r > 0) {
                ch = (char) ('A' + (n % 26) - 1);
                buf.append(ch);
                n /= 26;
            }else {
                buf.append('Z');
                n /= 26;
                n -= 1;
            }
        }
        ch = (char)('A' + n - 1);
        buf.append(ch);
        return buf.reverse().toString();
    }
}
