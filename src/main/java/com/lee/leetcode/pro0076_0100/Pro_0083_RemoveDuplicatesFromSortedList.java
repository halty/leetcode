package com.lee.leetcode.pro0076_0100;

import com.lee.leetcode.common.ListNode;

/**
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
        ListNode head = ListNode.build(nums);
        ListNode.print(head);
        ListNode result = deleteDuplicates(head);
        ListNode.print(result);
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
}
