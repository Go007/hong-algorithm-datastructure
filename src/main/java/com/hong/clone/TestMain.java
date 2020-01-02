package com.hong.clone;

/**
 * Created by wanghong
 * Date 2020-01-02 09:27
 * Description:
 */
public class TestMain {
    public static void main(String[] args) throws Exception{
        // Java 浅拷贝，p1 和 p2 的 address 属性指向同一个 Address实例对象，浅拷贝
        Person p1 = new Person("wh",30);
        p1.setAddress("安徽省","安庆市");

        System.out.println("p1:"+p1);
        System.out.println("p1.getPname:"+p1.getPname().hashCode());

        Person p2 = (Person) p1.clone();
        System.out.println("p2:"+p2);
        System.out.println("p2.getPname:"+p2.getPname().hashCode());

        p1.display("p1");
        p2.display("p2");
        p2.setAddress("安徽省", "池州市");
        System.out.println("将复制之后的对象地址修改：");
        p1.display("p1");
        p2.display("p2");
        
        // Java 深拷贝
        System.out.println("=================");
        // Java 浅拷贝，p1 和 p2 的 address 属性指向同一个 Address实例对象，浅拷贝
        Person2 p3 = new Person2("wh",30);
        p3.setAddress("安徽省","安庆市");

        System.out.println("p3:"+p3);
        System.out.println("p3.getPname:"+p3.getPname().hashCode());

        Person2 p4 = (Person2) p3.deepClone();
        System.out.println("p4:"+p4);
        System.out.println("p4.getPname:"+p4.getPname().hashCode());

        p3.display("p3");
        p4.display("p4");
        p4.setAddress("安徽省", "池州市");
        System.out.println("将复制之后的对象地址修改：");
        p3.display("p3");
        p4.display("p4");
        
    }
}
