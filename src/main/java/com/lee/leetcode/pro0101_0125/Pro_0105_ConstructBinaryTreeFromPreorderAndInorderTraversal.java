package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.

 For example, given

 preorder = [3,9,20,15,7]
 inorder = [9,3,15,20,7]
 Return the following binary tree:

   3
  / \
 9  20
   /  \
  15   7

 *
 */
public class Pro_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree(preorder, inorder);
        TreeNode.levelOrderPrint(root);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null) {
            return null;
        }else {
            if(preorder.length > 1) {
                return buildTree(preorder, inorder, 0, 0, preorder.length);
            }else {
                return preorder.length == 0 ? null : new TreeNode(preorder[0]);
            }
        }

    }

    private static TreeNode buildTree(int[] preorder, int[] inorder, int pb, int ib, int length) {
        if(length == 0) { return null; }
        int v = preorder[pb];
        int k = ib;
        for(int end=ib+length; k<end; k++) {
            if(inorder[k] == v) { break; }
        }
        int leftLen = k - ib;
        int rightLen = length - 1 - leftLen;
        TreeNode left = buildTree(preorder, inorder, pb+1, ib, leftLen);
        TreeNode right = buildTree(preorder, inorder,pb+1+leftLen, k+1,rightLen);
        TreeNode root = new TreeNode(v);
        root.left = left;
        root.right = right;
        return root;
    }
}
