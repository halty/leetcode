package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given a non-empty binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 The path must contain at least one node and does not need to go through the root.

 Example 1:
 Input: [1,2,3]
   1
  / \
 2   3
 Output: 6

 Example 2:
 Input: [-10,9,20,null,null,15,7]
    -10
    / \
   9  20
     /  \
    15   7
 Output: 42
 *
 */
public class Pro_0124_BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        Integer[] array = {-10,9,20,null,null,15,7};
        TreeNode root = TreeNode.levelOrderBuild(array);
        int maxPathSum = maxPathSum(root);
        System.out.println(maxPathSum);
    }

    public static int maxPathSum(TreeNode root) {
        return maxPathSumRecursively(root).ps;
    }

    private static MaxSum maxPathSumRecursively(TreeNode root) {
        if(root.left != null) {
            MaxSum left = maxPathSumRecursively(root.left);
            if(root.right != null) {
                MaxSum right = maxPathSumRecursively(root.right);
                if(left.psrs > 0) {
                    if(right.psrs > 0) {
                        left.ps = Math.max(root.val+left.psrs+right.psrs, Math.max(left.ps, right.ps));
                        left.psrs = left.psrs > right.psrs ? root.val+left.psrs : root.val+right.psrs;
                    }else {
                        left.psrs = root.val + left.psrs;
                        left.ps = Math.max(left.psrs, Math.max(left.ps, right.ps));
                    }
                }else {
                    if(right.psrs > 0) {
                        left.psrs = root.val + right.psrs;
                        left.ps = Math.max(left.psrs, Math.max(left.ps, right.ps));
                    }else {
                        left.psrs = root.val;
                        left.ps = Math.max(left.psrs, Math.max(left.ps, right.ps));
                    }
                }
                return left;
            }else {
                left.psrs = left.psrs > 0 ? root.val+left.psrs : root.val;
                left.ps = left.psrs > left.ps ? left.psrs : left.ps;
                return left;
            }
        }else {
            if(root.right != null) {
                MaxSum right = maxPathSumRecursively(root.right);
                right.psrs = right.psrs > 0 ? root.val+right.psrs : root.val;
                right.ps = right.psrs > right.ps ? right.psrs : right.ps;
                return right;
            }else {
                return new MaxSum(root.val, root.val);
            }
        }
    }

    private static class MaxSum {
        int ps;   // the paths in subtree
        int psrs;    // the paths start with root node of subtree
        MaxSum(int ps, int psrs) {
            this.ps = ps;
            this.psrs = psrs;
        }
    }
}
