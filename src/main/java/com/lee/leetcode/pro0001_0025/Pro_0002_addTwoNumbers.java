package com.lee.leetcode.pro0001_0025;

/*
 * 
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 * 
 */
public class Pro_0002_addTwoNumbers {

	public static void main(String[] args) {
		ListNode l1 = createList(new int[]{2, 4, 3});
		ListNode l2 = createList(new int[]{5, 6});
		ListNode result = addTwoNumbers(l1, l2);
		print(result);
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int carryFlag = sum / 10;
        sum %= 10;
        ListNode result = new ListNode(sum);
        ListNode prev = result;
        l1 = l1.next;
        l2 = l2.next;
        for(; l1 != null && l2 != null; l1=l1.next, l2=l2.next) {
        	sum = carryFlag + l1.val + l2.val;
        	carryFlag = sum / 10;
        	sum %= 10;
        	prev.next = new ListNode(sum);
        	prev = prev.next;
        }
        ListNode reserved = l1;
        if(reserved == null) { reserved = l2; }
        for(; reserved != null; reserved = reserved.next) {
        	sum = carryFlag + reserved.val;
        	carryFlag = sum / 10;
        	sum %= 10;
        	prev.next = new ListNode(sum);
        	prev = prev.next;
        }
        if(carryFlag != 0) {
        	prev.next = new ListNode(carryFlag);
        }
        return result;
    }
	
	public static ListNode createList(int[] array) {
		ListNode head = new ListNode(array[0]);
		ListNode prev = head;
		for(int i=1; i<array.length; i++) {
			prev.next = new ListNode(array[i]);
			prev = prev.next;
		}
		return head;
	}
	
	public static void print(ListNode list) {
		System.out.print(list.val);
		list = list.next;
		while(list != null) {
			System.out.print(" -> ");
			System.out.print(list.val);
			list = list.next;
		}
	}
	
	public static class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	}
}
