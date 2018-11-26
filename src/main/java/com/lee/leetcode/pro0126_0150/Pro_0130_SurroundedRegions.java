package com.lee.leetcode.pro0126_0150;

import java.util.HashSet;
import java.util.Set;

/**
 *
 Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 A region is captured by flipping all 'O's into 'X's in that surrounded region.

 Example:
     X X X X
     X O O X
     X X O X
     X O X X
 After running your function, the board should be:
     X X X X
     X X X X
     X X X X
     X O X X
 Explanation:
    Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
    Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
    Two cells are connected if they are adjacent cells connected horizontally or vertically.
 *
 */
public class Pro_0130_SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };
        solve(board);
        for(char[] line : board) {
            System.out.println(line);
        }
    }

    public static void solve(char[][] board) {
        int rowCount = board.length;
        if(rowCount <= 2) { return; }
        int columnCount = board[0].length;
        if(columnCount <= 2) {return; }
        Set<Long> set = new HashSet<>();
        for(int j=0; j<columnCount; j++) {
            if(board[0][j] == 'O') {
                set.add(composeOf(0, j));
            }
        }
        int endRow = rowCount - 1;
        for(int j=0; j<columnCount; j++) {
            if(board[endRow][j] == 'O') {
                set.add(composeOf(endRow, j));
            }
        }
        for(int i=0; i<rowCount; i++) {
            if(board[i][0] == 'O') {
                set.add(composeOf(i, 0));
            }
        }
        int endColumn = columnCount - 1;
        for(int i=0; i<rowCount; i++) {
            if(board[i][endColumn] == 'O') {
                set.add(composeOf(i, endColumn));
            }
        }
        Set<Long> connectedSet = new HashSet<>(set);
        while(!set.isEmpty()) {
            Set<Long> nextSet = new HashSet<>();
            for(long v : set) {
                int x = deComposeXFrom(v), y = deComposeYFrom(v);
                x -= 1;
                if (isConnected(board, x, y, rowCount, columnCount)) {
                    update(x, y, nextSet, connectedSet);
                }
                x += 2;
                if (isConnected(board, x, y, rowCount, columnCount)) {
                    update(x, y, nextSet, connectedSet);
                }
                x -= 1;
                y -= 1;
                if (isConnected(board, x, y, rowCount, columnCount)) {
                    update(x, y, nextSet, connectedSet);
                }
                y += 2;
                if (isConnected(board, x, y, rowCount, columnCount)) {
                    update(x, y, nextSet, connectedSet);
                }
            }
            set = nextSet;
        }
        for(int i=1; i<endRow; i++) {
            for(int j=1; j<endColumn; j++) {
                if(board[i][j] == 'O' && !connectedSet.contains(composeOf(i, j))) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private static long composeOf(int x, int y) {
        long v = x;
        v <<= 32;
        v |= y;
        return v;
    }

    private static int deComposeXFrom(long v) { return (int)(v >>> 32); }

    private static int deComposeYFrom(long v) { return (int)v; }

    private static boolean isConnected(char[][] board, int x, int y, int rowCount, int columCount) {
        return x >= 0 && x < rowCount
            && y >= 0 && y < columCount
            && board[x][y] == 'O';
    }

    private static void update(int x, int y, Set<Long> nextSet, Set<Long> connectedSet) {
        Long v = composeOf(x, y);
        if(connectedSet.add(v)) {
            nextSet.add(v);
        }
    }
}
