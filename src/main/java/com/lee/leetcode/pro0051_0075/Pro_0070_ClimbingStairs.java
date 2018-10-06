package com.lee.leetcode.pro0051_0075;

/*
 *
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 *
 */
public class Pro_0070_ClimbingStairs {

    public static void main(String[] args) {
        int n = 3;
//        int result = climbStairs1(n);
        int result = climbStairs2(n);
        System.out.println(result);
    }

    public static int climbStairs1(int n) {
        if(n == 0 || n == 1) {
            return 1;
        }else {
            return climbStairs1(n-1) + climbStairs1(n-2);
        }
    }

    public static int climbStairs2(int n) {
        if(n == 1) {
            return 1;
        }
        int n1 = 1, n2 = 1;
        for(int i=2; i<=n; i++) {
            int w = n1 + n2;
            n2 = n1;
            n1 = w;
        }
        return n1;
    }

    public static int climbStairs3(int n) {
        if(n == 0 || n == 1) {
            return 1;
        }
        int n1 = 1, n2 = 1;
        for(int i=2; i<=n; i++) {
            int w = n1 + n2;
            n2 = n1;
            n1 = w;
        }
        return n1;
    }
}
