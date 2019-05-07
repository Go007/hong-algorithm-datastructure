package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * Created by wanghong
 * Date 2019-05-07 14:36
 * Description:
 * 92. 反转链表 II
 */
public class Solution92 {

    /**
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }

        int i = 0;
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode point = dummyHead;
        while (point != null) {
            if (i == m - 1) {
                ListNode next = null;
                ListNode prev = null;
                ListNode cur = null;
                ListNode node = null;
                node = prev = point.next;
                cur = prev.next;
                prev.next = null;
                while (cur != null && m < n) {
                    next = cur.next;
                    cur.next = prev;
                    prev = cur;
                    cur = next;
                    m++;
                }
                point.next = prev;
                node.next = cur;
                break;
            }
            point = point.next;
            i++;
        }

        head = dummyHead.next;
        dummyHead.next = null;
        return head;
    }

    public static void main(String[] args) {
        //int[] nums = {3, 5};
        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        // int m = 1, n = 2;
        int m = 2, n = 4;
        head = reverseBetween(head, m, n);
        System.out.println(head);
    }
}
