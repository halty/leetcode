package com.lee.leetcode.pro0276_0300;

import com.lee.leetcode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 *
 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
 You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 Example:
 You may serialize the following tree:
     1
    / \
   2   3
      / \
     4   5
 as "[1,2,3,null,null,4,5]"

 Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format,
 so please be creative and come up with different approaches yourself.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 */
public class Pro_0297_SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        Integer[] array = {1,2,3,null,null,4,5};
        TreeNode root = TreeNode.levelOrderBuild(array);
//        String data = serialize(root);
//        String data = serialize1(root);
        String data = serialize2(root);
        System.out.println(data);
//        TreeNode r = deserialize(data);
//        TreeNode r = deserialize1(data);
        TreeNode r = deserialize2(data);
        TreeNode.levelOrderPrint(r);
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if(root == null) { return ""; }
        int index = 0;
        StringBuilder buf = new StringBuilder();
        buf.append(index).append(':').append(root.val);
        appendRecursively(root.left, 1, buf);
        appendRecursively(root.right, 2, buf);
        return buf.toString();
    }

    private static void appendRecursively(TreeNode r, int index, StringBuilder buf) {
        if(r == null) { return; }
        buf.append(',').append(index).append(':').append(r.val);
        index = index * 2 + 1;
        appendRecursively(r.left, index, buf);
        appendRecursively(r.right, index+1, buf);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data.isEmpty()) { return null; }
        String[] array = data.split(",");
        if(array.length == 1) {
            int pos = array[0].indexOf(':');
            return new TreeNode(Integer.parseInt(array[0].substring(pos+1)));
        }
        Map<Integer, Integer> cache = new HashMap<>(array.length);
        for(String element : array) {
            int pos = element.indexOf(':');
            Integer index = Integer.parseInt(element.substring(0, pos));
            Integer val = Integer.parseInt(element.substring(pos+1));
            cache.put(index, val);
        }
        TreeNode root = new TreeNode(cache.get(0));
        rebuildRecursively(root, 0, cache);
        return root;
    }

    private static void rebuildRecursively(TreeNode parent, int parentIndex, Map<Integer, Integer> cache) {
        int index = parentIndex * 2 + 1;
        Integer val = cache.get(index);
        if(val != null) {
            parent.left = new TreeNode(val);
            rebuildRecursively(parent.left, index, cache);
        }
        index += 1;
        val = cache.get(index);
        if(val != null) {
            parent.right = new TreeNode(val);
            rebuildRecursively(parent.right, index, cache);
        }
    }

    private static class Element {
        int index;
        TreeNode node;
        Element(int index, TreeNode node) {
            this.index = index;
            this.node = node;
        }
    }

    // Encodes a tree to a single string.
    public static String serialize1(TreeNode root) {
        if(root == null) { return ""; }
        int index = 0;
        StringBuilder buf = new StringBuilder();
        buf.append(index).append(':').append(root.val);
        Queue<Element> queue = new ArrayDeque<>();
        if(root.left != null) {
            queue.offer(new Element(1, root.left));
        }
        if(root.right != null) {
            queue.offer(new Element(2, root.right));
        }
        Element e;
        while((e = queue.poll()) != null) {
            index = e.index;
            root = e.node;
            buf.append(',').append(index).append(':').append(root.val);
            if(root.left == null) {
                if(root.right != null) {
                    e.index = index * 2 + 2;
                    e.node = root.right;
                    queue.offer(e);
                }
            }else {
                index = index * 2 + 1;
                if(root.right == null) {
                    e.index = index;
                    e.node = root.left;
                    queue.offer(e);
                }else {
                    queue.offer(new Element(index, root.left));
                    index += 1;
                    e.index = index;
                    e.node = root.right;
                    queue.offer(e);
                }
            }
        }
        return buf.toString();
    }

    public static TreeNode deserialize1(String data) {
        if(data.isEmpty()) { return null; }
        String[] array = data.split(",");
        if(array.length == 1) {
            int pos = array[0].indexOf(':');
            return new TreeNode(Integer.parseInt(array[0].substring(pos+1)));
        }
        Map<Integer, TreeNode> cache = new HashMap<>(array.length);
        Integer index, val, subIndex;
        TreeNode node = null, sub;
        for(int i=array.length-1; i>=0; i--) {
            String field = array[i];
            int pos = field.indexOf(':');
            index = Integer.parseInt(field.substring(0, pos));
            val = Integer.parseInt(field.substring(pos+1));
            node = new TreeNode(val);
            subIndex = index * 2 + 1;
            sub = cache.get(subIndex);
            if(sub != null) { node.left = sub; }
            subIndex += 1;
            sub = cache.get(subIndex);
            if(sub != null) { node.right = sub; }
            cache.put(index, node);
        }
        return node;
    }

    public static String serialize2(TreeNode root) {
        if(root == null) { return ""; }
        StringBuilder buf = new StringBuilder();
        TreeNode dummy = new TreeNode(0);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while((root = queue.poll()) != null) {
            if(dummy == root) {
                buf.append(',');
                continue;
            }
            buf.append(root.val).append(',');
            if(root.left != null) {
                queue.offer(root.left);
            }else {
                queue.offer(dummy);
            }
            if(root.right != null) {
                queue.offer(root.right);
            }else {
                queue.offer(dummy);
            }
        }
        int end = buf.length() - 1;
        while(buf.charAt(end) ==',') { end--; }
        buf.setLength(end+1);
        return buf.toString();
    }

    public static TreeNode deserialize2(String data) {
        if(data.isEmpty()) { return null; }
        Iterator iter = new Iterator(data, 0);
        TreeNode root = new TreeNode(iter.next());
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        TreeNode node;
        while((node = q.poll()) != null) {
            if(!iter.hasNext()) { break; }
            Integer val = iter.next();
            if(val != null) {
                node.left = new TreeNode(val);
                q.offer(node.left);
            }
            if(!iter.hasNext()) { break; }
            val = iter.next();
            if(val != null) {
                node.right = new TreeNode(val);
                q.offer(node.right);
            }
        }
        return root;
    }

    private static class Iterator {
        private String buf;
        private int len;
        private int begin;
        Iterator(String buf, int begin) {
            this.buf = buf;
            this.len = buf.length();
            this.begin = begin;
        }
        Integer next() {
            char ch = buf.charAt(begin);
            if(ch == ',') {
                begin++;
                return null;
            }
            boolean isNegative = false;
            long val = 0;
            if(ch == '-') {
                isNegative = true;
            }else {
                val = ch - '0';
            }
            begin++;
            while(begin < len && (ch=buf.charAt(begin)) != ',') {
                val = 10 * val + ch - '0';
                begin++;
            }
            if(begin < len) {
                begin++;
            }
            val = isNegative ? -val : val;
            return (int)val;
        }
        boolean hasNext() {
            return begin < len;
        }
    }
}
