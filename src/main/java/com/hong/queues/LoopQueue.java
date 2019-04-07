package com.hong.queues;

/**
 * @author wanghong
 * @date 2019/04/07 0:08
 * 循环队列
 * ArrayQueue弊端：在dequeue()时，因为底层依赖的时removeFirst()方法，
 * 时间复杂度为O(n),因为每次出队一个元素，就要将剩下的所有元素向前移动一位。
 * 而循环队列中，使用了两个指示标记front和tail，front始终指向队首元素位置，
 * 而tail始终指向队尾元素的下一个位置。
 **/
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;
    // LoopQueue中不声明size，如何完成所有的逻辑？
    // 这个问题可能会比想象的要难一点点：）
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    /**
     * 返回实际可用的最大空间
     *
     * @return
     */
    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        /**
         *   判断队列是否已满
         *   这里的 tail+1 主要考虑的是：
         *   isEmpty()的判定条件是 front == tail
         *   那么作为循环队列，判定队列是否满的条件需要多浪费一个空间，
         *   从而与isEmpty()的情况区分开来
         */
        if ((tail + 1) % data.length == front) {
            // 队列满了，要进行扩容
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        // 缩容，当实际元素个数等于数组容量的1/4，且数组容量>=2时进行缩容
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("Queue is empty");
        }

        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        //复位
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){
        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }

            System.out.println("=================");
        }
    }
}
