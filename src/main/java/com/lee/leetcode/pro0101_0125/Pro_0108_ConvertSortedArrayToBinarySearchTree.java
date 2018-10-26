package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example:

 Given the sorted array: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

       0
      / \
    -3   9
    /   /
 -10   5
 *
 */
public class Pro_0108_ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        TreeNode root = sortedArrayToBST(nums);
        TreeNode.levelOrderPrint(root);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : sortedArrayToBST(nums, 0, nums.length);
    }

    private static TreeNode sortedArrayToBST(int[] nums, int begin, int length) {
        switch(length) {
            case 0:
                return null;
            case 1:
                return new TreeNode(nums[begin]);
            default:
                int mid = begin + length / 2;
                TreeNode root = new TreeNode(nums[mid]);
                root.left = sortedArrayToBST(nums, begin, mid-begin);
                root.right = sortedArrayToBST(nums, mid+1, begin+length-mid-1);
                return root;
        }
    }
}
