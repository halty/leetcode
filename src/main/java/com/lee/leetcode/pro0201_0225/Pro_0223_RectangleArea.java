package com.lee.leetcode.pro0201_0225;

/**
 *
 Find the total area covered by two rectilinear rectangles in a 2D plane.
 Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 <img src="https://assets.leetcode.com/uploads/2018/10/22/rectangle_area.png" />

 Example:
 Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 Output: 45

 Note:
    Assume that the total area is never beyond the maximum possible value of int.
 *
 */
public class Pro_0223_RectangleArea {

    public static void main(String[] args) {
        int A=-3, B=0, C=3, D=4, E=0, F=-1, G=9, H=2;
        int result = computeArea(A, B, C, D, E, F, G, H);
        System.out.println(result);
    }

    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int s1 = (C-A) * (D-B);
        int s2 = (G-E) * (H-F);
        int sOverlap = 0;
        if(C>E && A<G && D>F && B<H) {
           sOverlap = (Math.min(C, G)-Math.max(A, E))*(Math.min(D, H)-Math.max(B, F));
        }
        return s1 + s2 - sOverlap;
    }
}
