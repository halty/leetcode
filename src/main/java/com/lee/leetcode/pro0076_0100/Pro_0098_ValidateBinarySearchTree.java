package com.lee.leetcode.pro0076_0100;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:

 Input:
   2
  / \
 1   3
 Output: true
 Example 2:

     5
    / \
   1   4
      / \
     3   6
 Output: false

 Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 is 5 but its right child's value is 4.
 *
 */
public class Pro_0098_ValidateBinarySearchTree {

    public static void main(String[] args) {
        Integer[] array = {5,1,6,null,null,3,7};
        TreeNode root = TreeNode.levelOrderBuild(array);
        TreeNode.preOrderPrint(root);
//        boolean result = isValidBST1(root);
//        boolean result = isValidBST2(root);
        boolean result = isValidBST3(root);
        System.out.println(result);
    }

    public static boolean isValidBST1(TreeNode root) {
        if(root == null) { return true; }
        Range r = isValidBSTRecursively(root);
        return r.isValid;
    }

    public static Range isValidBSTRecursively(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left != null) {
            Range lr = isValidBSTRecursively(left);
            if(!lr.isValid) { return lr; }
            if(right != null) {
                Range rr = isValidBSTRecursively(right);
                lr.isValid = rr.isValid && lr.max < root.val && rr.min > root.val;
                if(lr.isValid) { lr.max = rr.max; }
                return lr;
            }else {
                lr.isValid = lr.max < root.val;
                if(lr.isValid) { lr.max = root.val; }
                return lr;
            }
        }else {
            if(right != null) {
                Range rr = isValidBSTRecursively(right);
                rr.isValid = rr.isValid && rr.min > root.val;
                if(rr.isValid) { rr.min = root.val; }
                return rr;
            }else {
                return new Range(true, root.val, root.val);
            }
        }
    }

    private static class Range {
        boolean isValid;
        int min;
        int max;
        Range(boolean isValid, int min, int max) {
            this.isValid = isValid;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean isValidBST2(TreeNode root) {
        if(root == null) { return true; }
        List<TreeNode> nodes = new ArrayList<>();
        List<State> states = new ArrayList<>();
        nodes.add(root);
        states.add(new State(root.val, null, false));
        for(int i=0; i<nodes.size(); i++) {
            TreeNode r = nodes.get(i);
            State s = states.get(i);
            TreeNode lr = r.left;
            if(lr != null) {
                if(lr.val >= r.val) { return false; }
                nodes.add(lr);
                states.add(new State(lr.val, s, true));
            }
            TreeNode rr = r.right;
            if(rr != null) {
                if(rr.val <= r.val) { return false; }
                nodes.add(rr);
                states.add(new State(rr.val, s, false));
            }
        }
        for(int k=states.size()-1; k>=0; k--) {
            State s = states.get(k);
            int min = s.root;
            int max = s.root;
            if(s.lMax != null) {
                if(s.lMax >= s.root) { return false; }
                min = s.lMin;
            }
            if(s.rMin != null) {
                if(s.rMin <= s.root) { return  false; }
                max = s.rMax;
            }
            State p = s.parent;
            if(p != null) {
                if(s.isLeftSubtree) {
                    p.lMin = min;
                    p.lMax = max;
                }else {
                    p.rMin = min;
                    p.rMax = max;
                }
            }
        }
        return true;
    }

    private static class State {
        Integer root;
        State parent;
        boolean isLeftSubtree;
        Integer lMin;
        Integer lMax;
        Integer rMin;
        Integer rMax;
        State(Integer root, State parent, boolean isLeftSubtree) {
            this.root = root;
            this.parent = parent;
            this.isLeftSubtree = isLeftSubtree;
        }
    }

    public static boolean isValidBST3(TreeNode root) {
        Judge judge = new Judge();
        inOrderJudgeRecursively(root, judge);
        return judge.isValid;
    }

    private static void inOrderJudgeRecursively(TreeNode root, Judge judge) {
        if(root == null) { return; }
        inOrderJudgeRecursively(root.left, judge);
        if(!judge.isValid) { return; }
        if(judge.prev == null) {
            judge.prev = root;
        }else {
            if(!(judge.isValid = judge.prev.val < root.val)) {
                return;
            }else {
                judge.prev = root;
            }
        }
        inOrderJudgeRecursively(root.right, judge);
    }

    private static class Judge {
        boolean isValid = true;
        TreeNode prev = null;
    }
}
