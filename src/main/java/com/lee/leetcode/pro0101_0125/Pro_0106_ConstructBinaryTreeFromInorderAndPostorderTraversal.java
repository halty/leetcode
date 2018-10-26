package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given inorder and postorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.

 For example, given

 inorder = [9,3,15,20,7]
 postorder = [9,15,7,20,3]
 Return the following binary tree:

   3
  / \
 9  20
   /  \
  15   7
 *
 */
public class Pro_0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode root = buildTree(inorder, postorder);
        TreeNode.levelOrderPrint(root);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder == null) {
            return null;
        }else {
            if(postorder.length > 1) {
                return buildTree(inorder, postorder, 0, postorder.length-1, postorder.length);
            }else {
                return postorder.length == 0 ? null : new TreeNode(postorder[0]);
            }
        }
    }

    private static TreeNode buildTree(int[] inorder, int[] postorder, int ib, int pe, int length) {
        if(length == 0) { return null; }
        int v = postorder[pe];
        int k = ib;
        for(int end=ib+length; k<end; k++) {
            if(inorder[k] == v) { break; }
        }
        int leftLen = k - ib;
        int rightLen = length - 1 - leftLen;
        TreeNode left = buildTree(inorder, postorder, ib, pe+leftLen-length, leftLen);
        TreeNode right = buildTree(inorder,postorder, k+1, pe-1, rightLen);
        TreeNode root = new TreeNode(v);
        root.left = left;
        root.right = right;
        return root;
    }
}
