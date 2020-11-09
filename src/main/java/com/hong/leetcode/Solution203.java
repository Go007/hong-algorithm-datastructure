package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * @author wanghong
 * @date 2020/10/25 17:08
 * 203. 移除链表元素
 **/
public class Solution203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre.next != null) {
            ListNode next = pre.next;
            if (next.val == val) {
                pre.next = next.next;
                next.next = null;
            } else {
                pre = next;
            }
        }

        return dummyHead.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val != val) {
                prev.next = curr;
                prev = prev.next;
            }
            curr = curr.next;
        }
        prev.next = null;
        return dummy.next;
    }

    public ListNode removeElements3(ListNode head,int val){
        if (head == null){
            return head;
        }

        ListNode next = removeElements3(head.next,val);
        if (head.val == val){
            head.next = null;
            return next;
        }else {
            head.next = next;
            return head;
        }
    }

    public ListNode removeElements4(ListNode head,int val){
        if (head == null){
            return head;
        }

        head.next = removeElements4(head.next,val);
        return head.val == val ? head.next:head;
    }

    public static void main(String[] args) {
        Solution203 s = new Solution203();
        ListNode head = new ListNode(new int[]{6, 1, 2, 6, 3, 4, 5, 6});
        System.out.println(s.removeElements4(head, 6));
    }

}
