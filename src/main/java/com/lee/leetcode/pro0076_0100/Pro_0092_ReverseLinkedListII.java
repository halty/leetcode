package com.lee.leetcode.pro0076_0100;

import com.lee.leetcode.common.ListNode;

/**
 *
 Reverse a linked list from position m to n. Do it in one-pass.

 Note: 1 ≤ m ≤ n ≤ length of list.

 Example:

 Input: 1->2->3->4->5->NULL, m = 2, n = 4
 Output: 1->4->3->2->5->NULL
 *
 */
public class Pro_0092_ReverseLinkedListII {

    public static void main(String[] args) {
        int[] array = {1};
        int m = 1, n = 1;
        ListNode head = ListNode.build(array);
        ListNode result = reverseBetween(head, m, n);
        ListNode.print(result);
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n) { return head; }
        int i = 1;
        ListNode p = head;
        ListNode prev = null;
        while(i < m) {
            prev = p;
            p = p.next;
            i++;
        }
        ListNode t = p;
        ListNode next = p;
        p = p.next;
        ListNode h = p;
        i++;
        while(i < n) {
            p = p.next;
            h.next = next;
            next = h;
            h = p;
            i++;
        }
        p = p.next;
        h.next = next;
        if(prev == null) {
            head = h;
        }else {
            prev.next = h;
        }
        t.next = p;
        return head;
    }
}
