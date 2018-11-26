package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 An example is the root-to-leaf path 1->2->3 which represents the number 123.
 Find the total sum of all root-to-leaf numbers.
 Note: A leaf is a node with no children.

 Example:
 Input: [1,2,3]
      1
     / \
    2   3
 Output: 25
 Explanation:
    The root-to-leaf path 1->2 represents the number 12.
    The root-to-leaf path 1->3 represents the number 13.
    Therefore, sum = 12 + 13 = 25.

 Example 2:
 Input: [4,9,0,5,1]
     4
    / \
   9   0
  / \
 5   1
 Output: 1026
 Explanation:
    The root-to-leaf path 4->9->5 represents the number 495.
    The root-to-leaf path 4->9->1 represents the number 491.
    The root-to-leaf path 4->0 represents the number 40.
    Therefore, sum = 495 + 491 + 40 = 1026.
 *
 */
public class Pro_0129_SumRootToLeafNumbers {

    public static void main(String[] args) {
//        Integer[] array = {1,2,3};
        Integer[] array = {4,9,0,5,1};
        TreeNode root = TreeNode.levelOrderBuild(array);
        int sum = sumNumbers(root);
        System.out.println(sum);
    }

    public static int sumNumbers(TreeNode root) {
        if(root == null) { return 0; }
        return sumNumbersRecursively(root, 0);
    }


    private static int sumNumbersRecursively(TreeNode root, int highSum) {
        if(root.left == null) {
            if(root.right == null) {
                return highSum + root.val;
            }else {
                return sumNumbersRecursively(root.right, (highSum+root.val)*10);
            }
        }else {
            highSum = (highSum+root.val)*10;
            int sum = sumNumbersRecursively(root.left, highSum);
            if(root.right != null) {
                sum += sumNumbersRecursively(root.right, highSum);
            }
            return sum;
        }
    }
}
