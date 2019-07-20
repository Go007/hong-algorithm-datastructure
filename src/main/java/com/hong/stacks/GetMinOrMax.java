package com.hong.stacks;

import java.util.Stack;

/**
 * @author wanghong
 * @date 2019/07/20 17:45
 **/
public class GetMinOrMax {
    /**
     * java实现栈-输出最大值，最小值，时间复杂度O(1)
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的getMin函数。
     * 在该栈中，调用getMin、push及pop的时间复杂度都是O(1).
     *
     * 最小值思路：用一个辅助栈stack2记住每次入栈stack1的当前最小值:在stack1入栈时，往stack2中加入当前最小值；
     * stack1元素出栈时，stack2也出栈一个元素。最小值从stack2中获取及栈顶元素。
     */
    Stack<Integer> s0 = new Stack<>();
    Stack<Integer> s1 = new Stack<>();

}
