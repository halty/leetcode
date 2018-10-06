package com.lee.leetcode.pro0051_0075;

/*
 *
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 *
 */
public class Pro_0067_AddBinary {

    public static void main(String[] args) {
        String a = "100";       // 000100
        String b = "110010";    // 110010
//        String result = addBinary1(a, b);
        String result = addBinary2(a, b);
        System.out.println(result);
    }

    public static String addBinary1(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        String r;
        int maxLen, minLen;
        if(lenA > lenB) {
            r = a;
            maxLen = lenA;
            minLen = lenB;
        }else {
            r= b;
            maxLen = lenB;
            minLen = lenA;
        }
        char[] buf = new char[maxLen+1];
        int flag = 0;
        for(int i=lenA-1, j=lenB-1, k=maxLen; i>=0 && j>=0; i--,j--,k--) {
            int s = (a.charAt(i)-'0') + (b.charAt(j)-'0') + flag;
            flag = s / 2;
            buf[k] = (char)(s - flag*2 + '0');
        }
        int k = maxLen-minLen-1;
        while(k >= 0) {
            if(flag == 0) {
                break;
            }
            int s = (r.charAt(k)-'0') + flag;
            flag = s / 2;
            buf[k+1] = (char)(s - flag*2 + '0');
            k--;
        }
        if(flag == 0) {
            while(k >= 0) {
                buf[k+1] = r.charAt(k);
                k--;
            }
            return new String(buf, 1, maxLen);
        }else {
            buf[0] = '1';
            return new String(buf);
        }
    }

    public static String addBinary2(String a, String b) {
        int ia = Integer.parseInt(a, 2);
        int ib = Integer.parseInt(b, 2);
        return Integer.toBinaryString(ia+ib);
    }
}
