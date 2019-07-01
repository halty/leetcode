package com.lee.leetcode.pro0301_0325;

/**
 *
 Given a 2D matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 <a href="https://leetcode.com/static/images/courses/range_sum_query_2d.png">Range Sum Query 2D</a>
 The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

 Example:
 Given matrix = [
     [3, 0, 1, 4, 2],
     [5, 6, 3, 2, 1],
     [1, 2, 0, 1, 5],
     [4, 1, 0, 1, 7],
     [1, 0, 3, 0, 5]
 ]
 sumRegion(2, 1, 4, 3) -> 8
 sumRegion(1, 1, 2, 2) -> 11
 sumRegion(1, 2, 2, 4) -> 12

 Note:
    You may assume that the matrix does not change.
    There are many calls to sumRegion function.
    You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 */
public class Pro_0304_RangeSumQuery2DImmutable {

    public static void main(String[] args) {
        /*int[][] matrix = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };*/
        int[][] matrix = new int[1][0];
        Pro_0304_RangeSumQuery2DImmutable query = new Pro_0304_RangeSumQuery2DImmutable(matrix);
        System.out.println(query.sumRegion(2,1,4,3));
        System.out.println(query.sumRegion(1,1,2,2));
        System.out.println(query.sumRegion(1,2,2,4));
    }

    /*private int[][] matrix;

    public Pro_0304_RangeSumQuery2DImmutable(int[][] matrix) {
        this.matrix = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for(int i=row1; i<=row2; i++) {
            for(int j=col1; j<=col2; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }*/

    private long[][] rangeSum;

    public Pro_0304_RangeSumQuery2DImmutable(int[][] matrix) {
        int rowLen = matrix.length;
        if(rowLen == 0) {
            this.rangeSum = new long[0][];
            return;
        }
        int colLen = matrix[0].length;
        this.rangeSum = new long[rowLen][colLen];
        long sum = 0;
        for(int j=0; j<colLen; j++) {
            rangeSum[0][j] = sum += matrix[0][j];
        }
        for(int i=1, p=0; i<rowLen; i++, p++) {
            sum = 0;
            for(int j=0; j<colLen; j++) {
                sum += matrix[i][j];
                rangeSum[i][j] = sum + rangeSum[p][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(rangeSum.length == 0 || rangeSum[0].length == 0) {
            return 0;
        }
        if(row1 == 0) {
            if(col1 == 0) {
                return (int)rangeSum[row2][col2];
            }else {
                return (int)(rangeSum[row2][col2] - rangeSum[row2][col1-1]);
            }
        }else {
            if(col1 == 0) {
                return (int)(rangeSum[row2][col2] - rangeSum[row1-1][col2]);
            }else {
                return (int)(rangeSum[row2][col2] + rangeSum[row1-1][col1-1] - rangeSum[row1-1][col2] - rangeSum[row2][col1-1]);
            }
        }
    }
}
