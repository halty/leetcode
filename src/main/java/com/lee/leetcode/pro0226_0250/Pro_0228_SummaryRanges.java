package com.lee.leetcode.pro0226_0250;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 Given a sorted integer array without duplicates, return the summary of its ranges.

 Example 1:
 Input:  [0,1,2,4,5,7]
 Output: ["0->2","4->5","7"]
 Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.

 Example 2:
 Input:  [0,2,3,4,6,8,9]
 Output: ["0","2->4","6","8->9"]
 Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 *
 */
public class Pro_0228_SummaryRanges {

    public static void main(String[] args) {
        int[] nums = {0,1,2,4,5,7};
//        List<String> list = summaryRanges(nums);
        List<String> list = summaryRanges1(nums);
        System.out.println(list);
    }

    public static List<String> summaryRanges(int[] nums) {
        if(nums.length == 0) { return Collections.emptyList(); }
        List<String> list = new ArrayList<>();
        int from = 0, to = 1;
        for(; to < nums.length; to++) {
            if(nums[to] > nums[to-1] + 1) {
                list.add(summary(nums, from, to));
                from = to;
            }
        }
        list.add(summary(nums, from, to));
        return list;
    }

    private static String summary(int[] nums, int from, int to) {
        if(from == to-1) {
            return String.valueOf(nums[from]);
        }else {
            String f = String.valueOf(nums[from]);
            String t = String.valueOf(nums[to-1]);
            StringBuilder buf = new StringBuilder(f.length()+t.length()+2);
            return buf.append(f).append("->").append(t).toString();
        }
    }

    public static List<String> summaryRanges1(int[] nums) {
        if(nums.length == 0) { return Collections.emptyList(); }
        if(nums.length == 1) {
            return Arrays.asList(String.valueOf(nums[0]));
        }else if(nums[0]+nums.length-1 == nums[nums.length-1]) {
            return Arrays.asList(summary(nums, 0, nums.length));
        }else {
            List<Interval> list = new ArrayList<>();
            Range r = summaryRanges(nums, 0, nums.length - 1, list);
            list.add(new Interval(r.lb, r.le));
            list.add(new Interval(r.rb, r.re));
            Collections.sort(list);
            return summary(nums, list);
        }
    }

    private static Range summaryRanges(int[] nums, int b, int e, List<Interval> result) {
        int m = b + (e-b)/2;
        if(nums[b]+m-b == nums[m]) {
            if(nums[m+1]+e-m-1 == nums[e]) {
                if(nums[m]+1 == nums[m+1]) {
                    return new Range(b, e, b, e);
                }else {
                    return new Range(b, m, m+1, e);
                }
            }else {
                Range right = summaryRanges(nums, m+1, e, result);
                if(nums[m]+1 != nums[m+1]) {
                    result.add(new Interval(right.lb, right.le));
                    right.le = m;
                }
                right.lb = b;
                return right;
            }
        }else {
            Range left = summaryRanges(nums, b, m, result);
            if(nums[m+1]+e-m-1 == nums[e]) {
                if(nums[m]+1 != nums[m+1]) {
                    result.add(new Interval(left.rb, left.re));
                    left.rb = m + 1;
                }
                left.re = e;
                return left;
            }else {
                Range right = summaryRanges(nums, m+1, e, result);
                if(nums[m]+1 == nums[m+1]) {
                    result.add(new Interval(left.rb, right.le));
                }else {
                    result.add(new Interval(left.rb, left.re));
                    result.add(new Interval(right.lb, right.le));
                }
                left.rb = right.rb;
                left.re = right.re;
                return left;
            }
        }
    }

    private static List<String> summary(int[] nums, List<Interval> list) {
        List<String> result = new ArrayList<>(list.size());
        for(Interval i : list) {
            result.add(summary(nums, i.b, i.e+1));
        }
        return result;
    }

    private static class Range {
        int lb; // left range begin index
        int le; // left range end index
        int rb; // right range begin index
        int re; // right range end index
        Range(int lb, int le, int rb, int re) {
            this.lb = lb;
            this.le = le;
            this.rb = rb;
            this.re = re;
        }
    }

    private static class Interval implements Comparable<Interval> {
        int b;
        int e;
        Interval(int b, int e) {
            this.b = b;
            this.e = e;
        }
        @Override
        public int compareTo(Interval other) {
            if(this.b < other.b) {
                return -1;
            }else if(this.b > other.b) {
                return 1;
            }else {
                if(this.e < other.e) {
                    return -1;
                }else if(this.e > other.e) {
                    return 1;
                }else {
                    return 0;
                }
            }
        }
    }
}
