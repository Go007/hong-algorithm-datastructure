package com.hong.util;

import com.hong.cas.User;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 王宏
 * @email hong.wang8@amh-group.com
 * @date: 2021/11/8 15:07
 * @version: 1.0
 */
public class BeanToMapUtil {

    public static <T> List<Map> beanToMap(List<T> list) {
        List<Map> result = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return result;
        }

        for (T t : list) {
            result.add(beanToMap(t));
        }

        return result;
    }

    public static <T> Map beanToMap(T t) {
        if (t == null) {
            return null;
        }

        Map map = new HashMap();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : ps) {
                String key = propertyDescriptor.getName();
                if (!"class".equals(key)) {
                    Method getter = propertyDescriptor.getReadMethod();
                    getter.setAccessible(true);
                    Object value = getter.invoke(t);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return map;
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("a"));
        list.add(new User("b"));
        list.add(new User("c"));
        List<Map> maps = beanToMap(list);
        System.out.println(maps);
    }

}
