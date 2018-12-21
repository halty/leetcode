package com.lee.leetcode.pro0151_0175;

/**
 *
 A peak element is an element that is greater than its neighbors.
 Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 You may imagine that nums[-1] = nums[n] = - ∞.

 Example 1:
 Input: nums = [1,2,3,1]
 Output: 2
 Explanation: 3 is a peak element and your function should return the index number 2.

 Example 2:
 Input: nums = [1,2,1,3,5,6,4]
 Output: 1 or 5
 Explanation: Your function can return either index number 1 where the peak element is 2,
 or index number 5 where the peak element is 6.

 Note:
 Your solution should be in logarithmic complexity.
 *
 */
public class Pro_0162_FindPeakElement {

    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,4};
//        int index = findPeakElement(nums);
        int index = findPeakElement1(nums);
        System.out.println("peak index = " + index);
    }

    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        if(n == 1) { return 0; }
        if(nums[0] > nums[1]) { return 0; }
        int i = 1, end = n-1;
        for(; i<end && nums[i]<nums[i+1]; i++);
        return i;
    }

    public static int findPeakElement1(int[] nums) {
        int n = nums.length;
        if(n == 1) { return 0; }
        int b = 0, e = n - 1;
        while(b < e) {
            int m = b + ((e-b) >> 1);
            if(nums[m] < nums[m+1]) {
                b = m + 1;
            }else {
                e = m;
            }
        }
        return b;
    }
}
