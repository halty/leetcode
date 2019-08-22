package com.lee.leetcode.pro0301_0325;

import java.util.Arrays;

/**
 *
 Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example 1:
 Input: nums = [1, 5, 1, 1, 6, 4]
 Output: One possible answer is [1, 4, 1, 5, 1, 6].

 Example 2:
 Input: nums = [1, 3, 2, 2, 3, 1]
 Output: One possible answer is [2, 3, 1, 3, 1, 2].

 Note: You may assume all input has valid answer.

 Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 *
 */
public class Pro_0324_WiggleSortII {

    public static void main(String[] args) {
        int[] nums = {1,3,2,2,3,1};
//        wiggleSort(nums);
//        wiggleSort1(nums);
        wiggleSort2(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] tmp = new int[nums.length];
        int m = (nums.length+1) / 2;
        int i = m-1, j = nums.length-1, k = 0;
        for(; j>=m; i--,j--) {
            tmp[k++] = nums[i];
            tmp[k++] = nums[j];
        }
        if(i == 0) { tmp[k] = nums[i]; }
        System.arraycopy(tmp,0, nums,0, nums.length);
    }

    public static void wiggleSort1(int[] nums) {
        int minIndex, maxIndex, end = nums.length-1;
        for(int i=0; i<end; i+=2) {
            minIndex = i;
            maxIndex = i + 1;
            if(nums[minIndex] > nums[maxIndex]) {
                swap(nums, minIndex, maxIndex);
            }
            for(int j=i+2; j<nums.length; j++) {
                if(nums[j] > nums[maxIndex]) {
                    maxIndex = j;
                }
                if(nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if(maxIndex != i+1) {
                swap(nums, i+1, maxIndex);
            }
            if(minIndex != i) {
                swap(nums, i, minIndex);
            }
        }
        reverseOdd(nums);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void reverseOdd(int[] nums) {
        for(int i=0,j=(nums.length-1) & (~0x01); i<j; i+=2,j-=2) {
            swap(nums, i, j);
        }
    }

    public static void wiggleSort2(int[] nums) {
        int median = locateMedian(nums);
        int left = 0, right = nums.length-1;
        int length = nums.length | 1; // length = nums.length % 2 == 0 ? nums.length+1 : nums.length;
        for(int i=0; i<=right;) {
            int wIndex = wiggleIndex(i, length);
            int num = nums[wIndex];
            if(num > median) {
                if(i > left) {
                    swap(nums, wIndex, wiggleIndex(left, length));
                }else {
                    i++;
                }
                left++;
            }else if(num < median) {
                swap(nums, wIndex, wiggleIndex(right, length));
                right--;
            }else {
                i++;
            }
        }
    }

    private static int locateMedian(int[] nums) {
        return locateKthElements(nums, 0, nums.length-1, (nums.length-1)>>1);
    }

    private static int locateKthElements(int[] nums, int b, int e, int kIndex) {
        int idx = partition(nums, b, e);
        if(idx == kIndex) {
            return nums[idx];
        }else if(idx < kIndex) {
            return locateKthElements(nums, idx+1, e, kIndex);
        }else {
            return locateKthElements(nums, b, idx-1, kIndex);
        }
    }

    private static int partition(int[] nums, int b, int e) {
        int pivot = nums[e];
        int i = b;
        for(; b < e; b++) {
            if(nums[b] <= pivot) {
                swap(nums, b, i++);
            }
        }
        swap(nums, e, i);
        return i;
    }

    private static int wiggleIndex(int index, int length) {
        return (1+(index<<1)) % length;
    }
}
