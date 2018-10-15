package com.lee.leetcode.common;

/** common Linked Node **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode build(int[] array) {
        ListNode head = null, prev = null;
        if(array.length == 0) {
            return head;
        }
        head = prev = new ListNode(array[0]);
        for(int i=1; i<array.length; i++) {
            ListNode node = new ListNode(array[i]);
            prev.next = node;
            prev = node;
        }
        return head;
    }

    public static void print(ListNode head) {
        if(head != null) {
            System.out.print(head.val);
            ListNode current = head;
            while((current = current.next) != null) {
                System.out.print("->" + current.val);
            }
        }
        System.out.println();
    }
}
