package com.hong.linked;

import java.util.List;

/**
 * Created by John on 2019/1/24.
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * 逆转单向列表
 * 将 head -> a -> b -> c -> d <- tail 变成 head -> d -> c -> b -> a <- tail
 */
public class ReverseLinkedList<T> {

    static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this(value, null);
        }

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head, tail;

    /**
     * @param values
     * @SafeVarargs在JDK 7中引入，主要目的是处理可变长参数中的泛型，此注解告诉编译器：在可变长参数中的泛型是类型安全的。可变长参数是使用数组存储的，
     * 而数组和泛型不能很好的混合使用[effective-java, p105，第25条：列表优先于数组]
     */
    @SafeVarargs
    public ReverseLinkedList(T... values) {
        for (T v : values) {
            if (tail == null) {
                head = tail = new Node<>(v);
            } else {
                tail = tail.next = new Node<>(v);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> cur = head;
        while (cur != null) {
            T value = cur.value;
            sb.append(value);
            cur = cur.next;
            if (cur != null) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public int size() {
        int count = 0;
        Node<T> cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    /**
     * 循环调整 next 指针
     * 使用了三个临时局部变量 cur、next 和 nextNext
     *
     * @return
     */
    public ReverseLinkedList reverseByLoop() {
        /**
         * 空列表或者单元素都无需处理
         */
        if (head == tail) {
            return this;
        }

        //注意数据域与指针域
        Node<T> cur = head;
        Node<T> next = cur.next;
        while (next != null) {
            Node<T> nextNext = next.next;
            next.next = cur;
            cur = next;
            next = nextNext;
        }
        tail = head;
        tail.next = null;
        head = cur;

        return this;
    }

    /**
     * 使用递归的思想来解决这个问题也是一个很好的主意，只不过当链表特别长时，调用栈会很深，
     * 链表长到一定程度就会抛出臭名昭著的异常 StackOverflowException。
     *
     * @return
     */
    public ReverseLinkedList reverseByRecursive() {
        Node<T> oldTail = tail;
        tail = reverseFrom(head);
        tail.next = null;
        head = oldTail;
        return this;
    }

    public Node<T> reverseFrom(Node<T> from) {
        if (from == tail) {
            return from;
        }
        Node<T> end = reverseFrom(from.next);
        end.next = from;
        return from;
    }

    public static void main(String[] args) {
        ReverseLinkedList<Integer> nodeList = new ReverseLinkedList<>(1, 2, 3, 4, 5);
        System.out.println(nodeList);
        /*System.out.println(nodeList.size());
        // nodeList = nodeList.reverseByLoop();
        nodeList = nodeList.reverseByRecursive();
        System.out.println(nodeList);
        System.out.println(nodeList.reverseByRecursive());*/

        System.out.println("++++++++++++++++++++++++++");
        nodeList.head = nodeList.reverse2(nodeList.head);
        System.out.println(nodeList);
    }

    /**
     * https://www.jianshu.com/p/36ed87e1937a
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点
     * 递归实质上就是系统帮你压栈的过程，系统在压栈的时候会保留现场。
     * 递归法是从最后一个Node开始，在弹栈的过程中将指针顺序置换的
     * @param head
     * @return
     */
    public Node<T> reverse(Node<T> head) {
        if (head == null || head.next == null){
            return head;
        }
        Node<T> temp = head.next;
        /**
         * 假设原链表为1->2->3->4,
         * 递归到head=3,则temp=4，head.next=4, 再次进入递归，满足退出条件，压栈结束，开始弹栈
         * newHead = 4,
         * 程序继续执行 temp.next = head就相当于4->3
         * head.next = null 即把 3结点指向4结点的指针断掉。
         * 返回新链表的头结点newHead
         */
        Node<T> newHead = reverse(head.next);
        temp.next = head;
        head.next = null;
        return newHead ;
    }

    /**
     * 迭代遍历
     * 假设原链表 1->2->3->4
     *  第一次：head=1,next=2,1.next=null,pre=1    1->null
     *  第二次：head=2,next=3,2.next=1,pre=2       2->1->null
     *  第二次：head=3,next=4,3.next=2,pre=3       3->2->1->null
     *  第四次：head=4,next=null,4.next=3,pre=4    4->3->2->1->null
     * @param head
     * @return
     */
    public Node<T> reverse2(Node<T> head) {
        //pre保存先前节点
        Node<T> pre = null;
        //next中间临时变量
        Node<T> next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
