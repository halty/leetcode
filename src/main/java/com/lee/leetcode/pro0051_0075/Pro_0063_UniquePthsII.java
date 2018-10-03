package com.lee.leetcode.pro0051_0075;

import java.util.Arrays;

/*
 *
 A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 Now consider if some obstacles are added to the grids. How many unique paths would there be?

 An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 Note: m and n will be at most 100.

 Example 1:

 Input:
 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 Output: 2
 Explanation:
 There is one obstacle in the middle of the 3x3 grid above.
 There are two ways to reach the bottom-right corner:
 1. Right -> Right -> Down -> Down
 2. Down -> Down -> Right -> Right
 *
 */
public class Pro_0063_UniquePthsII {

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        int result = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(result);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] array = new int[n];
        for(int i=0; i<n; i++) {
            if(obstacleGrid[0][i] == 0) {
                array[i] = 1;
            }else {
                Arrays.fill(array, i, n, 0);
                break;
            }
        }
        for(int j=1; j<m; j++) {
            if(array[0] == 1 && obstacleGrid[j][0] == 1) {
                array[0] = 0;
            }
            for(int i=1; i<n; i++) {
                if(obstacleGrid[j][i] == 0) {
                    array[i] += array[i - 1];
                }else {
                    array[i] = 0;
                }
            }
        }
        return array[n-1];
    }
}
