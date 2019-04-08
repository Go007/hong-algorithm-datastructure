package com.hong.linked;

/**
 * @author Macrowang
 * @date 2019/04/08 11:53
 * Leetcode 203. Remove Linked List Elements
 * https://leetcode.com/problems/remove-linked-list-elements/description/
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 **/
public class Solution {

    private class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 删除链表中所有数据域=val的节点，并返回删除后链表的head
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        // 要删除的节点是头节点
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        // 判断是否还剩下元素
        if (head == null) {
            return head;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        /**
         * 在removeElement()方法中，每次找到数据域等于=val的节点node，
         * 在将node的prev的next指向node的next后，会将node的next置null,
         * 这样，node就变成了完全孤立的节点，让GC及时回收。
         * 但是在removeElements2()中，只管将node的prev的next指向node的next。
         */
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return head;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    public ListNode removeElements3(ListNode head, int val) {
        // 设置一个虚拟节点，从而避免对head的特殊对待
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode prev = dummyNode;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyNode.next;
    }
}
