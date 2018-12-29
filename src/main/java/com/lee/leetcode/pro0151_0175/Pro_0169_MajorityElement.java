package com.lee.leetcode.pro0151_0175;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 You may assume that the array is non-empty and the majority element always exist in the array.

 Example 1:
 Input: [3,2,3]
 Output: 3

 Example 2:
 Input: [2,2,1,1,1,2,2]
 Output: 2
 *
 */
public class Pro_0169_MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
//        int result = majorityElement(nums);
//        int result = majorityElement1(nums);
//        int result = majorityElement2(nums);
        int result = majorityElement3(nums);
        System.out.println(result);
    }

    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length>>1];
    }

    public static int majorityElement1(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int num : nums) {
            Integer n = num;
            Integer count = countMap.get(n);
            if(count == null) {
                countMap.put(n, 1);
            }else {
                countMap.put(n, count+1);
            }
        }
        int num = 0, maxCount = 0;
        for(Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if(entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                num = entry.getKey();
            }
        }
        return num;
    }

    public static int majorityElement2(int[] nums) {
        int len = nums.length;
        int halfLen = len >> 1;
        int[][] cardinalityTable = new int[16][len+1];    // hex cardinality
        for(int i=0; i<8; i++) {
            distribute(i, nums, len, cardinalityTable);
            Result r = collect(cardinalityTable, nums, halfLen);
            if(r.min == r.max) { break; }
            len = r.len;
        }
        return nums[0];
    }

    private static void distribute(int index, int[] nums, int rLen, int[][] cardinalityTable) {
        for(int i=0; i<rLen; i++) {
            int num = nums[i];
            int cardinality = (num >>> (index << 2)) & 0x0f;
            int[] array = cardinalityTable[cardinality];
            int count = array[0] + 1;
            array[0] = count;
            array[count] = num;
        }
    }

    private static Result collect(int[][] cardinalityTable, int[] nums, int halfLen) {
        int rLen = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int[] array : cardinalityTable) {
            int count = array[0];
            if(count > halfLen) {
                rLen = count;
                for(int j=1,i=0; j<=count; j++,i++) {
                    int num = array[j];
                    nums[i] = num;
                    if(min > num) { min = num; }
                    if(max < num) { max = num; }
                }
            }
            array[0] = 0;
        }
        return new Result(rLen, min, max);
    }

    private static class Result {
        int len;
        int min;
        int max;
        Result(int len, int min, int max) {
            this.len = len;
            this.min = min;
            this.max = max;
        }
    }

    public static int majorityElement3(int[] nums) {
        int n = nums.length;
        int target = nums[0];
        int count = 1;
        /* count(target) > n/2
         * count(others) < n/2
         * while traversal, the same elements increase count, diff elements decrease count, the reserved must be target
         */
        for(int i=1; i<n; i++) {
            if(count == 0) {
                target = nums[i];
                count = 1;
            }else if(target == nums[i]) {
                count++;
            }else {
                count--;
            }
        }
        return target;
    }
}
