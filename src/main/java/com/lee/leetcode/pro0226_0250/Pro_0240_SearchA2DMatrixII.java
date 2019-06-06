package com.lee.leetcode.pro0226_0250;

/**
 *
 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

 Example:
 Consider the following matrix:
 [
     [ 1,  4,  7, 11, 15],
     [ 2,  5,  8, 12, 19],
     [ 3,  6,  9, 16, 22],
     [10, 13, 14, 17, 24],
     [18, 21, 23, 26, 30]
 ]
 
 Given target = 5, return true.
 Given target = 20, return false.
 *
 */
public class Pro_0240_SearchA2DMatrixII {

    public static void main(String[] args) {
        /*int[][] matrix = {
            { 1,  4,  7, 11, 15},
            { 2,  5,  8, 12, 19},
            { 3,  6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };*/
        int[][] matrix = {{-1}, {-1}};
        int target = 0;
//        boolean existed = searchMatrix(matrix, target);
        boolean existed = searchMatrix1(matrix, target);
        System.out.println(existed);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) { return false; }
        int n = matrix[0].length;
        if(n == 0) { return false; }
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) { return false; }
        int n = matrix[0].length;
        if(n == 0) { return false; }
        int rb = 0, re = m-1, cb = 0, ce = n-1;
        int c = 0, r;
        while(true) {
            r = findInColumn(matrix, c, rb, re, target);
            if(r >= 0) { return true; }
            r = (-r-1) - 1;
            if(r < rb) { return false; }
            c = findInRow(matrix, r, cb, ce, target);
            if(c >= 0) { return true; }
            c = -c - 1;
            if(c > ce) { return false; }
            cb = c;
            re = r - 1;
            if(re < 0) { return false; }
        }
    }

    private static int findInRow(int[][] matrix, int row, int cb, int ce, int target) {
        while(cb <= ce) {
            int cm = cb + (ce-cb)/2;
            int v = matrix[row][cm];
            if(v == target) {
                return cm;
            }else if(v > target) {
                ce = cm - 1;
            }else {
                cb = cm + 1;
            }
        }
        return -cb - 1;
    }

    private static int findInColumn(int[][] matrix, int column, int rb, int re, int target) {
        while(rb <= re) {
            int rm = rb + (re-rb)/2;
            int v = matrix[rm][column];
            if(v == target) {
                return rm;
            }else if(v > target) {
                re = rm - 1;
            }else {
                rb = rm + 1;
            }
        }
        return -rb - 1;
    }
}
