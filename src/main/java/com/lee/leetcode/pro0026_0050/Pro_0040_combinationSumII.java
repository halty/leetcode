package com.lee.leetcode.pro0026_0050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 Given a collection of candidate numbers (candidates) and a target number (target),
 find all unique combinations in candidates where the candidate numbers sums to target.

 Each number in target may only be used once in the combination.

 Note:
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.

 Example 1:
 Input: candidates = [10,1,2,7,6,1,5], target = 8,
 A solution set is:
 [
 	[1, 7],
 	[1, 2, 5],
 	[2, 6],
 	[1, 1, 6]
 ]

 Example 2:
 Input: candidates = [2,5,2,1,2], target = 5,
 A solution set is:
 [
 	[1,2,2],
 	[5]
 ]
 * 
 */
public class Pro_0040_combinationSumII {

	public static void main(String[] args) {
		int[] candidates = {2,5,2,1,2};
		int target = 5;
		List<List<Integer>> result = combinationSum2(candidates, target);
		System.out.println(result);
	}

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		int first = candidates[0];
		if(target < first) { return Collections.emptyList(); }
		if(target == first) {
			return Arrays.asList(Arrays.asList(first));
		}
		Candidate ctx = calcDuplicate(candidates);
		List<List<Integer>> result = new ArrayList<>();
		combination(result, ctx, target, candidates.length);
		return result;
    }

	private static Candidate calcDuplicate(int[] candidates) {
		int len = candidates.length;
		int prev = 0, cur = 1, i = 0;
		int[] counts = new int[len];
		while(cur < len) {
			if(candidates[cur] == candidates[prev]) {
				cur++;
			}else {
				counts[i] = cur - prev;
				candidates[i++] = candidates[prev];
				prev = cur;
				cur++;
			}
		}
		counts[i] = cur - prev;
		candidates[i++] = candidates[prev];
		if(i < len) {
			candidates = Arrays.copyOf(candidates, i);
			counts = Arrays.copyOf(counts, i);
		}
		Candidate ctx = new Candidate();
		ctx.elements = candidates;
		ctx.counts = counts;
		ctx.size = i;
		return ctx;
	}

	private static class Candidate {
		int size;
		int[] elements;
		int[] counts;
	}

	private static void combination(List<List<Integer>> result, Candidate ctx, int target, int maxIdxCount) {
		int[] elements = ctx.elements;
		int[] counts = ctx.counts;
		int[] currentCounts = new int[ctx.size];
		int[] indexes = new int[maxIdxCount];
		int end = ctx.size - 1;
		int index = 0;	// elements index
		int idxEnd = maxIdxCount - 1;
		int idx = 0;	// indexes index
		int remain = target - ctx.elements[index];
		indexes[idx] = index;
		currentCounts[index] = 1;
		while(true) {
			if(remain > 0) {
				if(idx < idxEnd) {
					int currentCount = currentCounts[index];
					if(currentCount < counts[index]) {
						currentCounts[index] = currentCount + 1;
						remain -= elements[index];
						++idx;
						indexes[idx] = index;
						continue;
					}else {
						if(index < end) {
							++index;
							remain -= elements[index];
							currentCounts[index] += 1;
							++idx;
							indexes[idx] = index;
							continue;
						}
					}
				}
			}
			if(remain == 0) {
				result.add(convert(elements, indexes, idx));
			}
			remain += elements[index];
			currentCounts[index] -= 1;
			do {
				if(idx == 0) { return; }
				--idx;
				index = indexes[idx];
				remain += elements[index];
				currentCounts[index] -= 1;
			}while(index == end);
			++index;
			remain -= elements[index];
			currentCounts[index] += 1;
			indexes[idx] = index;
		}
	}

	private static List<Integer> convert(int[] elements, int[] indexes, int idx) {
		List<Integer> list = new ArrayList<>(indexes.length);
		for(int i=0; i<=idx; i++) {
			list.add(elements[indexes[i]]);
		}
		return list;
	}
}
