package com.lee.leetcode.pro0301_0325;

/**
 *
 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
    Given nums = [-2, 0, 3, -5, 2, -1]

    sumRange(0, 2) -> 1
    sumRange(2, 5) -> -1
    sumRange(0, 5) -> -3

 Note:
    You may assume that the array does not change.
    There are many calls to sumRange function.
 *
 */
public class Pro_0303_RangeSumQueryImmutable {

    public static void main(String[] args) {
        int[] nums = {-2,0,3,-5,2,-1};
        Pro_0303_RangeSumQueryImmutable query = new Pro_0303_RangeSumQueryImmutable(nums);
        System.out.println(query.sumRange(0,2));
        System.out.println(query.sumRange(2,5));
        System.out.println(query.sumRange(0,5));
    }

    /*private int[] nums;

    public Pro_0303_RangeSumQueryImmutable(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for(int k=i; k<=j; k++) {
            sum += nums[k];
        }
        return sum;
    }*/

    private long[] rangeSum;

    public Pro_0303_RangeSumQueryImmutable(int[] nums) {
        long sum = 0;
        rangeSum = new long[nums.length];
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            rangeSum[i] = sum;
        }
    }

    public int sumRange(int i, int j) {
        return i == 0 ? (int)rangeSum[j] : (int)(rangeSum[j]-rangeSum[i-1]);
    }
}
