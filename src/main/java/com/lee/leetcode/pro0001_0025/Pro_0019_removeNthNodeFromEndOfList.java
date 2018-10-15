package com.lee.leetcode.pro0001_0025;

import com.lee.leetcode.common.ListNode;

/**
 * 
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
 * 
 */
public class Pro_0019_removeNthNodeFromEndOfList {

	public static void main(String[] args) {
		int[] array = {1};
		ListNode head = ListNode.build(array);
		int n = 1;
		ListNode.print(head);
		ListNode.print(removeNthFromEnd(head, n));
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
        int i = 1;
        ListNode end = head;
        while(i++ < n) { end = end.next; }

        ListNode prev = null, cur = head;
        while(end.next != null) {
        	prev = cur;
        	cur = cur.next;
        	end = end.next;
        }
        if(prev == null) {
        	head = head.next;
        }else {
        	prev.next = prev.next.next;
        }
        
        return head;
    }
}
