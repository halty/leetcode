package com.lee.leetcode.pro0226_0250;

/**
 *
 Given an integer, write a function to determine if it is a power of two.

 Example 1:
 Input: 1
 Output: true
 Explanation: 2^(0) = 1

 Example 2:
 Input: 16
 Output: true
 Explanation: 2^(4) = 16

 Example 3:
 Input: 218
 Output: false
 *
 */
public class Pro_0231_PowerOfTwo {

    public static void main(String[] args) {
        int n = 8;
//        boolean result = isPowerOfTwo(n);
        boolean result = isPowerOfTwo1(n);
        System.out.println(result);
    }

    public static boolean isPowerOfTwo(int n) {
        long v = 1;
        if(v == n) { return true; }
        while(v < n) {
            if((v <<= 1) == n) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPowerOfTwo1(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }
}
