package com.lee.leetcode.common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) { this.val = val; }

    public static void preOrderPrint(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrderCollect(root, list);
        printListAndIgnoreTailNull(list);
    }

    private static void printListAndIgnoreTailNull(List<Integer> list) {
        int end = list.size() - 1;
        while(end >= 0) {
            if(list.get(end) != null) {
                break;
            }
            end--;
        }
        if(end >= 0) {
            System.out.print(list.get(0));
            for(int i=1; i<=end; i++) {
                System.out.print(","+list.get(i));
            }
            System.out.println();
        }
    }

    private static void preOrderCollect(TreeNode root, List<Integer> list) {
        if(root == null) {
            list.add(null);
            return;
        }
        list.add(root.val);
        preOrderCollect(root.left, list);
        preOrderCollect(root.right, list);
    }

    public static void levelOrderPrint(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        levelOrderCollect(root, list);
        printListAndIgnoreTailNull(list);
    }

    private static void levelOrderCollect(TreeNode root, List<Integer> list) {
        if(root == null) { return; }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        list.add(root.val);
        while(!q.isEmpty()) {
            TreeNode r = q.poll();
            TreeNode left = r.left;
            if(left != null) {
                q.offer(left);
                list.add(left.val);
            }else {
                list.add(null);
            }
            TreeNode right = r.right;
            if(right != null) {
                q.offer(right);
                list.add(right.val);
            }else {
                list.add(null);
            }
        }
    }

    public static void inOrderPrint(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrderCollect(root, list);
        printListAndIgnoreTailNull(list);
    }

    private static void inOrderCollect(TreeNode root, List<Integer> list) {
        if(root == null) { return; }
        inOrderCollect(root.left, list);
        list.add(root.val);
        inOrderCollect(root.right, list);
    }

    public static TreeNode preOrderBuild(Integer[] array) {
        return preOrderBuild(new Source(array));
    }

    private static TreeNode preOrderBuild(Source source) {
        Integer value = source.next();
        if(value == null) { return null; }
        TreeNode root = new TreeNode(value);
        root.left = preOrderBuild(source);
        root.right = preOrderBuild(source);
        return root;
    }

    private static class Source {
        Integer[] values;
        int index;
        Source(Integer[] values) {
            this.values = values;
            this.index = 0;
        }
        Integer next() {
            return index < values.length ? values[index++] : null;
        }
    }

    public static TreeNode levelOrderBuild(Integer[] array) {
        if(array.length == 0) { return null; }
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int i = 1;
        while(!q.isEmpty()) {
            TreeNode r = q.poll();
            if(i == array.length) { break; }
            Integer leftValue = array[i++];
            if(leftValue != null) {
                r.left = new TreeNode(leftValue);
                q.offer(r.left);
            }
            if(i == array.length) { break; }
            Integer rightValue = array[i++];
            if(rightValue != null) {
                r.right = new TreeNode(rightValue);
                q.offer(r.right);
            }
        }
        return root;
    }

    public static TreeNode preOrderTraversal(TreeNode root, int val) {
        if(root == null || root.val == val) { return root; }
        TreeNode target = preOrderTraversal(root.left, val);
        if(target != null) { return target; }
        return preOrderTraversal(root.right, val);
    }
}
