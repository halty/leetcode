package com.lee.leetcode.pro0226_0250;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes
 p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
       6
    /    \
   2      8
  / \    / \
 0   4  7   9
    / \
   3   5

 Example 1:
 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 Output: 6
 Explanation: The LCA of nodes 2 and 8 is 6.

 Example 2:
 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 Output: 2
 Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

 Note:
    All of the nodes' values will be unique.
    p and q are different and both values will exist in the BST.
 *
 */
public class Pro_235_LowestCommonAncestorOfABinarySearchTree {

    public static void main(String[] args) {
        Integer[] array = {6,2,8,0,4,7,9,null,null,3,5};
        TreeNode root = TreeNode.levelOrderBuild(array);
        TreeNode p = TreeNode.preOrderTraversal(root,3);
        TreeNode q = TreeNode.preOrderTraversal(root,9);
//        TreeNode lca = lowestCommonAncestor(root, p, q);
        TreeNode lca = lowestCommonAncestor1(root, p, q);
        System.out.println(lca.val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = pathOf(root, p.val);
        List<TreeNode> pathQ = pathOf(root, q.val);
        return commonAncestor(pathP, pathQ);
    }

    private static List<TreeNode> pathOf(TreeNode root, int target) {
        List<TreeNode> list = new ArrayList<>();
        while(target != root.val) {
            list.add(root);
            root = target < root.val ? root.left : root.right;
        }
        list.add(root);
        return list;
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
        while(true) {
            if(p == root || q == root) { return root; }
            if(p.val < root.val) {
                if(q.val < root.val) {
                    root = root.left;
                }else {
                    return root;
                }
            }else {
                if(q.val < root.val) {
                    return root;
                }else {
                    root = root.right;
                }
            }
        }
    }
}
