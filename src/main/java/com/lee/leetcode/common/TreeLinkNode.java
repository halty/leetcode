package com.lee.leetcode.common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeLinkNode {

    public int val;
    public TreeLinkNode left;
    public TreeLinkNode right;
    public TreeLinkNode next;

    public TreeLinkNode(int val) {
        this.val = val;
    }

    public static TreeLinkNode levelOrderBuild(Integer[] array) {
        if(array.length == 0) { return null; }
        TreeLinkNode root = new TreeLinkNode(array[0]);
        Queue<TreeLinkNode> q = new ArrayDeque<>();
        q.offer(root);
        int i = 1;
        while(!q.isEmpty()) {
            TreeLinkNode r = q.poll();
            if(i == array.length) { break; }
            Integer leftValue = array[i++];
            if(leftValue != null) {
                r.left = new TreeLinkNode(leftValue);
                q.offer(r.left);
            }
            if(i == array.length) { break; }
            Integer rightValue = array[i++];
            if(rightValue != null) {
                r.right = new TreeLinkNode(rightValue);
                q.offer(r.right);
            }
        }
        return root;
    }

    public static void levelOrderPrint(TreeLinkNode root) {
        List<Integer> list = new ArrayList<>();
        levelOrderCollect(root, list);
        printListAndIgnoreTailNull(list);
    }

    private static void levelOrderCollect(TreeLinkNode root, List<Integer> list) {
        if(root == null) { return; }
        Queue<TreeLinkNode> q = new ArrayDeque<>();
        q.offer(root);
        list.add(root.val);
        while(!q.isEmpty()) {
            TreeLinkNode r = q.poll();
            TreeLinkNode left = r.left;
            if(left != null) {
                q.offer(left);
                list.add(left.val);
            }else {
                list.add(null);
            }
            TreeLinkNode right = r.right;
            if(right != null) {
                q.offer(right);
                list.add(right.val);
            }else {
                list.add(null);
            }
        }
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
                System.out.print("," + list.get(i));
            }
            System.out.println();
        }
    }

    public static void levelNextPrint(TreeLinkNode root) {
        TreeLinkNode head = root;
        while(head != null) {
            TreeLinkNode p = head;
            System.out.print(p.val);
            head = (p.left != null ? p.left : p.right);
            while(p.next != null) {
                p = p.next;
                System.out.print(","+p.val);
                if(head == null) {
                    head = (p.left != null ? p.left : p.right);
                }
            }
            System.out.println();
        }
    }
}
