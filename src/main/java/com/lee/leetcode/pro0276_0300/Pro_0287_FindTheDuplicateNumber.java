package com.lee.leetcode.pro0276_0300;

/**
 *
 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Example 1:
 Input: [1,3,4,2,2]
 Output: 2

 Example 2:
 Input: [3,1,3,4,2]
 Output: 3

 Note:
    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n^2).
    There is only one duplicate number in the array, but it could be repeated more than once.
 *
 */
public class Pro_0287_FindTheDuplicateNumber {

    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
//        int duplicate = findDuplicate(nums);
        int duplicate = findDuplicate1(nums);
        System.out.println(duplicate);
    }

    public static int findDuplicate(int[] nums) {
        // Pigeonhole principle(抽屉原理)
        int b = 1, e = nums.length - 1;
        while(b < e) {
            int m = b + (e-b)/2;
            if(count(nums, m) > m) {
                e = m;
            }else {
                b = m + 1;
            }
        }
        return b;
    }

    private static int count(int[] nums, int m) {
        int count = 0;
        for(int num : nums) {
            if(num <= m) { count++; }
        }
        return count;
    }

    public static int findDuplicate1(int[] nums) {
        /* existed i != j, nums[i] = nums[j] = k
         * nums[i]=k, nums[k]=m, => i -> k -> m
         * so existed:
         * 0 -> ... -> i -> k -> m -> ... -> j -> k -> m
         * (k -> m -> j -> k) is a cycle
         * so the slow/fast step traversal used for linked list can be also used for here
         */
        int i = 0, j = 0;
        do {
            i = nums[i];
            j = nums[nums[j]];
        }while(i != j);
        i = 0;
        do {
          i = nums[i];
          j = nums[j];
        }while(i != j);
        return i;
    }
}
