package com.lee.leetcode.pro0251_0275;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 Given a binary tree, return all root-to-leaf paths.
 Note: A leaf is a node with no children.

 Example:
 Input:
    1
  /   \
 2     3
  \
   5
 Output: ["1->2->5", "1->3"]
 Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 *
 */
public class Pro_0257_BinaryTreePaths {

    public static void main(String[] args) {
        Integer[] array = {1,2,3,null,5};
        TreeNode root = TreeNode.levelOrderBuild(array);
        List<String> paths = binaryTreePaths(root);
        System.out.println(paths);
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        if(root == null) { return Collections.emptyList(); }
        List<String> paths = new ArrayList<>();
        traversalPath(root, new ArrayList<>(), paths);
        return paths;
    }

    private static void traversalPath(TreeNode root, List<String> stack, List<String> paths) {
        stack.add(String.valueOf(root.val));
        if(root.left == null) {
            if(root.right == null) {
                paths.add(buildPath(stack));
            }else {
                traversalPath(root.right, stack, paths);
            }
        }else {
            traversalPath(root.left, stack, paths);
            if(root.right != null) {
                traversalPath(root.right, stack, paths);
            }
        }
        stack.remove(stack.size()-1);
    }

    private static String buildPath(List<String> stack) {
        int count = stack.size();
        StringBuilder buf = new StringBuilder(count*3);
        buf.append(stack.get(0));
        for(int i=1; i<count; i++) {
            buf.append("->").append(stack.get(i));
        }
        return buf.toString();
    }
}
