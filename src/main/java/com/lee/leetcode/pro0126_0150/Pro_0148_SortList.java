package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.ListNode;

/**
 *
 Sort a linked list in O(n log n) time using constant space complexity.

 Example 1:
 Input: 4->2->1->3
 Output: 1->2->3->4

 Example 2:
 Input: -1->5->3->4->0
 Output: -1->0->3->4->5
 *
 */
public class Pro_0148_SortList {

    public static void main(String[] args) {
        int[] array = {-1,5,3,4,0};
        ListNode head = ListNode.build(array);
        head = sortList(head);
        ListNode.print(head);
    }

    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null) { return head; }
        ListNode end = halfCut(head);
        ListNode h1 = head, h2 = end.next;
        end.next = null;
        h1 = sortList(h1);
        h2 = sortList(h2);
        return merge(h1, h2);
    }

    private static ListNode halfCut(ListNode head) {
        ListNode one = head, two = head.next.next;
        while(two != null && two.next != null) {
            one = one.next;
            two = two.next.next;
        }
        return one;
    }

    private static ListNode merge(ListNode h1, ListNode h2) {
        ListNode h;
        if(h1.val <= h2.val) {
            h = h1;
            h1 = h1.next;
        }else {
            h = h2;
            h2 = h2.next;
        }
        ListNode p = h;
        while(h1 != null && h2 != null) {
            if(h1.val <= h2.val) {
                p.next = h1;
                p = h1;
                h1 = h1.next;
            }else {
                p.next = h2;
                p = h2;
                h2 = h2.next;
            }
        }
        if(h1 == null) {
            p.next = h2;
        }else {
            p.next = h1;
        }
        return h;
    }
}
