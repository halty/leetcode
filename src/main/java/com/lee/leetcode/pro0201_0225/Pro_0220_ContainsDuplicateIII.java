package com.lee.leetcode.pro0201_0225;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 Given an array of integers, find out whether there are two distinct indices i and j in the array
 such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

 Example 1:
 Input: nums = [1,2,3,1], k = 3, t = 0
 Output: true

 Example 2:
 Input: nums = [1,0,1,1], k = 1, t = 2
 Output: true

 Example 3:
 Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 Output: false
 *
 */
public class Pro_0220_ContainsDuplicateIII {

    public static void main(String[] args) {
        int[] nums = {1,0,1,1};
        int k = 1;
        int t = 2;
//        boolean isDuplicated = containsNearbyAlmostDuplicate(nums, k, t);
        boolean isDuplicated = containsNearbyAlmostDuplicate1(nums, k, t);
        System.out.println(isDuplicated);
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int end = nums.length - 1;
        for(int i=0; i<end; i++) {
            long v = nums[i];
            int limit = (end-i > k) ? i+k : end;
            for(int j=i+1; j<= limit; j++) {
                if(Math.abs(v - nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if(k <= 0 || t < 0) { return false; }
        int end = nums.length - 1;
        if(k >= end) {
            Arrays.sort(nums);
            for(int i=1; i<nums.length; i++) {
                long cur = nums[i];
                if(Math.abs(cur - nums[i-1]) <= t) {
                    return true;
                }
            }
            return false;
        }else if(t == 0) {
            Set<Integer> set = new HashSet<>(k);
            for(int i=0; i<k; i++) {
                if(!set.add(nums[i])) {
                    return true;
                }
            }
            for(int i=k; i<nums.length; i++) {
                int rIndex = i - k;
                if(!set.add(nums[i])) {
                    return true;
                }
                set.remove(nums[rIndex]);
            }
            return false;
        }else {
            int[] dest = Arrays.copyOf(nums, k+1);
            Arrays.sort(dest);
            for(int i=1; i<=k; i++) {
                long cur = dest[i];
                if(Math.abs(cur - dest[i-1]) <= t) {
                    return true;
                }
            }
            for(int i=k+1, r=0; i<nums.length; i++, r++) {
                int rv = nums[r];
                int index = find(dest, 0, k, rv);
                if(index < k) {    // remove
                    System.arraycopy(dest, index+1, dest, index, k-index);
                }
                int v = nums[i];
                index = find(dest, 0, k-1, v);
                if(index >= 0) { return true; }
                index = -index - 1;
                if(index == 0) {
                   long next = dest[index];
                   if(Math.abs(next - v) <= t) {
                       return true;
                   }
                   System.arraycopy(dest, index, dest, index+1, k-index);
                   dest[index] = v;
                }else if(index == k) {
                   long prev = dest[index-1];
                    if(Math.abs(v - prev) <= t) {
                        return true;
                    }
                    dest[index] = v;
                }else {
                    long cmp = dest[index];
                    if(Math.abs(cmp - v) <= t) {
                        return true;
                    }
                    cmp = dest[index-1];
                    if(Math.abs(v - cmp) <= t) {
                        return true;
                    }
                    System.arraycopy(dest, index, dest, index+1, k-index);
                    dest[index] = v;
                }
            }
            return false;
        }
    }

    private static int find(int[] dest, int begin, int end, int v) {
        while(begin <= end) {
            int m = begin + (end-begin)/2;
            int mv = dest[m];
            if(v > mv) {
                begin = m + 1;
            }else if(v < mv) {
                end = m - 1;
            }else {
                return m;
            }
        }
        return -1-begin;
    }
}
