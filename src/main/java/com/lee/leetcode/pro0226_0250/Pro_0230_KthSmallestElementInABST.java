package com.lee.leetcode.pro0226_0250;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Example 1:
 Input: root = [3,1,4,null,2], k = 1
    3
  /  \
 1    4
  \
   2
 Output: 1

 Example 2:
 Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
 Output: 3

 Follow up:
    What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
    How would you optimize the kthSmallest routine?
 *
 */
public class Pro_0230_KthSmallestElementInABST {

    public static void main(String[] args) {
        Integer[] array = {3,1,4,null,2};
        TreeNode root = TreeNode.levelOrderBuild(array);
        int k = 4;
        int result = kthSmallest(root, k);
        System.out.println(result);
    }

    public static int kthSmallest(TreeNode root, int k) {
        return kthSmallestRecursively(root, k).element;
    }

    private static Result kthSmallestRecursively(TreeNode root, int k) {
        if(root == null) { return new Result(0); }
        Result left = kthSmallestRecursively(root.left, k);
        if(left.element != null) { return left; }
        k -= left.count + 1;
        if(k == 0) {
            left.element = root.val;
            return left;
        }
        Result right = kthSmallestRecursively(root.right, k);
        if(right.element != null) { return right; }
        right.count += left.count + 1;
        return right;
    }

    private static class Result {
        Integer element;
        int count;
        Result(int count) {
            this.element = null;
            this.count = count;
        }
        Result(Integer element, int count) {
            this.element = element;
            this.count = count;
        }
    }
}
