package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * @author wanghong
 * @date 2019/05/14 21:46
 * 83. 删除排序链表中的重复元素
 **/
public class Solution83 {

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                ListNode pre = cur;
                while (pre.next != null && pre.val == pre.next.val) {
                    pre = pre.next;
                }
                cur.next = pre.next;
                pre.next = null;

            }
            cur = cur.next;
        }

        return head;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.val == head.next.val) {
            ListNode pre = head;
            while (pre.next != null && pre.val == pre.next.val) {
                pre = pre.next;
            }
            head.next = pre.next;
            pre.next = null;
        }

        head.next = deleteDuplicates2(head.next);
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3, 3};
        ListNode head = new ListNode(nums);
        head = deleteDuplicates2(head);
        System.out.println(head);
    }

}
