package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.Point;

import java.util.*;

/**
 *
 Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

 Example 1:
 Input: [[1,1],[2,2],[3,3]]
 Output: 3
 Explanation:
 ^
 |
 |        o
 |     o
 |  o
 +------------->
 0  1  2  3  4

 Example 2:
 Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 Output: 4
 Explanation:
 ^
 |
 |  o
 |     o        o
 |        o
 |  o        o
 +------------------->
 0  1  2  3  4  5  6
 *
 */
public class Pro_0149_MaxPointsOnALine {

    public static void main(String[] args) {
        int[][] matrix = {{40,-23},{9,138},{429,115},{50,-17},{-3,80},{-10,33},{5,-21},{-3,80},{-6,-65},{-18,26},{-6,-65},{5,72},{0,77},{-9,86},{10,-2},{-8,85},{21,130},{18,-6},{-18,26},{-1,-15},{10,-2},{8,69},{-4,63},{0,3},{-4,40},{-7,84},{-8,7},{30,154},{16,-5},{6,90},{18,-6},{5,77},{-4,77},{7,-13},{-1,-45},{16,-5},{-9,86},{-16,11},{-7,84},{1,76},{3,77},{10,67},{1,-37},{-10,-81},{4,-11},{-20,13},{-10,77},{6,-17},{-27,2},{-10,-81},{10,-1},{-9,1},{-8,43},{2,2},{2,-21},{3,82},{8,-1},{10,-1},{-9,1},{-12,42},{16,-5},{-5,-61},{20,-7},{9,-35},{10,6},{12,106},{5,-21},{-5,82},{6,71},{-15,34},{-10,87},{-14,-12},{12,106},{-5,82},{-46,-45},{-4,63},{16,-5},{4,1},{-3,-53},{0,-17},{9,98},{-18,26},{-9,86},{2,77},{-2,-49},{1,76},{-3,-38},{-8,7},{-17,-37},{5,72},{10,-37},{-4,-57},{-3,-53},{3,74},{-3,-11},{-8,7},{1,88},{-12,42},{1,-37},{2,77},{-6,77},{5,72},{-4,-57},{-18,-33},{-12,42},{-9,86},{2,77},{-8,77},{-3,77},{9,-42},{16,41},{-29,-37},{0,-41},{-21,18},{-27,-34},{0,77},{3,74},{-7,-69},{-21,18},{27,146},{-20,13},{21,130},{-6,-65},{14,-4},{0,3},{9,-5},{6,-29},{-2,73},{-1,-15},{1,76},{-4,77},{6,-29}};
        Point[] points = Point.build(matrix);
//        int result = maxPoints(points);
        int result = maxPoints1(points);
        System.out.println(result);
    }

    public static int maxPoints(Point[] points) {
        Context ctx = prepare(points);
        switch(ctx.count) {
            case 0: return 0;
            case 1: return ctx.nums[0];
            case 2: return ctx.nums[0]+ctx.nums[1];
        }
        int max = 0;
        Map<Line, Integer> map = new HashMap<>(ctx.count*(ctx.count-1)/2);
        for(int i=1; i<ctx.count; i++) {
            Point p1 = ctx.points[i];
            int cnt = ctx.nums[i];
            for(int j=i-1; j>=0; j--) {
                Point p0 = ctx.points[j];
                Line line = calcLinear(p0, p1);
                if(map.containsKey(line)) { continue; }
                int count = cnt + ctx.nums[j];
                for(int k=0; k<ctx.count; k++) {
                    if(k != i && k != j && line.exist(ctx.points[k])) {
                        count += ctx.nums[k];
                    }
                }
                map.put(line, count);
                if(count > max) { max = count; }
            }
        }
        return max;
    }

    private static Context prepare(Point[] points) {
        int n = points.length;
        Context ctx = new Context(n);
        for(int i=0; i<n; i++) {
            ctx.addPoint(points[i]);
        }
        return ctx;
    }

    public static int maxPoints1(Point[] points) {
        Context ctx = prepare(points);
        switch(ctx.count) {
            case 0: return 0;
            case 1: return ctx.nums[0];
            case 2: return ctx.nums[0]+ctx.nums[1];
        }
        int max = 0;
        Map<Line, Statistic> map = new HashMap<>(ctx.count*(ctx.count-1)/2);
        for(int i=1; i<ctx.count; i++) {
            Point p1 = ctx.points[i];
            int cnt = ctx.nums[i];
            for(int j=i-1; j>=0; j--) {
                Point p0 = ctx.points[j];
                Line line = calcLinear(p0, p1);
                Statistic s = map.get(line);
                if(s == null) {
                    s = new Statistic(j, i, cnt+ctx.nums[j]);
                    map.put(line, s);
                }else {
                    s.addPoint(i, cnt);
                }
                if(s.num > max) { max = s.num; }
            }
        }
        return max;
    }

    private static Line calcLinear(Point p0, Point p1) {
        if(p0.x == p1.x) { return new Line(1, -p0.x, 0); }
        if(p0.y == p1.y) { return new Line(0, p0.y, 1); }
        long a = ((long)p0.y) - p1.y;
        long m = ((long)p0.x) - p1.x;
        if(m < 0) {
            a = -a;
            m = -m;
        }
        long gcd = gcd(a, m);
        a /= gcd;
        m /= gcd;
        long b = m * p0.y - a * p0.x;
        return new Line(a, b, m);
    }

    private static long gcd(long m, long n) {
        m = Math.abs(m);
        n = Math.abs(n);
        if(m < n) {
            long tmp = m;
            m = n;
            n = tmp;
        }
        long r;
        do {
            r = m % n;
            m = n;
            n = r;
        }while(n != 0);
        return m;
    }

    private static class Statistic {
        int[] pointIndexes;
        int count;
        int num;

        Statistic(int index0, int index1, int pointNums) {
            pointIndexes = new int[4];
            pointIndexes[0] = index0;
            pointIndexes[1] = index1;
            count = 2;
            num = pointNums;
        }

        public void addPoint(int pointIndex, int pointNums) {
            if(pointIndexes[count-1] != pointIndex) {
                if(count == pointIndexes.length) {
                    pointIndexes = Arrays.copyOf(pointIndexes, count+3);
                }
                pointIndexes[count++] = pointIndex;
                num += pointNums;
            }
        }
    }

    private static class Context {
        int count;
        Point[] points;
        int[] nums;

        Context(int capacity) {
            count = 0;
            points = new Point[capacity];
            nums = new int[capacity];
        }

        public void addPoint(Point p) {
            int index = find(p);
            if(index >= 0) {
                nums[index] += 1;
            }else {
                index = -index - 1;
                if(index < count) {
                    int len = count - index;
                    int next = index + 1;
                    System.arraycopy(points, index, points, next, len);
                    System.arraycopy(nums, index, nums, next, len);
                }
                points[index] = p;
                nums[index] = 1;
                count += 1;
            }
        }

        private int find(Point p) {
            int b = 0, e = count-1;
            while(b <= e) {
                int mid = (int)(((long)b) + e) / 2;
                long c = compare(p, points[mid]);
                if(c < 0) {
                    e = mid - 1;
                }else if(c > 0) {
                    b = mid + 1;
                }else {
                    return mid;
                }
            }
            return -b - 1;
        }

        private static long compare(Point p0, Point p1) {
            long d = ((long)p0.x) - p1.x;
            if(d == 0) {
                d = ((long)p0.y) - p1.y;
            }
            return d;
        }
    }

    private static class Line {   // ax+b=my
        long a;
        long b;
        long m; // x = -b, if m = 0, a = 1

        Line(long a, long b, long m) {
            this.a = a;
            this.b = b;
            this.m = m;
        }

        public boolean exist(Point p) {
            return a*p.x + b == m*p.y;
        }

        @Override
        public boolean equals(Object other) {
            Line line = (Line) other;
            return a == line.a && b == line.b && m == line.m;
        }

        @Override
        public int hashCode() {
            int h = Long.hashCode(a);
            h = h*31 + Long.hashCode(b);
            h = h*31 + Long.hashCode(m);
            return h;
        }
    }
}
