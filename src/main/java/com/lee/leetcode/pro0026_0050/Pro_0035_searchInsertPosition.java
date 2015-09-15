package com.lee.leetcode.pro0026_0050;

/*
 * 
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
 * 
 */
public class Pro_0035_searchInsertPosition {

	public static void main(String[] args) {
		int[] nums = {1,3,5,6};
		int target = 0;
		System.out.println(searchInsert(nums, target));
	}

	public static int searchInsert(int[] nums, int target) {
        if(nums.length == 0) { return 0; }
        if(nums.length == 1) { return nums[0] >= target ? 0 : 1; }
        
        int left = 0, right = nums.length - 1;
        while(left < right) {
        	if(target <= nums[left]) { return left; }
        	if(target > nums[right]) { return right+1; }
        	if(target == nums[right]) { return right; }
        	int pivot = (left+right) / 2;
        	if(target == nums[pivot]) { return pivot; }
        	else if(target > nums[pivot]) {
        		left = pivot + 1;
        		right = right - 1;
        	}else {
        		left = left + 1;
        		right = pivot - 1;
        	}
        }
		if(left == right) {
			return nums[left] < target ? left+1 : left;
		}else {
			return left;
		}
    }
}
