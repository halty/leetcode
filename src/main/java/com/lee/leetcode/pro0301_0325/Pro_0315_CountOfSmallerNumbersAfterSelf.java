package com.lee.leetcode.pro0301_0325;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 You are given an integer array nums and you have to return a new counts array.
 The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 Example:

 Input: [5,2,6,1]
 Output: [2,1,1,0]
 Explanation:
 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.
 *
 */
public class Pro_0315_CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        int[] nums = {5,2,6,1};
//        List<Integer> counts = countSmaller(nums);
//        List<Integer> counts = countSmaller1(nums);
        List<Integer> counts = countSmaller2(nums);
        System.out.println(counts);
    }

    public static List<Integer> countSmaller(int[] nums) {
        if(nums.length == 0) { return Collections.emptyList(); }
        List<Integer> counts = new ArrayList<>(nums.length);
        int end = nums.length - 1;
        for(int i=0; i<end; i++) {
            int current = nums[i];
            int count = 0;
            for(int j=i+1; j<nums.length; j++) {
                if(nums[j] < current) {
                    count++;
                }
            }
            counts.add(count);
        }
        counts.add(0);
        return counts;
    }

    public static List<Integer> countSmaller1(int[] nums) {
        if(nums.length == 0) { return Collections.emptyList(); }
        List<Integer> counts = new ArrayList<>(nums.length);
        counts.add(0);
        int end = nums.length - 1;
        for(int i=end-1; i>=0; i--) {
            int v = nums[i];
            int b = i+1, e = end;
            while(b <= e) {
                int m = b + (e-b) / 2;
                int mv = nums[m];
                if(v > mv) {
                    b = m + 1;
                }else {
                    e = m - 1;
                }
            }
            int count = b - i - 1;
            System.arraycopy(nums, i+1, nums, i, count);
            nums[b-1] = v;
            counts.add(count);
        }
        Collections.reverse(counts);
        return counts;
    }

    public static List<Integer> countSmaller2(int[] nums) {
        if(nums.length == 0) { return Collections.emptyList(); }
        List<Integer> counts = new ArrayList<>(nums.length);
        int min = nums[0], max = min;
        for(int i=1; i<nums.length; i++) {
            int v = nums[i];
            if(v < min) {
                min = v;
            }else if(v > max) {
                max = v;
            }
        }
        if(max == min) {
            for(int i=0; i<nums.length; i++) {
                counts.add(0);
            }
            return counts;
        }
        BinaryIndexTree bit = new BinaryIndexTree(max-min+1);
        counts.add(0);
        bit.increment(nums[nums.length-1]-min);
        for(int i=nums.length-2; i>=0; i--) {
            int index = nums[i] - min;
            counts.add(index == 0 ?  0 : bit.count(index-1));
            bit.increment(index);
        }
        Collections.reverse(counts);
        return counts;
    }

    private static class BinaryIndexTree {

        int[] counts;

        BinaryIndexTree(int length) {
            counts = new int[length];
        }

        void increment(int i) {
            if(i == 0) {
                counts[i] += 1;
            }else {
                do {
                    counts[i] += 1;
                    i += Integer.lowestOneBit(i);
                }while(i < counts.length);
            }
        }

        int count(int end) {
            int count = 0;
            while(end > 0) {
                count += counts[end];
                end -= Integer.lowestOneBit(end);
            }
            count += counts[0];
            return count;
        }
    }
}
