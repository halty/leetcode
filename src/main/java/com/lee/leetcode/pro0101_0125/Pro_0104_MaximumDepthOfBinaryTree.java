package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],

   3
  / \
 9  20
   /  \
  15   7
 return its depth = 3.
 *
 */
public class Pro_0104_MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        Integer[] array = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.levelOrderBuild(array);
        int maxDepth = maxDepth(root);
        System.out.println(maxDepth);
    }

    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
