package com.hong.linked;

import java.util.*;

/**
 * @author Macrowang
 * @date 2019/04/08 11:53
 * Leetcode 203. Remove Linked List Elements
 * https://leetcode.com/problems/remove-linked-list-elements/description/
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 **/
public class Solution {

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
        // 要删除的节点是头节点，这里注意是while，不是if，因为有可能head后面数据域还是val
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
         * 这样，node就变成了完全孤立的节点，让GC及时回收，释放了被删除节点占用的空间。
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
     * 从递归的宏观语义上去看递归函数，不要陷入递归底层运行机制的纠结中。
     * 比如 removeElements(ListNode head, int val) 函数本身的意义就是要删除
     * 以head为头节点的链表中数据域为val的节点，将递归调用看成普通的A()调B()，
     * B()调C()。。。只不过A(),B(),C()函数体都是一样的。
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements4(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        /**
         * 递归的关键是将一个大的问题转化为一个同性质的更小的问题，
         * 直到这个更小的问题无法再进行拆分，且已有明确的答案，
         * 那么再向上回溯，组合每一层的答案，最终得到整个答案。
         */
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
        System.out.println("Call: remove " + val + " in " + (head != null ? head.val : null));

        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + null);
            return head;
        }

        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + (res != null ? res.val : null));

        ListNode ret;
        if (head.val == val)
            ret = res;
        else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret.val);

        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    /**
     * 1019. 链表中的下一个更大节点
     * https://leetcode-cn.com/problems/next-greater-node-in-linked-list/
     * 第一版：超时
     *
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> resList = new ArrayList<>();

        ListNode cur = head;
        while (cur != null) {
            ListNode compNode = cur.next;
            while (compNode != null) {
                if (compNode.val > cur.val) {
                    resList.add(compNode.val);
                    break;
                }
                compNode = compNode.next;
            }

            if (compNode == null) {
                resList.add(0);
            }

            cur = cur.next;
        }

        return resList.stream().mapToInt(Integer::valueOf).toArray();
    }

    public int[] nextLargerNodes1(ListNode head) {
        // 1. 创建一个容器 list 来存储数据
        List<Integer> list = new ArrayList<>();
        int size = 0;
        while (head != null) {
            list.add(head.val);
            size++;
            head = head.next;
        }

        int[] res = new int[size];
        // 2. 创建一个栈 stack ，这个栈里面top存储的是对应位置的 list 元素,及其之后元素中最大的值。
        Stack<Integer> stack = new Stack<>();
        // 倒序遍历
        for (int i = size - 1; i >= 0; i--) {
            int cur = list.get(i);
            /** 3. 在 list 中从右往左遍历，stack 中凡是 <= list.get(i) 都 pop 出去，
             *    这样 stack 剩下的元素都是比 list.get(i) 更大的元素,
             *    且栈顶就是第一个比list.get(i)大的元素，如果没有栈顶元素，则说明当前元素是它后面元素中最大的
             */
            while (!stack.isEmpty() && stack.peek() <= cur) {
                stack.pop();
            }

            res[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(cur);
        }

        return res;
    }

    /**
     * 21. 合并两个有序链表
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode tail = new ListNode(-1);
        ListNode dummyHead = tail;
        // 不改变原链表的next指向，创建新链表存储排序后的节点
        ListNode p1 = l1, p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                tail = tail.next = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                tail = tail.next = new ListNode(p2.val);
                p2 = p2.next;
            }
        }

        while (p1 != null) {
            tail = tail.next = new ListNode(p1.val);
            p1 = p1.next;
        }

        while (p2 != null) {
            tail = tail.next = new ListNode(p2.val);
            p2 = p2.next;
        }

        return dummyHead.next;
    }

    /**
     * 在mergeTwoLists()方法中，代码实现改变原来链表 l1 和 l2 的 next 指针指向，
     * 没有使用新的链表去存储原来链表的数据，减少了空间复杂度，这点上是有所优化的。
     * 题目中提到 "新链表是通过拼接给定的两个链表的所有节点组成的"，
     * 两种实现方式(改变原链表指向和创建新链表存储) 其实都实现了所谓的 "拼接"，
     * 若原链表在其他地方有所引用，最好创建新的链表进行数据存储，否则，改变原链表指向的方法更优
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //表示拼接后链表的尾节点
        ListNode tail = new ListNode(-1);
        //虚拟头结点 dummyHead 保留对链表头部的引用
        ListNode dummyHead = tail;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail = tail.next = l1;
                l1 = l1.next;
            } else {
                tail = tail.next = l2;
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            tail.next = l1;
        }

        if (l2 != null) {
            tail.next = l2;
        }

        return dummyHead.next;
    }

    /**
     * 141. 环形链表
     * https://leetcode-cn.com/problems/linked-list-cycle/
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        int pos = -1;
        Map<Integer, Integer> map = new HashMap<>();
        ListNode cur = head;
        int index = 0;
        while (cur.next != null) {
            if (map.containsKey(cur.next.val)) {
                pos = map.get(cur.next.val);
                break;
            }
            map.put(cur.val, index);
            index++;
            cur = cur.next;
        }

        return pos == -1 ? false : true;
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {3, 2, 1};
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
        head = solution.removeElements(head, 2, 0);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }


        System.out.println("=======================================");
        int[] array = {2, 7, 4, 3, 5};
        ListNode node = new ListNode(array);
        System.out.println(node);
        int[] res = solution.nextLargerNodes1(node);
        for (int i : res) {
            System.out.print(i + ",");
        }

        System.out.println("========================================");
        int[] a1 = {1, 2, 4};
        int[] a2 = {1, 3, 4};
        ListNode l1 = new ListNode(a1);
        ListNode l2 = new ListNode(a2);
        ListNode listNode = solution.mergeTwoLists2(l1, l2);
        System.out.println(listNode);
    }
}
