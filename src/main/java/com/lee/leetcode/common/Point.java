package com.lee.leetcode.common;

public class Point {

    public int x;
    public int y;

    Point() {
        this(0, 0);
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point[] build(int[][] matrix) {
        Point[] points = new Point[matrix.length];
        int i = 0;
        for(int[] e : matrix) {
            points[i++] = new Point(e[0], e[1]);
        }
        return points;
    }
}
