package com.lee.leetcode.pro0026_0050;

/*
 * 
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 * 
 */
public class Pro_0033_searchInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = {4,5,6,7,0,1,2};
		int target = 1;
		System.out.println(search1(nums, target));
		System.out.println(search(nums, target));
	}

	public static int search1(int[] nums, int target) {
		if(nums.length == 0) { return -1; }
		if(nums.length == 1) { return nums[0] == target ? 0 : -1; }
		
		int left = 0, right = nums.length - 1;
		while(left < right) {
			int pivot = (left+right) / 2;
			int lv = nums[left];
			int rv = nums[right];
			int pv = nums[pivot];
			if(target == lv) { return left; }
			if(target == rv) { return right; }
			if(target == pv) { return pivot; }
			
			if(lv < rv) {	// asc
				if(target < pv) {
					right = pivot - 1;
					left = left + 1;
				}else {
					left = pivot + 1;
					right = right - 1;
				}
			}else {		// rotated
				if(pv <= rv) {	// pv <= rv < lv
					if(target < pv) {
						right = pivot - 1;
						left = left + 1;
					}else {
						if(target < rv) {
							left = pivot + 1;
							right = right - 1;
						}else {
							right = pivot - 1;
							left = left + 1;
						}
					}
				}else {		// rv < lv <= pv
					if(target < pv) {
						if(target < lv) {
							left = pivot + 1;
							right = right - 1;
						}else {
							right = pivot - 1;
							left = left + 1;
						}
					}else {
						left = pivot + 1;
						right = right - 1;
					}
				}
			}
		}
        return left == right && nums[left] == target ? left : -1;
    }
	
	public static int search(int[] nums, int target) {
        for(int i=0; i<nums.length; i++) {
        	if(nums[i] == target) { return i; }
        }
        return -1;
    }
}
