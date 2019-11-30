package com.hong.heap;

import java.util.Arrays;

/**
 * @author wanghong
 * @date 2019/11/30 20:48
 * 最大索引堆
 **/
public class IndexMaxHeap<E extends Comparable> {
    // 最大索引堆中的数据
    private E[] data;
    // 最大索引堆中的索引
    private int[] indexes;
    // 索引堆中的元素个数
    private int count;
    private int capacity;

    /**
     * 构造一个空堆，可容纳capacity个元素
     * @param capacity
     */
    public IndexMaxHeap(int capacity){
        data = (E[])new Comparable[capacity+1];
        indexes = new int[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    /**
     * 向索引堆中插入一个元素，元素的索引为i，值为e
     * 对于用户而言，传入的i是从0开始的
     * @param i
     * @param e
     */
    public void insert(int i,E e){
        if (i + 1 < 1 || i + 1 > capacity || count + 1> capacity){
            throw new IllegalArgumentException("i is illegal");
        }

        i++;
        data[i] = e;
        count++;
        indexes[count] = i;

        shiftUp(count);
    }

    /**
     * 从最大索引堆中取出堆顶元素，即索引堆中存储的最大数据
     * @return
     */
    public E extractMax(){
        if (count <= 0){
            return null;
        }

        E ret = data[indexes[1]];
        swapIndexes(1,count);
        count--;
        shiftDown(1);
        return ret;
    }

    /**
     * 从最大索引堆中取出堆顶元素的索引
     * @return
     */
    public Integer extractMaxIndex(){
        if (count <= 0) return null;

        int ret = indexes[1] - 1;
        swapIndexes( 1 , count );
        count --;
        shiftDown(1);
        return ret;
    }

    /**
     *  返回最大索引堆中的堆顶元素
     * @return
     */
    public E getMax(){
        if (count <= 0) return null;
        return data[indexes[1]];
    }

    /**
     * 返回最大索引堆中的堆顶元素对应的索引
     * @return
     */
    public Integer getMaxIndex(){
        if (count <= 0) return null;

        return indexes[1] - 1;
    }

    /**
     * 返回最大索引堆中索引为k的元素值
     * @param k
     * @return
     */
    public E get(int k){
        if (k + 1 < 1 || k + 1 > capacity) return null;
        return data[k + 1];
    }

    /**
     * 将最大索引堆中索引为i的元素修改为newVal
     * @param i
     * @param newVal
     */
    public void change(int i,E newVal){
        i += 1;
        data[i] = newVal;

        /**
         * 找到indexes[j] = i,j表示data[i]在堆中的位置
         * 之后shiftUp(j),再shiftDown(j)
         */
        for (int j = 1;j <= count;j++){
            if (indexes[j] == i){
                shiftUp(j);
                shiftUp(j);
            }
        }
    }

    /**
     * 下沉元素
     * @param k
     */
    private void shiftDown(int k) {
        // 最后一个非叶子节点的索引为 count/2；
        while (2*k <= count){
            int j = 2*k; //左孩子
            if (j + 1 <= count && data[indexes[j+1]].compareTo(data[indexes[j]]) > 0){
                j = j + 1;
            }
            if (data[indexes[k]].compareTo(data[indexes[j]]) > 0){
                break;
            }
            swapIndexes(k,j);
            k = j;
        }
    }

    /**
     *  上浮元素
     * 索引堆中，数据之间的比较根据data的大小，但实际操作的是索引
     * @param k
     */
    private void shiftUp(int k){
       while (k > 1 && data[indexes[k]].compareTo(data[indexes[k/2]]) > 0){
           swapIndexes(k,k/2);
           k /= 2;
       }
    }

    /**
     *  交换索引堆中的索引
     * @param i
     * @param j
     */
    private void swapIndexes(int i, int j) {
        int temp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = temp;
    }

    // 测试索引堆中的索引数组index
    // 注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
    public boolean testIndexes(){
        int[] copyIndexes = new int[count+1];
        for( int i = 0 ; i <= count ; i ++ )
            copyIndexes[i] = indexes[i];

        copyIndexes[0] = 0;
        Arrays.sort(copyIndexes);

        // 在对索引堆中的索引进行排序后, 应该正好是1...count这count个索引
        boolean res = true;
        for( int i = 1 ; i <= count ; i ++ )
            if( copyIndexes[i-1] + 1 != copyIndexes[i] ){
                res = false;
                break;
            }

        if( !res ){
            System.out.println("Error!");
            return false;
        }

        return true;
    }

    // 测试 IndexMaxHeap
    public static void main(String[] args) {
        int N = 1000000;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(N);
        for( int i = 0 ; i < N ; i ++ )
            indexMaxHeap.insert( i , (int)(Math.random()*N) );
        System.out.println(indexMaxHeap.testIndexes());
    }
}
