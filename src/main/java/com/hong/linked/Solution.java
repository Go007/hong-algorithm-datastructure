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
     * 通过传递的数组构建链表,头插法
     *
     * @param nums
     * @return
     */
    public ListNode build(int[] nums) {
        ListNode head = null;
        for (int val : nums) {
            if (head == null) {
                head = new ListNode(val);
            } else {
                ListNode newHead = new ListNode(val);
                newHead.next = head;
                head = newHead;
            }
        }

        return head;
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

    /**
     * 使用递归的方式删除
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements4(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode next = removeElements4(head.next, val);
        if (head.val == val) {
            return next;
        } else {
            head.next = next;
            return head;
        }
    }

    public ListNode removeElements5(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        head.next = removeElements5(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * 递归算法的调试
     */
    public ListNode removeElements(ListNode head, int val, int depth) {

        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if (head.val == val)
            ret = res;
        else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);

        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = solution.build(nums);
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            cur = cur.next;
            if (cur != null) {
                System.out.print("->");
            } else {
                System.out.print("->NULL");
            }
        }

        System.out.println();
        head = solution.removeElements(head, 6,0);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}
