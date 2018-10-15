package com.lee.leetcode.pro0001_0025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
 * 
 */
public class Pro_0015_3sum {

	public static void main(String[] args) {
		int[] nums = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
		System.out.println(Arrays.toString(nums));
		System.out.println(threeSum(nums));
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		if(nums == null || nums.length < 3) { return Collections.emptyList(); }
		Arrays.sort(nums);
		
        int i = 0;
        for(; i<nums.length && nums[i]<0; i++);
        if(i == nums.length) { return Collections.emptyList(); }
        int negativeEnd = i - 1;
        for(; i<nums.length && nums[i]==0; i++);
        if(i == 0) { return Collections.emptyList(); }
        int positiveStart = i;
        int zeroCount = positiveStart - negativeEnd - 1;
        
        if(negativeEnd < 0 || positiveStart == nums.length) {
        	if(zeroCount < 3) {
        		return Collections.emptyList();
        	}else {
        		return Arrays.asList(threeZero());
        	}
        }
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.addAll(twoNegativeOnePositive(nums, negativeEnd, positiveStart));
    	result.addAll(oneNegativeTwoPositive(nums, negativeEnd, positiveStart));
        if(zeroCount > 0) {
        	result.addAll(oneNegativeOneZeroOnePositive(nums, negativeEnd, positiveStart));
        	if(zeroCount >= 3) { result.add(threeZero()); }
        }
        return result;
    }
	
	private static List<Integer> threeZero() { return Arrays.asList(0, 0, 0); }
	
	private static List<List<Integer>> twoNegativeOnePositive(int[] nums, int negativeEnd, int positiveStart) {
		List<List<Integer>> result = new ArrayList<List<Integer>>(nums.length - positiveStart);
		int prev = 0;
		for(int i = positiveStart; i<nums.length; i++) {
			if(nums[i] == prev) { continue; }
			prev = nums[i];
			int first=0, second=negativeEnd;
			while(first < second) {
				int sum = nums[first]+nums[second];
				if(sum == -prev) {
					result.add(Arrays.asList(nums[first], nums[second], prev));
					while(++first < second && nums[first] == nums[first-1]);
					while(first < --second && nums[second] == nums[second+1]);
				}else {
					if(sum < -prev) {
						while(++first < second && nums[first] == nums[first-1]);
					}else {
						while(first < --second && nums[second] == nums[second+1]);
					}
				}
			}
		}
		return result;
	}
	
	private static List<List<Integer>> oneNegativeTwoPositive(int[] nums, int negativeEnd, int positiveStart) {
		List<List<Integer>> result = new ArrayList<List<Integer>>(negativeEnd + 1);
		int prev = 0;
		for(int i = 0; i<=negativeEnd; i++) {
			if(nums[i] == prev) { continue; }
			prev = nums[i];
			int second=positiveStart, third=nums.length - 1;
			while(second < third) {
				int sum = nums[second]+nums[third];
				if(-sum == prev) {
					result.add(Arrays.asList(prev, nums[second], nums[third]));
					while(++second < third && nums[second] == nums[second-1]);
					while(second < --third && nums[third] == nums[third+1]);
				}else {
					if(-sum < prev) {
						--third;
					}else {
						++second;
					}
				}
			}
		}
		return result;
	}
	
	private static List<List<Integer>> oneNegativeOneZeroOnePositive(int[] nums, int negativeEnd, int positiveStart) {
		List<List<Integer>> result = new ArrayList<List<Integer>>(Math.min(negativeEnd+1, nums.length-positiveStart));
		int first = 0, third = nums.length - 1;
		while(first <= negativeEnd && third >= positiveStart) {
			if(nums[first] == -nums[third]) {
				result.add(Arrays.asList(nums[first], 0, nums[third]));
				while(++first < third && nums[first] == nums[first-1]);
				while(first < --third && nums[third] == nums[third+1]);
			}else {
				if(nums[first] < -nums[third]) {
					while(++first < third && nums[first] == nums[first-1]);
				}else {
					while(first < --third && nums[third] == nums[third+1]);
				}
			}
		}
		return result;
	}
}
