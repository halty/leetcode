package com.lee.leetcode.pro0151_0175;

/**
 *
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

 Find the minimum element.
 The array may contain duplicates.

 Example 1:
 Input: [1,3,5]
 Output: 1

 Example 2:
 Input: [2,2,2,0,1]
 Output: 0

 Note:
   1. This is a follow up problem to Find Minimum in Rotated Sorted Array.
   2. Would allow duplicates affect the run-time complexity? How and why?
 *
 */
public class Pro_0154_FindMinimumInRotatedSortedArrayII {

    public static void main(String[] args) {
        int[] nums = {1,3,3,3};
//        int min = findMin(nums);
//        int min = findMin1(nums);
        int min = findMin2(nums);
        System.out.println(min);
    }

    public static int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int num : nums) {
            if(min > num) {
                min = num;
            }
        }
        return min;
    }

    public static int findMin1(int[] nums) {
        int len = nums.length;
        int b = 0, e = len - 1;
        while(e-b >= 2) {
            int m = (b + e) / 2;
            int vm = nums[m];
            int ve = nums[e];
            if(vm > ve) {
                b = m + 1;
            }else if(vm < ve) {
                e = m;
            }else {
                if(e-m >= 2) {
                   int nextM = (m + e) / 2;
                   if(nums[nextM] != vm) {
                       b = m + 1;
                   }else {
                       int vb = nums[b];
                       if(vb > vm) {
                           b = b + 1;
                           e = m;
                       }else if(vb < vm) {  // vb < vm
                           e = m - 1;
                       }else {  // vb == vm
                           if(m-b >= 2) {
                               nextM = (b + m) / 2;
                               if(nums[nextM] != vm) {
                                   e = m - 1;
                               }else {
                                   int ib = m+1, ie = e-1;
                                   for(; ib<=ie && nums[ib]==vm && nums[ie]==vm; ib++, ie--);
                                   if(ib <= ie) {
                                       b = ib;
                                       e = ie;
                                   }else {
                                       e = m;
                                   }
                               }
                           }else {
                               b = m + 1;
                           }
                       }
                   }
                }else {
                    e = m;
                }

            }
        }
        return b == e ? nums[b] : Math.min(nums[b], nums[e]);
    }

    public static int findMin2(int[] nums) {
        int len = nums.length;
        int b = 0, e = len - 1;
        while(e-b >= 2) {
            int m = (b + e) / 2;
            int vm = nums[m];
            int ve = nums[e];
            if (vm > ve) {
                b = m + 1;
            } else if (vm < ve) {
                e = m;
            } else {
                e -= 1;
            }
        }
        return nums[b] < nums[e] ? nums[b] : nums[e];
    }
}
