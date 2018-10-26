package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
   3
  / \
 9  20
   /  \
 15    7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]
 *
 */
public class Pro_0103_BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        Integer[] array = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.levelOrderBuild(array);
        List<List<Integer>> result = zigzagLevelOrder(root);
        for(List<Integer> level : result) {
            System.out.println(level);
        }
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) { return levels; }
        List<Integer> level = new ArrayList<>(1);
        level.add(root.val);
        levels.add(level);
        Deque<TreeNode> parent = new ArrayDeque<>();
        parent.offerLast(root);
        boolean leftToRight = false;
        while(true) {
            Deque<TreeNode> current = new ArrayDeque<>(2*parent.size());
            level = new ArrayList<>(current.size());
            if(leftToRight) {
                TreeNode node = null;
                while((node = parent.pollLast()) != null) {
                    if(node.left != null) {
                        current.offerLast(node.left);
                        level.add(node.left.val);
                    }
                    if(node.right != null) {
                        current.offerLast(node.right);
                        level.add(node.right.val);
                    }
                }
            }else {
                TreeNode node = null;
                while((node = parent.pollLast()) != null) {
                    if(node.right != null) {
                        current.offerLast(node.right);
                        level.add(node.right.val);
                    }
                    if(node.left != null) {
                        current.offerLast(node.left);
                        level.add(node.left.val);
                    }
                }
            }
            if(current.isEmpty()) { break; }
            levels.add(level);
            parent = current;
            leftToRight = !leftToRight;
        }
        return levels;
    }
}
