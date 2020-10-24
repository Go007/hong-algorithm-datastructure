package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * @author wanghong
 * @date 2020/10/24 17:11
 * 876. 链表的中间结点
 **/
public class Solution876 {

    public ListNode middleNode(ListNode head) {
        int size = 0;
        ListNode tail = head;
        while (tail != null) {
            tail = tail.next;
            size++;
        }

        ListNode middleNode = head;
        for (int i = 0; i < size >> 1; i++) {
            middleNode = middleNode.next;
        }

        return middleNode;
    }

    /**
     * 快慢指针
     * 我们用两个指针一起来遍历这个链表，每次快指针走 2 步，慢指针走 1 步，
     * 这样当快指针走到头的时候，慢指针应该刚好在链表的中点。
     *
     * @param head
     * @return
     */
    public ListNode middleNode2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 如果链表长度为偶数，返回中间第一个
     * 如 1-> 2 -> 3 -> 4 ，返回 2
     *
     * @param head
     * @return
     */
    public ListNode middleNode3(ListNode head) {
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 通过判断 fast == null (偶数) 或 fast.next == null 推断出 链表长度是奇数还是偶数
        return fast == null ? pre : slow;
    }

    /**
     * 上述的两个方法孰优孰劣呢？
     * 网上很多说什么方法一过了两遍链表，方法二只过了一遍。
     * 但其实，方法二用了两个指针来遍历，所以两个方法过的遍数都是一样的。
     * 它们最大的区别是：
     * 方法一是 offline algorithm，方法二是 online algorithm。
     */
    public static void main(String[] args) {
        Solution876 s = new Solution876();
        int[] arr = {1, 2, 3, 4};
        ListNode head = new ListNode(arr);
        System.out.println(s.middleNode3(head));
    }
}
