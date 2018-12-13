package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

 You may not modify the values in the list's nodes, only nodes itself may be changed.

 Example 1:
 Given 1->2->3->4, reorder it to 1->4->2->3.

 Example 2:
 Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 */
public class Pro_0143_ReorderList {

    public static void main(String[] args) {
        int[] array = {1,2,3,4};
        ListNode head = ListNode.build(array);
//        reorderList(head);
//        reorderList1(head);
        reorderList2(head);
        ListNode.print(head);
    }

    public static void reorderList(ListNode head) {
        ListNode h = head;
        while(h.next != null && h.next.next != null) {
            ListNode prev = h, cur = h.next;
            while(cur.next != null) {
                prev = cur;
                cur = cur.next;
            }
            cur.next = h.next;
            h.next = cur;
            prev.next = null;
            h = cur.next;
        }
    }

    public static void reorderList1(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode h = head;
        while(h != null) {
            list.add(h);
            h = h.next;
        }
        int len = list.size();
        if(len > 2) {
            int j = len - 1;
            for(int i=0,in=1; in<j; i++,in++,j--) {
                ListNode iNode = list.get(i);
                ListNode inNode = list.get(in);
                ListNode jNode = list.get(j);
                jNode.next = inNode;
                iNode.next = jNode;
            }
            list.get(j).next = null;
        }
    }

    public static void reorderList2(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) { return; }
        ListNode odd = head, even = head.next;
        // different steps traversal
        while(even != null && even.next != null) {
            odd = odd.next;
            even = even.next.next;
        }
        // reverse last half list
        ListNode h = odd.next;
        ListNode prev = h;
        ListNode cur = prev.next;
        ListNode next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        h.next = null;
        h = prev;
        // merge
        while(h != null) {
            ListNode headNext = head.next;
            ListNode hNext = h.next;
            h.next = headNext;
            head.next = h;
            head = headNext;
            h = hNext;
        }
        head.next = null;
    }
}
