package com.lee.leetcode.pro0076_0100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 *
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 *
 */
public class Pro_0077_Combinations {

    public static void main(String[] args) {
        int n = 4, k = 2;
        List<List<Integer>> result = combine(n, k);
        print(result);
    }

    public static List<List<Integer>> combine(int n, int k) {
        if(n <= 0 || k <= 0 || n < k) {
            return Collections.emptyList();
        }
        if(k == 1) {
            List<List<Integer>> result = new ArrayList<>(n);
            for(int i=1; i<=n; i++) {
                result.add(Arrays.asList(i));
            }
            return result;
        }
        if(k == n) {
            List<Integer> list = new ArrayList<>(n);
            for(int i=1; i<=n; i++) {
                list.add(i);
            }
            return Arrays.asList(list);
        }
        int[] array = new int[k];
        long a = 1;
        long b = 1;
        for(int i=0; i<k; i++) {
            array[i] = i+1;
            a *= n-i;
            b *= i+1;
        }
        int size = (int)(a/b);
        List<List<Integer>> result = new ArrayList<>(size);
        int maxHead = n + 1 - k;
        int end = k - 1;
        int tail = k - 1;
        while(true) {
            if(tail == end) {
                while(array[end] <= n) {
                    result.add(asList(array));
                    array[end] += 1;
                }
                tail = moveBack(array, maxHead);
                if(tail == -1) {
                    break;
                }
            }else { // move ahead
                array[tail+1] = array[tail] + 1;
                tail++;
            }
        }
        return result;
    }

    private static List<Integer> asList(int[] array) {
        List<Integer> list = new ArrayList<>(array.length);
        for(int v : array) {
            list.add(v);
        }
        return list;
    }

    private static int moveBack(int[] array, int maxHead) {
        int k = array.length;
        for(int i=k-2; i>=0; i--) {
            int v = array[i] + 1;
            if(v <= maxHead+i) {
                array[i] = v;
                return i;
            }
        }
        return -1;
    }

    private static void print(List<List<Integer>> result) {
        for(List<Integer> list : result) {
            System.out.println(list);
        }
    }
}
