package com.lee.leetcode.pro0226_0250;

import java.util.Arrays;

/**
 *
 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

 Example:
 Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 Output: [3,3,5,5,6,7]
 Explanation:
     Window position                Max
     ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7

 Note: You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

 Follow up: Could you solve it in linear time?
 *
 */
public class Pro_0239_SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
//        int[] result = maxSlidingWindow(nums, k);
//        int[] result = maxSlidingWindow1(nums, k);
        int[] result = maxSlidingWindow2(nums, k);
        System.out.println(Arrays.toString(result));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) { return nums; }
        int len = nums.length - k + 1;
        int[] result = new int[len];
        int maxIndex = 0, max = nums[0];
        for(int i=1; i<k; i++) {
            if(max < nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }
        int j = 0, i;
        result[j] = max;
        for(i=k,j=1; i<nums.length; i++,j++) {
            if(maxIndex == j-1) {
                maxIndex = j; max = nums[j];
                for(int index=j+1; index<=i; index++) {
                    if(max < nums[index]) {
                        max = nums[index];
                        maxIndex = index;
                    }
                }
            }else {
                if(max <= nums[i]) {
                    max = nums[i];
                    maxIndex = i;
                }
            }
            result[j] = max;
        }
        return result;
    }

    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if(nums == null || nums.length == 0) { return nums; }
        int len = nums.length - k + 1;
        int[] result = new int[len];
        int[] array = Arrays.copyOf(nums, k);
        Arrays.sort(array);
        result[0] = array[k-1];
        int index, end = k - 1;
        for(int i=k, j=0; i<nums.length; i++, j++) {
            index = find(array, end, nums[j]);
            if(index < end) {
                System.arraycopy(array, index+1, array, index, end-index);
            }
            index = find(array, end-1, nums[i]);
            if(index < end) {
                System.arraycopy(array, index, array, index+1, end-index);
            }
            array[index] = nums[i];
            result[j+1] = array[end];
        }
        return result;
    }

    private static int find(int[] array, int end, int val) {
        int b = 0, e = end, m;
        while(b <= e) {
            m = b + (e-b)/2;
            int mVal = array[m];
            if(val == mVal) {
                return m;
            }else if(val < mVal) {
                e = m - 1;
            }else {
                b = m + 1;
            }
        }
        return b;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums == null || nums.length == 0) { return nums; }
        int i = k - 1, j = nums.length - k;
        /* w - min window for sliding window size k
         * c - count of the sliding window
         * b - while the min window, the leftmost index
         * e - while the min window, the rightmost index
         * increase window size, left bound and right bound, until w = k
         */
        int b, e, w, c;
        if(i <= j) {
            b = i;
            e = j;
        }else {
            b = j;
            e = i;
        }
        w = k - b;
        c = e - k + 2;
        int[] result = new int[nums.length-k+1];
        if(w == 1) {
            for(i=b,j=0; i<=e; i++,j++) {
                result[j]=nums[i];
            }
        }else { // c == 1
            int max = nums[b];
            for(i=b+1; i<=e; i++) {
                if(nums[i] > max) {
                    max = nums[i];
                }
            }
            result[0] = max;
        }
        while(w < k) {
            int max = result[0];
            for(i=b-1, j=0; j<c; i++, j++) {
                max = result[j];
                if(nums[i] > max) {
                    result[j] = nums[i];
                }
            }
            result[j] = max;
            i = e + 1;
            if(nums[i] > max) {
                result[j] = nums[i];
            }
            b -= 1;
            e += 1;
            w += 1;
            c += 1;
        }
        return result;
    }
}
