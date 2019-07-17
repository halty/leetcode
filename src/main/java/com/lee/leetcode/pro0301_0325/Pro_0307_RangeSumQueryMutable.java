package com.lee.leetcode.pro0301_0325;

/**
 *
 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 The update(i, val) function modifies nums by updating the element at index i to val.

 Example:
 Given nums = [1, 3, 5]
     sumRange(0, 2) -> 9
     update(1, 2)
     sumRange(0, 2) -> 8

 Note:
    The array is only modifiable by the update function.
    You may assume the number of calls to update and sumRange function is distributed evenly.
 *
 */
public class Pro_0307_RangeSumQueryMutable {

    public static void main(String[] args) {
        int[] nums = {1,3,5};
        Pro_0307_RangeSumQueryMutable query = new Pro_0307_RangeSumQueryMutable(nums);
        System.out.println(query.sumRange(0,2));
        query.update(1,2);
        System.out.println(query.sumRange(0,2));
    }

    /*private int[] nums;

    public Pro_0307_RangeSumQueryMutable(int[] nums) {
        this.nums = nums;
    }

    public void update(int i, int val) {
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for(int k=i; k<=j; k++) {
            sum += nums[k];
        }
        return sum;
    }*/

    private long[] rangeSum;

    public Pro_0307_RangeSumQueryMutable(int[] nums) {
        rangeSum = new long[nums.length];
        if(nums.length == 0) { return; }
        rangeSum[0] = nums[0];
        for(int i=1; i<nums.length; i++) {
            int idx = i;
            int delta = nums[i];
            while(idx < nums.length) {
                rangeSum[idx] += delta;
                idx += Integer.lowestOneBit(idx);
            }
        }
    }

    public void update(int i, int val) {
        if(rangeSum.length == 0) { return; }
        if(i == 0) {
            rangeSum[0] = val;
            return;
        }
        long delta;
        if((i & 0x01) == 1) {
            delta = val - rangeSum[i];
        }else {
            delta = val - (sumRange(i) - sumRange(i-1));
        }
        int idx = i;
        while(idx < rangeSum.length) {
            rangeSum[idx] += delta;
            idx += Integer.lowestOneBit(idx);
        }
    }

    private int sumRange(int i) {
        int sum = 0;
        while(i > 0) {
            sum += rangeSum[i];
            i -= Integer.lowestOneBit(i);
        }
        sum += rangeSum[0];
        return sum;
    }

    public int sumRange(int i, int j) {
        if(rangeSum.length == 0) { return 0; }
        return i == 0 ? sumRange(j) : sumRange(j)-sumRange(i-1);
    }
}
