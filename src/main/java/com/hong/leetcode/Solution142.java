package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * Created by wanghong
 * Date 2019-05-20 09:17
 * Description:
 * 142. 环形链表 II - 返回有环链表的环的起点
 */
public class Solution142 {

    public static ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        // 步骤一：使用快慢指针判断链表是否有环
        ListNode p = head, p2 = head;
        boolean hasCycle = false;
        while (p2.next != null && p2.next.next != null) {
            p = p.next;
            p2 = p2.next.next;
            if (p == p2) {
                hasCycle = true;
                break;
            }
        }

        // 步骤二：若有环，找到入环开始的节点
        if (hasCycle) {
            ListNode q = head;
            while (p != q) {
                p = p.next;
                q = q.next;
            }
            return q;
        } else {
            return null;
        }
    }

    public static ListNode detectCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            /**
             *  slow == fast 说明有环
             *  快指针遍历距离一定是慢指针两倍。
             *  快指针和慢指针相遇后，相遇点一定在环内，再经过a 的距离，会距离相遇点 b长度，也就是入环点处。
             */
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }
        return null;
    }

    /**
     * 先摆出结论：
     * 快慢指针从链表头开始走，相遇的那点，记为 M；
     * 再用 2 个指针，一个从头开始走，一个从 M 开始走，相遇点即为 cycle 的起点。
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode x = head;
                ListNode y = slow;
                while (x != y) {
                    x = x.next;
                    y = y.next;
                }
                return x;
            }
        }
        return null;
    }

}
