package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * @author ：wanghong
 * @date ：2020-11-09 17:42
 * @description： 237. 删除链表中的节点
 */
public class Solution237 {

    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        ListNode nextNext = next.next;
        node.val = next.val;
        node.next = nextNext;
        next.next = null;
    }

}
