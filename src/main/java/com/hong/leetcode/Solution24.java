package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * @author wanghong
 * @date 2019/05/12 16:12
 * 24. 两两交换链表中的节点
 **/
public class Solution24 {

    public static ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead;
        while (p.next != null) {
            ListNode q = p.next;
            ListNode next = q.next;
            if (next == null) {
                break;
            }
            q.next = next.next;
            next.next = q;
            p.next = next;
            p = q;
        }

        return dummyHead.next;
    }

    /**
     *
     * https://leetcode-cn.com/lyl0724-2/
     *
     * 使用递归来解决该题，主要就是递归的三部曲：
     * 1.找终止条件：本题终止条件很明显，当递归到链表为空或者链表只剩一个元素的时候，没得交换了，自然就终止了。
     * 2.找返回值：返回给上一层递归的值应该是已经交换完成后的子链表。
     * 3.单次的过程：因为递归是重复做一样的事情，所以从宏观上考虑，只用考虑某一步是怎么完成的。
     * 我们假设待交换的俩节点分别为head和next，next的应该接受上一级返回的子链表(参考第2步)。
     * 就相当于是一个含三个节点的链表交换前两个节点，就很简单了，想不明白的画画图就ok。
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;

        return next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3,4};
        ListNode head = new ListNode(nums);
        head = swapPairs(head);
        System.out.println(head);
    }
}
