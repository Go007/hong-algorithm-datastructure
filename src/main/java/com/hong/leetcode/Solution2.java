package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * @author wanghong
 * @date 2019/05/08 22:38
 * 2. 两数相加
 **/
public class Solution2 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int decade = 0;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }
            sum += decade;

            decade = sum / 10;
            int single = sum % 10;

            if (head == null) {
                tail = head = new ListNode(single);
            } else {
                tail = tail.next = new ListNode(single);
            }

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (decade > 0) {
            tail.next = new ListNode(decade);
        }

        return head;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cursor = dummyHead;
        int decade = 0;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }
            sum += decade;
            decade = sum / 10;

            cursor = cursor.next = new ListNode(sum % 10);

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (decade > 0) {
            cursor.next = new ListNode(decade);
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 4, 3};
        int[] nums2 = {5, 6, 4};
        ListNode l1 = new ListNode(nums1);
        ListNode l2 = new ListNode(nums2);
        System.out.println(l1);
        System.out.println(l2);
        ListNode head = addTwoNumbers(l1, l2);
        System.out.println(head);
    }

}
