package com.lee.leetcode.pro0051_0075;

import java.util.Arrays;

/*
 *
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 *
 */
public class Pro_0059_SpiralMatrixII {

    public static void main(String[] args) {
        int n = 3;
        int[][] matrix = generateMatrix(n);
        print(matrix);
    }

    public static int[][] generateMatrix(int n) {
        int size = n * n;
        int[][] matrix = new int[n][];
        for(int i=0; i<n; i++) {
            matrix[i] = new int[n];
        }
        int rb = 0, re = n-1, cb = 0, ce = n-1;
        int count = 1;
        while(count <= size) {
            // left to right
            for(int i=cb; i<=ce; i++) {
                matrix[rb][i] = count++;
            }
            rb += 1;
            if(count > size) { break; }
            // up to down
            for(int i=rb; i<=re; i++) {
                matrix[i][ce] = count++;
            }
            ce -= 1;
            if(count > size) { break; }
            // right to left
            for(int i=ce; i>=cb; i--) {
                matrix[re][i] = count++;
            }
            re -= 1;
            if(count > size) { break; }
            // down to up
            for(int i=re; i>=rb; i--) {
                matrix[i][cb] = count++;
            }
            cb += 1;
        }
        return matrix;
    }

    private static void print(int[][] matrix) {
        int length = matrix.length;
        for(int i=0; i<length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
