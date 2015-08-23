package com.lee.leetcode;

public class Pro_0021_mergeTwoSortedLists {

	public static void main(String[] args) {
		
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) { return l2; }
        if(l2 == null) { return l1; }
        ListNode head, prev;
        if(l1.val < l2.val) {
        	prev = head = l1;
        	l1 = l1.next;
        }else {
        	prev = head = l2;
        	l2 = l2.next;
        }
        while(l1 != null && l2 != null) {
        	if(l1.val < l2.val) {
        		prev.next = l1;
        		prev = l1;
        		l1 = l1.next;
        	}else {
        		prev.next = l2;
        		prev = l2;
        		l2 = l2.next;
        	}
        }
        
        prev.next = (l1 == null ? l2 : l1);
        return head;
    }
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}
