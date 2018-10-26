package com.lee.leetcode.pro0101_0125;

import com.lee.leetcode.common.TreeNode;

import java.util.Stack;

/**
 *
 Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

     1
   /   \
  2     2
 / \   / \
 3  4 4   3
 But the following [1,2,2,null,3,null,3] is not:
   1
  / \
 2   2
  \   \
   3   3
 Note:
 Bonus points if you could solve it both recursively and iteratively.
 *
 */
public class Pro_0101_SymmetricTree {

    public static void main(String[] args) {
        Integer[] array = {1,2,2,3,4,4,3};
        TreeNode root = TreeNode.levelOrderBuild(array);
//        boolean result = isSymmetric1(root);
        boolean result = isSymmetric2(root);
        System.out.println(result);
    }

    public static boolean isSymmetric1(TreeNode root) {
        if(root == null) {
            return true;
        }else {
            return isSymmetricRecursively(root.left, root.right);
        }
    }

    private static boolean isSymmetricRecursively(TreeNode a, TreeNode b) {
        if(a == null) {
            if(b == null) {
                return true;
            }else {
                return false;
            }
        }else {
            if(b == null) {
                return false;
            }else {
                boolean isSymmetric = isSymmetricRecursively(a.left, b.right);
                if(!isSymmetric) { return isSymmetric; }
                if(a.val != b.val) { return false; }
                return isSymmetricRecursively(a.right, b.left);
            }
        }
    }

    public static boolean isSymmetric2(TreeNode root) {
        if(root == null) { return true; }
        if(root.left == null) {
            return root.right == null;
        }else {
            if(root.right == null) {
                return false;
            }
            Stack<State> stack = new Stack<>();
            stack.push(new State(root.left, root.right));
            while(!stack.isEmpty()) {
                State s = stack.pop();
                if(s.a.val != s.b.val || !judge(stack, s.a.left, s.b.right) || !judge(stack, s.a.right, s.b.left)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean judge(Stack<State> stack, TreeNode a, TreeNode b) {
        if(a == null) {
            if(b != null) {
                return false;
            }
        }else {
            if(b == null) {
                return false;
            }
            stack.push(new State(a, b));
        }
        return true;
    }

    private static class State {
        TreeNode a;
        TreeNode b;
        State(TreeNode a, TreeNode b) {
            this.a = a;
            this.b = b;
        }
    }
}
