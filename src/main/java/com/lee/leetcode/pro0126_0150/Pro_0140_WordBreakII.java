package com.lee.leetcode.pro0126_0150;

import java.util.*;

/**
 *
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 Note:
    1. The same word in the dictionary may be reused multiple times in the segmentation.
    2. You may assume the dictionary does not contain duplicate words.

 Example 1:
 Input:
 s = "catsanddog"
 wordDict = ["cat", "cats", "and", "sand", "dog"]
 Output:
 [
    "cats and dog",
    "cat sand dog"
 ]

 Example 2:
 Input:
 s = "pineapplepenapple"
 wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 Output:
 [
    "pine apple pen apple",
    "pineapple pen apple",
    "pine applepen apple"
 ]
 Explanation: Note that you are allowed to reuse a dictionary word.

 Example 3:
 Input:
 s = "catsandog"
 wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output:
 []
 *
 */
public class Pro_0140_WordBreakII {

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
//        List<String> list = wordBreak(s, wordDict);
        List<String> list = wordBreak1(s, wordDict);
        System.out.println(list);
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        int sLen = s.length();
        BeginPositions[] sources = new BeginPositions[sLen+1];
        append(s, 0, wordDict, sources);
        for(int i=1; i<sLen; i++) {
            if(sources[i] != null) {
                append(s, i, wordDict, sources);
            }
        }
        if(sources[sLen] == null) {
            return Collections.emptyList();
        }
        List<String> sentences = new ArrayList<>();
        char[] buf = new char[sLen+sLen-1];
        buildSentencesRecursively(s.toCharArray(), sources, sLen, buf, buf.length, sentences);
        return sentences;
    }

    private static void append(String s, int begin, List<String> wordDict, BeginPositions[] sources) {
        for(String w : wordDict) {
            if(match(s, begin, w)) {
                int end = begin + w.length();
                BeginPositions beginPos = sources[end];
                if(beginPos == null) {
                    sources[end] = new BeginPositions(begin);
                }else {
                    beginPos.add(begin);
                }
            }
        }
    }

    private static boolean match(String s, int begin, String w) {
        int sLen = s.length();
        int wLen = w.length();
        if(begin+wLen > sLen) { return false; }
        int wi = 0;
        for(int si=begin; wi<wLen && s.charAt(si)==w.charAt(wi); si++,wi++);
        return wi == wLen;
    }

    private static void buildSentencesRecursively(char[] s, BeginPositions[] sources, int end, char[] buf, int endIndex,
                                                  List<String> sentences) {
        BeginPositions source = sources[end];
        for(int i=0; i<source.count; i++) {
            int begin = source.beginPositions[i];
            int len = end - begin;
            int beginIndex = endIndex - len;
            System.arraycopy(s, begin, buf, beginIndex, len);
            if(begin == 0) {
                sentences.add(String.valueOf(buf, beginIndex, buf.length-beginIndex));
            }else {
                buf[--beginIndex] = ' ';
                buildSentencesRecursively(s, sources, begin, buf, beginIndex, sentences);
            }
        }
    }

    private static class BeginPositions {
        int[] beginPositions;
        int count;

        BeginPositions(int beginPosition) {
            beginPositions = new int[2];
            beginPositions[0] = beginPosition;
            count = 1;
        }

        void add(int beginPosition) {
            if(count == beginPositions.length) {
                beginPositions = Arrays.copyOf(beginPositions, count+2);
            }
            beginPositions[count] = beginPosition;
            count += 1;
        }
    }

    public static List<String> wordBreak1(String s, List<String> wordDict) {
        int sLen = s.length();
        TrieNode root = prepare(wordDict);
        BeginPositions[] sources = new BeginPositions[sLen+1];
        fillPositions(s, 0, root, sources);
        for(int i=1; i<sLen; i++) {
            if(sources[i] != null) {
                fillPositions(s, i, root, sources);
            }
        }
        if(sources[sLen] == null) {
            return Collections.emptyList();
        }
        List<String> sentences = new ArrayList<>();
        char[] buf = new char[sLen+sLen-1];
        buildSentencesRecursively(s.toCharArray(), sources, sLen, buf, buf.length, sentences);
        return sentences;
    }

    private static TrieNode prepare(List<String> wordDict) {
        TrieNode root = new TrieNode(false);
        for(String w : wordDict) {
            int end = w.length() - 1;
            TrieNode parent = root;
            for(int i=0; i<end; i++) {
                parent = parent.addInternalChar(w.charAt(i));
            }
            parent.addStopChar(w.charAt(end));
        }
        return root;
    }

    private static void fillPositions(String s, int begin, TrieNode root, BeginPositions[] sources) {
        int sLen = s.length();
        TrieNode parent = root;
        for(int i=begin; i<sLen; i++) {
            char ch = s.charAt(i);
            parent = parent.childNode(ch);
            if(parent == null) { return; }
            if(parent.isStopChar) {
                int end = i + 1;
                BeginPositions beginPos = sources[end];
                if(beginPos == null) {
                    sources[end] = new BeginPositions(begin);
                }else {
                    beginPos.add(begin);
                }
            }
        }
    }

    private static class TrieNode {
        char[] childChars;
        TrieNode[] childNodes;
        boolean isStopChar;

        TrieNode(boolean isStopChar) {
            this.childChars = new char[0];
            this.childNodes = new TrieNode[0];
            this.isStopChar = isStopChar;
        }

        TrieNode addInternalChar(char ch) {
            return addChar(ch, false);
        }

        TrieNode addChar(char ch, boolean isStopChar) {
            int index = findIndex(ch);
            TrieNode node;
            if(index < 0) {
                index = (-index) - 1;
                int len = childChars.length;
                char[] chars = new char[len+1];
                TrieNode[] nodes = new TrieNode[len+1];
                System.arraycopy(childChars, 0, chars, 0, index);
                System.arraycopy(childNodes, 0, nodes, 0, index);
                if(index < len) {
                    int rIndex = index + 1;
                    int rLen = len - index;
                    System.arraycopy(childChars, index, chars, rIndex, rLen);
                    System.arraycopy(childNodes, index, nodes, rIndex, rLen);
                }
                node = new TrieNode(isStopChar);
                chars[index] = ch;
                nodes[index] = node;
                childChars = chars;
                childNodes = nodes;
            }else {
                node = childNodes[index];
                if(isStopChar) {
                    node.isStopChar = true;
                }
            }
            return node;
        }

        int findIndex(char ch) {
            int begin = 0, end = childChars.length - 1;
            while(begin <= end) {
                int mid = (begin + end) / 2;
                char c = childChars[mid];
                if(ch < c) {
                    end = mid - 1;
                }else if(ch > c) {
                    begin = mid + 1;
                }else {
                    return mid;
                }
            }
            return (-begin) - 1;
        }

        TrieNode addStopChar(char ch) {
            return addChar(ch, true);
        }

        TrieNode childNode(char ch) {
            int index = findIndex(ch);
            return index < 0 ? null : childNodes[index];
        }
    }

}
