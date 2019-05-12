package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * @author wanghong
 * @date 2019/05/12 16:12
 * 24. 两两交换链表中的节点
 **/
public class Solution24 {

    public static ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead;
        while (p.next != null) {
            ListNode q = p.next;
            ListNode next = q.next;
            if (next == null) {
                break;
            }
            if (next != null) {
                q.next = next.next;
                next.next = q;
                p.next = next;
                p = q;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        ListNode head = new ListNode(nums);
        head = swapPairs(head);
        System.out.println(head);
    }
}
