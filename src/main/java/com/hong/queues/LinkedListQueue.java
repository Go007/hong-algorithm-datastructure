package com.hong.queues;

/**
 * @author Macrowang
 * @date 2019/04/08 10:59
 * <p>
 * 带有尾指针的链表：使用链表实现队列
 **/
public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public E e;
        public Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 定义头指针和尾指针
    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            head = tail = new Node(e, null);
        } else {
            // 从tail端入队，从head端出队,可以想象成右进左出的数组
            tail = tail.next = new Node(e);
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;
        /**
         * 如果dequeue()后，head == null，说明当前队列中只有一个节点，即head = tail
         * 需要将tail = null。因为如果不这样做，tail指向的是已经出对的节点了，这显然是不对的。
         */
        if (head == null){
            tail = null;
        }

        size--;
        return retNode.e;
    }

    /**
     * 获取对首节点数据域
     * @return
     */
    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
