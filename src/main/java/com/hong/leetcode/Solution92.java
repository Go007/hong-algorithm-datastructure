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
        ListNode next = null;
        ListNode prev = null;
        ListNode cur = null;
        while (head != null) {
            i++;
            if (i == m-1) {
                prev = head.next;
                cur = prev.next;
                while (cur != null && m <= n){
                    next = cur.next;
                    cur.next = prev;
                    cur = next;
                }


                break;
            }
            head = head.next;
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        int m=2,n=4;
        head = reverseBetween(head,m,n);
        System.out.println(head);
    }
}
