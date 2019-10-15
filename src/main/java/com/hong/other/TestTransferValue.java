package com.hong.other;

import com.hong.cas.User;

/**
 * @author wanghong
 * @date 2019/10/15 22:33
 * 传值还是传引用
 **/
public class TestTransferValue {

    public void changeValue1(int age){
        age = 30;
    }

    public void changeValue2(User user){
        user.setName("xxx");
    }

    public void changeValue3(String str){
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        //age属于main方法的，然后调用方法时复印了一份传给它，然后方法把复印件给改动了
        //我只是给你复印了一份值，原件根本没动，所以第一个age还是20
        test.changeValue1(age);
        System.out.println("age---" + age);

        User user = new User("abc");
       // user是main的，传引用传内存地址给方法，两个引用指向了同一个地址，这时把这个地址的值改动了
        test.changeValue2(user);
        System.out.println(user);

        String str = "abc";
        //str是属于main方法的，这个池子里有了abc
        //这个池子里面没有xxx，那么就重新创建一个指向它
        test.changeValue3(str);
        System.out.println("String---" + str);
    }
}
