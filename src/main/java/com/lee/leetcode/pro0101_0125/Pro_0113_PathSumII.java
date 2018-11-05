package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

       5
      / \
     4   8
    /   / \
   11  13  4
  /  \    / \
 7    2  5   1

 Return:
 [
   [5,4,11,2],
   [5,8,4,5]
 ]
 *
 */
public class Pro_0113_PathSumII {

    public static void main(String[] args) {
        Integer[] array = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        int sum = 22;
        TreeNode root = TreeNode.levelOrderBuild(array);
        List<List<Integer>> paths = pathSum(root, sum);
        for(List<Integer> path : paths) {
            System.out.println(path);
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) { return Collections.emptyList(); }
        List<List<Integer>> paths = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        collectPathSumRecursively(root, sum, paths, stack);
        return paths;
    }

    private static void collectPathSumRecursively(TreeNode root, int sum, List<List<Integer>> paths, Stack<Integer> stack) {
        if(root.left == null) {
            if(root.right == null) {
                if(root.val == sum) {
                    stack.push(root.val);
                    paths.add((List<Integer>)stack.clone());
                    stack.pop();
                }
            }else {
                stack.push(root.val);
                collectPathSumRecursively(root.right, sum-root.val, paths, stack);
                stack.pop();
            }
        }else {
            if(root.right == null) {
                stack.push(root.val);
                collectPathSumRecursively(root.left, sum-root.val, paths, stack);
                stack.pop();
            }else {
                stack.push(root.val);
                collectPathSumRecursively(root.left, sum-root.val, paths, stack);
                collectPathSumRecursively(root.right, sum-root.val, paths, stack);
                stack.pop();
            }
        }
    }
}
