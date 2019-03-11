package com.lee.leetcode.pro0201_0225;

import com.lee.leetcode.common.ListNode;

/**
 *
 Remove all elements from a linked list of integers that have value val.

 Example:
 Input:  1->2->6->3->4->5->6, val = 6
 Output: 1->2->3->4->5
 *
 */
public class Pro_0203_RemoveLinkedListElements {

    public static void main(String[] args) {
//        int[] array = {1,2,6,3,4,5,6};
        int[] array = {1,6,6,6};
        ListNode head = ListNode.build(array);
        int val = 6;
        ListNode result = removeElements(head, val);
        ListNode.print(result);
    }

    public static ListNode removeElements(ListNode head, int val) {
        if(head == null) { return null; }
        ListNode probe = new ListNode(-1);
        probe.next = head;
        ListNode prev = probe;
        ListNode current = head;
        ListNode next = head.next;
        while(next != null) {
            if(current.val != val) {
                prev.next = current;
                prev = current;
            }
            current = next;
            next = next.next;
        }
        prev.next = current.val == val ? next : current;
        return probe.next;
    }
}
