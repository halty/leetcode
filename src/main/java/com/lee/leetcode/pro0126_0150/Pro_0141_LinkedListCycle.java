package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.ListNode;

/**
 *
 Given a linked list, determine if it has a cycle in it.
 To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
 If pos is -1, then there is no cycle in the linked list.

 Example 1:
 Input: head = [3,2,0,-4], pos = 1
 Output: true
 Explanation: There is a cycle in the linked list, where tail connects to the second node.

 Example 2:
 Input: head = [1,2], pos = 0
 Output: true
 Explanation: There is a cycle in the linked list, where tail connects to the first node.

 Example 3:
 Input: head = [1], pos = -1
 Output: false
 Explanation: There is no cycle in the linked list.

 Follow up:
 Can you solve it using O(1) (i.e. constant) memory?
 *
 */
public class Pro_0141_LinkedListCycle {

    public static void main(String[] args) {
        int[] array = {3,2,0,-4};
        int pos = -1;
        ListNode head = ListNode.buildCycle(array, pos);
//        boolean result = hasCycle(head);
        boolean result = hasCycle1(head);
        System.out.println(result);
    }

    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) { return false; }
        ListNode one = oneStep(head);
        ListNode two = twoStep(head);
        while(one != null && two!= null) {
            if(one == two) { return true; }
            one = oneStep(one);
            two = twoStep(two);
        }
        return false;
    }

    private static ListNode oneStep(ListNode current) {
        return current.next;
    }

    private static ListNode twoStep(ListNode current) {
        current = current.next;
        return current == null ? null : current.next;
    }

    public static boolean hasCycle1(ListNode head) {
        if(head == null || head.next == null) { return false; }
        ListNode one = head.next;
        ListNode two = head.next.next;
        while(two != null && two.next != null) {
            if(one == two) { return true; }
            one = one.next;
            two = two.next.next;
        }
        return false;
    }
}
