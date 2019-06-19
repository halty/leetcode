package com.lee.leetcode.pro0276_0300;

import java.util.Arrays;

/**
 *
 According to the Wikipedia's article: "The Game of Life, also known simply as Life,
 is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

 Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
    1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    2. Any live cell with two or three live neighbors lives on to the next generation.
    3. Any live cell with more than three live neighbors dies, as if by over-population..
    4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

 Write a function to compute the next state (after one update) of the board given its current state.
 The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

 Example:
 Input:
   [
     [0,1,0],
     [0,0,1],
     [1,1,1],
     [0,0,0]
   ]
 Output:
   [
     [0,0,0],
     [1,0,1],
     [0,1,1],
     [0,1,0]
   ]

 Follow up:
    1. Could you solve it in-place? Remember that the board needs to be updated at the same time:
       You cannot update some cells first and then use their updated values to update other cells.
    2. In this question, we represent the board using a 2D array. In principle, the board is infinite,
       which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 *
 */
public class Pro_0289_GameOfLife {

    public static void main(String[] args) {
        int[][] board = {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
//        gameOfLife(board);
        gameOfLife1(board);
        for(int[] line : board) {
            System.out.println(Arrays.toString(line));
        }
    }

    public static void gameOfLife(int[][] board) {
        int rowEnd = board.length - 1;
        if(rowEnd < 0) { return; }
        int colEnd = board[0].length - 1;
        if(colEnd < 0) { return; }
        int[][] target = new int[board.length][board[0].length];
        for(int i=0; i<=rowEnd; i++) {
            for(int j=0; j<=colEnd; j++) {
                target[i][j] = transfer(board[i][j], liveNeighbors(board, i, j, rowEnd, colEnd));
            }
        }
        System.arraycopy(target, 0, board, 0, board.length);
    }

    private static int liveNeighbors(int[][] board, int row, int col, int rowEnd, int colEnd) {
        int rowBegin = Math.max(row-1, 0), colBegin = Math.max(col-1, 0);
        rowEnd = Math.min(row+1, rowEnd);
        colEnd = Math.min(col+1, colEnd);
        int count = -board[row][col];
        for(int i=rowBegin; i<=rowEnd; i++) {
            for(int j=colBegin; j<=colEnd; j++) {
                count += board[i][j];
            }
        }
        return count;
    }

    private static int transfer(int source, int liveNeighbors) {
        return liveNeighbors == 3 || source+liveNeighbors == 3 ? 1 : 0;
    }

    public static void gameOfLife1(int[][] board) {
        int rowEnd = board.length - 1;
        if(rowEnd < 0) { return; }
        int colEnd = board[0].length - 1;
        if(colEnd < 0) { return; }
        for(int i=0; i<=rowEnd; i++) {
            for(int j=0; j<=colEnd; j++) {
                int target = transfer(board[i][j] & 0x01, sourceLiveNeighbors(board, i, j, rowEnd, colEnd));
                board[i][j] |= (target << 16);
            }
        }
        for(int i=0; i<=rowEnd; i++) {
            for(int j=0; j<=colEnd; j++) {
                board[i][j] >>>= 16;
            }
        }
    }

    private static int sourceLiveNeighbors(int[][] board, int row, int col, int rowEnd, int colEnd) {
        int rowBegin = Math.max(row-1, 0), colBegin = Math.max(col-1, 0);
        rowEnd = Math.min(row+1, rowEnd);
        colEnd = Math.min(col+1, colEnd);
        int count = -(board[row][col] & 0x01);
        for(int i=rowBegin; i<=rowEnd; i++) {
            for(int j=colBegin; j<=colEnd; j++) {
                count += (board[i][j] & 0x01);
            }
        }
        return count;
    }
}
