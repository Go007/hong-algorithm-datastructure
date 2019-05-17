package com.hong.leetcode;

import com.hong.linked.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-05-17 09:46
 * Description:
 * 141. 环形链表
 */
public class Solution141 {

    public static boolean hasCycle(ListNode head) {
        int pos = -1;
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode cur = head;
        int index = 0;
        while (cur != null) {
            if (map.containsKey(cur)) {
                pos = map.get(cur);
                break;
            }
            map.put(cur, index);
            index++;
            cur = cur.next;
        }

        return pos == -1 ? false : true;
    }

    public static boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }

        while (head.next != head) {
            ListNode next = head.next;
            if (next == null){
                return false;
            }
            if (next.next == head){
                return true;
            }


        }

        return false;
    }

}
