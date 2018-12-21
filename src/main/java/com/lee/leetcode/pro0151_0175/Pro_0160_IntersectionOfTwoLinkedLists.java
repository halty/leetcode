package com.lee.leetcode.pro0151_0175;

import com.lee.leetcode.common.ListNode;
import com.lee.leetcode.common.Pair;

/**
 *
 Write a program to find the node at which the intersection of two singly linked lists begins.

 For example, the following two linked lists:
   A:         a1 -> a2
                      \
                       c1 -> c2 -> c3
                      /
   B:   b1 -> b2 -> b3
 begin to intersect at node c1.

 Example 1:
   A:         4 -> 1
                    \
                     8 -> 4 -> 5
                    /
   B:    5 -> 0 -> 1
 Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 Output: Reference of the node with value = 8
 Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5].
 There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

 Example 2:
   A:   0 -> 9 -> 1
                   \
                    2 -> 4
                   /
   B:             3
 Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 Output: Reference of the node with value = 2
 Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
 From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4].
 There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

 Example 3:
   A: 2 -> 6 -> 4

   B:      1 -> 5
 Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 Output: null
 Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5].
 Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 Explanation: The two lists do not intersect, so return null.

 Notes:
   1. If the two linked lists have no intersection at all, return null.
   2. The linked lists must retain their original structure after the function returns.
   3. You may assume there are no cycles anywhere in the entire linked structure.
   4. Your code should preferably run in O(n) time and use only O(1) memory.
 *
 */
public class Pro_0160_IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {
        int[] a = {4,1,8,4,5};
        int[] b = {5,0,1,8,4,5};
        int joinIndexA = 2, joinIndexB = 3;
        Pair<ListNode, ListNode> pair = ListNode.buildIntersection(a, joinIndexA, b, joinIndexB);
        ListNode target = getIntersectionNode(pair.one, pair.two);
        System.out.println(target == null ? null : target.val);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) { return null; }
        int sizeA = 0, sizeB = 0;
        ListNode ha = headA, hb = headB;
        while(ha != null && hb != null) {
            sizeA++;
            sizeB++;
            ha = ha.next;
            hb = hb.next;
        }
        while(ha != null) {
            sizeA++;
            ha = ha.next;
        }
        while(hb != null) {
            sizeB++;
            hb = hb.next;
        }
        ha = headA;
        hb = headB;
        if(sizeA > sizeB) {
            while(sizeA > sizeB) {
                ha = ha.next;
                sizeA--;
            }
        }else if(sizeA < sizeB) {
            while(sizeB > sizeA) {
                hb = hb.next;
                sizeB--;
            }
        }
        while(ha != null && hb != null) {
            if(ha == hb) { return ha; }
            ha = ha.next;
            hb = hb.next;
        }
        return null;
    }
}
