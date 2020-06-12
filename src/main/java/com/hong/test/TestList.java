package com.hong.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/6/11 16:44
 * @Version V1.0
 * <p>
 * List 如何一边遍历，一边删除？
 **/
public class TestList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list = rightDelete3(list);
        System.out.println(list);
    }

    public static List<String> wrongDelete(List<String> list) {
        for (String s : list) {
            if ("b".equals(s)) {
                list.remove(s);
            }
        }
        return list;
    }

    public static List<String> wrongDelete2(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if ("a".equals(s)) {
                list.remove(s);
            }
        }
        return list;
    }

    public static List<String> wrongDelete3(List<String> list) {
        for (int i = 0;i < list.size();i++) {
            String s = list.get(i);
            if ("b".equals(s)) {
                list.remove(s);
            }
        }
        return list;
    }

    public static List<String> rightDelete(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if ("a".equals(s)) {
                it.remove();
            }
        }
        return list;
    }

    public static List<String> rightDelete2(List<String> list) {
        for (int i = 0;i < list.size();i++) {
            String s = list.get(i);
            if ("a".equals(s)) {
                list.remove(i);
                i = i - 1;
            }
        }
        return list;
    }

    public static List<String> rightDelete3(List<String> list) {
        for (int i = list.size() - 1;i >= 0;i--) {
            String s = list.get(i);
            if ("a".equals(s)) {
                list.remove(i);
            }
        }
        return list;
    }

}
