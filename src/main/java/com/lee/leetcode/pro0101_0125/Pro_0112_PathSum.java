package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

       5
      / \
     4   8
    /   / \
   11  13  4
  /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 *
 */
public class Pro_0112_PathSum {

    public static void main(String[] args) {
        Integer[] array = {5,4,8,11,null,13,4,7,2,null,null,null,1};
        int sum = 22;
        TreeNode root = TreeNode.levelOrderBuild(array);
        boolean result = hasPathSum(root, sum);
        System.out.println(result);
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        return root == null ? false : hasPathSumRecursively(root, sum);
    }

    private static boolean hasPathSumRecursively(TreeNode root, int sum) {
        if(root.left == null) {
            if(root.right == null) {
                return root.val == sum;
            }else {
                return hasPathSumRecursively(root.right, sum-root.val);
            }
        }else {
            if(root.right == null) {
                return hasPathSumRecursively(root.left, sum-root.val);
            }else {
                return hasPathSumRecursively(root.left, sum-root.val)
                    || hasPathSumRecursively(root.right, sum-root.val);
            }
        }
    }
}
