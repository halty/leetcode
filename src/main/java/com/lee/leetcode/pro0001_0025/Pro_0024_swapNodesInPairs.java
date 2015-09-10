package com.lee.leetcode.pro0001_0025;

/*
 * 
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 * 
 */
public class Pro_0024_swapNodesInPairs {

	public static void main(String[] args) {
		int[] array = {1,2,3,4,5};
		ListNode head = build(array);
		print(head);
		print(swapPairs(head));
	}

	public static ListNode swapPairs(ListNode head) {
        ListNode first = head;
        if(first == null) { return first; }
        ListNode second = first.next;
        if(second == null) { return head; }
        first.next = second.next;
        second.next = first;
        head = second;
        ListNode prev = first;
        first = prev.next;
        while(first != null) {
        	second = first.next;
        	if(second == null) { break; }
        	
        	// swap
        	first.next = second.next;
        	second.next = first;
        	prev.next = second;
        	prev = first;
        	first = prev.next;
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
	
	private static void print(ListNode head) {
		StringBuilder buf = new StringBuilder();
		if(head != null) {
			buf.append(head.val);
			head = head.next;
		}
		while(head != null) {
			buf.append(" -> ").append(head.val);
			head = head.next;
		}
		System.out.println(buf.toString());
	}
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}
