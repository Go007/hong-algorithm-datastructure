package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * Created by wanghong
 * Date 2019-05-16 14:54
 * Description:
 * 61. 旋转链表
 */
public class Solution61 {

    /**
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        /**
         * 找到倒数第 k+1 个节点，
         *  k = k > 链表长度 ？ k % 链表长度 : k;
         * 遍历到尾节点后，回到头节点继续遍历，直到到达 k+1 个计数，
         * 说是循环旋转，但其实本质上是将尾部向前数第K个元素作为头，原来的头接到原来的尾上
         */
        ListNode tail = head;
        int size = 1;
        while (tail.next != null) {
            size++;
            tail = tail.next;
        }
        k = k > size ? k % size : k;

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead, prev = dummyHead;
        for (int i = 1; i <= k; i++) {
            p = p.next;
        }

        while (p.next != null) {
            p = p.next;
            prev = prev.next;
        }

        dummyHead.next = null;
        ListNode newHead = prev.next;
        if (newHead == null) {
            return head;
        }

        if (newHead.next == null) {
            newHead.next = head;
        } else {
            tail.next = head;
        }
        prev.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        head = rotateRight(head, 2);
        System.out.println(head);
    }

}
