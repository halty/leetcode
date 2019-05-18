package com.lee.leetcode.pro0201_0225;

/**
 *
 Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 Example:
 Input:
     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
 Output: 4
 *
 */
public class Pro_0221_MaximalSquare {

    public static void main(String[] args) {
//        char[][] matrix = {{'1'}};
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
//        int result = maximalSquare(matrix);
        int result = maximalSquare1(matrix);
        System.out.println(result);
    }

    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if(m == 0) { return 0; }
        int n = matrix[0].length;
        if(n == 0) { return 0; }
        int maxS = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j] == '1') {
                    int s = Math.min(m - i, n - j);
                    int k = 0, r;
                    while (k < s) {
                        r = i + k;
                        int d = 0, c;
                        while(d < s) {
                            c = j + d;
                            if(matrix[r][c] == '0') {
                                break;
                            }
                            d++;
                        }
                        s = d;
                        k++;
                    }
                    if(maxS < s) { maxS = s; }
                }
            }
        }
        return maxS * maxS;
    }

    public static int maximalSquare1(char[][] matrix) {
        int m = matrix.length;
        if(m == 0) { return 0; }
        int n = matrix[0].length;
        if(n == 0) { return 0; }
        int[] sLine = new int[n];
        int sMax = 0;
        for(int j=0; j<n; j++) {
            if(matrix[0][j] == '1') {
                sLine[j] = 1;
                sMax = 1;
            }
        }
        for(int i=1; i<m; i++) {
            if(matrix[i][0] == '0') {
                sLine[0] = 0;
            }else {
                sLine[0] = 1;
                if(sMax == 0) { sMax = 1; }
            }
            for(int j=1; j<n; j++) {
                if(matrix[i][j] == '0') {
                    sLine[j] = 0;
                }else {
                    int a = sLine[j-1], b = sLine[j], s;
                    if(a < b) {
                        s = a + 1;
                    }else if(a > b) {
                        s = b + 1;
                    }else {
                        s = matrix[i-b][j-a] == '0' ? a : a + 1;
                    }
                    sLine[j] = s;
                    if(sMax < s) { sMax = s; }
                }
            }
        }
        return sMax * sMax;
    }
}
