package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * @author wanghong
 * @date 2020/10/24 21:56
 * 21. 合并两个有序链表
 **/
public class Solution21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        ListNode h1 = l1;
        ListNode h2 = l2;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                tail = tail.next = h1;
                h1 = h1.next;
            } else if (h1.val > h2.val) {
                tail = tail.next = h2;
                h2 = h2.next;
            } else {
                tail = tail.next = h1;
                h1 = h1.next;
                tail = tail.next = h2;
                h2 = h2.next;
            }
        }

        if (h1 != null) {
            tail.next = h1;
        }

        if (h2 != null) {
            tail.next = h2;
        }

        ListNode newHead = dummyHead.next;
        dummyHead.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        Solution21 s = new Solution21();
        ListNode l1 = new ListNode(new int[]{1, 2, 4});
        ListNode l2 = new ListNode(new int[]{1, 3, 4});
        ListNode head = s.mergeTwoLists(l1, l2);
        System.out.println(head);
    }
}
