package com.lee.leetcode.pro0201_0225;

/**
 *
 Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

 Example 1:
 Input: [5,7]
 Output: 4

 Example 2:
 Input: [0,1]
 Output: 0
 *
 */
public class Pro_0201_BitwiseAndOfNumbersRange {

    public static void main(String[] args) {
        int m = 2147483646, n = 2147483647;
//        int r = rangeBitwiseAnd(m, n);
        int r = rangeBitwiseAnd1(m, n);
        System.out.println(r);
    }

    public static int rangeBitwiseAnd(int m, int n) {
        int r = m;
        for(int i=m+1; i<n; i++) {
            r &= i;
        }
        return r&n;
    }

    public static int rangeBitwiseAnd1(int m, int n) {
        if(m == 0) { return 0; }
        long limit = n;
        int cur = m;
        long next = ((long)cur) + Integer.lowestOneBit(cur);
        while(next <= limit) {
            cur &= next;
            if(cur == 0) { break; }
            next = ((long)cur) + Integer.lowestOneBit(cur);
        }
        return cur;
    }
}
