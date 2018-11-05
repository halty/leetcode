package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],

   3
  / \
 9  20
   /  \
  15   7
 return its minimum depth = 2.
 *
 */
public class Pro_0111_MinimumDepthOfBinaryTree {

    public static void main(String[] args) {
        Integer[] array = {1,2};
        TreeNode root = TreeNode.levelOrderBuild(array);
        int result = minDepth(root);
        System.out.println(result);
    }

    public static int minDepth(TreeNode root) {
        return root == null ? 0 : minDepthRecursively(root);
    }

    public static int minDepthRecursively(TreeNode root) {
        if(root.left == null) {
            if(root.right == null) {
                return 1;
            }else {
                return minDepthRecursively(root.right) + 1;
            }
        }else {
            if(root.right == null) {
                return minDepthRecursively(root.left) + 1;
            }else {
                return Math.min(minDepthRecursively(root.left), minDepthRecursively(root.right)) + 1;
            }
        }
    }
}
