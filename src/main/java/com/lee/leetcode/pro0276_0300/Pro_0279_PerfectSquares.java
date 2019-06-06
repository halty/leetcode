package com.lee.leetcode.pro0276_0300;

/**
 *
 Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 Example 1:
 Input: n = 12
 Output: 3
 Explanation: 12 = 4 + 4 + 4.

 Example 2:
 Input: n = 13
 Output: 2
 Explanation: 13 = 4 + 9.
 *
 */
public class Pro_0279_PerfectSquares {

    public static void main(String[] args) {
        int n = 22;
//        int result = numSquares(n);
        int result = numSquares1(n);
        System.out.println(result);
    }

    public static int numSquares(int n) {
        int max = (int) Math.sqrt(n);
        int d = n - max * max;
        if(d == 0) { return 1; }
        int minCount = numSquares(d), c;
        for(int i=max-1; i>=1; i--) {
            d = n - i * i;
            c = numSquares(d);
            if(c < minCount) { minCount = c; }
        }
        return minCount + 1;
    }

    public static int numSquares1(int n) {
        return cachedNumSquares(n, new int[n+1]);
    }

    private static int cachedNumSquares(int n, int[] cache) {
        int num = cache[n];
        if(num != 0) { return num; }
        int max = (int) Math.sqrt(n);
        int d = n - max * max;
        if(d == 0) {
            cache[n] = num = 1;
            return num;
        }else {
            int minCount = cachedNumSquares(d, cache), c;
            if(minCount == 1) {
                cache[n] = num = 2;
                return num;
            }
            for(int i=max-1; i>=1; i--) {
                d = n - i * i;
                c = cachedNumSquares(d, cache);
                if(c == 1) {
                    cache[n] = num = 2;
                    return num;
                }
                if(c < minCount) { minCount = c; }
            }
            cache[n] = num = minCount+1;
            return num;
        }
    }

    public static int numSquares2(int n) {
        if(isSquare(n)) { return 1; }
        // Check if it can be expressed as 4^a(8b+7)[theorem on the sum of four squares]
        while(n % 4 == 0) { n /= 4; }
        if(n % 8 == 7) { return 4; }
        int p;
        for(int i=1; (p=i*i)<n; i++) {
            int d = n - p;
            if(isSquare(d)) {
                return 2;
            }
        }
        return 3;
    }

    private static boolean isSquare(int n) {
        int q = (int)Math.sqrt(n);
        return q*q == n;
    }
}