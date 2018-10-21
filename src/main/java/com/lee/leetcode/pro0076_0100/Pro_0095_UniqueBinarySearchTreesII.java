package com.lee.leetcode.pro0076_0100;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

 Example:

 Input: 3
 Output:
 [
 [1,null,3,2],
 [3,2,1],
 [3,1,null,2],
 [2,1,null,null,3],
 [1,null,2,null,3]
 ]
 Explanation:
 The above output corresponds to the 5 unique BST's shown below:

 1         3     3      2      1
  \       /     /      / \      \
   3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3
 *
 */
public class Pro_0095_UniqueBinarySearchTreesII {

    public static void main(String[] args) {
        int n = 3;
        List<TreeNode> resultList = generateTrees(n);
        for(TreeNode node : resultList) {
            TreeNode.preOrderPrint(node);
        }
    }

    public static List<TreeNode> generateTrees(int n) {
        return generateTreesRecursively(1, n);
    }

    private static List<TreeNode> generateTreesRecursively(int min, int max) {
        if(min > max) {
            return Collections.emptyList();
        }else if(min == max) {
            List<TreeNode> list = new ArrayList<>(1);
            list.add(new TreeNode(min));
            return list;
        }else {
            int n = max - min + 1;
            int count = n * (int)Math.pow(2, n);    // estimate n*2^n < count < n!
            List<TreeNode> list = new ArrayList<>(count);
            for(int i=min; i<=max; i++) {
                List<TreeNode> leftList = generateTreesRecursively(min, i-1);
                List<TreeNode> rightList = generateTreesRecursively(i+1, max);
                list.addAll(combine(i, leftList, rightList));
            }
            return list;
        }
    }

    private static List<TreeNode> combine(int rootValue, List<TreeNode> leftList, List<TreeNode> rightList) {
        int leftCount = leftList.isEmpty() ? 1 : leftList.size();
        int rightCount = rightList.isEmpty() ? 1: rightList.size();
        int totalCount = leftCount * rightCount;
        List<TreeNode> list = new ArrayList<>(totalCount);
        if(leftList.isEmpty()) {
            if(rightList.isEmpty()) {
                list.add(new TreeNode(rootValue));
            }else {
                for(TreeNode right : rightList) {
                    TreeNode root = new TreeNode(rootValue);
                    root.right = right;
                    list.add(root);
                }
            }
        }else {
            if(rightList.isEmpty()) {
                for(TreeNode left : leftList) {
                    TreeNode root = new TreeNode(rootValue);
                    root.left = left;
                    list.add(root);
                }
            }else {
                for(TreeNode left : leftList) {
                    for(TreeNode right : rightList) {
                        TreeNode root = new TreeNode(rootValue);
                        root.left = left;
                        root.right = right;
                        list.add(root);
                    }
                }
            }
        }
        return list;
    }
}
