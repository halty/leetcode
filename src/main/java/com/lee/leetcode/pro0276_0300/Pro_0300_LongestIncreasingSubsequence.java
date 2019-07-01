package com.lee.leetcode.pro0276_0300;

/**
 *
 Given an unsorted array of integers, find the length of longest increasing subsequence.

 Example:
 Input: [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

 Note:
    There may be more than one LIS combination, it is only necessary for you to return the length.
    Your algorithm should run in O(n^2) complexity.

 Follow up: Could you improve it to O(n log n) time complexity?
 *
 */
public class Pro_0300_LongestIncreasingSubsequence {

    public static void main(String[] args) {
//        int[] nums = {1,3,6,7,9,4,10,5,6};
        int[] nums = {10,9,2,5,3,7,101,18};
//        int len = lengthOfLIS(nums);
        int len = lengthOfLIS1(nums);
        System.out.println(len);
    }

    public static int lengthOfLIS(int[] nums) {
        if(nums.length <= 1) { return nums.length; }
        int[] lengths = new int[nums.length];
        int maxLen = 1;
        lengths[0] = 1;
        for(int i=1; i<nums.length; i++) {
            int len = 1, v = nums[i];
            for(int j=0; j<i; j++) {
                if(v > nums[j]) {
                    int newLen = lengths[j] + 1;
                    if(newLen > len) {
                        len = newLen;
                    }
                }
            }
            lengths[i] = len;
            if(len > maxLen) {
                maxLen = len;
            }
        }
        return maxLen;
    }

    public static int lengthOfLIS1(int[] nums) {
        if(nums.length <= 1) { return nums.length; }
        int maxLen = 1;
        // minNums[i] - 递增子序列长度为i时，最小末尾元素
        int[] minNums = new int[nums.length];
        minNums[0] = nums[0];
        for(int i=1; i<nums.length; i++) {
            int v = nums[i];
            int b = 0, e = maxLen - 1;
            while(b <= e) {
                int m = b + (e-b)/2;
                int mv = minNums[m];
                if(v < mv) {
                    e = m - 1;
                }else if(v > mv) {
                    b = m + 1;
                }else {
                    b = m;
                    break;
                }
            }
            minNums[b] = v;
            if(b == maxLen) { maxLen += 1; }
        }
        return maxLen;
    }
}
