package com.hong.heap;

import com.hong.arrays.Array;

/**
 * @author wanghong
 * @date 2019/04/16 9:06
 * 基于数组实现的最大堆
 *
 * 堆树的定义如下：
 *   二叉堆 Binary Heap
 * （1）堆树是一颗完全二叉树；
 *  完全二叉树：除了最后一层，其他层的节点个数都是最大值，即 2^N,N为层数，根节点为0;
 *  且最后一层自左向右的节点是连续的
 *
 * （2）堆树中某个节点的值总是不大于或不小于其孩子节点的值；
 *
 * （3）堆树中每个节点的子树都是堆树。
 *
 * 当父节点的键值总是大于或等于任何一个子节点的键值时为最大堆。
 * 当父节点的键值总是小于或等于任何一个子节点的键值时为最小堆。
 **/
public class MaxHeap<E extends Comparable<E>> {

    /**
     * 用数组存储堆
     */
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 将任意数组转化成堆的形式 heapify
     * 两种方式：
     * 1.向堆中一个一个的添加元素，时间复杂度O(NlongN) ,N为元素个数
     * 2.从第一个非叶子节点开始，进行siftDown，时间复杂度 O(N)
     *
     * @param arr
     */
    public MaxHeap(E[] arr) {
        /**
         *  由最后一个元素的索引推导出第一个非叶子节点的索引,然后开始向前一个一个的进行siftDown()
         *  这样，就不需要像第一种方式一样，需要遍历每个元素
         */
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 返回堆中元素的个数
     *
     * @return
     */
    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，index索引位置的元素的父元素在数组中的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，index索引位置的元素的左孩子在数组中的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，index索引位置的元素的右孩子在数组中的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     * 在堆的最后增加一个结点,然后沿这堆树上升.
     * 将新元素放到数组末尾，然后上浮到合适的位置。
     * @param e
     */
    public void add(E e) {
        //尾插法
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 向上筛选，找到新插入元素的正确位置
     * 在堆中，当一个节点比父节点大，那么需要交换这个两个节点。
     * 交换后还可能比它新的父节点大，因此需要不断地进行比较和交换操作，把这种操作称为上浮（ShiftUp）。
     *
     * @param k
     */
    private void siftUp(int k) {
       /* while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }*/
        /**
         * siftUp优化：
         * 在上面的代码中，每次比较当前节点和其父节点的值，符合条件则交换
         * 这中间的交换过程是可以优化的，我们的目的是最终将k位置的元素与沿着
         * 其父节点的路径上找到第一个比k位置元素大的节点时或者到达根节点时则停止比较.
         * 参考Java的PriorityQueue的实现。
         */
        E cur = data.get(k);
        while (k > 0){
            int parentIndex = parent(k);
            E parent = data.get(parentIndex);
            if (cur.compareTo(parent) <= 0){
                break;
            }
            data.set(k,parent);
            k = parentIndex;
        }
        data.set(k,cur);
    }

    /**
     * 查看堆中的最大元素
     *
     * @return
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }

        return data.get(0);
    }

    /**
     * 取出堆中的最大元素
     *
     * @return
     */
    public E extractMax() {
        E max = findMax();
        //将最后一个元素先放到第一个位置，然后数据下沉筛选，找到在堆中的正确位置
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return max;
    }

    /**
     * 向下调整k位置的元素
     * 当前k位置的元素与 k位置元素的左右孩子中较大的值比较，
     * 如果 > 较大值，则说明元素已经在正确的位置了，终止循环；
     * 否则互换，从较大值位置继续上面的逻辑
     *
     * 类似地，当一个节点比子节点来得小，也需要不断地向下进行比较和交换操作，把这种操作称为下沉（Shift Down）。
     * 一个节点如果有两个子节点，应当与两个子节点中最大那个节点进行交换。
     * @param k
     */
    private void siftDown(int k) {
        // 当前节点是非叶子节点
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            // 如果也存在右孩子，则取出左右孩子较大值索引
            if ((j + 1) < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = j + 1;
            }

            // data[j] 是 leftChild 和 rightChild 中的最大值
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            data.swap(j, k);
            k = j;
        }
    }

    /**
     * 从堆中取出最大元素，替换成元素e
     *
     * @param e
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
