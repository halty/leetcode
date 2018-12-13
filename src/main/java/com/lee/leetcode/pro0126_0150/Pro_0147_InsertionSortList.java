package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.ListNode;

/**
 *
 <pre>
 Sort a linked list using insertion sort.
 <img src="https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif" />
 A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
 With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list

 Algorithm of Insertion Sort:
    1. Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
    2. At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
    3. It repeats until no input elements remain.

 Example 1:
 Input: 4->2->1->3
 Output: 1->2->3->4

 Example 2:
 Input: -1->5->3->4->0
 Output: -1->0->3->4->5
 </pre>
 *
 */
public class Pro_0147_InsertionSortList {

    public static void main(String[] args) {
        int[] array = {-1,5,3,4,0};
        ListNode head = ListNode.build(array);
        head = insertionSortList(head);
        ListNode.print(head);
    }

    public static ListNode insertionSortList(ListNode head) {
        if(head == null) { return null; }
        for(ListNode prev=head, end=head.next; end != null;) {
            int val = end.val;
            ListNode next = end.next;
            if(prev.val <= val) {
                prev = end;
            }else {
                ListNode p = null, cur = head;
                while(cur.val <= val) {
                    p = cur;
                    cur = cur.next;
                }
                prev.next = next;
                if(p == null) {
                    end.next = head;
                    head = end;
                }else {
                    end.next = p.next;
                    p.next = end;
                }
            }
            end = next;
        }
        return head;
    }
}
