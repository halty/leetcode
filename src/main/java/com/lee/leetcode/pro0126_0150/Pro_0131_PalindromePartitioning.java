package com.lee.leetcode.pro0126_0150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 Given a string s, partition s such that every substring of the partition is a palindrome.
 Return all possible palindrome partitioning of s.

 Example:
 Input: "aab"
 Output:
 [
    ["aa","b"],
    ["a","a","b"]
 ]
 *
 */
public class Pro_0131_PalindromePartitioning {

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> result = partition(s);
        for(List<String> list : result) {
            System.out.println(list);
        }
    }

    public static List<List<String>> partition(String s) {
        int len = s.length();
        switch(len) {
            case 0: return Collections.emptyList();
            case 1: return Arrays.asList(Arrays.asList(s));
            case 2:
                List<List<String>> resultList = new ArrayList<>(2);
                char a = s.charAt(0), b = s.charAt(1);
                resultList.add(Arrays.asList(String.valueOf(a), String.valueOf(b)));
                if(a == b) { resultList.add(Arrays.asList(s)); }
                return resultList;
            default:
                return doParition(s, len);
        }
    }

    private static List<List<String>> doParition(String s, int len) {
        boolean[][] state = new boolean[len][len];
        List<String>[] array = new List[len];
        array[0] = Arrays.asList(String.valueOf(s.charAt(0)));
        for(int j=1; j<len; j++) {
            List<String> list = new ArrayList<>();
            char ch = s.charAt(j);
            int k = j - 1;
            int end = j + 1;
            for(int i=0; i<j; i++) {
                boolean isPalindrome = s.charAt(i)==ch && (i+1 >= k || state[i+1][k]);    // substring[i,j] is Palindrome or not
                state[i][j] = isPalindrome;
                if(isPalindrome) { list.add(s.substring(i,end)); }
            }
            array[j] = list;    // all palindrome sub string end with j
        }
        List<List<String>>[] partitions = new List[len];
        partitions[0] = Arrays.asList(array[0]);
        for(int i=1; i<len; i++) {
            List<List<String>> target = new ArrayList<>(partitions[i-1].size());
            append(partitions[i-1], String.valueOf(s.charAt(i)), target);
            for(String str : array[i]) {
                int k = i - str.length();
                if(k >= 0) {
                    append(partitions[k], str, target);
                }else {
                    target.add(Arrays.asList(str));
                }
            }
            partitions[i] = target;
        }
        return partitions[len-1];
    }

    private static void append(List<List<String>> source, String s, List<List<String>> target) {
        for(List<String> strList : source) {
            List<String> retList = new ArrayList<>(strList);
            retList.add(s);
            target.add(retList);
        }
    }
}
