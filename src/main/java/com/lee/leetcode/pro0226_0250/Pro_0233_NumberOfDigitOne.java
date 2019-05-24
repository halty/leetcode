package com.lee.leetcode.pro0226_0250;

/**
 *
 Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

 Example:
 Input: 13
 Output: 6
 Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 *
 */
public class Pro_0233_NumberOfDigitOne {

    public static void main(String[] args) {
        int n = 1410065408;
//        int result = countDigitOne(n);
        int result = countDigitOne1(n);
        System.out.println(result);
    }

    public static int countDigitOne(int n) {
        int total = 0;
        for(int i=1; i<=n; i++) {
            total += numOfOne(i);
        }
        return total;
    }

    private static int numOfOne(int n) {
        int cnt = 0;
        while(n != 0) {
            if(n%10 == 1) {
                cnt++;
            }
            n /= 10;
        }
        return cnt;
    }

    public static int countDigitOne1(int n) {
        int total = 0;
        int base = 1;
        long d = 10;
        int q, r, k;
        while(true) {
            q = (int)(n / d);
            r = (int)(n % d);
            total += q * base;
            k = r / base;
            if(k > 1) {
                total += base;
            }else if(k == 1) {
                total += (r - base + 1);
            }
            if(q == 0) { break; }
            base *= 10;
            d *= 10;
        }
        return total;
    }
}
