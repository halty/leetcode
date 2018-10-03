package com.lee.leetcode.pro0051_0075;

import java.util.Arrays;

/*
 *
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 *
 */
public class Pro_0064_MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        int result = minPathSum(grid);
        System.out.println(result);
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] array = new int[n];
        array[0] = grid[0][0];
        for(int j=1; j<n; j++) {
            array[j] = array[j-1] + grid[0][j];
        }
        for(int i=1; i<m; i++) {
            array[0] += grid[i][0];
            for(int j=1; j<n; j++) {
                array[j] = grid[i][j] + Math.min(array[j-1], array[j]);
            }
        }
        return array[n-1];
    }
}
