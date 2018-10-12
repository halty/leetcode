package com.lee.leetcode.pro0076_0100;

import java.util.ArrayList;
import java.util.List;

/*
 *
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
 *
 */
public class Pro_0085_MaximalRectangle {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
//        int result = maximalRectangle1(matrix);
        int result = maximalRectangle2(matrix);
        System.out.println(result);
    }

    public static int maximalRectangle1(char[][] matrix) {
        int m = matrix.length;
        if(m == 0) { return 0; }
        int n = matrix[0].length;
        if(n == 0) { return 0; }
        int totalSMax = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j] == '0') { continue; }
                int down = i;
                int rMax = n;
                int sMax = 0;
                while(down < m) {
                    int r = j;
                    while(r < rMax) {
                        if(matrix[down][r] == '0') {
                            break;
                        }
                        r++;
                    }
                    if(r == j) {
                        break;
                    }
                    int s = (down-i+1)*(r-j);
                    if(sMax < s) {
                        sMax = s;
                    }
                    rMax = r;
                    down++;
                }
                if(totalSMax < sMax) {
                    totalSMax = sMax;
                }
            }
        }
        return totalSMax;
    }

    public static int maximalRectangle2(char[][] matrix) {

    }

    // 获取每行里面连续的'1'片段
    private static List<Long>[] findSequentialOnePerRow(char[][] matrix, int rowCnt, int colCnt) {
        List<Long>[] sequentialOnes = new List[rowCnt];
        for(int i=0; i<rowCnt; i++) {
            List<Long> list = new ArrayList<>();
            int begin = -1;
            for(int j=0; j<colCnt; j++) {
                if(matrix[i][j] == '0') {
                    if(begin != -1) {
                        long s = composeOf(begin, j-begin);
                        list.add(s);
                        begin = -1;
                    }
                }else { // == '1'
                    if(begin == -1) {
                        begin = j;
                    }
                }
            }
            if(begin != -1) {
                long s = composeOf(begin, colCnt-begin);
                list.add(s);
            }
            sequentialOnes[i] = list;
        }
        return sequentialOnes;
    }

    // 以row增长方向为x轴，以column增长方向为Y轴，生成多个直方图(直方图的矩形个数等于矩阵的行数)
    // 所有直方图中最大面积的矩形即为矩阵中全'1'构成的最大矩形
    private static int[][] generateHistogramByColumn(List<Long> sequentialOnes) {

    }

    private static int largestRectangleAreaInHistogram(int[] rows) {

    }

    private static long composeOf(int begin, int length) {
        long v = length;
        v = (v << 32) + begin;
        return v;
    }

    private static int decomposeBegin(long v) {
        return (int) v;
    }

    private static int decomposeLength(long v) {
        return (int) (v >>> 32);
    }





























}
