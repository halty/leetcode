package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as:

 a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example 1:

 Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
 Return true.

 Example 2:

 Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
 Return false.
 *
 */
public class Pro_0110_BalancedBinaryTree {

    public static void main(String[] args) {
        Integer[] array = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.levelOrderBuild(array);
        boolean result = isBalanced(root);
        System.out.println(result);
    }

    public static boolean isBalanced(TreeNode root) {
        return root == null ? true : isBalancedRecursively(root).isBalanced;
    }

    private static State isBalancedRecursively(TreeNode root) {
        if(root.left == null) {
            if(root.right == null) {
                return new State(true, 1);
            }else {
                State r = isBalancedRecursively(root.right);
                r.isBalanced = r.isBalanced && (r.height == 1);
                if(r.isBalanced) { r.height += 1; }
                return r;
            }
        }else {
            if(root.right == null) {
                State l = isBalancedRecursively(root.left);
                l.isBalanced = l.isBalanced && (l.height == 1);
                if(l.isBalanced) { l.height += 1; }
                return l;
            }else {
                State l = isBalancedRecursively(root.left);
                State r = isBalancedRecursively(root.right);
                l.isBalanced = l.isBalanced && r.isBalanced && (Math.abs(l.height-r.height) <= 1);
                if(l.isBalanced) { l.height = Math.max(l.height, r.height) + 1; }
                return l;
            }
        }
    }

    private static class State {
        boolean isBalanced;
        int height;
        State(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
}
