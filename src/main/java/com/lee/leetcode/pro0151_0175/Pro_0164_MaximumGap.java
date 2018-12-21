package com.lee.leetcode.pro0151_0175;

import java.util.Arrays;

/**
 *
 Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 Return 0 if the array contains less than 2 elements.

 Example 1:
 Input: [3,6,9,1]
 Output: 3
 Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.

 Example 2:
 Input: [10]
 Output: 0
 Explanation: The array contains less than 2 elements, therefore return 0.

 Note:
   1. You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
   2. Try to solve it in linear time/space.
 *
 */
public class Pro_0164_MaximumGap {

    public static void main(String[] args) {
        int[] nums = {100,3,2,1};
//        int gap = maximumGap(nums);
        int gap = maximumGap1(nums);
        System.out.println(gap);
    }

    public static int maximumGap(int[] nums) {
        if(nums.length < 2) { return 0; }
         Arrays.sort(nums);
//        cardinalitySort(nums);
        return findMaxGap(nums);
    }

    private static int findMaxGap(int[] nums) {
        int n = nums.length;
        int maxGap = nums[1] - nums[0];
        for(int i=2; i<n; i++) {
            int gap = nums[i] - nums[i-1];
            if(gap > maxGap) { maxGap = gap; }
        }
        return maxGap;
    }

    private static void cardinalitySort(int[] nums) {
        int n = nums.length;
        int[][] cardinalityTable = new int[16][n+1];    // hex cardinality
        for(int i=0; i<8; i++) {
            distribute(i, nums, cardinalityTable);
            collect(cardinalityTable, nums);
        }
    }

    private static void distribute(int index, int[] nums, int[][] cardinalityTable) {
        for(int num : nums) {
            int cardinality = (num >>> (index << 2)) & 0x0f;
            int[] array = cardinalityTable[cardinality];
            int count = array[0] + 1;
            array[0] = count;
            array[count] = num;
        }
    }

    private static void collect(int[][] cardinalityTable, int[] nums) {
        int i = 0;
        for(int[] array : cardinalityTable) {
            int count = array[0];
            for(int j=1; j<=count; j++) {
                nums[i++] = array[j];
            }
            array[0] = 0; // reset
        }
    }

    public static int maximumGap1(int[] nums) {
        int n = nums.length;
        if(n < 2) { return 0; }
        int min = nums[0], max = min;
        for(int i=1; i<n; i++) {
            int num = nums[i];
            if(num < min) { min = num; }
            if(num > max) { max = num; }
        }
        // bucket sort
        int totalGap = max - min;
        int bucketRange = Math.max(totalGap/(n-1), 1);
        int bucketCount = totalGap/bucketRange + 1;
        int[] bucketMax = new int[bucketCount];
        int[] bucketMin = new int[bucketCount];
        for(int i=0; i<n; i++) {
            int num = nums[i];
            int bucketIndex = (num-min)/bucketRange;
            if(num > bucketMax[bucketIndex]) {
                bucketMax[bucketIndex] = num;
            }
            int bMin = bucketMin[bucketIndex];
            if(bMin == 0 || num < bMin) {
                bucketMin[bucketIndex] = num;
            }
        }
        /* maxGap >= bucketRange, because bucketRange is average gap of (n-1) intervals which between n numbers
         * if maxGap == bucketRange, num array is ordered and each bucket contains only 1 num
         * if maxGap > bucketRange，2 end numbers at maxGap interval must be in 2 different buckets
         */
        int prevMax = bucketMax[0]; // bucketMin[0] = min, so calculate from index 1
        int maxGap = 0;
        for(int i=1; i<bucketCount; i++) {
            int bMin = bucketMin[i];
            if(bMin == 0) { continue; }
            int gap = bMin - prevMax;
            if(maxGap < gap) { maxGap = gap; }
            prevMax = bucketMax[i];
        }
        return maxGap;
    }
}
