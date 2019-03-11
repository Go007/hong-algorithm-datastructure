package com.hong.linked;

/**
 * Created by John on 2019/1/24.
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * 逆转单向列表  https://juejin.im/post/5c46d82ee51d45215c2e438b
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

       /* System.out.println("++++++++++++++++++++++++++");
        nodeList.head = nodeList.reverse(nodeList.head);
        System.out.println(nodeList);*/

        System.out.println("=========================");
        //  nodeList.deleteNode(1);
       // nodeList.head = nodeList.removeNthFromEnd(nodeList.head, 3);
        nodeList.head = nodeList.removeNthFromEnd2(nodeList.head, 2);
        System.out.println(nodeList);
    }

    /**
     * https://www.jianshu.com/p/36ed87e1937a
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点
     * 递归实质上就是系统帮你压栈的过程，系统在压栈的时候会保留现场。
     * 递归法是从最后一个Node开始，在弹栈的过程中将指针顺序置换的
     *
     * @param head
     * @return
     */
    public Node<T> reverse(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        /**
         * 假设原链表为1->2->3->4,
         * 递归到head=3,则next=4，head.next=4, 再次进入递归，满足退出条件，压栈结束，开始弹栈
         * newHead = 4,
         * 程序继续执行 next.next = head就相当于4->3
         * head.next = null 即把 3结点指向4结点的指针断掉。
         * 返回新链表的头结点newHead
         */
        Node<T> next = head.next;
        Node<T> newHead = reverse(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 迭代遍历
     * 假设原链表 1->2->3->4
     * 第一次：head=1,next=2,1.next=null,pre=1    1->null
     * 第二次：head=2,next=3,2.next=1,pre=2       2->1->null
     * 第二次：head=3,next=4,3.next=2,pre=3       3->2->1->null
     * 第四次：head=4,next=null,4.next=3,pre=4    4->3->2->1->null
     *
     * @param head
     * @return
     */
    public Node<T> reverse2(Node<T> head) {
        //pre保存先前节点
        Node<T> pre = null;
        //next中间临时变量
        Node<T> next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 1->2->3->4
     * 第一次遍历：1->2 2->1 3->4  cur=2,nextNext=next=3
     * 第二次遍历：1->2 3->2->1 4  cur=3,nextNext=next=4
     * 第三次遍历：1->2 4-3->2->1 cur=4,nextNext=next=null,退出循环
     * <p>
     * 使用三个临时变量，cur,next,nextNext，分别代表当前元素，当前元素的下一个元素，当前元素下一个元素的下一个元素，
     * 将这三个变量从head开始依次向后递进，每次递进过程中，将当前元素的下一个元素的next域指向自己
     *
     * @param head
     * @return
     */
    public Node<T> reverse3(Node<T> head) {
        Node<T> cur = head;
        Node<T> next = cur.next;
        while (next != null) {
            Node<T> nextNext = next.next;
            next.next = cur;
            cur = next;
            next = nextNext;
        }
        head.next = null;
        return cur;
    }

    /**
     * 删除链表中数据域等于指定值的节点
     * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
     *
     * @param t
     */
    public void deleteNode(T t) {
        Node<T> prev = null;
        Node<T> cur = head;

        while (cur.value != t) {
            prev = cur;
            cur = cur.next;
        }

        // 待删除的节点是头节点,则将头节点的下一个节点置为头结点
        if (prev == null) {
            Node delNode = head;
            head = head.next;
            delNode.next = null;
        } else {
            prev.next = cur.next;
            cur.next = null;
        }
    }

    /**
     * 删除链表的倒数第N个节点,并返回删除后的链表头节点
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * <p>
     * 使用两个节点，一个是p一个是q。先让p走n步，然后再让p和q同时往前走，当p走到头时，q即是倒数第n+1个节点了。
     * 令q.next = q.next.next则删除了倒数第n个结点
     * 但有两种特殊情况：1.链表长度小于n，则返回原链表 2.链表长度为n，则返回head.next
     *
     * @param head
     * @param n
     * @return
     */
    public Node<T> removeNthFromEnd(Node<T> head, int n) {
        Node<T> p = head;
        int i;
        for (i = 0; i < n; i++) {
            p = p.next;
            if (p == null) {
                break;
            }
        }

        if (p == null) {
            //链表长度为n时，删除头节点
            if (i == (n - 1)) {
                Node<T> newHead = head.next;
                head.next = null;
                return newHead;
            }
            // 链表长度<n时，返回原链表
            if (i < (n - 1)) {
                return head;
            }
        }

        Node<T> q = head;
        while (p.next != null) {
            q = q.next;
            p = p.next;
        }

        Node<T> nNode = q.next;
        q.next = nNode.next;
        nNode.next = null;

        return head;
    }

    public Node<T> removeNthFromEnd2(Node<T> head, int n) {
        if (head == null)
            return head;

        Node node = head;
        int size = 1;
        while (node.next != null) {
            size++;
            node = node.next;
        }

        int delIndex = size - n;
        if (delIndex == 0) {
            Node delNode = head;
            head = head.next;
            delNode.next = null;
        } else if (delIndex > 0) {
            Node prev = head;
            for (int i = 1; i < delIndex; i++) {
                prev = prev.next;
            }
            Node delNode = prev.next;
            prev.next = prev.next.next;
            delNode.next = null;
        }

        return head;
    }

}
