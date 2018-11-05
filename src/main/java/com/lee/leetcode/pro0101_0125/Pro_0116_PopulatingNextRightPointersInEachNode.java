package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeLinkNode;

/**
 *
 Given a binary tree
     struct TreeLinkNode {
        TreeLinkNode *left;
        TreeLinkNode *right;
        TreeLinkNode *next;
     }

 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 Initially, all next pointers are set to NULL.

 Note:
 You may only use constant extra space.
 Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).

 Example:
 Given the following perfect binary tree,
       1
    /    \
   2      3
  / \    / \
 4   5  6   7
 After calling your function, the tree should look like:
       1 -> NULL
    /    \
   2  ->  3 -> NULL
  / \    / \
 4-> 5->6-> 7 -> NULL
 *
 */
public class Pro_0116_PopulatingNextRightPointersInEachNode {

    public static void main(String[] args) {
        Integer[] array = {1,2,3,4,5,6,7};
        TreeLinkNode root = TreeLinkNode.levelOrderBuild(array);
        connect(root);
        TreeLinkNode.levelNextPrint(root);
    }

    public static void connect(TreeLinkNode root) {
        TreeLinkNode head = root;
        while(head != null) {
            TreeLinkNode p = head;
            TreeLinkNode prev = null;
            head = null;
            while(p != null) {
                if(p.left != null) {
                    head = prev = p.left;
                    if(p.right != null) {
                        prev = prev.next = p.right;
                    }
                    p = p.next;
                    break;
                }else {
                    if(p.right != null) {
                        head = prev = p.right;
                        p = p.next;
                        break;
                    }else {
                        p = p.next;
                    }
                }
            }
            while(p != null) {
                if(p.left != null) {
                    prev = prev.next = p.left;
                }
                if(p.right != null) {
                    prev = prev.next = p.right;
                }
                p = p.next;
            }
        }
    }
}
