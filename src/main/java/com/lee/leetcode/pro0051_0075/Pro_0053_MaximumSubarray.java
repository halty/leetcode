package com.lee.leetcode.pro0051_0075;

/**
 *
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 *
 */
public class Pro_0053_MaximumSubarray {

    public static void main(String[] args) {
        int result = 0;
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] nums = {-2,1};
//        result = maxSubArray1(nums);
//        result = maxSubArray2(nums);
        result = maxSubArray3(nums);
        System.out.println(result);
    }

    public static int maxSubArray1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i<=nums.length-1; i++) {
            int sum = 0;
            for(int j=i; j<nums.length; j++) {
                sum += nums[j];
                if(sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    public static int maxSubArray2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        int maxSum = nums[0];
        int subSum = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(subSum < 0) {
                subSum = nums[i];
            }else {
                subSum += nums[i];
            }
            if(maxSum < subSum) {
                maxSum = subSum;
            }
        }
        return maxSum;
    }

    public static int maxSubArray3(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        return maxSubSum(nums, 0, nums.length-1).maxSub;
    }

    private static SumStatistic maxSubSum(int[] nums, int begin, int end) {
        if(begin == end) {
            return new SumStatistic(nums[end], nums[end], nums[end], nums[end]);
        }
        int middle = (begin + end) / 2;
        SumStatistic leftSS = maxSubSum(nums, begin, middle);
        SumStatistic rightSS = maxSubSum(nums, middle + 1, end);
        int left = Math.max(leftSS.left, leftSS.total + rightSS.left);
        int right = Math.max(rightSS.right, rightSS.total + leftSS.right);
        int total = leftSS.total + rightSS.total;
        int maxSub = Math.max(leftSS.maxSub, rightSS.maxSub);
        maxSub = Math.max(maxSub, leftSS.right + rightSS.left);
        return new SumStatistic(left, right, total, maxSub);
    }

    private static class SumStatistic {
        int left;
        int right;
        int total;
        int maxSub;
        SumStatistic(int left, int right, int total, int maxSub) {
            this.left = left;
            this.right = right;
            this.total = total;
            this.maxSub = maxSub;
        }
    }
}
