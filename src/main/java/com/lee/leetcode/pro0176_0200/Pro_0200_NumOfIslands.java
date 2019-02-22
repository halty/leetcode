package com.lee.leetcode.pro0176_0200;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 You may assume all four edges of the grid are all surrounded by water.

 Example 1:
 Input:
     11110
     11010
     11000
     00000
 Output: 1

 Example 2:
 Input:
     11000
     11000
     00100
     00011
 Output: 3
 *
 */
public class Pro_0200_NumOfIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','1','1','1'},
                {'0','0','0','0','0','0','1'},
                {'1','1','1','1','1','0','1'},
                {'1','0','0','0','1','0','1'},
                {'1','0','1','0','1','0','1'},
                {'1','0','1','1','1','0','1'},
                {'1','1','1','1','1','1','1'}
        };
//        int count = numIslands(grid);
        int count = numIslands1(grid);
        System.out.println(count);
    }

    public static int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0) { return 0; }
        int n = grid[0].length;
        if(n == 0) { return 0; }
        UnionSet set = new UnionSet(m*n);
        int zeroCount = (grid[0][0] == '0' ? 1 : 0);
        for(int j=1; j<n; j++) {
            if(grid[0][j] == '0') {
                zeroCount++;
            }else {
                if(grid[0][j-1] == '1') {
                    set.union(j-1, j);
                }
            }
        }
        for(int i=1; i<m; i++) {
            if(grid[i][0] == '0') {
                zeroCount++;
            }else {
                if(grid[i-1][0] == '1') {
                    int index = i * n;
                    set.union(index-n, index);
                }
            }
        }
        for(int i=1; i<m; i++) {
            int index = i * n + 1;
            for(int j=1; j<n; j++, index++) {
                if(grid[i][j] == '0') {
                    zeroCount++;
                }else {
                    if(grid[i][j-1] == '1') {
                        set.union(index-1, index);
                    }
                    if(grid[i-1][j] == '1') {
                        set.union(index-n, index);
                    }
                }
            }
        }
        return set.size - zeroCount;
    }

    private static class UnionSet {
        int[] array;
        int size;

        UnionSet(int capacity) {
            array = new int[capacity];
            for(int i=0; i<capacity; i++) {
                array[i] = -1;
            }
            size = capacity;
        }
        void union(int a, int b) {
            int ra = rootIndex(a);
            int rb = rootIndex(b);
            if(ra != rb) {
                int ha = array[ra];
                int hb = array[rb];
                if(ha < hb) {
                    array[rb] = ra;
                }else if(ha > hb) {
                    array[ra] = rb;
                }else {
                    array[rb] = ra;
                    array[ra]--;
                }
                size--;
            }
        }
        int rootIndex(int index) {
            int k = array[index];
            while(k >= 0) {
                index = k;
                k = array[index];
            }
            return index;
        }
    }

    public static int numIslands1(char[][] grid) {  // DFS
        int m = grid.length;
        if(m == 0) { return 0; }
        int n = grid[0].length;
        if(n == 0) { return 0; }
        int count = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    flipGrid(m, n, grid, i, j);
                }
            }
        }
        return count;
    }

    private static void flipGrid(int m, int n, char[][] grid, int r, int c) {
        grid[r][c] = '0';
        if(c > 0 && grid[r][c-1] == '1') {
            flipGrid(m, n, grid, r, c-1);
        }
        if(c < n-1 && grid[r][c+1] == '1') {
            flipGrid(m, n, grid, r, c+1);
        }
        if(r > 0 && grid[r-1][c] == '1') {
            flipGrid(m, n, grid, r-1, c);
        }
        if(r < m-1 && grid[r+1][c] == '1') {
            flipGrid(m, n, grid, r+1, c);
        }
    }
}
