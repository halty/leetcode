package com.lee.leetcode.pro0051_0075;

/**
 *
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
 *
 */
public class Pro_0062_UniquePaths {

    public static void main(String[] args) {
        int m = 10, n = 10; // 48620
//        int result = uniquePaths1(m, n);
//        int result = uniquePaths2(m, n);
        int result = uniquePaths3(m, n);
        System.out.println(result);
    }

    public static int uniquePaths1(int m, int n) {
        if(m == 1 || n == 1) {
            return 1;
        }
        return calcUniquePaths(m-1, n-1);
    }

    private static int calcUniquePaths(int x, int y) {
        if(x == 0) {
            if(y == 0) {
                return 1;
            }else {
                return calcUniquePaths(x, y-1);
            }
        }else if(y == 0) {
            return calcUniquePaths(x-1, y);
        }else {
            return calcUniquePaths(x - 1, y) + calcUniquePaths(x, y - 1);
        }
    }

    public static int uniquePaths2(int m, int n) {
        if(m == 1 || n == 1) {
            return 1;
        }

        if(m > n) {
            int tmp = m;
            m = n;
            n = tmp;
        }
        int[] array = new int[m];
        for(int i=0; i<m; i++) {
            array[i] = 1;
        }
        for(int j=1; j<n; j++) {
            for(int i=1; i<m; i++) {
                array[i] += array[i-1];
            }
        }
        return array[m-1];
    }

    public static int uniquePaths3(int m, int n) {
        if(m == 1 || n == 1) {
            return 1;
        }

        if(m > n) {
            int tmp = m;
            m = n;
            n = tmp;
        }

        // total (m-1)+(n-1) step, select (m-1) step move right
        long a=1, b=1;
        for(int i=1,j=n; i<=m-1; i++,j++) {
            a *= i;
            b *= j;
        }
        return (int)(b/a);
    }
}
