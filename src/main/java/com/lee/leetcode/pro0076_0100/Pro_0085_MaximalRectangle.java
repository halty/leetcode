package com.lee.leetcode.pro0076_0100;

import java.util.ArrayList;
import java.util.Arrays;
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
                {'0','1'},
                {'1','0'}
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

    // 时间复杂度：O(m*n)，空间复杂度：最大O(m*n)，最小O(m)
    public static int maximalRectangle2(char[][] matrix) {
        int rowCnt = matrix.length;
        if(rowCnt == 0) { return 0; }
        int colCnt = matrix[0].length;
        if(colCnt == 0) { return 0; }
        SeqBuffer[] sequentialOnes = findSequentialOnePerRow(matrix, rowCnt, colCnt);
        List<int[]> histogramsByColumn = generateHistogramsByColumn(sequentialOnes, colCnt);
        int[] index = new int[rowCnt];
        int sMax = 0;
        for(int[] histogram : histogramsByColumn) {
            int s = largestRectangleAreaInHistogram(histogram, index);
            if(sMax < s) {
                sMax = s;
            }
        }
        return sMax;
    }

    // 获取每行里面连续的'1'片段
    private static SeqBuffer[] findSequentialOnePerRow(char[][] matrix, int rowCnt, int colCnt) {
        SeqBuffer[] sequentialOnes = new SeqBuffer[rowCnt];
        for(int i=0; i<rowCnt; i++) {
            SeqBuffer buffer = new SeqBuffer();
            int begin = -1;
            for(int j=0; j<colCnt; j++) {
                if(matrix[i][j] == '0') {
                    if(begin != -1) {
                        buffer.add(new Seq(begin, j-begin));
                        begin = -1;
                    }
                }else { // == '1'
                    if(begin == -1) {
                        begin = j;
                    }
                }
            }
            if(begin != -1) {
                buffer.add(new Seq(begin, colCnt-begin));
            }
            sequentialOnes[i] = buffer;
        }
        return sequentialOnes;
    }

    // 以row增长方向为x轴，以column增长方向为Y轴，生成多个直方图(直方图的矩形个数等于矩阵的行数)
    // 所有直方图中最大面积的矩形即为矩阵中全'1'构成的最大矩形
    private static List<int[]> generateHistogramsByColumn(SeqBuffer[] sequentialOnes, int colCnt) {
        int rowCnt = sequentialOnes.length;
        int colMin = -1;
        List<int[]> histograms = new ArrayList<>(colCnt);
        while(true) {
            int j = Integer.MAX_VALUE;
            for(int i=0; i<rowCnt; i++) {
                int begin = greaterThanColMin(sequentialOnes[i], colMin);
                if(j > begin) {
                    j = begin;
                }
            }
            if(j == Integer.MAX_VALUE) {
                break;
            }
            int[] histogram = new int[rowCnt];
            for(int i=0; i<rowCnt; i++) {
                Seq seq = removeAndGetFirst(sequentialOnes[i], j);
                if(seq == null) {
                    histogram[i] = 0;
                }else {
                    if(seq.begin <= j) {
                        histogram[i] = seq.begin + seq.length - j;
                    }else {
                        histogram[i] = 0;
                    }
                }
            }
            histograms.add(histogram);
            colMin = j;
        }
        return histograms;
    }

    private static int greaterThanColMin(SeqBuffer seqBuffer, int colMin) {
        for(int i=0; i<seqBuffer.size; i++) {
            int begin = seqBuffer.buf[i].begin;
            if(begin > colMin) {
                return begin;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static Seq removeAndGetFirst(SeqBuffer seqBuffer, int j) {
        int i = 0;
        while(i < seqBuffer.size) {
            Seq seq = seqBuffer.buf[i];
            if(seq.begin+seq.length > j) {
                break;
            }
            i++;
        }
        seqBuffer.sub(i);
        return seqBuffer.size == 0 ? null : seqBuffer.buf[0];
    }

    private static int largestRectangleAreaInHistogram(int[] histogram, int[] index) {
        int len = histogram.length;
        int sMax = 0;
        int top = 0;
        index[top] = 0;
        for(int i=1; i<len; i++) {
            int h = histogram[index[top]];
            int hi = histogram[i];
            if(hi > h) {
                index[++top] = i;
            }else if(hi < h) {
                int right = i;
                int s;
                do {
                    if (top > 0) {
                        int left = index[top-1]+1;
                        s = h * (right - left);
                    } else {
                        s = h * right;
                    }
                    if(sMax < s) {
                        sMax = s;
                    }
                    --top;
                }while(top >= 0 && (h = histogram[index[top]]) > hi);
                if(top < 0) {
                    index[++top] = i;
                }else {
                    if(h < hi) {
                        index[++top] = i;
                    }else {
                        index[top] = i;
                    }
                }
            }else {
                index[top] = i;
            }
        }
        int right = len;
        int s;
        do {
            int h = histogram[index[top]];
            if (top > 0) {
                int left = index[top-1]+1;
                s = h * (right - left);
            } else {
                s = h * right;
            }
            if(sMax < s) {
                sMax = s;
            }
            --top;
        }while(top >= 0);
        return sMax;
    }

    private static class Seq {
        int begin;
        int length;
        Seq(int begin, int length) {
            this.begin = begin;
            this.length = length;
        }
    }

    private static class SeqBuffer {
        Seq[] buf;
        int size;
        SeqBuffer() {
            buf = new Seq[16];
            size = 0;
        }
        void add(Seq seq) {
            if(size == buf.length) {
                buf = Arrays.copyOf(buf, buf.length * 3 / 2);
            }
            buf[size++] = seq;
        }
        void sub(int beginIndex) {
            if(beginIndex >= size) {
                size = 0;
            }else if(beginIndex <= 0) {

            }else {
                size -= beginIndex;
                System.arraycopy(buf, beginIndex, buf, 0, size);
            }
        }
    }
}
