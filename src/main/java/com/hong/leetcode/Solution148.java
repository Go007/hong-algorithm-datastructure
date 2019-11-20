package com.hong.leetcode;

import com.hong.linked.ListNode;

/**
 * Created by wanghong
 * Date 2019-05-10 19:18
 * Description:148. 排序链表
 */
public class Solution148 {

    /**
     * 自顶向下
     * 参考：Sort List——经典（链表中的归并排序） https://www.cnblogs.com/qiaozhoulin/p/4585401.html
     *
     * 归并排序法：在动手之前一直觉得空间复杂度为常量不太可能，因为原来使用归并时，都是 O(N)的，
     * 需要复制出相等的空间来进行赋值归并。对于链表，实际上是可以实现常数空间占用的（链表的归并
     * 排序不需要额外的空间）。利用归并的思想，递归地将当前链表分为两段，然后merge，分两段的方
     * 法是使用 fast-slow 法，用两个指针，一个每次走两步，一个走一步，知道快的走到了末尾，然后
     * 慢的所在位置就是中间位置，这样就分成了两段。merge时，把两段头部节点值比较，用一个 p 指向
     * 较小的，且记录第一个节点，然后 两段的头一步一步向后走，p也一直向后走，总是指向较小节点，
     * 直至其中一个头为NULL，处理剩下的元素。最后返回记录的头即可。
     *
     * 主要考察3个知识点，
     * 知识点1：归并排序的整体思想
     * 知识点2：找到一个链表的中间节点的方法
     * 知识点3：合并两个已排好序的链表为一个新的有序链表
     *
     * 一切的前提就是搞清为啥归并排序可以排序链表，原因如下
     * 包括选择、插入、堆排序、快排等等都需要随机读取进行元素的交换，
     * 这正是链表所缺乏的，归并排序是为数不多不需要随机读取的排序算法
     */
    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        }
        // p 最后表示中间节点，pre 最后表示 中间节点的前一个节点
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        // 将整个链表一分为二
        pre.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(p);
        return merge(l, r);
    }

    ListNode merge(ListNode l, ListNode r) {
        // 哑链表头。临时创建的一个链表头，把边界情况和普通情况做统一处理
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        if (l != null) {
            cur.next = l;
        }
        if (r != null) {
            cur.next = r;
        }
        return dummyHead.next;
    }

    /****************************************************/
    /** TODO 链表排序的自底向上的归并排序实现
     *要求空间复杂度是 O(1)，因此不能使用递归。因此这里使用 bottom-to-up 的算法来解决
     * bottom-to-up 的归并思路是这样的：先两个两个的 merge，完成一趟后，再 4 个4个的 merge，直到结束。举个简单的例子：[4,3,1,7,8,9,2,11,5,6].
     *
     * step=1: (3->4)->(1->7)->(8->9)->(2->11)->(5->6)
     * step=2: (1->3->4->7)->(2->8->9->11)->(5->6)
     * step=4: (1->2->3->4->7->8->9->11)->5->6
     * step=8: (1->2->3->4->5->6->7->8->9->11)
     * 链表里操作最难掌握的应该就是各种断链啊，然后再挂接啊。在这里，我们主要用到链表操作的两个技术：
     *
     * merge(l1, l2)，双路归并，我相信这个操作大家已经非常熟练的，就不做介绍了。
     * cut(l, n)，可能有些同学没有听说过，它其实就是一种 split 操作，即断链操作。不过我感觉使用 cut 更准确一些，它表示，将链表 l 切掉前 n 个节点，并返回后半部分的链表头。
     * 额外再补充一个 dummyHead 大法，已经讲过无数次了，仔细体会吧。
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {

        return null;
    }

    public static void main(String[] args) {
        Solution148 s = new Solution148();
        int[] nums = {4,2,1,3};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        head = s.sortList(head);
        System.out.println(head);
    }

}
