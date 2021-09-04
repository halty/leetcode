package com.lee.leetcode.pro0301_0325;

/**
 *
 Given an integer, write a function to determine if it is a power of three.

 Example 1:
 Input: 27
 Output: true

 Example 2:
 Input: 0
 Output: false

 Example 3:
 Input: 9
 Output: true

 Example 4:
 Input: 45
 Output: false

 Follow up: Could you do it without using any loop / recursion?
 *
 */
public class Pro_0326_PowerOfThree {

    public static void main(String[] args) {
        int n = 1;
        // n = 0;
        // n = 3;
        // n = 9;
        // n = 45;
        // n = -10;
        // n = 2147483647;
        System.out.println(isPowerOfThree2(n));
        /*long s = 3;
        while(s < Integer.MAX_VALUE) {
            System.out.println(s);
            s *= 3;
        }*/
    }

    public static boolean isPowerOfThree(int n) {
        long s = 1;
        while (s < n) {
            s *= 3;
        }
        return s == n;
    }

    public static boolean isPowerOfThree1(int n) {
        if(n > 1) {
            if(n % 3 == 0) {
                return isPowerOfThree1(n /= 3);
            }else {
                return false;
            }
        }else if(n == 1) {
            return true;
        }else {
            return false;
        }
    }

    public static boolean isPowerOfThree2(int n) {
        // [3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969, 14348907, 43046721, 129140163, 387420489, 1162261467]
        return (n > 1 && 1162261467 % n == 0) || n == 1;
    }
}
