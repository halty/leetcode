package com.lee.leetcode.pro0201_0225;

/**
 *
 Given an array of n positive integers and a positive integer s,
 find the minimal length of a contiguous subarray of which the sum ≥ s.
 If there isn't one, return 0 instead.

 Example:
 Input: s = 7, nums = [2,3,1,2,4,3]
 Output: 2
 Explanation: the subarray [4,3] has the minimal length under the problem constraint.

 Follow up:
 If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 *
 */
public class Pro_0209_MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int s= 7;
        int[] nums = {2,3,1,2,4,3};
//        int result = minSubArrayLen(s, nums);
        int result = minSubArrayLen1(s, nums);
        System.out.println(result);
    }

    public static int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) { return 0; }
        int length = nums.length;
        int minLen = Integer.MAX_VALUE;
        int b = 0, e = 0;
        int sum = nums[0];
        while(true) {
            if(sum >= s) {
                int len = e - b + 1;
                if(len < minLen) { minLen = len; }
                if(b == e) {
                    e = b = b + 1;
                    if(e == length) { break; }
                    sum = nums[b];
                }else {
                    sum -= nums[b];
                    b += 1;
                }
            }else {
                e += 1;
                if(e == length) { break; }
                sum += nums[e];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static int minSubArrayLen1(int s, int[] nums) {
        if(nums == null || nums.length == 0) { return 0; }
        int minLen = minSubArrayLen(s, nums, 0, nums.length-1);
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private static int minSubArrayLen(int s, int[] nums, int b, int e) {
        int len = e - b + 1;
        if(len == 1) {
             return nums[b] >= s ? 1 : Integer.MAX_VALUE;
        }else if(len == 2) {
            if(nums[b] >= s || nums[e] >= s) {
                return 1;
            }else if(nums[b]+nums[e] >= s) {
                return 2;
            }else {
                return Integer.MAX_VALUE;
            }
        }else {
            int mid = b + (len >> 1);
            int minLen = Math.min(minSubArrayLen(s, nums, b, mid-1), minSubArrayLen(s, nums, mid+1, e));
            return Math.min(minLen, midSubArrayLen(s, nums, b, e, mid));
        }
    }

    private static int midSubArrayLen(int s, int[] nums, int b, int e, int mid) {
        int sum = nums[mid];
        int minLen = 1;
        if(sum >= s) { return minLen; }
        int left = mid - 1, right = mid + 1;
        for(;left >= b && (sum += nums[left]) < s; left--);
        if(left < b) { left = b; }
        for(; sum < s && right <= e; sum += nums[right], right++);
        if(sum < s) { return Integer.MAX_VALUE; }
        for(minLen = right-left; left < mid && (sum -= nums[left]) >= s; left++, minLen--);
        left += 1;
        while(left <= mid && right <= e) {
            sum += nums[right];
            while(sum >= s) {
                sum -= nums[left];
                left++;
            }
            int len = right - left + 2;
            if(len < minLen) { minLen = len; }
            right++;
        }
        return minLen;
    }
}
