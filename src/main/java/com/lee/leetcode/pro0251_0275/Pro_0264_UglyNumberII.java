package com.lee.leetcode.pro0251_0275;

/**
 *
 Write a program to find the n-th ugly number.
 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

 Example:
 Input: n = 10
 Output: 12
 Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

 Note:
    1 is typically treated as an ugly number.
    n does not exceed 1690.
 *
 */
public class Pro_0264_UglyNumberII {

    public static void main(String[] args) {
        int n = 1600;
//        int result = nthUglyNumber(n);
//        int result = nthUglyNumber1(n);
        int result = nthUglyNumber2(n);
        System.out.println(result);
    }

    public static int nthUglyNumber(int n) {
        int count = 1, num = 1;
        while(count < n) {
            if(isUgly(++num)) {
                count++;
            }
        }
        return num;
    }

    private static boolean isUgly(int num) {
        while(num != 1) {
            if(num%2 == 0) {
                num /= 2;
                continue;
            }
            if(num%3 == 0) {
                num /= 3;
                continue;
            }
            if(num%5 == 0) {
                num /= 5;
                continue;
            }
            return false;
        }
        return true;
    }

    public static int nthUglyNumber1(int n) {
        if(n < 7) { return n; }
        long[] nums = new long[n];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;
        nums[3] = 4;
        nums[4] = 5;
        nums[5] = 6;
        int i = 1, j = 6, end = n - 1;
        long v, t;
        while(i < n) {
            v = nums[i];
            t = v * 2;
            if(j == n && t >= nums[end]) { break; }
            j = insert(nums, t, j);
            t = v * 3;
            j = insert(nums, t, j);
            t = v * 5;
            j = insert(nums, t, j);
            i++;
        }
        return (int)nums[n-1];
    }

    private static int insert(long[] nums, long t, int next) {
        int prev = next - 1;
        while(true) {
            long v = nums[prev];
            if(t > v) {
                break;
            }else if(t == v) {
                return next;
            }
            prev--;
        }
        int cur = prev + 1;
        if(cur == nums.length) { return cur; }
        next = Math.min(next, nums.length-1);
        if(cur < next) {
            System.arraycopy(nums, cur, nums, cur+1, next-cur);
        }
        nums[cur] = t;
        return next + 1;
    }

    public static int nthUglyNumber2(int n) {
        if(n < 7) { return n; }
        int[] nums = new int[n];
        // 1, 2, 3, 4, 5, 6, 8, 9, 10, 12
        int next2 = 2, next3 = 3, next5 = 5;
        int nextIndex2 = 0, nextIndex3 = 0, nextIndex5 = 0;
        nums[0] = 1;
        for(int i=1; i<n; i++) {
            int num = min(next2, next3, next5);
            nums[i] = num;
            if(num == next2) { next2 = 2 * nums[++nextIndex2]; }
            if(num == next3) { next3 = 3 * nums[++nextIndex3]; }
            if(num == next5) { next5 = 5 * nums[++nextIndex5]; }
        }
        return nums[n-1];
    }

    private static int min(int a, int b, int c) {
        return a <= b ? (a <= c ? a : c) : (b <= c ? b : c);
    }
}
