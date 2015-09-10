package com.lee.leetcode.pro0001_0025;

import java.util.Arrays;

/*
 * 
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
 * 
 */
public class Pro_0001_twoSum {

	public static void main(String[] args) {
		int[] array = {0, 4, 3, 0};
		int target = 0;
		System.out.println(Arrays.toString(twoSum(array, target)));
	}
	
	public static int[] twoSum(int[] nums, int target) {
		if(nums[0]+nums[1]==target) { return new int[]{1, 2}; }
		if(nums[1]+nums[2]==target) { return new int[] { 2, 3}; }
		if(nums[0]+nums[2]==target) { return new int[] { 1, 3}; }
		
		int[] idx = new int[2];
		int j = 3, i = 2;
		while(j < nums.length) {
			if(nums[j]+nums[i]==target) {
        		idx[0] = i+1;
        		idx[1] = j+1;
        		break;
        	}
			if(i == 0) {
				i = j++;
			}else {
				i--;
			}
		}
        
        return idx;
    }

	public static int[] twoSum1(int[] nums, int target) {
		if(nums.length == 2) { return new int[]{1, 2}; }
        return twoSumRecursive(nums, target, 2);
    }
	
	public static int[] twoSumRecursive(int[] nums, int target, int next) {
		int second = nums[next];
		for(int i=0; i<next; i++) {
			if(nums[i]+second == target) {
				return new int[] { i+1, next+1 };
			}
		}
		return twoSumRecursive(nums, target, next+1);
	}
	
	public static int[] twoSum2(int[] nums, int target) {
        int[] idx = new int[2];
        boolean existed = false;
        
        for(int gap=1; gap<nums.length; gap++) { 
	        for(int first=0, second=first+gap; second<nums.length; first++, second++) {
	        	if(nums[first]+nums[second]==target) {
	        		idx[0] = first+1;
	        		idx[1] = second+1;
	        		existed = true;
	        		break;
	        	}
	        }
	        if(existed) { break; }
        }
        
        return idx;
    }
	
	public static int[] twoSum3(int[] nums, int target) {
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        Arrays.sort(copy);
        int first = 0, second = nums.length - 1;
        while(first < second) {
        	int sum = copy[first] + copy[second];
        	if(sum == target) { break; }
        	if(sum < target) {
        		first++;
        	}else {
        		second--;
        	}
        }
        first = copy[first];
        second = copy[second];
        int[] idx = new int[2];
        for(int i=0; i<nums.length; i++) {
        	if(nums[i] == first) { idx[0] = i+1; }
        }
        for(int i=nums.length-1; i>=0; i--) {
        	if(nums[i] == second) { idx[1] = i+1; }
        }
        if(idx[0] > idx[1]) {
        	first = idx[0];
        	idx[0] = idx[1];
        	idx[1] = first;
        }
        return idx;
    }
}
