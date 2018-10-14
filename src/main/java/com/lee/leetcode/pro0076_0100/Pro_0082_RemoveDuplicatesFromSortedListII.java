package com.lee.leetcode.pro0076_0100;

import com.lee.leetcode.common.ListNode;

/*
 *
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:
Input: 1->2->3->3->4->4->5
Output: 1->2->5

Example 2:
Input: 1->1->1->2->3
Output: 2->3
 *
 */
public class Pro_0082_RemoveDuplicatesFromSortedListII {

    public static void main(String[] args) {
        int[] nums = {1};
        ListNode head = ListNode.build(nums);
        ListNode.print(head);
        ListNode result = deleteDuplicates(head);
        ListNode.print(result);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null) { return head; }
        ListNode current = head, p = head;
        ListNode prev = null;
        head = null;
        while((p = p.next) != null) {
            if(p.val != current.val) {
                if(p == current.next) {
                    if(prev == null) {
                        head = current;
                    }
                    prev = current;
                    current = p;
                }else { // have duplicates
                    if(prev != null) {
                        prev.next = p;
                    }
                    current = p;
                }
            }
        }
        if(current.next != null) {
            if(prev != null) {
                prev.next = null;
            }
        }else {
            if(prev == null) {
                head = current;
            }
        }
        return head;
    }
}
