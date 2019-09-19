package com.hong.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wanghong
 * @date 2019/04/25 21:29
 **/
// 文件相关操作
public class FileOperation {

    // 读取文件名称为filename中的内容，并将其中包含的所有词语放进words中
    public static boolean readFile(String filename, ArrayList<String> words){

        if (filename == null || words == null){
            System.out.println("filename is null or words is null");
            return false;
        }

        // 文件读取
        Scanner scanner;

        try {
            File file = new File(filename);
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }
            else
                return false;
        }
        catch(IOException ioe){
            System.out.println("Cannot open " + filename);
            return false;
        }

        // 简单分词
        // 这个分词方式相对简陋, 没有考虑很多文本处理中的特殊问题
        // 在这里只做demo展示用
        if (scanner.hasNextLine()) {

            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); )
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else
                    i++;
        }

        return true;
    }

    // 寻找字符串s中，从start的位置开始的第一个字母字符的位置
    private static int firstCharacterIndex(String s, int start){

        for( int i = start ; i < s.length() ; i ++ )
            if( Character.isLetter(s.charAt(i)) )
                return i;
        return s.length();
    }

    public static void main(String[] args) {
        String s0 = "<span class=\"highlight\">";
        String s1 = "</span>";
        String str = "<span class=\"highlight\">万</span><span class=\"highlight\">科</span>开发公司";
        str = str.replace(s0,"").replace(s1,"");
        System.out.println(str);
        System.out.println(str.length());

        String localMaven = System.getProperty("user.home") + "/.m2/repository";
        System.out.println(localMaven);
        File file = new File(localMaven);
        System.out.println(file.getAbsoluteFile());

        List<Map<String,Object>> l0 = new ArrayList<>();
        Map<String,Object> m0 = new HashMap<>();
        m0.put("proCode",26);
        m0.put("proId",1);
        Map<String,Object> m1 = new HashMap<>();
        m1.put("proCode",28);
        m1.put("proId",2);
        Map<String,Object> m6 = new HashMap<>();
        m6.put("proCode",29);
        m6.put("proId",1);
        l0.add(m0);
        l0.add(m1);
        l0.add(m6);

        List<Object> collect = l0.stream().filter(m -> (Integer) m.get("proCode") == 26).map(m -> m.get("proId")).collect(Collectors.toList());
        collect.forEach(p -> System.out.println(p));

        List<Map<String, Object>> ll = l0.stream().filter(m -> !collect.contains(m.get("proId"))).collect(Collectors.toList());
        ll.forEach(m -> System.out.println(m));


        System.out.println("==========");
       // Map<Object, List<Map<String, Object>>> proId = l0.stream().filter(m -> (Integer)m.get("proCode")!=26).collect(Collectors.groupingBy(m -> m.get("proId")));
        Map<Object, List<Object>> proId = l0.stream().collect(Collectors.groupingBy(m -> m.get("proId"), Collectors.mapping(m -> m.get("proCode"), Collectors.toList())));


        for (Map.Entry<Object, List<Object>> entry:proId.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("====================");
        List<Map<String,Object>> l1 = new ArrayList<>();
        Map<String,Object> m2 = new HashMap<>();
        m2.put("proCode",26);
        l1.add(m2);

        List<Object> l2 = l0.stream().map(m -> m.get("proCode")).collect(Collectors.toList());
        List<Object> l3 = l1.stream().map(m -> m.get("proCode")).collect(Collectors.toList());
        List<Object> l4 = l2.stream().filter(c -> !l3.contains(c)).collect(Collectors.toList());
        l4.forEach(c -> System.out.println(c));

        List<Map<String, Object>> l5 = l0.stream().filter(m -> l4.contains(m.get("proCode"))).collect(Collectors.toList());
        l5.forEach(d -> System.out.println(d));


        System.out.println("++++++++++++");
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        num.add(5);
        List<Integer> subList = num.subList(1, 2);
        System.out.println(subList);
    }
}

