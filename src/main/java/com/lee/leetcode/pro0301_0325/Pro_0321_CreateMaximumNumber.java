package com.lee.leetcode.pro0301_0325;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 Given two arrays of length m and n with digits 0-9 representing two numbers.
 Create the maximum number of length k <= m + n from digits of the two.
 The relative order of the digits from the same array must be preserved. Return an array of the k digits.

 Note: You should try to optimize your time and space complexity.

 Example 1:
 Input:
 nums1 = [3, 4, 6, 5]
 nums2 = [9, 1, 2, 5, 8, 3]
 k = 5
 Output: [9, 8, 6, 5, 3]

 Example 2:
 Input:
 nums1 = [6, 7]
 nums2 = [6, 0, 4]
 k = 5
 Output: [6, 7, 6, 0, 4]

 Example 3:
 Input:
 nums1 = [3, 9]
 nums2 = [8, 9]
 k = 3
 Output: [9, 8, 9]
 *
 */
public class Pro_0321_CreateMaximumNumber {

    public static void main(String[] args) {
        /*int[] nums1 = {8,9,7,3,5,9,1,0,8,5,3,0,9,2,7,4,8,9,8,1,0,2,0,2,7,2,3,5,4,7,4,1,4,0,1,4,2,1,3,1,5,3,9,3,9,0,1,7,0,6,1,8,5,6,6,5,0,4,7,2,9,2,2,7,6,2,9,2,3,5,7,4,7,0,1,8,3,6,6,3,0,8,5,3,0,3,7,3,0,9,8,5,1,9,5,0,7,9,6,8,5,1,9,6,5,8,2,3,7,1,0,1,4,3,4,4,2,4,0,8,4,6,5,5,7,6,9,0,8,4,6,1,6,7,2,0,1,1,8,2,6,4,0,5,5,2,6,1,6,4,7,1,7,2,2,9,8,9,1,0,5,5,9,7,7,8,8,3,3,8,9,3,7,5,3,6,1,0,1,0,9,3,7,8,4,0,3,5,8,1,0,5,7,2,8,4,9,5,6,8,1,1,8,7,3,2,3,4,8,7,9,9,7,8,5,2,2,7,1,9,1,5,5,1,3,5,9,0,5,2,9,4,2,8,7,3,9,4,7,4,8,7,5,0,9,9,7,9,3,8,0,9,5,3,0,0,3,0,4,9,0,9,1,6,0,2,0,5,2,2,6,0,0,9,6,3,4,1,2,0,8,3,6,6,9,0,2,1,6,9,2,4,9,0,8,3,9,0,5,4,5,4,6,1,2,5,2,2,1,7,3,8,1,1,6,8,8,1,8,5,6,1,3,0,1,3,5,6,5,0,6,4,2,8,6,0,3,7,9,5,5,9,8,0,4,8,6,0,8,6,6,1,6,2,7,1,0,2,2,4,0,0,0,4,6,5,5,4,0,1,5,8,3,2,0,9,7,6,2,6,9,9,9,7,1,4,6,2,8,2,5,3,4,5,2,4,4,4,7,2,2,5,3,2,8,2,2,4,9,8,0,9,8,7,6,2,6,7,5,4,7,5,1,0,5,7,8,7,7,8,9,7,0,3,7,7,4,7,2,0,4,1,1,9,1,7,5,0,5,6,6,1,0,6,9,4,2,8,0,5,1,9,8,4,0,3,1,2,4,2,1,8,9,5,9,6,5,3,1,8,9,0,9,8,3,0,9,4,1,1,6,0,5,9,0,8,3,7,8,5};
        int[] nums2 = {7,8,4,1,9,4,2,6,5,2,1,2,8,9,3,9,9,5,4,4,2,9,2,0,5,9,4,2,1,7,2,5,1,2,0,0,5,3,1,1,7,2,3,3,2,8,2,0,1,4,5,1,0,0,7,7,9,6,3,8,0,1,5,8,3,2,3,6,4,2,6,3,6,7,6,6,9,5,4,3,2,7,6,3,1,8,7,5,7,8,1,6,0,7,3,0,4,4,4,9,6,3,1,0,3,7,3,6,1,0,0,2,5,7,2,9,6,6,2,6,8,1,9,7,8,8,9,5,1,1,4,2,0,1,3,6,7,8,7,0,5,6,0,1,7,9,6,4,8,6,7,0,2,3,2,7,6,0,5,0,9,0,3,3,8,5,0,9,3,8,0,1,3,1,8,1,8,1,1,7,5,7,4,1,0,0,0,8,9,5,7,8,9,2,8,3,0,3,4,9,8,1,7,2,3,8,3,5,3,1,4,7,7,5,4,9,2,6,2,6,4,0,0,2,8,3,3,0,9,1,6,8,3,1,7,0,7,1,5,8,3,2,5,1,1,0,3,1,4,6,3,6,2,8,6,7,2,9,5,9,1,6,0,5,4,8,6,6,9,4,0,5,8,7,0,8,9,7,3,9,0,1,0,6,2,7,3,3,2,3,3,6,3,0,8,0,0,5,2,1,0,7,5,0,3,2,6,0,5,4,9,6,7,1,0,4,0,9,6,8,3,1,2,5,0,1,0,6,8,6,6,8,8,2,4,5,0,0,8,0,5,6,2,2,5,6,3,7,7,8,4,8,4,8,9,1,6,8,9,9,0,4,0,5,5,4,9,6,7,7,9,0,5,0,9,2,5,2,9,8,9,7,6,8,6,9,2,9,1,6,0,2,7,4,4,5,3,4,5,5,5,0,8,1,3,8,3,0,8,5,7,6,8,7,8,9,7,0,8,4,0,7,0,9,5,8,2,0,8,7,0,3,1,8,1,7,1,6,9,7,9,7,2,6,3,0,5,3,6,0,5,9,3,9,1,1,0,0,8,1,4,3,0,4,3,7,7,7,4,6,4,0,0,5,7,3,2,8,5,1,4,5,8,5,6,7,5,7,3,3,9,6,8,1,5,1,1,1,0,3};
        int k = 500;*/
        /*int[] nums1 = {3,3,3,2,3,7,3,8,6,0,5,0,7,8,9,2,9,6,6,9,9,7,9,7,6,1,7,2,7,5,5,1};
        int[] nums2 = {5,6,4,9,6,9,2,2,7,5,4,3,0,0,1,7,1,8,1,5,2,5,7,0,4,3,8,7,3,8,5,3,8,3,4,0,2,3,8,2,7,1,2,3,8,7,6,7,1,1,3,9,0,5,2,8,2,8,7,5,0,8,0,7,2,8,5,6,5,9,5,1,5,2,6,2,4,9,9,7,6,5,7,9,2,8,8,3,5,9,5,1,8,8,4,6,6,3,8,4,6,6,1,3,4,1,6,7,0,8,0,3,3,1,8,2,2,4,5,7,3,7,7,4,3,7,3,0,7,3,0,9,7,6,0,3,0,3,1,5,1,4,5,2,7,6,2,4,2,9,5,5,9,8,4,2,3,6,1,9};
        int k = 160;*/
        /*int[] nums1 = {4,6,9,1,0,6,3,1,5,2,8,3,8,8,4,7,2,0,7,1,9,9,0,1,5,9,3,9,3,9,7,3,0,8,1,0,9,1,6,8,8,4,4,5,7,5,2,8,2,7,7,7,4,8,5,0,9,6,9,2};
        int[] nums2 = {9,9,4,5,1,2,0,9,3,4,6,3,0,9,2,8,8,2,4,8,6,5,4,4,2,9,5,0,7,3,7,5,9,6,6,8,8,0,2,4,2,2,1,6,6,5,3,6,2,9,6,4,5,9,7,8,0,7,2,3};
        int k = 60;*/
        int[] nums1 = {5,7,3};
        int[] nums2 = {4,2,3};
        int k = 2;
        long t = System.nanoTime();
//        int[] maxNum = maxNumber(nums1, nums2, k);
//        int[] maxNum = maxNumber1(nums1, nums2, k);
        int[] maxNum = maxNumber2(nums1, nums2, k);
        t = System.nanoTime() - t;
        System.out.println(t);
        System.out.println(Arrays.toString(maxNum));
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if(k == 0) {
            return new int[0];
        }else if(k == 1) {
            return maxOne(nums1, nums2);
        }else {
            int m = nums1.length;
            int n = nums2.length;
            Result result = new Result(k);
            if(m == 0) {
                max(nums2, k, result);
            }else if(n == 0) {
                max(nums1, k, result);
            }else {
                max(nums1, nums2, k, result);
            }
            return result.hasMax ? result.maxDigits : new int[0];
        }
    }

    private static int[] maxOne(int[] nums1, int[] nums2) {
        if(nums1.length == 0 && nums2.length == 0) {
            return new int[0];
        }else {
            return new int[] { Math.max(maxOne(nums1, 0), maxOne(nums2, 0)) };
        }
    }

    private static int maxOne(int[] nums, int begin) {
        int max = Integer.MIN_VALUE;
        for(int i=begin; i<nums.length; i++) {
            int num = nums[i];
            if(num > max) { max = num; }
        }
        return max;
    }

    private static void max(int[] nums, int k, Result result) {
        if(k > nums.length) {
            return;
        }else if(k == nums.length) {
            result.compareAndSet(nums);
        }else {
            max(nums, 0, k, result, 0);
        }
    }

    private static void max(int[] nums, int begin, int k, Result result, int digitIndex) {
        if(k == 0) {
            result.compareAndSet();
            return;
        }
        int upperLimit = 10;
        int index = maxIndex(nums, begin, upperLimit);
        while(index >= begin) {
            int reserved = nums.length - index;
            if(reserved > k) {
                result.digits[digitIndex] = nums[index];
                max(nums, index + 1, k - 1, result, digitIndex + 1);
                return;
            }else if(reserved < k) {
                upperLimit = nums[index];
                index = maxIndex(nums, begin, upperLimit);
            }else {
                System.arraycopy(nums, index, result.digits, digitIndex, k);
                result.compareAndSet();
                return;
            }
        }
    }

    private static int maxIndex(int[] nums, int begin, int upperLimit) {
        int maxIndex = -1, max = Integer.MIN_VALUE;
        for(int i=begin; i<nums.length; i++) {
            int num = nums[i];
            if(num < upperLimit) {
                if(num > max) {
                    max = num;
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }

    private static void max(int[] nums1, int[] nums2, int k, Result result) {
        int len = nums1.length + nums2.length;
        if(k < len) {
            max(nums1, 0, nums2, 0, k, result, 0);
        }else if(k == len) {
            copy(nums1, 0, nums2, 0, result.maxDigits, result.maxDigits.length - k);
            result.hasMax = true;
        }
    }

    private static void max(int[] nums1, int b1, int[] nums2, int b2, int k, Result result, int digitIndex) {
        if(k == 0) {
            result.compareAndSet();
            return;
        }
        int upperLimit1 = 10, upperLimit2 = 10;
        int index1 = maxIndex(nums1, b1, upperLimit1);
        int index2 = maxIndex(nums2, b2, upperLimit2);
        while(index1 >= b1 && index2 >= b2) {
            int n1 = nums1[index1], n2 = nums2[index2];
            if(n1 < n2) {
                int r2 = nums1.length - b1 + nums2.length - index2;
                if(r2 > k) {
                    result.digits[digitIndex] = n2;
                    max(nums1, b1, nums2, index2+1, k-1, result, digitIndex+1);
                    return;
                }else if(r2 < k) {
                    upperLimit2 = n2;
                    index2 = maxIndex(nums2, b2, upperLimit2);
                }else {
                    copy(nums1, b1, nums2, index2, result.digits, digitIndex);
                    result.compareAndSet();
                    return;
                }
            }else if(n1 > n2) {
                int r1 = nums1.length - index1 + nums2.length - b2;
                if(r1 > k) {
                    result.digits[digitIndex] = n1;
                    max(nums1, index1+1, nums2, b2, k-1, result, digitIndex+1);
                    return;
                }else if(r1 < k) {
                    upperLimit1 = n1;
                    index1 = maxIndex(nums1, b1, upperLimit1);
                }else {
                    copy(nums1, index1, nums2, b2, result.digits, digitIndex);
                    result.compareAndSet();
                    return;
                }
            }else {
                int r1 = nums1.length - index1 + nums2.length - b2;
                int r2 = nums1.length - b1 + nums2.length - index2;
                if(r1 < k) {
                    if(r2 > k) {
                        result.digits[digitIndex] = n2;
                        max(nums1, b1, nums2, index2+1, k-1, result, digitIndex+1);
                        return;
                    }else if(r2 < k) {
                        upperLimit1 = n1;
                        index1 = maxIndex(nums1, b1, upperLimit1);
                        upperLimit2 = n2;
                        index2 = maxIndex(nums2, b2, upperLimit2);
                    }else {
                        copy(nums1, b1, nums2, index2, result.digits, digitIndex);
                        result.compareAndSet();
                        return;
                    }
                }else {
                    if(r1 > k) {
                        result.digits[digitIndex] = n1;
                        max(nums1, index1+1, nums2, b2, k-1, result, digitIndex+1);
                    }else { // r1 == k
                        copy(nums1, index1, nums2, b2, result.digits, digitIndex);
                        result.compareAndSet();
                    }
                    if(r2 > k) {
                        result.digits[digitIndex] = n2;
                        max(nums1, b1, nums2, index2+1, k-1, result, digitIndex+1);
                    }else if(r2 == k) {
                        copy(nums1, b1, nums2, index2, result.digits, digitIndex);
                        result.compareAndSet();
                    }
                    return;
                }
            }
        }
        if(index1 < b1) {
            max(nums2, b2, k, result, digitIndex);
        }else {
            max(nums1, b1, k, result, digitIndex);
        }
    }

    private static void copy(int[] nums1, int b1, int[] nums2, int b2, int[] target, int b) {
        while(b1 < nums1.length && b2 < nums2.length) {
            int n1 = nums1[b1], n2 = nums2[b2];
            if(n1 > n2) {
                target[b] = n1;
                b1++;
            }else if(n1 < n2) {
                target[b] = n2;
                b2++;
            }else {
                int k1 = b1+1, k2 = b2+1;
                boolean isNum1First = true;
                while(k1 < nums1.length && k2 < nums2.length) {
                    int v1 = nums1[k1], v2 = nums2[k2];
                    if(v1 > v2) {
                        isNum1First = true;
                        break;
                    }else if(v1 < v2) {
                        isNum1First = false;
                        break;
                    }else {
                        k1++;
                        k2++;
                    }
                }
                if(k1 == nums1.length) {
                    isNum1First = false;
                }else if(k2 == nums2.length) {
                    isNum1First = true;
                }
                if(isNum1First) {
                    target[b] = n1;
                    b1++;
                }else {
                    target[b] = n2;
                    b2++;
                }
            }
            b++;
        }
        if(b1 == nums1.length) {
            System.arraycopy(nums2, b2, target, b, nums2.length-b2);
        }else {
            System.arraycopy(nums1, b1, target, b, nums1.length-b1);
        }
    }

    private static class Result {
        private int[] digits;
        private int[] maxDigits;
        private boolean hasMax;

        Result(int k) {
            digits = new int[k];
            maxDigits = new int[k];
            hasMax = false;
        }

        void compareAndSet() {
            compareAndSet(digits);
        }

        void compareAndSet(int[] nums) {
            if(hasMax) {
                int i = 0, len = nums.length;
                boolean needReplace = false;
                for(; i < len; i++) {
                    int a = nums[i], b = maxDigits[i];
                    if(a == b) { continue; }
                    needReplace = a > b;
                    break;
                }
                if(needReplace) {
                    System.arraycopy(nums, i, maxDigits, i, len-i);
                }
            }else {
                hasMax = true;
                System.arraycopy(nums, 0, maxDigits, 0, nums.length);
            }
        }
    }

    public static int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        // 依次从多个nums中选出当前最大的数字，然后再递归，直至选出全部K个数字
        if(k == 0) {
            return new int[0];
        }else if(k == 1) {
            return maxOne(nums1, nums2);
        }else {
            if(nums1.length == 0) {
                return max(nums2, k);
            }else if(nums2.length == 0) {
                return max(nums1, k);
            }else {
                return max(nums1, nums2, k);
            }
        }
    }

    private static int[] max(int[] nums, int k) {
        if(k > nums.length) {
            return new int[0];
        }else if(k == nums.length) {
            return nums;
        }else {
            Result result = new Result(k);
            int[][] matrix = descMatrix(nums);
            max(nums, matrix, 0, k, result, 0);
            return result.maxDigits;
        }
    }

    private static int[][] descMatrix(int[] nums) {
        int[][] matrix = new int[nums.length][];
        int end = nums.length - 1;
        int[] indexes = new int[1];
        indexes[0] = end;
        matrix[end] = indexes;
        for(int i=end-1; i>=0; i--) {
            int idx = find(nums, indexes, i);
            int[] newIndexes = new int[indexes.length+1];
            if(idx > 0) {
                System.arraycopy(indexes, 0, newIndexes, 0, idx);
            }
            newIndexes[idx] = i;
            if(idx < indexes.length) {
                System.arraycopy(indexes, idx, newIndexes, idx+1, indexes.length-idx);
            }
            matrix[i] = newIndexes;
            indexes = newIndexes;
        }
        return matrix;
    }

    private static int find(int[] nums, int[] indexes, int index) {
        int target = nums[index];
        int b = 0, e = indexes.length-1;
        while(b <= e) {
            int m = b + (e-b)/2;
            int num = nums[indexes[m]];
            if(target < num) {
                b = m + 1;
            }else if(target > num) {
                e = m - 1;
            }else {
                if(index < indexes[m]) {
                    e = m - 1;
                }else {
                    b = m + 1;
                }
            }
        }
        return b;
    }

    private static void max(int[] nums, int[][] matrix, int begin, int k, Result result, int digitIndex) {
        if(k == 0) {
            result.compareAndSet();
            return;
        }
        int[] indexes = matrix[begin];
        for(int index : indexes) {
            int reserved = nums.length - index;
            if(reserved > k) {
                result.digits[digitIndex] = nums[index];
                max(nums, matrix, index+1, k-1, result, digitIndex+1);
                return;
            }else if(reserved == k) {
                System.arraycopy(nums, index, result.digits, digitIndex, k);
                result.compareAndSet();
                return;
            }
        }
    }

    private static int[] max(int[] nums1, int[] nums2, int k) {
        int len = nums1.length + nums2.length;
        if(k < len) {
            Result result = new Result(k);
            int[][] matrix1 = descMatrix(nums1);
            int[][] matrix2 = descMatrix(nums2);
            max(nums1, matrix1, 0, nums2, matrix2, 0, k, result, 0);
            return result.maxDigits;
        }else if(k == len) {
            int[] digits = new int[k];
            copy(nums1, 0, nums2, 0, digits, 0);
            return digits;
        }else {
            return new int[0];
        }
    }

    private static void max(int[] nums1, int[][] matrix1, int b1,
                            int[] nums2, int[][] matrix2, int b2,
                            int k, Result result, int digitIndex) {
        if(k == 0) {
            result.compareAndSet();
            return;
        }
        if(k == 1) {
            result.digits[digitIndex] = Math.max(maxOne(nums1, b1), maxOne(nums2, b2));
            result.compareAndSet();
            return;
        }
        if(b1 == nums1.length) {
            max(nums2, matrix2, b2, k, result, digitIndex);
            return;
        }
        if(b2 == nums2.length) {
            max(nums1, matrix1, b1, k, result, digitIndex);
            return;
        }
        int[] indexes1 = matrix1[b1];
        int[] indexes2 = matrix2[b2];
        int i = 0, j = 0;
        while(true) {
            int index1 = indexes1[i], index2 = indexes2[j];
            int n1 = nums1[index1], n2 = nums2[index2];
            if(n1 < n2) {
                int r2 = nums1.length - b1 + nums2.length - index2;
                if(r2 > k) {
                    result.digits[digitIndex] = n2;
                    max(nums1, matrix1, b1, nums2, matrix2, index2+1, k-1, result, digitIndex+1);
                    return;
                }else if(r2 == k) {
                    copy(nums1, b1, nums2, index2, result.digits, digitIndex);
                    result.compareAndSet();
                    return;
                }else {
                    while(++j < indexes2.length && nums2[indexes2[j]] == n2);
                    if(j == indexes2.length) { break; }
                }
            }else if(n1 > n2) {
                int r1 = nums1.length - index1 + nums2.length - b2;
                if(r1 > k) {
                    result.digits[digitIndex] = n1;
                    max(nums1, matrix1, index1+1, nums2, matrix2, b2, k-1, result, digitIndex+1);
                    return;
                }else if(r1 == k) {
                    copy(nums1, index1, nums2, b2, result.digits, digitIndex);
                    result.compareAndSet();
                    return;
                }else {
                    while(++i < indexes1.length && nums1[indexes1[i]] == n1);
                    if(i == indexes1.length) { break; }
                }
            }else {
                int r1 = nums1.length - index1 + nums2.length - b2;
                int r2 = nums1.length - b1 + nums2.length - index2;
                if (r1 < k) {
                    if (r2 > k) {
                        result.digits[digitIndex] = n2;
                        max(nums1, matrix1, b1, nums2, matrix2, index2 + 1, k - 1, result, digitIndex + 1);
                        return;
                    } else if (r2 == k) {
                        copy(nums1, b1, nums2, index2, result.digits, digitIndex);
                        result.compareAndSet();
                        return;
                    } else {
                        while(++i < indexes1.length && nums1[indexes1[i]] == n1);
                        while(++j < indexes2.length && nums2[indexes2[j]] == n2);
                        if (i == indexes1.length) { break; }
                        if (j == indexes2.length) { break; }
                    }
                }else if(r1 == k) {
                    copy(nums1, index1, nums2, b2, result.digits, digitIndex);
                    result.compareAndSet();
                    if(r2 > k) {
                        result.digits[digitIndex] = n2;
                        max(nums1, matrix1, b1, nums2, matrix2, index2+1, k-1, result, digitIndex+1);
                    }else if(r2 == k) {
                        copy(nums1, b1, nums2, index2, result.digits, digitIndex);
                        result.compareAndSet();
                    }
                    return;
                }else {
                    if(r2 > k) {
                        if(i > 0) {
                            if(j > 0) {
                                result.digits[digitIndex] = n1;
                                max(nums1, matrix1, index1+1, nums2, matrix2, b2, k-1, result, digitIndex+1);
                                max(nums1, matrix1, b1, nums2, matrix2, index2+1, k-1, result, digitIndex+1);
                            }else {
                                result.digits[digitIndex] = n1;
                                max(nums1, matrix1, index1+1, nums2, matrix2, b2, k-1, result, digitIndex+1);
                            }
                        }else {
                            if(j > 0) {
                                result.digits[digitIndex] = n2;
                                max(nums1, matrix1, b1, nums2, matrix2, index2+1, k-1, result, digitIndex+1);
                            }else {
                                int e1 = repeatEnd(nums1, indexes1, i+1, n1) - 1;
                                int e2 = repeatEnd(nums2, indexes2, j+1, n2) - 1;
                                IndexSet indexSet = selectMax(nums1, indexes1, i, e1, b1,
                                                              nums2, indexes2, j, e2, b2,
                                                              k);
                                int idx = digitIndex, cnt = indexSet.cnt;
                                for(int c=0; c < cnt; c++,idx++) {
                                    result.digits[idx] = n2;
                                }
                                for(IndexPair pair : indexSet.list) {
                                    if(pair.idx1 == -1) {
                                        index2 = indexes2[pair.idx2];
                                        if(nums1.length-b1 + nums2.length-index2-1 + cnt == k) {
                                            copy(nums1, b1, nums2, index2+1, result.digits, idx);
                                            result.compareAndSet();
                                        }else {
                                            max(nums1, matrix1, b1, nums2, matrix2, index2+1, k-cnt, result, idx);
                                        }
                                    }else if(pair.idx2 == -1) {
                                        index1 = indexes1[pair.idx1];
                                        if(nums1.length-index1-1 + nums2.length-b2 + cnt == k) {
                                            copy(nums1, index1+1, nums2, b2, result.digits, idx);
                                            result.compareAndSet();
                                        }else {
                                            max(nums1, matrix1, index1+1, nums2, matrix2, b2, k-cnt, result, idx);
                                        }
                                    }else {
                                        index1 = indexes1[pair.idx1];
                                        index2 = indexes2[pair.idx2];
                                        if(nums1.length-index1-1 + nums2.length-index2-1 + cnt == k) {
                                            copy(nums1, index1+1, nums2, index2+1, result.digits, idx);
                                            result.compareAndSet();
                                        }else {
                                            max(nums1, matrix1, index1+1, nums2, matrix2, index2+1, k-cnt, result, idx);
                                        }
                                    }
                                }
                            }
                        }
                    }else if(r2 == k) {
                        result.digits[digitIndex] = n1;
                        max(nums1, matrix1, index1+1, nums2, matrix2, b2, k-1, result, digitIndex+1);
                        copy(nums1, b1, nums2, index2, result.digits, digitIndex);
                        result.compareAndSet();
                    }else {
                        result.digits[digitIndex] = n1;
                        max(nums1, matrix1, index1+1, nums2, matrix2, b2, k-1, result, digitIndex+1);
                    }
                    return;
                }
            }
        }
        if(i == indexes1.length) {
            max(nums2, matrix2, indexes2[j], k, result, digitIndex);
        }else {
            max(nums1, matrix1, indexes1[i], k, result, digitIndex);
        }
    }

    private static int repeatEnd(int[] nums, int[] indexes, int begin, int t) {
        for(; begin < indexes.length; begin++) {
            if(nums[indexes[begin]] != t) {
                break;
            }
        }
        return begin;
    }

    private static IndexSet selectMax(int[] nums1, int[] indexes1, int b1, int e1, int begin1,
                                             int[] nums2, int[] indexes2, int b2, int e2, int begin2,
                                             int k) {
        int t1 = e1 - b1 + 1, t2 = e2 - b2 + 1;
        int cnt = Math.min(t1 + t2, k);
        List<IndexPair> list = new ArrayList<>();
        for(; cnt >= 1; cnt--) {
            if(cnt <= t1) {
                int i = b1 + cnt - 1;
                int r1 = (cnt - 1) + (nums1.length - indexes1[i]) + (nums2.length - begin2);
                if(r1 >= k) {
                    list.add(new IndexPair(i, -1));
                }
            }
            if(cnt <= t2) {
                int j = b2 + cnt - 1;
                int r2 = (cnt - 1) + (nums1.length - begin1) + (nums2.length - indexes2[j]);
                if(r2 >= k) {
                    list.add(new IndexPair(-1, j));
                }
            }
            int maxI = Math.min(e1, b1+cnt-1);
            int minI = Math.max(b1, b1+cnt-t2-1);
            for(int i=maxI, j=b2 + cnt-(i-b1+1) - 1; i>=minI; i--,j++) {
                if(j < b2) { continue; }
                int r = (cnt-2) + (nums1.length - indexes1[i]) + (nums2.length - indexes2[j]);
                if(r >= k) {
                    list.add(new IndexPair(i, j));
                }
            }
            if(!list.isEmpty()) { break; }
        }
        return new IndexSet(cnt, list);
    }

    private static class IndexSet {
        int cnt;
        List<IndexPair> list;
        IndexSet(int cnt, List<IndexPair> list) {
            this.cnt = cnt;
            this.list = list;
        }
    }

    private static class IndexPair {
        int idx1;
        int idx2;
        IndexPair(int idx1, int idx2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
        }
    }

    public static int[] maxNumber2(int[] nums1, int[] nums2, int k) {
        // 优先从单个nums里面选出相关元素，再进行merge，确保总数量等于K（且各自相对顺序保持不变）
        if(k == 0) {
            return new int[0];
        }else if(k == 1) {
            return maxOne(nums1, nums2);
        }else {
            if(nums1.length == 0) {
                return max1(nums2, k);
            }else if(nums2.length == 0) {
                return max1(nums1, k);
            }else {
                return max1(nums1, nums2, k);
            }
        }
    }

    private static int[] max1(int[] nums, int k) {
        if(k > nums.length) {
            return new int[0];
        }else if(k == nums.length) {
            return nums;
        }else {
            int[][] matrix = descMatrix(nums);
            int[] result = new int[k];
            selectMax(nums, matrix, 0, k, result, 0);
            return result;
        }
    }

    private static int[] max1(int[] nums1, int[] nums2, int k) {
        int len = nums1.length + nums2.length;
        if(k < len) {
            int maxCnt = Math.min(nums1.length, k);
            int minCnt = Math.max(0, k-nums2.length);
            int[][] matrix1 = descMatrix(nums1);
            int[][] matrix2 = descMatrix(nums2);
            int[] digits1 = new int[nums1.length];
            int[] digits2 = new int[nums2.length];
            int[] maxDigits = new int[k];
            for(int cnt1=minCnt, cnt2=k-cnt1; cnt1<=maxCnt; cnt1++, cnt2--) {
                selectMax(nums1, matrix1, 0, cnt1, digits1, 0);
                selectMax(nums2, matrix2,0, cnt2, digits2, 0);
                compareAndSet(digits1, cnt1, digits2, cnt2, maxDigits);
            }
            return maxDigits;
        }else if( k == len) {
            int[] digits = new int[k];
            copy(nums1, 0, nums2, 0, digits, 0);
            return digits;
        }else {
            return new int[0];
        }
    }

    private static void selectMax(int[] nums, int[][] matrix, int begin, int cnt, int[] result, int idx) {
        if(cnt == 0) {
            return;
        }else if(cnt == 1) {
            result[idx] = nums[matrix[begin][0]];
            return;
        }else if(cnt == nums.length-begin) {
            System.arraycopy(nums, begin, result, idx, cnt);
            return;
        }else {
            int[] indexes = matrix[begin];
            for(int index : indexes) {
                int reserved = nums.length - index;
                if(reserved > cnt) {
                    result[idx] = nums[index];
                    selectMax(nums, matrix, index+1, cnt-1, result, idx+1);
                    return;
                }else if(reserved == cnt) {
                    System.arraycopy(nums, index, result, idx, cnt);
                    return;
                }
            }
        }
    }

    private static void compareAndSet(int[] nums1, int cnt1, int[] nums2, int cnt2, int[] result) {
        int b1 = 0, b2 = 0, num = 0;
        boolean isLargerThanResult = false;
        for(int i=0; i<result.length; i++) {
            if(compare(nums1, b1, cnt1, nums2, b2, cnt2)) {
                num = nums1[b1++];
            }else {
                num = nums2[b2++];
            }
            if(isLargerThanResult) {
                result[i] = num;
            }else if(num > result[i]) {
                result[i] = num;
                isLargerThanResult = true;
            }else if(num < result[i]) {
                return;
            }   // '==' is continue
        }
    }

    private static boolean compare(int[] nums1, int b1, int cnt1, int[] nums2, int b2, int cnt2) {
        while(b1 < cnt1 && b2 < cnt2) {
            if(nums1[b1] == nums2[b2]) {
                b1++;
                b2++;
            }else {
                return nums1[b1] > nums2[b2];
            }
        }
        return b2 == cnt2;
    }
}
