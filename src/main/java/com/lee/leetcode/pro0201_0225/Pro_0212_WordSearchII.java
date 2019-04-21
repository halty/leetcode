package com.lee.leetcode.pro0201_0225;

import java.util.*;

/**
 *
 Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell,
 where "adjacent" cells are those horizontally or vertically neighboring.
 The same letter cell may not be used more than once in a word.

 Example:

 Input:
 words = ["oath","pea","eat","rain"],
 board =
 [
   ['o','a','a','n'],
   ['e','t','a','e'],
   ['i','h','k','r'],
   ['i','f','l','v']
 ]
 Output: ["eat","oath"]

 Note:
   You may assume that all inputs are consist of lowercase letters a-z.
 *
 */
public class Pro_0212_WordSearchII {

    public static void main(String[] args) {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> resultList = findWords(board, words);
        System.out.println(resultList);
    }

    private static class Node {
        private Node[] childs;
        private int wordIndex;

        Node() {
            childs = null;
            wordIndex = -1;
        }
        Node addChar(char ch) {
            Node child;
            if(childs == null) {
                childs = new Node[26];
                childs[ch-'a'] = child = new Node();
            }else {
                child = childs[ch-'a'];
                if(child == null) {
                    childs[ch-'a'] = child = new Node();
                }
            }
            return child;
        }
        Node childNode(char ch) {
            return childs == null ? null : childs[ch-'a'];
        }
        void setWordIndex(int wordIndex) {
            this.wordIndex = wordIndex;
        }
        boolean isValidWordIndex() { return wordIndex >= 0; }
    }

    private static class Context {
        char[][] board;
        int m;  // board row count
        int n;  // board column count
        String[] words;
        int wordCount;
        Node root;  // root of words trie
        int maxWordLength;
        long[] path;    // path of board
        byte[] step;     // step of direction, 0b0001-left, 0b0010-right, 0b0100-up, 0b1000-down
        Node[] parent;
        BitSet matchResult;  // word matched result
    }

    public static List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        if(m == 0) { return Collections.emptyList(); }
        int n = board[0].length;
        if(n == 0) { return Collections.emptyList(); }
        if(words.length == 0) { return Collections.emptyList(); }
        Context ctx = new Context();
        ctx.board = board;
        ctx.m = m;
        ctx.n = n;
        ctx.words = words;
        ctx.wordCount = words.length;
        buildTrie(ctx);
        return traversal(ctx);
    }

    private static void buildTrie(Context ctx) {
        String[] words = ctx.words;
        Node root = new Node();
        int maxWordLength = 0;
        for(int wordIndex=0; wordIndex<words.length; wordIndex++) {
            String word = words[wordIndex];
            Node parent = root;
            int wordLength = word.length();
            for(int i=0; i<wordLength; i++) {
                char ch = word.charAt(i);
                parent = parent.addChar(ch);
            }
            parent.setWordIndex(wordIndex);
            if(maxWordLength < wordLength) {
                maxWordLength = wordLength;
            }
        }
        ctx.root = root;
        ctx.maxWordLength = maxWordLength;
    }

    private static List<String> traversal(Context ctx) {
        int maxWordLength = ctx.maxWordLength;
        int wordCount = ctx.wordCount;
        ctx.path = new long[maxWordLength];
        ctx.step = new byte[maxWordLength];
        ctx.parent = new Node[maxWordLength+1];
        ctx.matchResult = new BitSet(wordCount);
        int m = ctx.m;
        int n = ctx.n;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                match(ctx, i, j);
            }
        }
        List<String> result = new ArrayList<>();
        for(int i=0; i<wordCount; i++) {
            if(ctx.matchResult.get(i)) {
                result.add(ctx.words[i]);
            }
        }
        return result;
    }

    private static void match(Context ctx, int row, int column) {
        char ch = ctx.board[row][column];
        Node child = ctx.root.childNode(ch);
        if(child == null) { return; }
        if(child.isValidWordIndex()) {  // matched
            ctx.matchResult.set(child.wordIndex);
        }
        ctx.path[0] = position(row, column);
        ctx.step[0] = 0b1111;
        ctx.parent[0] = ctx.root;
        ctx.parent[1] = child;
        int index = 0;
        while(true) {
            byte step = ctx.step[index];
            byte nextStep = 0b1111; // all four direction
            boolean canMove = false;
            while(step != 0) {
                if((step & 0b0001) != 0) {
                    step -= 0b0001;
                    if(canMoveLeft(ctx, row, column, index)) {
                        column -= 1;
                        nextStep = 0b1101; // next step remove right direction
                        canMove = true;
                        break;
                    }
                }
                if((step & 0b0010) != 0) {
                    step -= 0b0010;
                    if(canMoveRight(ctx, row, column, index)) {
                        column += 1;
                        nextStep = 0b1110; // next step remove left direction
                        canMove = true;
                        break;
                    }
                }
                if((step & 0b0100) != 0) {
                    step -= 0b0100;
                    if(canMoveUp(ctx, row, column, index)) {
                        row -= 1;
                        nextStep = 0b0111; // next step remove down direction
                        canMove = true;
                        break;
                    }
                }
                if((step & 0b1000) != 0) {
                    step -= 0b1000;
                    if(canMoveDown(ctx, row, column, index)) {
                        row += 1;
                        nextStep = 0b1011; // next step remove up direction
                        canMove = true;
                        break;
                    }
                }
            }
            ctx.step[index] = step;
            if(canMove) {
                index++;
                ctx.path[index] = position(row, column);
                ctx.step[index] = nextStep;
                Node parent = ctx.parent[index];
                ch = ctx.board[row][column];
                child = parent.childNode(ch);
                if(child.isValidWordIndex()) {
                    ctx.matchResult.set(child.wordIndex);
                }
                ctx.parent[index+1] = child;
            }else { // fallback
                while(index > 0 && ctx.step[--index] == 0);
                if(ctx.step[index] == 0) {
                    break;
                }else {
                    long p = ctx.path[index];
                    // deposition (row, column)
                    row = (int)(p >>> 32);
                    column = (int)p;
                }
            }
        }
    }

    private static long position(int row, int column) {
        long p = row;
        p <<= 32;
        p += column;
        return p;
    }

    private static boolean canMoveLeft(Context ctx, int row, int column, int index) {
        column -= 1;
        return column >= 0 && isNodeMatch(ctx, row, column, index);
    }

    private static boolean isNodeMatch(Context ctx, int row, int column, int index) {
        Node parent = ctx.parent[index+1];
        char ch = ctx.board[row][column];
        Node child = parent.childNode(ch);
        if(child == null) { return false; }
        long p = position(row, column);
        long[] path = ctx.path;
        for(int i=index; i>=0; i--) {
            if(path[i] == p) {
                return false;
            }
        }
        return true;
    }

    private static boolean canMoveRight(Context ctx, int row, int column, int index) {
        column += 1;
        return column < ctx.n && isNodeMatch(ctx, row, column, index);
    }

    private static boolean canMoveUp(Context ctx, int row, int column, int index) {
        row -= 1;
        return row >= 0 && isNodeMatch(ctx, row, column, index);
    }

    private static boolean canMoveDown(Context ctx, int row, int column, int index) {
        row += 1;
        return row < ctx.m && isNodeMatch(ctx, row, column, index);
    }
}
