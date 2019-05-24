package com.lee.leetcode.pro0226_0250;

import com.lee.leetcode.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a singly linked list, determine if it is a palindrome.

 Example 1:
 Input: 1->2
 Output: false

 Example 2:
 Input: 1->2->2->1
 Output: true

 Follow up: Could you do it in O(n) time and O(1) space?
 *
 */
public class Pro_0234_PalindromeLinkedList {

    public static void main(String[] args) {
        int[] array = {1,1,2,2,1,1};
        ListNode head = ListNode.build(array);
//        boolean result = isPalindrome(head);
        boolean result = isPalindrome1(head);
        System.out.println(result);
    }

    public static boolean isPalindrome(ListNode head) {
        if(head == null) { return true; }
        List<Integer> list = new ArrayList<>();
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        int count = list.size();
        if(count == 1) { return true; }
        for(int i=0, j=count-1; i<j; i++, j--) {
            if(list.get(i).intValue() != list.get(j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome1(ListNode head) {
        if(head == null || head.next == null) { return true; }
        ListNode p1 = head, p2 = head.next.next;
        if(p2 == null) { return head.val == head.next.val; }
        ListNode prev1 = null;
        while(true) {
            ListNode next1 = p1.next;
            p1.next = prev1;
            ListNode next2 = p2.next;
            if(next2 == null) {
                p2 = next1.next;
                break;
            }
            next2 = next2.next;
            if(next2 == null) {
                prev1 = p1;
                p1 = next1;
                p2 = next1.next;
                p1.next = prev1;
                break;
            }else {
                prev1 = p1;
                p1 = next1;
                p2 = next2;
            }
        }
        while(p1 != null && p2 != null) {
            if(p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}
