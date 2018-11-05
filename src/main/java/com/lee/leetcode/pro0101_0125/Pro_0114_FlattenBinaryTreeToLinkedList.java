package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 Given a binary tree, flatten it to a linked list in-place.

 For example, given the following tree:
     1
    / \
   2   5
  / \   \
 3   4   6

 The flattened tree should look like:
 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
 *
 */
public class Pro_0114_FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        Integer[] array = {1,2,5,3,4,null,6};
        TreeNode root = TreeNode.levelOrderBuild(array);
//        flatten1(root);
        flatten2(root);
        TreeNode.levelOrderPrint(root);
    }

    public static void flatten1(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        collectRecursively(root, list);
        int size = list.size();
        if(size >= 2) {
            TreeNode prev = list.get(0);
            for(int i=1; i<size; i++) {
                TreeNode cur = list.get(i);
                prev.right = cur;
                prev.left = null;
                prev = cur;
            }
        }
    }

    private static void collectRecursively(TreeNode root, List<TreeNode> list) {
        if(root == null) { return; }
        list.add(root);
        collectRecursively(root.left, list);
        collectRecursively(root.right, list);
    }

    public static void flatten2(TreeNode root) {
        if(root != null) {
            Stack<TreeNode> stack = new Stack<>();
            flattenRecursively(root, stack);
        }
    }

    private static void flattenRecursively(TreeNode root, Stack<TreeNode> stack) {
        if(root.left == null) {
            if(root.right == null) {
                if(stack.isEmpty()) { return; }
                TreeNode node = stack.pop();
                root.right = node;
            }
            flattenRecursively(root.right, stack);
        }else {
            if(root.right != null) {
                stack.push(root.right);
            }
            root.right = root.left;
            root.left = null;
            flattenRecursively(root.right, stack);
        }
    }
}
