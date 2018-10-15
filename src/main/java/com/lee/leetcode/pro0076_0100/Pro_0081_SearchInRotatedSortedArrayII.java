package com.lee.leetcode.pro0076_0100;

/**
 *
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

Follow up:
This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
 *
 */
public class Pro_0081_SearchInRotatedSortedArrayII {

    public static void main(String[] args) {
        int[] nums = {1,1,3,1};
        int target = 3;
//        boolean result = search1(nums, target);
        boolean result = search2(nums, target);
        System.out.println(result);
    }

    public static boolean search1(int[] nums, int target) {
        for(int i=0; i<nums.length; i++) {
            if(nums[i] == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean search2(int[] nums, int target) {
        int len = nums.length;
        if(len == 0) { return false; }
        if(len == 1) { return nums[0] == target; }
        int left = 0, right = len-1;
        while(left < right) {
            int pivot = (left+right) >>> 1;
            int lv = nums[left];
            int pv = nums[pivot];
            int rv = nums[right];
            if(target == lv || target == pv || target == rv) {
                return true;
            }
            if(lv < rv) {   // asc
                if(target < pv) {
                    left = left + 1;
                    right = pivot - 1;
                }else {
                    left = pivot + 1;
                    right = right - 1;
                }
            }else { // rotated
                if(pv < rv) {  // [pivot, right] asc
                    if(target < pv) {
                        left = left + 1;
                        right = pivot - 1;
                    }else {
                        if(target < rv) {
                            left = pivot + 1;
                            right = right - 1;
                        }else {
                            left = left + 1;
                            right = pivot - 1;
                        }
                    }
                }else if(pv > rv) { // [left, pivot] asc
                    if(target > pv) {
                        left = pivot + 1;
                        right = right - 1;
                    }else {
                        if(target > lv) {
                            left = left + 1;
                            right = pivot - 1;
                        }else {
                            left = pivot + 1;
                            right = right - 1;
                        }
                    }
                }else {
                    if(lv > rv) {
                        left = left + 1;
                        right = pivot - 1;
                    }else { // lv = pv = rv
                        // can not determine the rotated point is in [left, pivot] or [pivot, right],
                        // so degrade to O(n) traversal
                        while(++left < len) {
                            if(nums[left] != lv) { break; }
                        }
                        while(--right >= 0) {
                            if(nums[right] != rv) { break; }
                        }
                    }
                }
            }
        }
        return left == right && nums[left] == target;
    }
}
