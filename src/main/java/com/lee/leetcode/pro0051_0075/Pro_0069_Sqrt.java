package com.lee.leetcode.pro0051_0075;

/**
 *
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
 *
 */
public class Pro_0069_Sqrt {

    public static void main(String[] args) {
        int x = 2147483647;
//        int result = mySqrt1(x);
//        int result = mySqrt2(x);
        int result = mySqrt3(x);
        System.out.println(result);
    }

    public static int mySqrt1(int x) {
        double r = Math.sqrt(x);
        return (int) r;
    }

    public static int mySqrt2(int x) {
        int i = 0;
        while(true) {
            long r = ((long)i) * i;
            if(r < x) {
                i++;
            }else if(r == x) {
                return i;
            }else {
                return i - 1;
            }
        }
    }

    public static int mySqrt3(int x) {
        long b = 1, e = x;
        while(b <= e) {
            long m = (b + e) >> 1;
            long r = m * m;
            if(r > x) {
                e = m - 1;
            }else if(r < x) {
                b = m + 1;
            }else {
                return (int)m;
            }
        }
        return (int)e;
    }
}
