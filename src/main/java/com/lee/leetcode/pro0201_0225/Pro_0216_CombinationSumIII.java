package com.lee.leetcode.pro0201_0225;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 Find all possible combinations of k numbers that add up to a number n,
 given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

 Note:
    All numbers will be positive integers.
    The solution set must not contain duplicate combinations.

 Example 1:
    Input: k = 3, n = 7
    Output: [
              [1,2,4]
            ]

 Example 2:
    Input: k = 3, n = 9
    Output: [
              [1,2,6],
              [1,3,5],
              [2,3,4]
            ]
 *
 */
public class Pro_0216_CombinationSumIII {

    public static void main(String[] args) {
        int k = 5;
        int n = 16;
        List<List<Integer>> result = combinationSum3(k, n);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        if(k < 1 || k > 9) { return Collections.emptyList(); }
        int minSum = (1+k)*k/2;
        int maxSum = (9-k+1+9)*k/2;
        if(n < minSum || n > maxSum) { return Collections.emptyList(); }
        if(n == minSum) {
            List<Integer> combination = new ArrayList<>(k);
            for(int i=1; i<=k; i++) {
                combination.add(i);
            }
            return Arrays.asList(combination);
        }
        if(n == maxSum) {
            List<Integer> combination = new ArrayList<>(k);
            for(int i=9-k+1; i<=9; i++) {
                combination.add(i);
            }
            return Arrays.asList(combination);
        }

        List<List<Integer>> result = new ArrayList<>();
        int[] candidate = new int[k];
        int s = 1;
        int index = 0;
        candidate[0] = 1;
        traversal(result, n, candidate, s, index);
//        traversalRecursively(result, n, candidate, s, index);
        return result;
    }

    private static void traversal(List<List<Integer>> result, int n, int[] candidate, int s, int index) {
        Context ctx = new Context(candidate, s, index);
        while(true) {
            int next = ctx.candidate[ctx.index] + 1;
            int count = ctx.candidate.length - ctx.index - 1;
            int maxNext = 10 - count;
            if(next > maxNext) {
                if(continueTraversal(ctx)) {
                    continue;
                }else {
                    return;
                }
            }
            int minSum = ctx.s + (next+next+count-1)*count/2;
            if(n < minSum) {
                if(continueTraversal(ctx)) {
                    continue;
                }else {
                    return;
                }
            }
            int maxSum = ctx.s - ctx.candidate[ctx.index] + (maxNext-1) + (maxNext+9)*count/2;
            if(n > maxSum) {
                if(continueTraversal(ctx)) {
                    continue;
                }else {
                    return;
                }
            }
            if(n == minSum) {
                result.add(collect(ctx.candidate, ctx.index+1, next));
                if(continueTraversal(ctx)) {
                    continue;
                }else {
                    return;
                }
            }
            if(n == maxSum) {
                result.add(collect(ctx.candidate, ctx.index, maxNext-1));
                if(continueTraversal(ctx)) {
                    continue;
                }else {
                    return;
                }
            }
            if(count == 1) {
                collect(result, n, ctx.candidate, ctx.s, ctx.index);
                if(continueTraversal(ctx)) {
                    continue;
                }else {
                    return;
                }
            }
            ++ctx.index;
            ctx.candidate[ctx.index] = next;
            ctx.s += next;
        }
    }

    private static class Context {
        int[] candidate;
        int s;
        int index;
        Context(int[] candidate, int s, int index) {
            this.candidate = candidate;
            this.s = s;
            this.index = index;
        }
    }

    private static boolean continueTraversal(Context ctx) {
        if(ctx.index == 0) { return false; }
        ctx.s -= ctx.candidate[ctx.index];
        --ctx.index;
        ctx.candidate[ctx.index] += 1;
        ctx.s += 1;
        return true;
    }

    private static void traversalRecursively(List<List<Integer>> result, int n, int[] candidate, int s, int index) {
        int next = candidate[index] + 1;
        int count = candidate.length - index - 1;
        int maxNext = 10 - count;
        if(next > maxNext) {
            fallback(result, n, candidate, s, index);
            return;
        }
        int minSum = s + (next+next+count-1)*count/2;
        if(n < minSum) {
            fallback(result, n, candidate, s, index);
            return;
        }
        int maxSum = s - candidate[index] + (maxNext-1) + (maxNext+9)*count/2;
        if(n > maxSum) {
            fallback(result, n, candidate, s, index);
            return;
        }
        if(n == minSum) {
            result.add(collect(candidate, index+1, next));
            fallback(result, n, candidate, s, index);
            return;
        }
        if(n == maxSum) {
            result.add(collect(candidate, index, maxNext-1));
            fallback(result, n, candidate, s, index);
            return;
        }
        if(count == 1) {
            collect(result, n, candidate, s, index);
            fallback(result, n, candidate, s, index);
            return;
        }
        forward(result, n, candidate, s, index);
    }

    private static void fallback(List<List<Integer>> result, int n, int[] candidate, int s, int index) {
        if(index == 0) { return; }
        s -= candidate[index];
        --index;
        candidate[index] += 1;
        s += 1;
        traversalRecursively(result, n, candidate, s, index);
    }

    private static List<Integer> collect(int[] candidate, int index, int next) {
        List<Integer> list = new ArrayList<>(candidate.length);
        int i = 0;
        while(i < index) {
            list.add(candidate[i]);
            i++;
        }
        while(i < candidate.length) {
            list.add(next);
            next++;
            i++;
        }
        return list;
    }

    private static void collect(List<List<Integer>> result, int n, int[] candidate, int s, int index) {
        int sum = n - (s-candidate[index]);
        for(int a=candidate[index]; a<9; a++) {
            int b = sum - a;
            if(b <= 9 && b > a) {
                List<Integer> list = new ArrayList<>(candidate.length);
                int i = 0;
                while(i < index) {
                    list.add(candidate[i]);
                    i++;
                }
                list.add(a);
                list.add(b);
                result.add(list);
            }
        }
    }

    private static void forward(List<List<Integer>> result, int n, int[] candidate, int s, int index) {
        int next = candidate[index] + 1;
        ++index;
        candidate[index] = next;
        s += next;
        traversalRecursively(result, n, candidate, s, index);
    }
}
