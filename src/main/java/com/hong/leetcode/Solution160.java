package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * @author wanghong
 * @date 2019/05/21 19:56
 * 160. 相交链表
 **/
public class Solution160 {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         * 定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部,
         *  最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
         *  两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
         */
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头,
        // 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode last = headB;
        while (last.next != null) {
            last = last.next;
        }
        last.next = headB;

        ListNode fast = headA;
        ListNode slow = headA;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = headA;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                last.next = null;
                return fast;
            }
        }
        last.next = null;
        return null;
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        ListNode n0 = new ListNode(2);
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(4);
        headA.next = n0;
        n0.next = n1;
        n1.next = n2;

        ListNode headB = new ListNode(5);
        headB.next = n1;

        ListNode node = getIntersectionNode(headA, headB);
        System.out.println(node);
    }

}
