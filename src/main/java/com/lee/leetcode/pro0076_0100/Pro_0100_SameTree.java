package com.lee.leetcode.pro0076_0100;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given two binary trees, write a function to check if they are the same or not.

 Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

 Example 1:

 Input:
   1         1
  / \       / \
 2   3     2   3
 [1,2,3],  [1,2,3]
 Output: true

 Example 2:
 Input:
   1         1
  /           \
 2             2

 [1,2],     [1,null,2]
 Output: false

 Example 3:
 Input:
   1         1
  / \       / \
 2   1     1   2
 [1,2,1],  [1,1,2]
 Output: false
 *
 */
public class Pro_0100_SameTree {

    public static void main(String[] args) {
        Integer[] a = {1,2,1};
        Integer[] b = {1,1,2};
        TreeNode p = TreeNode.levelOrderBuild(a);
        TreeNode q = TreeNode.levelOrderBuild(b);
        boolean result = isSameTree(p, q);
        System.out.println(result);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null) {
            return q == null;
        }else {
            return q != null &&
                   p.val == q.val &&
                   isSameTree(p.left, q.left) &&
                   isSameTree(p.right, q.right);
        }
    }
}
