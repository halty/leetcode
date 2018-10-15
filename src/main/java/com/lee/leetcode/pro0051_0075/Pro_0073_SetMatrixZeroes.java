package com.lee.leetcode.pro0051_0075;

import java.util.Arrays;

/**
 *
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input:
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output:
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input:
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output:
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
 *
 */
public class Pro_0073_SetMatrixZeroes {

    public static void main(String[] args) {
        int[][] matrix = {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };
//        setZeroes1(matrix);
//        setZeroes2(matrix);
        setZeroes3(matrix);
        print(matrix);
    }

    public static void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] flags = new boolean[m][];
        for(int i=0; i<m; i++) {
            flags[i] = new boolean[n];
        }
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j] == 0) {
                    for(int k=0; k<n; k++) {
                        flags[i][k] = true;
                    }
                    for(int k=0; k<m; k++) {
                        flags[k][j] = true;
                    }
                }
            }
        }
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
               if(flags[i][j]) {
                   matrix[i][j] = 0;
               }
            }
        }
    }

    public static void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] column = new boolean[n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void setZeroes3(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean row1 = false, column1 = false;
        if(matrix[0][0] == 0) {
            row1 = column1 = true;
        }else {
            for(int j=1; j<n; j++) {
                if(matrix[0][j] == 0) {
                    row1 = true;
                    break;
                }
            }
            for(int i=1; i<m; i++) {
                if(matrix[i][0] == 0) {
                    column1 = true;
                    break;
                }
            }
        }
        // row 1 and column 1 save the zero flags
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(row1) {
            for(int j=0; j<n; j++) {
                matrix[0][j] = 0;
            }
        }
        if(column1) {
            for(int i=0; i<m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    private static void print(int[][] matrix) {
        for(int[] array : matrix) {
            System.out.println(Arrays.toString(array));
        }
    }
}
