package com.hong.leetcode;

import com.hong.linked.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by wanghong
 * Date 2019-07-26 10:26
 * Description:
 * <p>
 * 23. 合并K个排序链表
 * <p>
 * 合并 k 个排序链表，返回合并后的排序链表。
 */
public class Solution23 {

    /**
     * 1. 优先队列 ，最小堆
     * 首先把 k 个链表的首元素都加入最小堆中，它们会自动排好序。
     * 然后我们每次取出最小的那个元素加入我们最终结果的链表中，
     * 然后把取出元素的下一个元素再加入堆中，下次仍从堆中取出最小的元素做相同的操作，
     * 以此类推，直到堆中没有元素了，此时 k 个链表也合并为了一个链表，返回首节点即可。
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // PriorityQueue<ListNode> queue = new PriorityQueue<>((l0,l1) -> Integer.valueOf(l0.val).compareTo(l1.val));
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(ListNode::getVal));
        /*PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l0, ListNode l1) {
                return Integer.valueOf(l0.val).compareTo(l1.val);
            }
        });*/

        for (ListNode node : lists) {
            if (node != null){
                queue.add(node);
            }
        }

        if (queue.isEmpty()){
            return null;
        }

        ListNode head = queue.poll();
        ListNode tail = head;
        while (tail != null) {
            if (tail.next != null) {
                queue.add(tail.next);
            }
            tail = tail.next = queue.poll();
        }

        return head;
    }

    /**
     * 2. 分而治之
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists){


    }

    private ListNode merge(ListNode[] lists,int left,int right){

    }

    private ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }

        if (l1.val <= l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode l0 = new ListNode(new int[]{1, 4, 5});
        ListNode l1 = new ListNode(new int[]{1, 3, 4});
        ListNode l2 = new ListNode(new int[]{2, 6});
        // ListNode[] listNodes = new ListNode[]{l0, l1, l2};
        ListNode[] listNodes = new ListNode[1];
        ListNode head = mergeKLists(listNodes);
        System.out.println(head);
    }

}
