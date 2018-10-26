package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.ListNode;
import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example:

 Given the sorted linked list: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 *
 */
public class Pro_0109_ConvertSortedListToBinarySearchTree {

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        ListNode head = ListNode.build(nums);
        TreeNode root = sortedListToBST(head);
        TreeNode.levelOrderPrint(root);
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if(head == null) { return null; }
        List<Integer> nums = copyToArrayList(head);
        return sortedArrayToBST(nums, 0, nums.size());
    }

    private static List<Integer> copyToArrayList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode p = head;
        while(p != null) {
            list.add(p.val);
            p = p.next;
        }
        return list;
    }

    private static TreeNode sortedArrayToBST(List<Integer> nums, int begin, int length) {
        switch(length) {
            case 0:
                return null;
            case 1:
                return new TreeNode(nums.get(begin));
            default:
                int mid = begin + length / 2;
                TreeNode root = new TreeNode(nums.get(mid));
                root.left = sortedArrayToBST(nums, begin, mid-begin);
                root.right = sortedArrayToBST(nums, mid+1, begin+length-mid-1);
                return root;
        }
    }
}
