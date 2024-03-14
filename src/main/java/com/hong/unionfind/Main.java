package com.hong.unionfind;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author wanghong
 * @date 2019/04/24 21:27
 **/
public class Main {
    private static double testUF(UF uf, int m) {

        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();


        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        // UnionFind1 慢于 UnionFind2
//        int size = 100000;
//        int m = 10000;

        // UnionFind2 慢于 UnionFind1, 但UnionFind3最快
        int size = 100000;
        int m = 100000;

     /*   UnionFind1 uf1 = new UnionFind1(size);
        System.out.println("UnionFind1 : " + testUF(uf1, m) + " s");

        UnionFind2 uf2 = new UnionFind2(size);
        System.out.println("UnionFind2 : " + testUF(uf2, m) + " s");
*/
//        UnionFind3 uf3 = new UnionFind3(size);
//        System.out.println("UnionFind3 : " + testUF(uf3, m) + " s");
//
//        UnionFind4 uf4 = new UnionFind4(size);
//        System.out.println("UnionFind4 : " + testUF(uf4, m) + " s");
//
//        UnionFind5 uf5 = new UnionFind5(size);
//        System.out.println("UnionFind5 : " + testUF(uf5, m) + " s");
//
//        UnionFind6 uf6 = new UnionFind6(size);
//        System.out.println("UnionFind6 : " + testUF(uf6, m) + " s");

        /**
         * A ->  B,C
         * C ->  A,E
         * D -> F
         * G -> H,I
         *
         * [A,B,C,E],[D,F],[G,H,I]
         */
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(1, Lists.newArrayList(2, 3));
        map.put(3, Lists.newArrayList(1, 5));
        map.put(4, Lists.newArrayList(6));
        map.put(7, Lists.newArrayList(8, 9));

        UnionFind6 uf6 = new UnionFind6(size);
        List<Integer> list = Lists.newArrayList(1, 2, 3, 5, 6, 7, 9);
        for (Integer s : list) {
            List<Integer> l = map.get(s);
            if (null == l || l.size() == 0) {
                continue;
            }

            for (Integer e : l) {
                if (!list.contains(e)) {
                    continue;
                }
                uf6.unionElements(s, e);
            }
        }

        int len = list.size();
        Set<Integer> tempSet = new HashSet<>();
        List<Set<Integer>> groupList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Integer a = list.get(i);
            List<Integer> l = map.get(a);
            if (null == l || l.size() == 0) {
                continue;
            }

            Set<Integer> groupSet = new HashSet<>();
            for (int j = i + 1; j < len; j++) {
                Integer b = list.get(j);
                if (!tempSet.contains(b)) {
                    boolean connected = uf6.isConnected(a, b);
                    if (connected) {
                        groupSet.add(b);
                        tempSet.add(b);
                    }
                }
            }
            if (groupSet.size() > 0) {
                groupSet.add(a);
                groupList.add(groupSet);
            }
        }

        System.out.println(groupList);

    }
}
