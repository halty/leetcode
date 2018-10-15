package com.lee.leetcode.pro0076_0100;

/**
 *
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Given a histogram where width of each bar is 1 and height = [2,1,5,6,2,3].
              6
          5 +++++
        +++++***+
        +***+***+     3
  2     +***+***+ 2 +++++
+++++ 1 +***+***+++++   +
+   +++++***+***+   +   +
+   +   +***+***+   +   +
+++++++++++++++++++++++++

The largest rectangle is shown in the shaded area ('*' filling), which has area = 10 unit.

Example:

Input: [2,1,5,6,2,3]
Output: 10
 *
 */
public class Pro_0084_LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] heights = {0,1,0,1};
//        int area = largestRectangleArea1(heights);
//        int area = largestRectangleArea2(heights);
        int area = largestRectangleArea3(heights);
        System.out.println(area);
    }

    public static int largestRectangleArea1(int[] heights) {
        int sMax = 0;
        for(int i=0; i<heights.length; i++) {
            int s = heights[i];
            int hMin = heights[i];
            for(int j=i+1; j<heights.length; j++) {
                if(hMin > heights[j]) {
                    hMin = heights[j];
                }
                int a = hMin * (j-i+1);
                if(s < a) {
                    s = a;
                }
            }
            if(sMax < s) {
                sMax = s;
            }
        }
        return sMax;
    }

    public static int largestRectangleArea2(int[] heights) {
        return largestRectangleArea2(heights, 0, heights.length-1);
    }

    public static int largestRectangleArea2(int[] heights, int left, int right) {
        if(left > right) {
            return 0;
        }else if(left == right) {
            return heights[left];
        }else {
            int hMin = heights[left];
            int k = left;
            for(int i=left+1; i<=right; i++) {
                if(hMin > heights[i]) {
                    hMin = heights[i];
                    k = i;
                }
            }
            int sMax = hMin * (right-left+1);
            int s;
            if(left < k) {
                s = largestRectangleArea2(heights, left, k-1);
                if(sMax < s) {
                    sMax = s;
                }
            }
            if(k < right) {
                s = largestRectangleArea2(heights, k+1, right);
                if(sMax < s) {
                    sMax = s;
                }
            }
            return sMax;
        }
    }

    /*
     * 利用数组维持最长递增子序列，然后利用以下性质计算最大面积：
     * (1) heights[i+1] >= heights[i]，则heights[i]不可能是最大面积矩形的右边界；反之，则heights[i]可能是最大面积矩形的右边界；
     * (2) 以heights[i]为右边界，利用最长递增子序列，则可以遍历每1个可能的左边界，计算并比较得出最大面积
     * (3) 整体的均摊复杂度O(n)
     */
    public static int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        int sMax = 0;
        if(len == 0) { return sMax; }
        int[] index = new int[len];
        int top = 0;
        index[top] = 0;
        for(int i=1; i<len; i++) {
            int h = heights[index[top]];
            int hi = heights[i];
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
                }while(top >= 0 && (h = heights[index[top]]) > hi);
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
            int h = heights[index[top]];
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
}
