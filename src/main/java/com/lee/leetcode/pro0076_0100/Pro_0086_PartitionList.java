package com.lee.leetcode.pro0076_0100;

import com.lee.leetcode.common.ListNode;

/*
 *
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
 *
 */
public class Pro_0086_PartitionList {

    public static void main(String[] args) {
        int[] array = {1,4,3,2,5,2};
        int x = 3;
        ListNode head = ListNode.build(array);
//        ListNode result = partition1(head, x);
        ListNode result = partition2(head, x);
        ListNode.print(result);
    }

    public static ListNode partition1(ListNode head, int x) {
        ListNode smallHead = null, sp = null;
        ListNode bigHead = null, bp = null;
        ListNode p = head;
        while(p != null) {
            if(p.val < x) {
                if(smallHead == null) {
                    smallHead = sp = p;
                }else {
                    sp = sp.next = p;
                }
            }else {
                if(bigHead == null) {
                    bigHead = bp = p;
                }else {
                    bp = bp.next = p;
                }
            }
            p = p.next;
        }
        if(sp != null) {
            sp.next = bigHead;
        }else {
            smallHead = bigHead;
        }
        if(bp != null) {
            bp.next = null;
        }
        return smallHead;
    }

    public static ListNode partition2(ListNode head, int x) {
        ListNode smallHead = new ListNode(-1), sp = smallHead;
        ListNode bigHead = new ListNode(-1), bp = bigHead;
        ListNode p = head;
        while(p != null) {
            if(p.val < x) {
                sp = sp.next = p;
            }else {
                bp = bp.next = p;
            }
            p = p.next;
        }
        ListNode r;
        if(sp != smallHead) {
            sp.next = bigHead.next;
            r = smallHead.next;
        }else {
            r = bigHead.next;
        }
        bp.next = null;
        return r;
    }
}
