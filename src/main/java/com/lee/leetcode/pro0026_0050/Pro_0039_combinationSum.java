package com.lee.leetcode.pro0026_0050;

import java.util.*;

/**
 *
 Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 find all unique combinations in candidates where the candidate numbers sums to target.
 The same repeated number may be chosen from candidates unlimited number of times.

 Note:
 	All numbers (including target) will be positive integers.
 	The solution set must not contain duplicate combinations.

 Example 1:
 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 	[7],
 	[2,2,3]
 ]

 Example 2:
 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 	[2,2,2,2],
 	[2,3,3],
 	[3,5]
 ]
 * 
 */
public class Pro_0039_combinationSum {

	public static void main(String[] args) {
		int[] candidates = {2,3,6,7};
		int target = 9;
//		List<List<Integer>> result = combinationSum(candidates, target);
		List<List<Integer>> result = combinationSum1(candidates, target);
		System.out.println(result);
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		// candidates = removeDuplicate(candidates);
		int end = candidates[candidates.length-1];
		int minCount = (target+end-1) / end;
		int maxCount = target / candidates[0];
		List<List<Integer>> result = new ArrayList<>();
		for(int i=minCount; i<=maxCount; i++) {
			combination(result, candidates, target, i);
		}
        return result;
    }

    private static int[] removeDuplicate(int[] candidates) {
		int prev = 0, cur = 1, len = candidates.length;
		while(cur < len) {
			if(candidates[cur] == candidates[prev]) {
				cur++;
			}else {
				if(cur == prev+1) {
					prev++;
					cur++;
				}else {
					candidates[++prev] = candidates[cur];
					cur++;
				}
			}
		}
		if(prev == len-1) {
			return candidates;
		}else {
			return Arrays.copyOf(candidates, prev+1);
		}
	}

	private static void combination(List<List<Integer>> result, int[] candidates, int target, int count) {
		int[] indexes = new int[count];
		int end = candidates.length - 1;
		int idxEnd = count - 1;
		int remain = target - candidates[0];
		int idx = 0;
		indexes[idx] = 0;
		while(true) {
			if(remain <= 0) {
				if(remain == 0 && idx == idxEnd) {
					result.add(convert(candidates, indexes));
				}
				int index = indexes[idx];
				remain += candidates[index];
				do {
					if(idx == 0) { return; }
					--idx;
					index = indexes[idx];
					remain += candidates[index];
				}while(index == end);
				++index;
				remain -= candidates[index];
				indexes[idx] = index;
			}else {
				int index = indexes[idx];
				if(idx == idxEnd) {
					if(index == end) {
						remain += candidates[index];
						do {
							if(idx == 0) { return; }
							--idx;
							index = indexes[idx];
							remain += candidates[index];
						}while(index == end);
						++index;
						remain -= candidates[index];
						indexes[idx] = index;
					}else {
						remain += candidates[index];
						++index;
						remain -= candidates[index];
						indexes[idx] = index;
					}
				}else {
					remain -= candidates[index];
					++idx;
					indexes[idx] = index;
				}
			}
		}
	}

	private static List<Integer> convert(int[] candidates, int[] indexes) {
		List<Integer> list = new ArrayList<>(indexes.length);
		for(int index : indexes) {
			list.add(candidates[index]);
		}
		return list;
	}

	public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
		Context ctx = initContext(candidates);
		List<List<Integer>> result = new ArrayList<>();
		List<List<Integer>> combinations = ctx.mapping.get(target);
		if(combinations != null) { result.addAll(combinations); }
		while(target > ctx.min) {
			int newMin = Integer.MAX_VALUE;
			Map<Integer, List<List<Integer>>> newMapping = new HashMap<>(ctx.mapping.size()*(candidates.length-1));
			for(Map.Entry<Integer, List<List<Integer>>> pair : ctx.mapping.entrySet()) {
				Integer sum = pair.getKey();
				combinations = pair.getValue();
				for(int num : candidates) {
					Integer newSum = sum + num;
					for(List<Integer> sub : combinations) {
						int end = sub.get(sub.size()-1);
						if(num >= end) {
							List<Integer> newSub = append(sub, num);
							addCombination(newMapping, newSum, newSub);
							if(newSum < newMin) { newMin = newSum; }
						}
					}
				}
			}
			combinations = newMapping.get(target);
			if(combinations != null) { result.addAll(combinations); }
			ctx.min = newMin;
			ctx.mapping = newMapping;
		}
		return result;
	}

	private static Context initContext(int[] candidates) {
		Context ctx = new Context();
		int min = Integer.MAX_VALUE;
		Map<Integer, List<List<Integer>>> mapping = new HashMap<>(candidates.length);
		for(int num : candidates) {
			mapping.put(num, Arrays.asList(Arrays.asList(num)));
			if(num < min) { min = num; }
		}
		ctx.min = min;
		ctx.mapping = mapping;
		return ctx;
	}

	private static List<Integer> append(List<Integer> list, Integer num) {
		List<Integer> newList = new ArrayList<>(list.size()+1);
		newList.addAll(list);
		newList.add(num);
		return newList;
	}

	private static void addCombination(Map<Integer, List<List<Integer>>> mapping, Integer sum, List<Integer> combination) {
		List<List<Integer>> combinations = mapping.get(sum);
		if(combinations == null) {
			combinations = new ArrayList<>();
			mapping.put(sum, combinations);
		}
		combinations.add(combination);
	}

	private static class Context {
		int min;
		Map<Integer, List<List<Integer>>> mapping;
	}
}
