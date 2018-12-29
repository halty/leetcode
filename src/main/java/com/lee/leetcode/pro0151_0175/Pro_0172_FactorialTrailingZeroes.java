package com.lee.leetcode.pro0151_0175;

/**
 *
 Given an integer n, return the number of trailing zeroes in n!.

 Example 1:
 Input: 3
 Output: 0
 Explanation: 3! = 6, no trailing zero.

 Example 2:
 Input: 5
 Output: 1
 Explanation: 5! = 120, one trailing zero.

 Note: Your solution should be in logarithmic time complexity.
 *
 */
public class Pro_0172_FactorialTrailingZeroes {

    public static void main(String[] args) {
        int n = 130;
        int count = trailingZeroes(n);
//        int count = trailingZeroes1(n);
        System.out.println(count);
    }

    public static int trailingZeroes(int n) {
        // n/5 + n/25 + n/125 + ... +n/(5^k)
        int count = 0;
        for(long base=5; n>=base; base*=5) {
            count += n/base;
        }
        return count;
    }

    public static int trailingZeroes1(int n) {
        int count = 0;
        while(n > 4) {
            n /= 5;
            count += n;
        }
        return count;
    }
}
