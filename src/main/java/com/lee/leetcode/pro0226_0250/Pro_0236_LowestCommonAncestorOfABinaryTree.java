package com.lee.leetcode.pro0226_0250;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes
 p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
       3
    /    \
   5      1
  / \    / \
 6   2  0   8
    / \
   7   4

 Example 1:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 Output: 3
 Explanation: The LCA of nodes 5 and 1 is 3.

 Example 2:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 Output: 5
 Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

 Note:
    All of the nodes' values will be unique.
    p and q are different and both values will exist in the binary tree.
 *
 */
public class Pro_0236_LowestCommonAncestorOfABinaryTree {

    public static void main(String[] args) {
        Integer[] array = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeNode.levelOrderBuild(array);
        TreeNode p = TreeNode.preOrderTraversal(root,0);
        TreeNode q = TreeNode.preOrderTraversal(root,4);
//        TreeNode lca = lowestCommonAncestor(root, p, q);
        TreeNode lca = lowestCommonAncestor1(root, p, q);
        System.out.println(lca.val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<List<TreeNode>> paths = new ArrayList<>(2);
        pathsOf(root, p, q, paths, new Stack<>());
        return commonAncestor(paths.get(0), paths.get(1));
    }

    private static void pathsOf(TreeNode root, TreeNode p, TreeNode q, List<List<TreeNode>> lists, Stack<TreeNode> stack) {
        if(root == null) { return; }
        stack.push(root);
        if(p == root || q == root) { lists.add(new ArrayList<>(stack)); }
        pathsOf(root.left, p, q, lists, stack);
        pathsOf(root.right, p, q, lists, stack);
        stack.pop();
    }

    private static TreeNode commonAncestor(List<TreeNode> a, List<TreeNode> b) {
        int count = Math.min(a.size(), b.size());
        for(int i=0; i<count; i++) {
            if(a.get(i) != b.get(i)) {
                return a.get(i-1);
            }
        }
        return a.get(count-1);
    }

    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) { return null; }
        if(p == root || q == root) { return root; }
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if(left == null) { return right; }
        if(right == null) { return left; }
        return root;
    }
}
