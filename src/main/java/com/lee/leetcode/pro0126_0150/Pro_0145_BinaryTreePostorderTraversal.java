package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.TreeNode;

import java.util.*;

/**
 *
 Given a binary tree, return the postorder traversal of its nodes' values.

 Example:
 Input: [1,null,2,3]
 1
  \
   2
  /
 3
 Output: [3,2,1]
 Follow up: Recursive solution is trivial, could you do it iteratively?


 *
 */
public class Pro_0145_BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        Integer[] array = {1,2,3,4,null,null,5};
        TreeNode root = TreeNode.levelOrderBuild(array);
//        List<Integer> list = postorderTraversal(root);
//        List<Integer> list = postorderTraversalInteratively(root);
        List<Integer> list = postorderTraversalInteratively1(root);
        System.out.println(list);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) { return Collections.emptyList(); }
        List<Integer> result = new ArrayList<>();
        postorderTraversalRecursively(root, result);
        return result;
    }

    private static void postorderTraversalRecursively(TreeNode root, List<Integer> result) {
        if(root.left != null) {
            postorderTraversalRecursively(root.left, result);
        }
        if(root.right != null) {
            postorderTraversalRecursively(root.right, result);
        }
        result.add(root.val);
    }

    public static List<Integer> postorderTraversalInteratively(TreeNode root) {
        if(root == null) { return Collections.emptyList(); }
        List<Integer> result = new ArrayList<>();
        Deque<State> q = new ArrayDeque<>();
        TreeNode e = root;
        while(true) {
            while(e.left != null) {
                q.offerLast(new State(e, false));
                e = e.left;
            }
            if(e.right != null) {
                q.offerLast(new State(e, true));
                e = e.right;
            }else {
                result.add(e.val);
                State s = null;
                while((s=q.peekLast()) != null) {
                    if(s.isRightVisited) {
                        result.add(s.node.val);
                        q.pollLast();
                    }else {
                        TreeNode right = s.node.right;
                        if(right != null) {
                            s.isRightVisited = true;
                            e = right;
                            break;
                        }else {
                            result.add(s.node.val);
                            q.pollLast();
                        }
                    }
                }
                if(s == null) { break; }
            }
        }
        return result;
    }

    private static class State {
        TreeNode node;
        boolean isRightVisited;
        State(TreeNode node, boolean isRightVisited) {
            this.node = node;
            this.isRightVisited = isRightVisited;
        }
    }

    public static List<Integer> postorderTraversalInteratively1(TreeNode root) {
        if(root == null) { return Collections.emptyList(); }
        List<Integer> result = new ArrayList<>();
        TreeNode e = root;
        while(e != null) {
            TreeNode left = e.left;
            if(left != null) {
                TreeNode prev = left;
                TreeNode r = left.right;
                while(r != null && r != e) {
                    prev = r;
                    r = r.right;
                }
                if(r == null) {
                    prev.right = e;
                    e = e.left;
                }else {
                    int insertIndex = result.size();
                    TreeNode t = e.left;
                    while(t != e) {
                        result.add(insertIndex, t.val);
                        t = t.right;
                    }
                    prev.right = null;
                    e = e.right;
                }
            }else {
                e = e.right;
            }
        }
        int insertIndex = result.size();
        TreeNode t = root;
        while(t != null) {
            result.add(insertIndex, t.val);
            t = t.right;
        }
        return result;
    }
}
