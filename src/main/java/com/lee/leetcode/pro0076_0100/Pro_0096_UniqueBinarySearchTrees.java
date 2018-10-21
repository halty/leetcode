package com.lee.leetcode.pro0076_0100;

/**
 *
 Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

 Example:

 Input: 3
 Output: 5
 Explanation:
 Given n = 3, there are a total of 5 unique BST's:

 1         3     3      2      1
  \       /     /      / \      \
   3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3
 *
 */
public class Pro_0096_UniqueBinarySearchTrees {

    public static void main(String[] args) {
        for(int n=0; n<=19; n++) {
//        int n = 4;
//        int result = numTrees1(n);
            int result = numTrees2(n);
            int e = n * (int)Math.pow(2, n);
            int r = e * n;
            System.out.println(n + ": " + result + ", " + e + ", " + r);

        }
    }

    public static int numTrees1(int n) {
        return n > 0 ? numTreesRecursively(1, n) : 0;
    }

    private static int numTreesRecursively(int min, int max) {
        if(min >= max) {
            return 1;
        }else {
            int count = 0;
            for(int i=min; i<=max; i++) {
                int leftCount = numTreesRecursively(min, i-1);
                int rightCount = numTreesRecursively(i+1, max);
                count += leftCount * rightCount;
            }
            return count;
        }
    }

    public static int numTrees2(int n) {
        if(n > 1) {
            int[] array = new int[n + 1];
            array[0] = 1;
            array[1] = 1;
            for(int i=2; i<=n; i++) {
                int count = 0;
                for(int left=0, right=i-1; left<i; left++, right--) {
                    count += array[left] * array[right];
                }
                array[i] = count;
            }
            return array[n];
        }else {
            return n == 1 ? 1 : 0;
        }
    }
}
