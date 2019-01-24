package com.hong.other;

/**
 * Created by John on 2019/1/24.
 * 逆转单向列表
 * 将 head -> a -> b -> c -> d <- tail 变成 head -> d -> c -> b -> a <- tail
 */
public class ReverseLinkedList<T> {

    private Node<T> head, tail;

    public ReverseLinkedList(T... values) {
        for (T v : values) {
            if (tail == null) {
                head = tail = new Node<>(v);
            } else {
                Node<T> oldTail = tail;
                oldTail.next = tail = new Node<>(v);
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
        System.out.println(nodeList.size());
        // nodeList = nodeList.reverseByLoop();
        nodeList = nodeList.reverseByRecursive();
        System.out.println(nodeList);
    }

}
