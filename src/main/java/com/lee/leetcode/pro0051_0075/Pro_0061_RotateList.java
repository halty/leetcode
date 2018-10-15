package com.lee.leetcode.pro0051_0075;

import com.lee.leetcode.common.ListNode;

/**
 *
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
 *
 */
public class Pro_0061_RotateList {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        int k = 2;
        ListNode head = ListNode.build(array);
        ListNode.print(head);
        ListNode list = rotateRight(head, k);
        ListNode.print(list);
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) {
            return head;
        }
        ListNode p = head;
        int length = 1;
        while(p.next != null) {
            length += 1;
            p = p.next;
        }
        k = k % length;
        if(k == 0) {
            return head;
        }
        // rotate k node
        p.next = head;
        int n = length - k;
        ListNode prev = p;
        p = p.next;
        while(n > 0) {
            prev = p;
            p = p.next;
            n -= 1;
        }
        prev.next = null;
        return p;
    }
}
