package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * Created by wanghong
 * Date 2019-05-16 14:54
 * Description:
 * 61. 旋转链表
 */
public class Solution61 {

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        ListNode prev = head;
        ListNode oldTail = null;

        for (int i = 1; i < k; i++) {
            if (prev.next == null) {
                oldTail = prev;
                prev = head;
            } else {
                prev = prev.next;
            }
        }

        ListNode newHead = null;
        ListNode tail = prev.next;
        if (tail == null) {
            if (prev != head) {

            } else {
                newHead = head;
            }
        }


        if (tail.next == null) {
            prev.next = null;
            tail.next = head;
        } else {

        }


        if (newHead != null) {
            tail.next = null;
            oldTail.next = head;
        } else {
            tail.next = head;
        }

        return newHead;
    }

}
