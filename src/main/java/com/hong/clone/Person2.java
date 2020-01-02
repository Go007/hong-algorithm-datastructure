package com.hong.clone;

import java.io.*;

/**
 * Created by wanghong
 * Date 2020-01-02 09:22
 * Description:
 * Java中创建对象的方式之一：序列化 深拷贝
 * 如果有某个属性不需要序列化，可以将其声明为 transient，即将其排除在克隆属性之外。
 */
public class Person2 implements Serializable {

    public String pname;
    public int page;
    public Address address;

    public Person2() {}

    public Person2(String pname, int page) {
        this.pname = pname;
        this.page = page;
        this.address = new Address();
    }

    public void setAddress(String province, String city) {
        address.setAddress(province, city);
    }

    public void display(String name) {
        System.out.println(name + ":" + "pname=" + pname + ", page=" + page + "," + address);
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    //深度拷贝,系列化
    public Object deepClone() throws Exception{
        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(this);

        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }
}
