package com.lee.leetcode.pro0226_0250;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Invert a binary tree.

 Example:
 Input:
       4
    /    \
   2     7
  / \   / \
 1  3  6  9
 Output:
       4
    /    \
   7     2
  / \   / \
 9  6  3  1

 Trivia:
 This problem was inspired by this original tweet by Max Howell:
    Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
 *
 */
public class Pro_0226_InvertBinaryTree {

    public static void main(String[] args) {
        Integer[] array = {4,2,7,1,3,6,9};
        TreeNode root = TreeNode.levelOrderBuild(array);
        TreeNode.levelOrderPrint(root);
        root = invertTree(root);
        TreeNode.levelOrderPrint(root);
    }

    public static TreeNode invertTree(TreeNode root) {
        if(root == null) { return root; }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
