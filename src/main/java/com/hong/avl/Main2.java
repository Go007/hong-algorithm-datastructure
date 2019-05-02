package com.hong.avl;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author wanghong
 * @date 2019/05/02 23:14
 **/
public class Main2 {
    public static void main(String[] args) {

        int n = 200000;

        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>(n);
        for(int i = 0 ; i < n ; i ++)
          //  testData.add(i);
            testData.add(random.nextInt(Integer.MAX_VALUE));

        // Test AVL
        long startTime = System.nanoTime();

        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (Integer x: testData)
            avl.add(x, null);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: " + time + " s");


        // Test RBTree
        startTime = System.nanoTime();

        RBTree<Integer, Integer> rbt = new RBTree<>();
        for (Integer x: testData)
            rbt.add(x, null);

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: " + time + " s");
    }
}
