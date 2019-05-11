package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * Created by wanghong
 * Date 2019-05-10 19:18
 * Description:148. 排序链表
 */
public class Solution148 {

    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        pre.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(p);
        return merge(l, r);
    }

    ListNode merge(ListNode l, ListNode r) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        if (l != null) {
            cur.next = l;
        }
        if (r != null) {
            cur.next = r;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        Solution148 s = new Solution148();
        int[] nums = {4,2,1,3};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        head = s.sortList(head);
        System.out.println(head);
    }

}
