package com.lee.leetcode.pro0126_0150;

import java.util.*;

/**
 *
 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
    1. Only one letter can be changed at a time.
    2. Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 Note:
    1. Return 0 if there is no such transformation sequence.
    2. All words have the same length.
    3. All words contain only lowercase alphabetic characters.
    4. You may assume no duplicates in the word list.
    5. You may assume beginWord and endWord are non-empty and are not the same.

 Example 1:
 Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
 Output: 5
 Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.

 Example 2:
 Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
 Output: 0
 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 */
public class Pro_0127_WordLadder {

    public static void main(String[] args) {
        String beginWord = "a";
        String endWord = "c";
        String[] array = {"a","b","c"};
        List<String> wordList = asArrayList(array);
        int length = ladderLength(beginWord, endWord, wordList);
        System.out.println(length);
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList.isEmpty()) { return 0; }
        int wordLength = beginWord.length();
        Set<String> candidateSet = new HashSet<>(wordList);
        if(!candidateSet.contains(endWord)) { return 0; }
        StringBuilder wordBuf = new StringBuilder(wordLength);
        Set<String> level0 = new HashSet<>();
        appendToNextLevel(wordLength, wordBuf, beginWord, endWord, candidateSet, level0);
        if(level0.isEmpty()) { return 0; }
        int levelCount = 2;
        if(level0.contains(endWord)) { return levelCount; }
        Set<String> currentLevel = level0;
        boolean canReach = false;
        while(!candidateSet.isEmpty()) {
            Set<String> nextLevel = new HashSet<>();
            if(generateNextLevel(wordLength, wordBuf, endWord, currentLevel, candidateSet, nextLevel)) {
                levelCount++;
                canReach = true;
                break;
            }else {
                if(nextLevel.isEmpty()) { return 0; }
                levelCount++;
                currentLevel = nextLevel;
            }
        }
        return canReach ? levelCount : 0;
    }

    private static boolean appendToNextLevel(int wordLength, StringBuilder wordBuf, String word, String endWord,
                                             Set<String> candidateSet, Set<String> nextLevel) {
        if(candidateSet.size() > 26) {  // lowercase alphabetic traversal
            wordBuf.setLength(0);
            wordBuf.append(word);
            for(int i = 0; i < wordLength; i++) {
                char ch = wordBuf.charAt(i);
                for(char c = 'a'; c < 'z'; c++) {
                    if (c != ch) {
                        wordBuf.setCharAt(i, c);
                        String w = wordBuf.toString();
                        if (candidateSet.remove(w)) {
                            nextLevel.add(w);
                            if (w.equals(endWord)) {
                                return true;
                            }
                        }
                    }
                }
                wordBuf.setCharAt(i, ch);
            }
        }else {
            for(Iterator<String> it=candidateSet.iterator(); it.hasNext();) {
                String w = it.next();
                if(oneDiff(word, w, wordLength)) {
                    it.remove();
                    nextLevel.add(w);
                    if(w.equals(endWord)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean generateNextLevel(int wordLength, StringBuilder wordBuf, String endWord,
                                             Set<String> currentLevel, Set<String> candidateSet, Set<String> nextLevel) {
        for(String word : currentLevel) {
            if(appendToNextLevel(wordLength, wordBuf, word, endWord, candidateSet, nextLevel)) {
                return true;
            }
        }
        return false;
    }

    private static boolean oneDiff(String one, String another, int wordLength) {
        boolean exactOne = false;
        for(int i=0; i<wordLength; i++) {
            if(one.charAt(i) == another.charAt(i)) { continue; }
            if(exactOne) {
                return false;
            }
            exactOne = true;
        }
        return exactOne;
    }

    private static List<String> asArrayList(String[] array) {
        List<String> list = new ArrayList<>(array.length);
        for(String e : array) {
            list.add(e);
        }
        return list;
    }
}
