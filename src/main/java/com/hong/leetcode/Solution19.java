package com.hong.leetcode;

import com.hong.linked.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghong
 * @date 2019/05/12 15:16
 * 19. 删除链表的倒数第N个节点
 **/
public class Solution19 {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead, q = dummyHead;
        for (int i = 1; i <= n; i++) {
            p = p.next;
        }

        while (p.next != null) {
            p = p.next;
            q = q.next;
        }

        ListNode delNode = q.next;
        q.next = delNode.next;
        delNode.next = null;

        return dummyHead.next;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }

        ListNode pre = list.get(list.size() - 1 - n);
        ListNode delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(nums);
        head = removeNthFromEnd2(head, 2);
        System.out.println(head);
    }

}
