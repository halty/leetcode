package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.TreeNode;

import java.util.*;

/**
 *
 Given a binary tree, return the preorder traversal of its nodes' values.

 Example:
 Input: [1,null,2,3]
 1
  \
   2
  /
 3
 Output: [1,2,3]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 */
public class Pro_0144_BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        Integer[] array = {2,1,3,null,4};
        TreeNode root = TreeNode.levelOrderBuild(array);
//        List<Integer> list = preorderTraversal(root);
//        List<Integer> list = preorderTraversalIteratively(root);
        List<Integer> list = preorderTraversalIteratively1(root);
        System.out.println(list);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) { return Collections.emptyList(); }
        List<Integer> result = new ArrayList<>();
        preorderTraversalRecursively(root, result);
        return result;
    }

    private static void preorderTraversalRecursively(TreeNode root, List<Integer> result) {
        result.add(root.val);
        if(root.left != null) {
            preorderTraversalRecursively(root.left, result);
        }
        if(root.right != null) {
            preorderTraversalRecursively(root.right, result);
        }
    }

    public static List<Integer> preorderTraversalIteratively(TreeNode root) {
        if(root == null) { return Collections.emptyList(); }
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offerLast(root);
        TreeNode e = null;
        while(e != null || (e=q.pollLast()) != null) {
            result.add(e.val);
            if(e.right != null) { q.offerLast(e.right); }
            e = e.left;
        }
        return result;
    }

    public static List<Integer> preorderTraversalIteratively1(TreeNode root) {
        if(root == null) { return Collections.emptyList(); }
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while(cur != null) {
            TreeNode left = cur.left;
            if(left == null) {
                result.add(cur.val);
                cur = cur.right;
            }else { // use leaf node's right field as a thread pointer which point to the succeed node
                TreeNode prev = left;
                TreeNode r = left.right;
                while(r != null && r != cur) {
                    prev = r;
                    r = r.right;
                }
                if(r == null) {
                    result.add(cur.val);
                    prev.right = cur;
                    cur = left;
                }else {
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return result;
    }
}
