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

    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode cursor = head;
        ListNode tail = null;//尾指针
        int length = 1;
        while (cursor.next != null)//循环 得到总长度
        {
            cursor = cursor.next;
            length++;
        }
        //因为cursor是从head节点开始循环的，让它循环到原链表的倒数第(k%len)个需要len-(k%len)次
        int loop = length - (k % length);//得到循环的次数
        tail = cursor;//指向尾结点
        cursor.next = head;//改成循环链表
        cursor = head;//指向头结点
        for (int i = 0; i < loop; i++) {//开始循环
            cursor = cursor.next;
            tail = tail.next;
        }
        tail.next = null;//改成单链表
        return cursor;//返回当前头
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        head = rotateRight(head, 2);
        System.out.println(head);
    }

}
