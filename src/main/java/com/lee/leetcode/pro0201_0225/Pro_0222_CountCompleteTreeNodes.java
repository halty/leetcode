package com.lee.leetcode.pro0201_0225;

import com.lee.leetcode.common.TreeNode;

/**
 *
 Given a complete binary tree, count the number of nodes.

 Note:

 Definition of a complete binary tree from <a href="http://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees">Wikipedia</a>:
 In a complete binary tree every level, except possibly the last, is completely filled,
 and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 Example:
 Input:
      1
    /   \
   2    3
  / \  /
 4  5 6
 Output: 6
 *
 */
public class Pro_0222_CountCompleteTreeNodes {

    public static void main(String[] args) {
        Integer[] array = {1,2,3,4,5,6};
        TreeNode root = TreeNode.levelOrderBuild(array);
//        int count = countNodes(root);
        int count = countNodes1(root);
        System.out.println(count);
    }

    public static int countNodes(TreeNode root) {
        if(root == null) { return 0; }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public static int countNodes1(TreeNode root) {
        if(root == null) { return 0; }
        int treeLevel = leftLevelOf(root);
        int count = 0;
        while(root != null) {
            count += 1;
            TreeNode left = root.left;
            if(left == null) { break; }
            int level = rightLevelOf(left);
            if(level == treeLevel - 1) {
                count += Math.pow(2, level + 1) - 1;
                root = root.right;
                if(root == null) { break; }
                treeLevel = leftLevelOf(root);
                if(treeLevel != level) {
                    count += Math.pow(2, treeLevel + 1) - 1;
                    break;
                }
            } else {
                count += Math.pow(2, level + 1) - 1;
                root = root.left;
                treeLevel -= 1;
            }
        }
        return count;
    }

    private static int leftLevelOf(TreeNode root) {
        int level = 0;
        for(; (root = root.left) != null; level++);
        return level;
    }

    private static int rightLevelOf(TreeNode root) {
        int level = 0;
        for(; (root = root.right) != null; level++);
        return level;
    }
}
