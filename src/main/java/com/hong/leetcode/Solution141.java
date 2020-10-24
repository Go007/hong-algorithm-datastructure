package com.hong.leetcode;

import com.hong.linked.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wanghong
 * Date 2019-05-17 09:46
 * Description:
 * 141. 环形链表-判断单链表是否有环
 */
public class Solution141 {

    /**
     * 可以通过检查一个结点此前是否被访问过来判断链表是否为环形链表。
     * 常用的方法是使用哈希表
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        int pos = -1;
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode cur = head;
        int index = 0;
        while (cur != null) {
            if (map.containsKey(cur)) {
                pos = map.get(cur);
                break;
            }
            map.put(cur, index);
            index++;
            cur = cur.next;
        }

        return pos == -1 ? false : true;
    }

    /**
     * 快慢指针
     * 通过使用具有 不同速度 的快、慢两个指针遍历链表，空间复杂度可以被降低至 O(1)O(1)。
     * 慢指针每次移动一步，而快指针每次移动两步。
     *
     * @param head
     * @return
     */
    public static boolean hasCycle3(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

    public boolean hasCycle4(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

}
