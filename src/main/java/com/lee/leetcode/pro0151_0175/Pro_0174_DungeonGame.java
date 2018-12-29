package com.lee.leetcode.pro0151_0175;

/**
 *
 The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 The dungeon consists of M x N rooms laid out in a 2D grid.
 Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

 The knight has an initial health point represented by a positive integer.
 If at any point his health point drops to 0 or below, he dies immediately.

 Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

 In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

 Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

 For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
     -2(K)   -3      3
        -5  -10	     1
        10	 30  -5(P)

 Note:
   1. The knight's health has no upper bound.
   2. Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 */
public class Pro_0174_DungeonGame {

    public static void main(String[] args) {
        int[][] dungeon = {
                {-2, -3, 3},
                {-5,-10, 1},
                {10, 30,-5}
        };
//        int result = calculateMinimumHP(dungeon);
        int result = calculateMinimumHP1(dungeon);
        System.out.println(result);
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int hp = calcHP(1, dungeon[m-1][n-1]);
        State s = new State(Integer.MAX_VALUE, hp, m-1, n-1);
        minHPRecursively(dungeon, m, n, s);
        return s.minHP;
    }

    private static void minHPRecursively(int[][] dungeon, int m, int n, State s) {
        if(s.i == 0) {
            int minHP = walkLeft(dungeon, s.j, s.hp);
            if(s.minHP > minHP) { s.minHP = minHP; }
        }else {
            if(s.j == 0) {
                int minHP = walkUp(dungeon, s.i, s.hp);
                if(s.minHP > minHP) { s.minHP = minHP; }
            }else {
                int hp = s.hp;
                s.i -= 1;
                s.hp = calcHP(s.hp, dungeon[s.i][s.j]);
                minHPRecursively(dungeon, m, n, s);
                s.i += 1;
                s.hp = hp;
                s.j -= 1;
                s.hp = calcHP(s.hp, dungeon[s.i][s.j]);
                minHPRecursively(dungeon, m, n, s);
                s.j += 1;
                s.hp = hp;
            }
        }
    }

    private static int walkLeft(int[][] dungeon, int j, int hp) {
        while(j > 0) {
            j -= 1;
            hp = calcHP(hp, dungeon[0][j]);
        }
        return hp;
    }

    private static int calcHP(int nextNeedHP, int currentHP) {
        return currentHP >= nextNeedHP ? 1 : nextNeedHP - currentHP;
    }

    private static int walkUp(int[][] dungeon, int i, int hp) {
        while(i > 0) {
            i -= 1;
            hp = calcHP(hp, dungeon[i][0]);
        }
        return hp;
    }

    private static class State {
        int minHP;
        int hp;
        int i;
        int j;
        State(int minHP, int hp, int i, int j) {
            this.minHP = minHP;
            this.hp = hp;
            this.i = i;
            this.j = j;
        }
    }

    public static int calculateMinimumHP1(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[] hp = new int[n];
        int i = m - 1, j = n - 1;
        hp[j] = calcHP(1, dungeon[i][j]);
        while(j > 0) {
            j -= 1;
            hp[j] = calcHP(hp[j+1], dungeon[i][j]);
        }
        while(i > 0) {
            i -= 1;
            j = n - 1;
            hp[j] = calcHP(hp[j], dungeon[i][j]);
            while(j > 0) {
                j -= 1;
                hp[j] = calcHP(Math.min(hp[j], hp[j+1]), dungeon[i][j]);
            }
        }
        return hp[0];
    }
}
