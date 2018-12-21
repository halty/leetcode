package com.lee.leetcode.pro0151_0175;

import java.util.Arrays;

/**
 *
 Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

 Note:
   1. Your returned answers (both index1 and index2) are not zero-based.
   2. You may assume that each input would have exactly one solution and you may not use the same element twice.
 Example:

 Input: numbers = [2,7,11,15], target = 9
 Output: [1,2]
 Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 *
 */
public class Pro_0167_TwoSumIIInputArrayIsSorted {

    public static void main(String[] args) {
        int[] numbers = {2,3,4};
        int target = 6;
        int[] result = twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int half = target >> 1;
        int n = numbers.length;
        int b = 0, e = n - 1;
        while(b <= e) {
            int m = b + ((e-b) >> 1);
            int vm = numbers[m];
            if(half < vm) {
                e = m - 1;
            }else if(half > vm) {
                b = m + 1;
            }else {
                b = m;
                break;
            }
        }
        int v = numbers[b] << 1;
        if(v == target) {
            if(numbers[b-1] == numbers[b]) {
                return of(b-1, b);
            }else if(numbers[b+1] == numbers[b]) {
                return of(b, b+1);
            }else {
                e = b + 1;
                b -= 1;
            }
        }else if(v < target) {
            e = b + 1;
        }else {
            e = b;
            b -= 1;
        }
        while (true) {
            int sum = numbers[b] + numbers[e];
            if (sum < target) {
                e += 1;
            } else if (sum > target) {
                b -= 1;
            } else {
                break;
            }
        }
        return of(b, e);
    }

    private static int[] of(int b, int e) {
        int[] result = new int[2];
        result[0] = b + 1;
        result[1] = e + 1;
        return result;
    }
}
