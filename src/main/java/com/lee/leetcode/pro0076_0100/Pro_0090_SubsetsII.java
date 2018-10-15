package com.lee.leetcode.pro0076_0100;

import java.util.*;

/*
 *
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 *
 */
public class Pro_0090_SubsetsII {

    public static void main(String[] args) {
        int[] nums = {2,1,2,1,3};
        List<List<Integer>> result = subsetsWithDup(nums);
        for(List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            List<List<Integer>> result = new ArrayList<>(1);
            result.add(Collections.<Integer>emptyList());
            return result;
        }
        if(n == 1) {
            List<List<Integer>> result = new ArrayList<>(2);
            result.add(Collections.<Integer>emptyList());
            result.add(Arrays.asList(nums[0]));
            return result;
        }

        Arrays.sort(nums);
        int length = (int)Math.pow(2, n);
        List<List<Integer>> result = new ArrayList<>(length);
        result.add(Collections.<Integer>emptyList());
        int[] index = new int[n];
        int cnt = 1;
        for(int k=1; k<=n; k++) {
            cnt = cnt * (n+1-k) / k;
            addCombineSubset(nums, n, k, index, result, cnt);
        }
        return result;
    }

    private static void addCombineSubset(int[] nums, int n, int k, int[] index, List<List<Integer>> result, int cnt) {
        if(k == 1) {
            result.add(Arrays.asList(nums[0]));
            int prev = nums[0];
            for(int i=1; i<n; i++) {
                if(nums[i] != prev) {
                    prev = nums[i];
                    result.add(Arrays.asList(nums[i]));
                }
            }
            return;
        }
        if(k == n-1) {
            result.add(asList(nums, 0, k));
            int prev = nums[k];
            for(int i=k-1; i>=0; i--) {
                if(prev != nums[i]) {
                    prev = nums[i];
                    swap(nums, i, k);
                    result.add(asList(nums, 0, k));
                }
            }
            return;
        }
        if(k == n) {
            result.add(asList(nums, 0, n));
            return;
        }
        for(int i=0; i<k; i++) {
            index[i] = i+1;
        }

        int maxHead = n + 1 - k;
        int end = k - 1;
        int tail = k - 1;
        Set<List<Integer>> set = new HashSet<>(cnt);
        List<Integer> list = asList(nums, index, k);
        result.add(list);
        set.add(list);
        while(true) {
            if(tail == end) {
                list = asList(nums, index, k);
                if(!set.contains(list)) {
                    result.add(list);
                    set.add(list);
                }
                int prev = nums[index[end]-1];
                index[end] += 1;
                while(index[end] <= n) {
                    int current = nums[index[end]-1];
                    if(current != prev) {
                        prev = current;
                        list = asList(nums, index, k);
                        if(!set.contains(list)) {
                            result.add(list);
                            set.add(list);
                        }
                    }
                    index[end] += 1;
                }
                tail = moveBack(index, k, maxHead);
                if(tail == -1) { break; }
            }else { // move ahead
                index[tail+1] = index[tail] + 1;
                tail++;
            }
        }
    }

    private static List<Integer> asList(int[] nums, int begin, int end) {
        List<Integer> list = new ArrayList<>(end - begin);
        for(int i=begin; i<end; i++) {
            list.add(nums[i]);
        }
        return list;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static List<Integer> asList(int[] nums, int[] index, int len) {
        List<Integer> list = new ArrayList<>(len);
        for(int i=0; i<len; i++) {
            list.add(nums[index[i]-1]);
        }
        return list;
    }

    private static boolean equals(List<Integer> a, List<Integer> b) {
        int sa = a.size(), sb = b.size();
        if(sa != sb) { return false; }
        for(int i=sa-1; i>=0; i--) {
            if(a.get(i) != b.get(i)) {
                return false;
            }
        }
        return true;
    }

    private static int moveBack(int[] index, int len, int maxHead) {
        for(int i=len-2; i>=0; i--) {
            int v = index[i] + 1;
            if(v <= maxHead+i) {
                index[i] = v;
                return i;
            }
        }
        return -1;
    }
}
