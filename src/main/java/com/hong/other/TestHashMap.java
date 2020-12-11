package com.hong.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：wanghong
 * @date ：2020-12-11 09:21
 * @description：
 */
public class TestHashMap {

    public static void main(String[] args) {
        /**
         * HashMap初始化后，默认值不变，在不扩容的情况下，最多可以存入多少元素？
         */
        //第一种情况：一直往同一个 table[bucketIndex]中放入元素，当放入第9个元素时，触发树形转换,扩容
        HashMap<MyObj,Integer> map = new HashMap<>();
        for (int i = 1;i <= 9;i++){
            map.put(new MyObj(),i);
        }
        System.out.println(map.size());

        // 第二种情况：依次往table中12个不同的bucket中放入数据，当放入第13个元素时，触发 ++size > threshold,扩容
        HashMap<Integer,Integer> map2 = new HashMap<>();
        for (int j = 1;j <= 13;j++){
            map2.put(j,j);
        }
        System.out.println(map2.size());

        /////////////////////////////////
        Map<Person,String> m = new HashMap<>();
        Person p = new Person();
        p.setName("A");
        m.put(p,"X");
        p.setName("B");
        m.put(p,"Y");
        /**
         * 因为Person类中重写了hashCode(),依赖String类的hashCode()
         * 所以两次put操作,虽然操作的是同一个Person实例,但因为中间改变了
         * name成员变量值,导致在第二次put()时p的hashCode()返回值被改变，
         * 会被放入Entry[]中的另外一个位置.在get(p)操作时会根据p的hashCode()去对应的
         * 数组位置处取值,因为此时p的name=“B”,所以取的是第二次的值，但Entry[]数组中存的是
         * 有2个Entry,即使2个Entry的key是同一个类实例，但在HashMap看来，你的hashCode()不一样，
         * 你两个key就不一样，这也从侧面说明了正确的重写hashCode()的重要性。
         */
        System.out.println(m.get(p)); // Y
        System.out.println(m.size());// 2
    }

    private static class MyObj{

        @Override
        public int hashCode() {
            return 15;
        }
    }

    private static class Person{
        private String name;

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
