package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.ListNode;

/**
 *
 Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
 If pos is -1, then there is no cycle in the linked list.

 Note: Do not modify the linked list.

 Example 1:
 Input: head = [3,2,0,-4], pos = 1
 Output: tail connects to node index 1
 Explanation: There is a cycle in the linked list, where tail connects to the second node.

 Example 2:
 Input: head = [1,2], pos = 0
 Output: tail connects to node index 0
 Explanation: There is a cycle in the linked list, where tail connects to the first node.

 Example 3:
 Input: head = [1], pos = -1
 Output: no cycle
 Explanation: There is no cycle in the linked list.

 Follow up:
 Can you solve it without using extra space?
 *
 */
public class Pro_0142_LinkedListCycleII {

    public static void main(String[] args) {
        int[] array = {3, 2, 0, -4};
        int pos = 0;
        ListNode head = ListNode.buildCycle(array, pos);
        ListNode begin = detectCycle(head);
        System.out.println(begin == null ? null : begin.val);
    }

    public static ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        ListNode one = head.next;
        ListNode two = head.next.next;
        ListNode node = null;
        while(two != null && two.next != null) {
            if(one == two) {
                node = one;
                break;
            }
            one = one.next;
            two = two.next.next;
        }
        if(node == null) { return null; }
        one = head;
        two = node;
        while(one != two) {
            one = one.next;
            two = two.next;
        }
        return one;
    }

}
