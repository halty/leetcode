package com.lee.leetcode.pro0001_0025;

public class Pro_0025_reverseNodesInKGroup {

	public static void main(String[] args) {
		int[] array = {1,2,3,4,5};
		int k = 1;
		ListNode head = build(array);
		print(head);
		head = reverseKGroup(head, k);
		print(head);
	}

	public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) { return head; }
        if(k <= 1) { return head; }
        
        ListNode newHead = new ListNode(0);
        ListNode prev = newHead;
        ListNode first = head, end = head;
        int count = 0;
        while(end != null) {
        	ListNode cur = end;
        	end = end.next;
        	if(count < k) {
        		ListNode tmp = prev.next;
        		prev.next = cur;
        		cur.next = tmp;
        		count++;
        	}else {
        		prev = first;
        		prev.next = first = cur;
        		count = 1;
        	}
        }
        first.next = null;
        if(count < k) {
        	first = prev.next;
        	ListNode cur = first;
        	ListNode next = cur.next;
        	while(next != null) {
        		cur = next;
        		next = next.next;
        		ListNode tmp = prev.next;
        		prev.next = cur;
        		cur.next = tmp;
        	}
        	first.next = null;
        }
        return newHead.next;
    }
	
	private static ListNode build(int[] array) {
		if(array == null || array.length == 0) { return null; }
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