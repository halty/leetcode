package com.lee.leetcode.pro0001_0025;

/*
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
		ListNode head = build(array);
		int n = 1;
		String result = print(head);
		System.out.println(result);
		result = print(removeNthFromEnd(head, n));
		System.out.println(result);
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
	
	private static ListNode build(int[] array) {
		ListNode head = new ListNode(array[0]);
		ListNode prev = head;
		for(int i=1; i<array.length; i++) {
			ListNode node = new ListNode(array[i]);
			prev.next = node;
			prev = node;
		}
		return head;
	}
	
	private static String print(ListNode head) {
		StringBuilder buf = new StringBuilder();
		if(head != null) {
			buf.append(head.val);
			head = head.next;
		}
		while(head != null) {
			buf.append(" -> ").append(head.val);
			head = head.next;
		}
		return buf.toString();
	}
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}
