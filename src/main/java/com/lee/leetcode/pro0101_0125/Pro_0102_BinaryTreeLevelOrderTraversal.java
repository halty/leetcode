package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
   3
  / \
 9  20
   /  \
  15   7
 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]
 *
 */
public class Pro_0102_BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Integer[] array = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.levelOrderBuild(array);
        List<List<Integer>> result = levelOrder(root);
        for(List<Integer> level : result) {
            System.out.println(level);
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) { return levels; }
        List<Integer> level = new ArrayList<>(1);
        level.add(root.val);
        levels.add(level);
        List<TreeNode> parent = new ArrayList<>(1);
        parent.add(root);
        while(true) {
            List<TreeNode> current = new ArrayList<>(2*parent.size());
            level = new ArrayList<>(current.size());
            for(TreeNode node : parent) {
                if(node.left != null) {
                    current.add(node.left);
                    level.add(node.left.val);
                }
                if(node.right != null) {
                    current.add(node.right);
                    level.add(node.right.val);
                }
            }
            if(current.isEmpty()) { break; }
            levels.add(level);
            parent = current;
        }
        return levels;
    }
}
