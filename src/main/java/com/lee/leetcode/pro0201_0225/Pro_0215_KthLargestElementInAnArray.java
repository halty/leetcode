package com.lee.leetcode.pro0201_0225;

import java.util.Arrays;

/**
 *
 Find the kth largest element in an unsorted array.
 Note that it is the kth largest element in the sorted order, not the kth distinct element.

 Example 1:
 Input: [3,2,1,5,6,4] and k = 2
 Output: 5

 Example 2:
 Input: [3,2,3,1,2,4,5,5,6] and k = 4
 Output: 4

 Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 */
public class Pro_0215_KthLargestElementInAnArray {

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        int result = 0;
//        result = findKthLargest(nums, k);
//        result = findKthLargest1(nums, k);
//        result = findKthLargest2(nums, k);
//        result = findKthLargest3(nums, k);
//        result = findKthLargest4(nums, k);
        result = findKthLargest5(nums, k);
        System.out.println(result);
        System.out.println(Arrays.toString(nums));
    }

    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    public static int findKthLargest1(int[] nums, int k) {
        int len = nums.length;
        if(2*k <= len) {    // order desc
            for(int i=0; i<k; i++) {
                for(int j=i+1; j<len; j++) {
                    if(nums[i] < nums[j]) {
                        int t = nums[i];
                        nums[i] = nums[j];
                        nums[j] = t;
                    }
                }
            }
            return nums[k-1];
        }else { // order asc
            int e = len - k + 1;
            for(int i=0; i<e; i++) {
                for(int j=i+1; j<len; j++) {
                    if(nums[i] > nums[j]) {
                        int t = nums[i];
                        nums[i] = nums[j];
                        nums[j] = t;
                    }
                }
            }
            return nums[e-1];
        }
    }

    public static int findKthLargest2(int[] nums, int k) {
        int len = nums.length;
        if(2*k <= len) {    // order asc
            int b = len - k;
            for(int i=len-1; i>=b; i--) {
                for(int j=0; j<i; j++) {
                    int next = j + 1;
                    if(nums[j] > nums[next]) {
                        int t = nums[j];
                        nums[j] = nums[next];
                        nums[next] = t;
                    }
                }
            }
            return nums[b];
        }else { // order desc
            int b = k - 1;
            for(int i=len-1; i>=b; i--) {
                for(int j=0; j<i; j++) {
                    int next = j + 1;
                    if(nums[j] < nums[next]) {
                        int t = nums[j];
                        nums[j] = nums[next];
                        nums[next] = t;
                    }
                }
            }
            return nums[b];
        }
    }

    public static int findKthLargest3(int[] nums, int k) {
        int len = nums.length;
        // construct
        for(int i=0; i<len; i++) {
            int num = nums[i];
            int current = i;
            while(current > 0) {
                int parent = (current - 1) / 2;
                if(num <= nums[parent]) { break; }
                nums[current] = nums[parent];
                current = parent;
            }
            nums[current] = num;
        }
        // adjust
        int parent, left, right;
        for(int i=len-1; i>0; i--) {
            int tmp = nums[i];
            nums[i] = nums[0];
            nums[0] = tmp;
            parent = 0;
            while(true) {
                left = parent*2 + 1;
                right = left + 1;
                if(left >= i) { break; }
                if(right < i && nums[left] < nums[right]) {
                    if(nums[right] <= nums[parent]) { break; }
                    tmp = nums[parent];
                    nums[parent] = nums[right];
                    nums[right] = tmp;
                    parent = right;
                }else {
                    if(nums[left] <= nums[parent]) { break; }
                    tmp = nums[parent];
                    nums[parent] = nums[left];
                    nums[left] = tmp;
                    parent = left;
                }
            }
        }
        return nums[len-k];
    }

    public static int findKthLargest4(int[] nums, int k) {
        int len = nums.length;
        // construct
        for(int i=0; i<k; i++) {
            int num = nums[i];
            int current = i;
            while(current > 0) {
                int parent = (current - 1) / 2;
                if(num >= nums[parent]) { break; }
                nums[current] = nums[parent];
                current = parent;
            }
            nums[current] = num;
        }
        // adjust
        int parent, left, right;
        for(int i=k; i<len; i++) {
            int num = nums[i];
            if(num <= nums[0]) { continue; }
            nums[i] = nums[0];
            parent = 0;
            while(parent < k) {
                left = parent*2 + 1;
                right = left + 1;
                if(left >= k) { break; }
                if(right < k) {
                    if(num <= nums[left]) {
                        if(num <= nums[right]) { break; }
                        nums[parent] = nums[right];
                        parent = right;
                    }else {
                        if(nums[left] <= nums[right]) {
                            nums[parent] = nums[left];
                            parent = left;
                        }else {
                            nums[parent] = nums[right];
                            parent = right;
                        }
                    }
                }else {
                    if(num <= nums[left]) { break; }
                    nums[parent] = nums[left];
                    parent = left;
                }
            }
            nums[parent] = num;
        }
        return nums[0];
    }

    public static int findKthLargest5(int[] nums, int k) {
        int len = nums.length;
        int bucketCount = 10;
        int[][] buckets = new int[bucketCount][len+1];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int count = len;
        for(int i=0; i<count; i++) {
            int num = nums[i];
            if(num < min) { min = num; }
            if(num > max) { max = num; }
        }

        while(min < max) {
            int range = max - min + 1;
            int gap = (range + bucketCount - 1) / bucketCount;
            for(int i=0; i<count; i++) {
                int num = nums[i];
                int bucketIndex = (num - min) / gap;
                int[] bucket = buckets[bucketIndex];
                int elementCount = bucket[0];
                bucket[++elementCount] = num;
                bucket[0] = elementCount;
            }
            int sum = 0;
            for(int i = bucketCount-1; i >= 0; i--) {
                int elementCount = buckets[i][0];
                sum += elementCount;
                if (sum >= k) {
                    sum -= elementCount;
                    k -= sum;
                    int[] bucket = buckets[i];
                    min = Integer.MAX_VALUE;
                    max = Integer.MIN_VALUE;
                    count = elementCount;
                    for (int j = 1; j <= elementCount; j++) {
                        int num = bucket[j];
                        if (num < min) { min = num; }
                        if (num > max) { max = num; }
                        nums[j-1] = num;
                    }
                    break;
                }
            }
            for(int i = bucketCount-1; i >= 0; i--) {
                buckets[i][0] = 0;
            }
        }
        return min;
    }
}
