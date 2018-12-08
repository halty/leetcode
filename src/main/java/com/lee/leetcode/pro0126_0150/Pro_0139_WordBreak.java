package com.lee.leetcode.pro0126_0150;

import java.util.*;

/**
 *
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 Note:
    1. The same word in the dictionary may be reused multiple times in the segmentation.
    2. You may assume the dictionary does not contain duplicate words.

 Example 1:
 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".

 Example 2:
 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.

 Example 3:
 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false
 *
 */
public class Pro_0139_WordBreak {

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet","code");
        boolean result = wordBreak(s, wordDict);
        System.out.println(result);
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
//        Map<Integer, EndPositions> segmentMap = prepare(s, wordDict);
        Map<Integer, EndPositions> segmentMap = prepare1(s, wordDict);
//        return depthTraversal(s, 0, segmentMap);
//        return breadthTraversal(s, segmentMap);
        return greedyTraversal(s, segmentMap);
    }

    private static boolean depthTraversal(String s, Integer begin, Map<Integer, EndPositions> segmentMap) {
        EndPositions eps = segmentMap.get(begin);
        if(eps == null) { return false; }
        for(int i=0; i<eps.count; i++) {
            Integer ep = eps.endPositions[i];
            if(ep == s.length() || depthTraversal(s, ep, segmentMap)) {
                return true;
            }
        }
        return false;
    }

    private static boolean breadthTraversal(String s, Map<Integer, EndPositions> segmentMap) {
        boolean[] reach = new boolean[s.length()+1];
        Queue<Integer> q = new ArrayDeque<>(s.length()+1);
        q.offer(0);
        Integer i = null;
        while((i = q.poll()) != null) {
            reach[i] = true;
            EndPositions eps = segmentMap.get(i);
            if(eps != null) {
                for(int j=0; j<eps.count; j++) {
                    int k = eps.endPositions[j];
                    if(!reach[k]) {
                        q.add(k);
                    }
                }
            }
        }
        return reach[s.length()];
    }

    private static boolean greedyTraversal(String s, Map<Integer, EndPositions> segmentMap) {
        int len = s.length();
        boolean[] reach = new boolean[len+1];
        reach[0] = true;
        for(int i=0; i<len; i++) {
            if(reach[i]) {
                EndPositions eps = segmentMap.get(i);
                if(eps != null) {
                    for(int j=0; j<eps.count; j++) {
                        reach[eps.endPositions[j]] = true;
                    }
                }
            }
        }
        return reach[len];
    }

    // sequence match
    private static Map<Integer, EndPositions> prepare(String s, List<String> wordDict) {
        Map<Integer, EndPositions> segmentMap = new HashMap<>();
        int sLen = s.length();
        for(String w : wordDict) {
            int wLen = w.length();
            int end = sLen - wLen;
            for(int i=0; i<=end; i++) {
                int j = 0;
                for(int b=i; j<wLen && s.charAt(b) == w.charAt(j); b++,j++);
                if(j == wLen) {
                    addSegment(i, wLen, segmentMap);
                }
            }
        }
        return segmentMap;
    }

    // kmp match
    private static Map<Integer, EndPositions> prepare1(String s, List<String> wordDict) {
        Map<Integer, EndPositions> segmentMap = new HashMap<>();
        int sLen = s.length();
        for(String w : wordDict) {
            int wLen = w.length();
            if(wLen > 2) {
                int[] prefix = prefixTable(w);
                int i = 0, j = 0;
                while(i < sLen) {
                    if(s.charAt(i) == w.charAt(j)) {
                        i++;
                        j++;
                        if(j == wLen) {
                            addSegment(i-wLen, wLen, segmentMap);
                            j = prefix[j];
                        }
                    }else {
                        j = prefix[j];
                        if(j < 0) {
                            j = 0;
                            i++;
                        }
                    }
                }
            }else if(wLen == 2) {
                int end = sLen - wLen;
                for(int i=0; i<=end; i++) {
                    if(s.charAt(i) == w.charAt(0) && s.charAt(i+1) == w.charAt(1)) {
                        addSegment(i, wLen, segmentMap);
                    }
                }
            }else { // wLen == 1
                int end = sLen - wLen;
                for(int i=0; i<=end; i++) {
                    if(s.charAt(i) == w.charAt(0)) {
                        addSegment(i, wLen, segmentMap);
                    }
                }
            }
        }
        return segmentMap;
    }

    private static int[] prefixTable(String w) {
        int wLen = w.length();
        int[] prefix = new int[wLen+1];
        prefix[0] = -1;
        prefix[1] = 0;
        for(int i=2; i<=wLen; i++) {
            int k = i - 1;
            for(char ch = w.charAt(k); (k=prefix[k]) >= 0 &&  w.charAt(k) != ch;);
            prefix[i] = k + 1;
        }
        return prefix;
    }

    private static void addSegment(int begin, int wLen, Map<Integer, EndPositions> segmentMap) {
        EndPositions eps = segmentMap.get(begin);
        if(eps == null) {
            eps = new EndPositions(begin+wLen);
            segmentMap.put(begin, eps);
        }else {
            eps.add(begin+wLen);
        }
    }

    private static class EndPositions {
        int[] endPositions;
        int count;

        EndPositions(int endPosition) {
            endPositions = new int[2];
            endPositions[0] = endPosition;
            count = 1;
        }

        void add(int endPosition) {
            if(count == endPositions.length) {
                endPositions = Arrays.copyOf(endPositions, count+3);
            }
            endPositions[count] = endPosition;
            count += 1;
        }
    }
}
