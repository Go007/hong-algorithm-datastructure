package com.hong.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wanghong
 * Date 2019-10-15 09:44
 * Description:
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User u0 = new User("u0",21);
        User u1 = new User("u1",28);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(u0);

        System.out.println(atomicReference.compareAndSet(u0,u1) + "\t" + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(u0,u1) + "\t" + atomicReference.get());
    }

}
