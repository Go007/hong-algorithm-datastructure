package com.hong.arrays;

/**
 * @author wanghong
 * @date 2019/04/06 17:04
 * 封装自定义数组
 **/
public class Array<T> {
    private T[] data;
    private int size;

    public Array() {
        this(10);
    }

    public Array(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public Array(T[] arr) {
        data = (T[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    public int getCapacity() {
        return data.length;
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 头插法
     *
     * @param t
     */
    public void addFirst(T t) {
        add(0, t);
    }

    /**
     * 尾插法
     *
     * @param t
     */
    public void addLast(T t) {
        add(size, t);
    }

    /**
     * 向index索引处插入新元素
     *
     * @param index
     * @param t
     */
    public void add(int index, T t) {
        // 判断要插入的索引是否越界,即要插入的索引位置在[0,size]的区间内
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed,Require index > 0 and index <= size");
        }

        /**
         *  扩容有有两种触发方式：
         *  1. 先检测当前容量是否已达到阈值，是则扩容然后插入新元素；
         *  2. 先插入新元素，然后再检测当前容量是否已达到阈值，是则扩容
         */
        // 判断当前数组容量是否已满，满了自动扩容
        if (size == data.length) {
            // throw new IllegalArgumentException("Add failed,Array is full");
            resize(2 * data.length);
        }

        // 将 索引 >= index 位置的元素依次向后挪一位，腾出index的位置放入新元素,这里从最后一个元素开始挪动
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = t;
        size++;
    }

    /**
     * 获取index索引处的元素
     *
     * @param index
     * @return
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }

        return data[index];
    }

    public T getLast() {
        return get(size - 1);
    }

    public T getFirst() {
        return get(0);
    }

    /**
     * 修改index索引位置的元素为t
     *
     * @param index
     * @param t
     */
    public void set(int index, T t) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }

        data[index] = t;
    }

    /**
     * 判断数组中是否包含元素t
     *
     * @param t
     * @return
     */
    public boolean contains(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素t的索引，无则返回-1
     *
     * @param t
     * @return
     */
    public int find(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除第一个元素
     *
     * @return
     */
    public T removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     *
     * @return
     */
    public T removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组中删除指定的元素
     *
     * @param t
     */
    public void removeElement(T t) {
        int index = find(t);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 从数组中删除index位置处的元素并返回被删除的元素
     *
     * @param index
     * @return
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        T t = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null; // loitering objects != memory leak

        /**
         *  删除元素后，如果剩下的容量到了一半则缩容
         *  为了防止复杂度震荡，即出现这样的操作：
         *   addLast -> 超过data.length -> resize -> removeLast -> resize
         *   反复几次，会造成复杂度突然上升，性能下降
         *   解决方案：Lazy
         */
        //  if (size == data.length / 2) {
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return t;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d，capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(",");
            }
        }
        res.append("]");

        return res.toString();
    }

    /**
     * 将数组扩容为newCapacity大小，从旧数组迁移元素到新数组中
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 互换i，j位置的两个元素
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal.");

        T t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
