package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.RandomListNode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 Return a deep copy of the list.
 *
 */
public class Pro_0138_CopyListWithRandomPointer {

    public static void main(String[] args) {
        String serialization = "1,4#2,3#3,1#4,4#5";
        RandomListNode head = RandomListNode.build(serialization);
        System.out.println("original: ");
        RandomListNode.print(head);
        RandomListNode cloneHead = copyRandomList(head);
        System.out.println("\nclone: ");
        RandomListNode.print(cloneHead);
    }

    public static RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) { return null; }
        Map<RandomListNode, RandomListNode> cloneMapping = new HashMap<>();
        RandomListNode cloneHead = new RandomListNode(head.label);
        if(head.random != null) { cloneMapping.put(head, cloneHead); }
        RandomListNode current = head.next;
        RandomListNode clonePrev = cloneHead;
        RandomListNode cloneCurrent;
        while(current != null) {
            cloneCurrent = new RandomListNode(current.label);
            clonePrev.next = cloneCurrent;
            clonePrev = cloneCurrent;
            cloneMapping.put(current, cloneCurrent);
            current = current.next;
        }
        current = head;
        cloneCurrent = cloneHead;
        while(current != null) {
            if(current.random != null) {
                cloneCurrent.random = cloneMapping.get(current.random);
            }
            current = current.next;
            cloneCurrent = cloneCurrent.next;
        }
        return cloneHead;
    }
}
