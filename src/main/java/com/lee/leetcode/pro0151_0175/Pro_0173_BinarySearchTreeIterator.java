package com.lee.leetcode.pro0151_0175;

import com.lee.leetcode.common.TreeNode;

import java.util.Stack;

/**
 *
 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 Calling next() will return the next smallest number in the BST.
 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 *
 */
public class Pro_0173_BinarySearchTreeIterator {

    public static void main(String[] args) {
        Integer[] array ={4,2,6,1,3,5,7};
        TreeNode root = TreeNode.levelOrderBuild(array);
        TreeNode.inOrderPrint(root);
        Pro_0173_BinarySearchTreeIterator iterator = new Pro_0173_BinarySearchTreeIterator(root);
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public Pro_0173_BinarySearchTreeIterator(TreeNode root) {
        stack = new Stack<>();
        next = firstNode(root, stack);
    }

    private static TreeNode firstNode(TreeNode root, Stack<TreeNode> stack) {
        if(root == null) { return null; }
        TreeNode p = root;
        for(; p.left != null; p = p.left) {
            stack.push(p);
        }
        return p;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return next != null;
    }

    /** @return the next smallest number */
    public int next() {
        int val = next.val;
        next = nextNode(next, stack);
        return val;
    }

    private static TreeNode nextNode(TreeNode current, Stack<TreeNode> stack) {
        TreeNode right = current.right;
        if(right != null) {
            return firstNode(right, stack);
        }else {
            return stack.isEmpty() ? null : stack.pop();
        }
    }

    private Stack<TreeNode> stack;
    private TreeNode next;
}
