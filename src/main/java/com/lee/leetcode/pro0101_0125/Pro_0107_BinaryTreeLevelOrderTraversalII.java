package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
   3
  / \
 9  20
   /  \
  15   7
 return its bottom-up level order traversal as:
 [
 [15,7],
 [9,20],
 [3]
 ]
 *
 */
public class Pro_0107_BinaryTreeLevelOrderTraversalII {

    public static void main(String[] args) {
        Integer[] array = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.levelOrderBuild(array);
        List<List<Integer>> result = levelOrderBottom(root);
        for(List<Integer> level : result) {
            System.out.println(level);
        }
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = new LinkedList<>();
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
            levels.add(0, level);
            parent = current;
        }
        return levels;
    }
}
