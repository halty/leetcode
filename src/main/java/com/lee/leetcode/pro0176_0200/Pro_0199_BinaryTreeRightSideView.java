package com.lee.leetcode.pro0176_0200;

import com.lee.leetcode.common.TreeNode;

import java.util.*;

/**
 *
 Given a binary tree, imagine yourself standing on the right side of it,
 return the values of the nodes you can see ordered from top to bottom.

 Example:
 Input: [1,2,3,null,5,null,4]
 Output: [1, 3, 4]

 Explanation:
    1           <---
   / \
  2   3         <---
   \   \
    5   4       <---
 *
 */
public class Pro_0199_BinaryTreeRightSideView {

    public static void main(String[] args) {
//        Integer[] array = {1,2,3,null,5,null,4};
        Integer[] array = {1,2,3,5,null,4};
        TreeNode root = TreeNode.levelOrderBuild(array);
//        List<Integer> list = rightSideView(root);
        List<Integer> list = rightSideView1(root);
        System.out.println(list);
    }

    public static List<Integer> rightSideView(TreeNode root) {
        if(root == null) { return Collections.emptyList(); }
        List<Integer> resultList = new ArrayList<>();
        rightSideViewRecursively(root, resultList, 1);
        return resultList;
    }

    public static void rightSideViewRecursively(TreeNode root, List<Integer> resultList, int height) {
        if(height > resultList.size()) {
            resultList.add(root.val);
        }
        if(root.right != null) {
            rightSideViewRecursively(root.right, resultList, height + 1);
        }
        if(root.left != null) {
            rightSideViewRecursively(root.left, resultList, height + 1);
        }
    }

    public static List<Integer> rightSideView1(TreeNode root) {
        if(root == null) { return Collections.emptyList(); }
        List<Integer> resultList = new ArrayList<>();
        Stack stack = new Stack(16);
        stack.push(new Element(root, 1));
        Element e = null;
        while((e = stack.pop()) != null) {
            TreeNode node = e.node;
            int height = e.height;
            if(height > resultList.size()) {
                resultList.add(node.val);
            }
            TreeNode child = node.left;
            if(child != null) {
                e.height = height + 1;
                e.node = child;
                stack.push(e);
            }
            child = node.right;
            if(child != null) {
                stack.push(new Element(child, height+1));
            }
        }
        return resultList;
    }

    private static class Element {
        TreeNode node;
        int height;
        Element(TreeNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }

    private static class Stack {
        Element[] elements;
        int size;
        Stack(int capacity) {
            this.elements = new Element[capacity];
            this.size = 0;
        }
        void push(Element e) {
            if(size == elements.length) {
                ensureCapacity(size + size >> 1);
            }
            elements[size++] = e;
        }
        void ensureCapacity(int newCapacity) {
            elements = Arrays.copyOf(elements, newCapacity);
        }
        Element pop() {
            return size == 0 ? null : elements[--size];
        }
    }
}
