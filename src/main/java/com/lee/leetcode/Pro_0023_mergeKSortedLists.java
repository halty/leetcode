package com.lee.leetcode;

public class Pro_0023_mergeKSortedLists {

	public static void main(String[] args) {
		int[] a1 = {};
		int[] a2 = {2, 4, 9};
		int[] a3 = {3, 7};
		ListNode[] list = new ListNode[3];
		list[0] = build(a1);
		list[1] = build(a2);
		list[2] = build(a3);
		print(list[0]);
		print(list[1]);
		print(list[2]);
		print(mergeKLists(list));
	}

	public static ListNode mergeKLists(ListNode[] lists) {
        int count = lists.length;
        int k = 0;
		for(int i=0; i<count; i++) { if(lists[i] != null) { lists[k++] = lists[i]; } }
		
		if(k == 0) { return null; }
		if(k == 1) { return lists[0]; }
		
		int idx = 0;
		ListNode head = lists[0];
		for(int i=1; i<k; i++) {
			if(lists[i].val < head.val) {
				idx = i;
				head = lists[i];
			}
		}
		lists[idx] = head.next;
        ListNode prev = head;
        
        count = k;
        while(count > 0) {
        	k = 0;
        	for(int i=0; i<count; i++) { if(lists[i] != null) { lists[k++] = lists[i]; } }
        	if((count = k) == 1) {
        		prev.next = lists[0];
        		break;
        	}
        	idx = 0;
        	ListNode min = lists[0];
        	for(int i=1; i<count; i++) {
        		if(lists[i].val < min.val) {
    				idx = i;
    				min = lists[i];
    			}
        	}
        	lists[idx] = min.next;
        	prev.next = min;
        	prev = min;
        }
        
        return head;
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
