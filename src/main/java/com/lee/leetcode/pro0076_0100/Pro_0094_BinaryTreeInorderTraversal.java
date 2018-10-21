package com.lee.leetcode.pro0076_0100;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 Given a binary tree, return the inorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
  1
   \
    2
  /
 3

 Output: [1,3,2]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 */
public class Pro_0094_BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        Integer[] array = {1,2,4,null,null,5,null,null,3,null,6};
        TreeNode root = TreeNode.preOrderBuild(array);
//        List<Integer> result = inorderTraversal1(root);
        List<Integer> result = inorderTraversal2(root);
        System.out.println(result);
    }

    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversalRecursively(root, list);
        return list;
    }

    private static void inorderTraversalRecursively(TreeNode root, List<Integer> values) {
        if(root == null) { return; }
        inorderTraversalRecursively(root.left, values);
        values.add(root.val);
        inorderTraversalRecursively(root.right, values);
    }

    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) { return list; }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if(node.left != null) {
                stack.push(node.left);
                node.left = null;   // modify the original tree node state
                continue;
            }
            list.add(node.val);
            stack.pop();
            if(node.right != null) {
                stack.push(node.right);
            }
        }
        return list;
    }
}
