package com.lee.leetcode.pro0076_0100;

/*
 *
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2

Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
 *
 */
public class Pro_0083_RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        ListNode head = build(nums);
        print(head);
        ListNode result = deleteDuplicates(head);
        print(result);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null) { return null; }
        ListNode current = head, prev = current;
        while((current=current.next) != null) {
            if(current.val != prev.val) {
                prev.next = current;
                prev = current;
            }
        }
        prev.next = null;
        return head;
    }

    private static ListNode build(int[] array) {
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

    private static void print(ListNode head) {
        if(head != null) {
            System.out.print(head.val);
            ListNode current = head;
            while((current = current.next) != null) {
                System.out.print("->" + current.val);
            }
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
}
