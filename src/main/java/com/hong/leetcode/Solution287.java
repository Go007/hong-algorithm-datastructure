package com.hong.leetcode;

/**
 * @author wanghong
 * @date 2020/10/24 20:41
 * 287. 寻找重复数
 **/
public class Solution287 {

    /**
     * 从理论上讲，数组中如果有重复的数，那么就会产生多对一的映射，这样，形成的链表就一定会有环路了，
     * 综上
     * 1.数组中有一个重复的整数 <==> 链表中存在环
     * 2.找到数组中的重复整数 <==> 找到链表的环入口
     * 至此，问题转换为 142 题。那么针对此题，快、慢指针该如何走呢。根据上述数组转链表的映射关系，可推出
     * 142 题中慢指针走一步 slow = slow.next ==> 本题 slow = nums[slow]
     * 142 题中快指针走两步 fast = fast.next.next ==> 本题 fast = nums[nums[fast]]
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]]; //因为题目规定 nums[].length = n + 1,且nums[] 值范围 [1,n],所以nums[nums[n]] 一定不会下标越界
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public static void main(String[] args) {
        Solution287 s = new Solution287();
        int[] arr = {1, 3, 4, 2, 2};
        System.out.println(s.findDuplicate(arr));
    }

}
