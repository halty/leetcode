package com.lee.leetcode.pro0201_0225;

import com.lee.leetcode.common.ListNode;

/**
 *
 Reverse a singly linked list.

 Example:
 Input: 1->2->3->4->5->NULL
 Output: 5->4->3->2->1->NULL

 Follow up:
 A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 */
public class Pro_0206_ReverseLinkedList {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        ListNode head = ListNode.build(array);
//        ListNode result = reverseList(head);
        ListNode result = reverseList1(head);
        ListNode.print(result);
    }

    public static ListNode reverseList(ListNode head) {
        if(head == null) { return head; }
        return reverseListRecursively(null, head);
    }

    public static ListNode reverseListRecursively(ListNode prev, ListNode current) {
        ListNode next = current.next;
        if(next != null) {
            current.next = prev;
            return reverseListRecursively(current, next);
        }else {
            current.next = prev;
            return current;
        }
    }

    public static ListNode reverseList1(ListNode head) {
        if(head == null) { return head; }
        ListNode prev = null;
        ListNode current = head;
        ListNode next;
        while((next = current.next) != null) {
            current.next = prev;
            prev = current;
            current = next;
        }
        current.next = prev;
        return current;
    }
}
