package com.lee.leetcode.pro0201_0225;

import java.util.HashSet;
import java.util.Set;

/**
 *
 Write an algorithm to determine if a number is "happy".
 A happy number is a number defined by the following process:
   Starting with any positive integer,
   replace the number by the sum of the squares of its digits,
   and repeat the process until the number equals 1 (where it will stay),
   or it loops endlessly in a cycle which does not include 1.
   Those numbers for which this process ends in 1 are happy numbers.

 Example:
 Input: 19
 Output: true
 Explanation: (x^2 = x * x)
 1^2 + 9^2 = 82
 8^2 + 2^2 = 68
 6^2 + 8^2 = 100
 1^2 + 0^2 + 0^2 = 1
 *
 */
public class Pro_0202_HappyNumber {

    public static void main(String[] args) {
        int n = 2;
        boolean result = isHappy(n);
        System.out.println(result);
    }

    public static boolean isHappy(int n) {
        if(n == 1 || isHappyEnding(n)) { return true; }
        Set<Integer> set = new HashSet<>();
        while(true) {
            n = digitSquareSum(n);
            if(isHappyEnding(n)) { return true; }
            if(!set.add(n)) { return false; }
        }
    }

    private static int digitSquareSum(int n) {
        int sum = 0;
        do {
            int m = n % 10;
            sum += m * m;
        }while((n/=10) != 0);
        return sum;
    }

    private static boolean isHappyEnding(int n) {
        return n == 10 || n == 100;
    }
}
