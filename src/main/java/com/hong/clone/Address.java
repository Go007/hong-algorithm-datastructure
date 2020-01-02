package com.hong.clone;

import java.io.Serializable;

/**
 * Created by wanghong
 * Date 2020-01-02 09:24
 * Description:
 * 深拷贝需要引用类型属性都要实现Serializable
 */
public class Address implements Serializable {

    private String province;
    private String city;

    public void setAddress(String province, String city) {
        this.province = province;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
