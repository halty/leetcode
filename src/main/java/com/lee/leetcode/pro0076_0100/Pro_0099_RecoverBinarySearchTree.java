package com.lee.leetcode.pro0076_0100;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 Two elements of a binary search tree (BST) are swapped by mistake.

 Recover the tree without changing its structure.

 Example 1:
 Input: [1,3,null,null,2]
   1
  /
 3
  \
   2
 Output: [3,1,null,null,2]
   3
  /
 1
  \
   2

 Example 2:
 Input: [3,1,4,null,null,2]
   3
  / \
 1   4
    /
   2
 Output: [2,1,4,null,null,3]
   2
  / \
 1   4
    /
   3

 Follow up:
 A solution using O(n) space is pretty straight forward.
 Could you devise a constant space solution?
 *
 */
public class Pro_0099_RecoverBinarySearchTree {

    public static void main(String[] args) {
        Integer[] array = {3,1,4,null,null,2};
        TreeNode root = TreeNode.levelOrderBuild(array);
        TreeNode.levelOrderPrint(root);
//        recoverTree1(root);
//        recoverTree2(root);
//        recoverTree3(root);
        recoverTree4(root);
        TreeNode.levelOrderPrint(root);
    }

    public static void recoverTree1(TreeNode root) {
        if(root == null) { return; }
        List<TreeNode> nodes = collect(root);
        recoverAndValidate(nodes, root);
    }

    private static List<TreeNode> collect(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        collectRecursively(root, list);
        return list;
    }

    private static void collectRecursively(TreeNode root, List<TreeNode> resultList) {
        if(root == null) { return; }
        resultList.add(root);
        collectRecursively(root.left, resultList);
        collectRecursively(root.right, resultList);
    }

    private static void recoverAndValidate(List<TreeNode> nodes, TreeNode root) {
        int size = nodes.size();
        if(size == 1) { return; }
        int end = size - 1;
        for(int i=0; i<end; i++) {
            for(int j=i+1; j<size; j++) {
                swap(nodes.get(i), nodes.get(j));
                if(isValidBST(root)) {
                    return;
                }
                swap(nodes.get(i), nodes.get(j));
            }
        }
    }

    private static boolean isValidBST(TreeNode root) {
        return isValidBSTRecursively(root).isValid;
    }

    private static State isValidBSTRecursively(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left != null) {
            State lr = isValidBSTRecursively(left);
            if(!lr.isValid) { return lr; }
            if(right != null) {
                State rr = isValidBSTRecursively(right);
                lr.isValid = rr.isValid && lr.rightMax < root.val && rr.leftMin > root.val;
                if(lr.isValid) { lr.rightMax = rr.rightMax; }
                return lr;
            }else {
                lr.isValid = lr.rightMax < root.val;
                if(lr.isValid) { lr.rightMax = root.val; }
                return lr;
            }
        }else {
            if(right != null) {
                State rr = isValidBSTRecursively(right);
                rr.isValid = rr.isValid && rr.leftMin > root.val;
                if(rr.isValid) { rr.leftMin = root.val; }
                return rr;
            }else {
                return new State(true, root.val, root.val);
            }
        }
    }

    private static class State {
        boolean isValid;
        int leftMin;
        int rightMax;
        State(boolean isValid, int leftMin, int rightMax) {
            this.isValid = isValid;
            this.leftMin = leftMin;
            this.rightMax = rightMax;
        }
    }

    private static void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    public static void recoverTree2(TreeNode root) {
        if(root == null) { return; }
        List<TreeNode> nodes = collectMistakes(root);
        recoverAndValidate(nodes, root);
    }

    private static List<TreeNode> collectMistakes(TreeNode root) {
        Set<TreeNode> set = new HashSet<>();
        collectMistakesRecursively(root, set);
        return new ArrayList<>(set);
    }

    private static Position collectMistakesRecursively(TreeNode root, Set<TreeNode> mistakeSet) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left != null) {
            Position lr = collectMistakesRecursively(left, mistakeSet);
            if(right != null) {
                Position rr = collectMistakesRecursively(right, mistakeSet);
                if(lr.rightMax.val > root.val) {
                    if(rr.leftMin.val < root.val) {
                        mistakeSet.add(lr.rightMax);
                        mistakeSet.add(rr.leftMin);
                    }else {
                        mistakeSet.add(lr.rightMax);
                        mistakeSet.add(root);
                    }
                }else {
                    if(rr.leftMin.val < root.val) {
                        mistakeSet.add(rr.leftMin);
                        mistakeSet.add(root);
                    }
                }
                lr.rightMax = rr.rightMax;
                return lr;
            }else {
                if(lr.rightMax.val > root.val) {
                    mistakeSet.add(lr.rightMax);
                    mistakeSet.add(root);
                }
                lr.rightMax = root;
                return lr;
            }
        }else {
            if(right != null) {
                Position rr = collectMistakesRecursively(right, mistakeSet);
                if(rr.leftMin.val < root.val) {
                    mistakeSet.add(rr.leftMin);
                    mistakeSet.add(root);
                }
                rr.leftMin = root;
                return rr;
            }else {
                return new Position(root, root);
            }
        }
    }

    private static class Position {
        TreeNode leftMin;
        TreeNode rightMax;
        Position(TreeNode leftMin, TreeNode rightMax) {
            this.leftMin = leftMin;
            this.rightMax = rightMax;
        }
    }

    /** 树的遍历算法有(前序、中序、后序、层序)，结合具体的上下文知识选择合适的遍历算法，能够快速解决问题 */
    public static void recoverTree3(TreeNode root) {
        if(root == null) { return; }
        // BST中序遍历构成1个从小到大的有序序列
        List<TreeNode> list = new ArrayList<>();
        inOrderCollectRecursively(root, list);
        int size = list.size();
        if(size == 1) { return; }
        TreeNode first = null, second = null;
        TreeNode prev = list.get(0);
        for(int i=1; i<size; i++) {
            TreeNode cur = list.get(i);
            if(prev.val > cur.val) {
                if(first == null) {
                    first = prev;
                }
                second = cur;
            }
            prev = cur;
        }
        swap(first, second);
    }

    private static void inOrderCollectRecursively(TreeNode root, List<TreeNode> list) {
        if(root == null) { return; }
        inOrderCollectRecursively(root.left, list);
        list.add(root);
        inOrderCollectRecursively(root.right, list);
    }

    public static void recoverTree4(TreeNode root) {
        if(root == null) { return; }
        Swap s = new Swap();
        inOrderTraversalRecursively(root, s);
        s.swap();
    }

    private static void inOrderTraversalRecursively(TreeNode root, Swap s) {
        if(root == null) { return; }
        inOrderTraversalRecursively(root.left, s);
        if(s.prev == null) {
            s.prev = root;
        }else {
            if(s.prev.val > root.val) {
                if(s.first == null) {
                    s.first = s.prev;
                }
                s.second = root;
            }
            s.prev = root;
        }
        inOrderTraversalRecursively(root.right, s);
    }

    private static class Swap {
        TreeNode prev;
        TreeNode first;
        TreeNode second;

        void swap() {
            if(first != null) {
                int tmp = first.val;
                first.val = second.val;
                second.val = tmp;
            }
        }
    }
}
