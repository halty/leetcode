package com.lee.leetcode.pro0076_0100;

/**
 *
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 *
 */
public class Pro_0079_WordSearch {

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "SEE";
        boolean result = exist(board, word);
        System.out.println(result);
    }

    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        if(m == 0) { return false; }
        int n = board[0].length;
        if(n == 0) { return false; }
        int len = word.length();
        if(len == 0) { return false; }
        long[] path = new long[len];
        int[] steps = new int[len];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(match(board, i, j, word, path, steps)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean match(char[][] board, int row, int column, String word, long[] path, int[] steps) {
        int len = word.length();
        if(len == 1) { return true; }
        path[0] = position(row, column);
        steps[1] = 0;
        int i = 1;
        while(true) {
            char letter = word.charAt(i);
            int step = steps[i] + 1;
            while(step <= 4) {
                boolean isBreak = false;
                switch(step) {
                    case 1:
                        if(canMoveLeft(board, row, column, letter, path, i)) {
                            column -= 1;
                            isBreak = true;
                        }
                        break;
                    case 2:
                        if(canMoveRight(board, row, column, letter, path, i)) {
                            column += 1;
                            isBreak = true;
                        }
                        break;
                    case 3:
                        if(canMoveUp(board, row, column, letter, path, i)) {
                            row -= 1;
                            isBreak = true;
                        }
                        break;
                    case 4:
                        if(canMoveDown(board, row, column, letter, path, i)) {
                            row += 1;
                            isBreak = true;
                        }
                        break;
                }
                if(isBreak) {
                    break;
                }else {
                    step++;
                }
                /*if(step == 1) {
                    if(canMoveLeft(board, row, column, letter, path, i)) {
                        column -= 1;
                        break;
                    }
                }else if(step == 2) {
                    if(canMoveRight(board, row, column, letter, path, i)) {
                        column += 1;
                        break;
                    }
                }else if(step == 3) {
                    if(canMoveUp(board, row, column, letter, path, i)) {
                        row -= 1;
                        break;
                    }
                }else { // step == 4
                    if(canMoveDown(board, row, column, letter, path, i)) {
                        row += 1;
                        break;
                    }
                }
                step++;*/
            }
            if(step <= 4) {
                path[i] = position(row, column);
                steps[i] = step;
                i++;
                if(i == len) {
                    return true;
                }else {
                    steps[i] = 0;
                }
            }else { // fallback
                if(i == 1) {
                    return false;
                }else {
                    i--;
                    long p = path[i-1];
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

    private static boolean canMoveUp(char[][] board, int row, int column, char letter, long[] path, int end) {
        row -= 1;
        return row >= 0 && isLetterMatch(board, row, column, letter, path, end);
    }

    private static boolean isLetterMatch(char[][] board, int row, int column, char letter, long[] path, int end) {
        if(board[row][column] != letter) {
            return false;
        }
        long p = position(row, column);
        return !isFound(p, path, end);
    }

    private static boolean isFound(long position, long[] path, int end) {
        for(int i=end-1; i>=0; i--) {
            if(path[i] == position) {
                return true;
            }
        }
        return false;
    }

    private static boolean canMoveRight(char[][] board, int row, int column, char letter, long[] path, int end) {
        column += 1;
        return column < board[row].length && isLetterMatch(board, row, column, letter, path, end);
    }

    private static boolean canMoveDown(char[][] board, int row, int column, char letter, long[] path, int end) {
        row += 1;
        return row < board.length && isLetterMatch(board, row, column, letter, path, end);
    }

    private static boolean canMoveLeft(char[][] board, int row, int column, char letter, long[] path, int end) {
        column -= 1;
        return column >= 0 && isLetterMatch(board, row, column, letter, path, end);
    }
}
