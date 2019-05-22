package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-05-22 16:22
 * Description:
 * 121. 买卖股票的最佳时机
 */
public class Solution121 {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            minPrice = prices[i] < minPrice ? prices[i] : minPrice;
            maxProfit = prices[i] - minPrice > maxProfit ? prices[i] - minPrice : maxProfit;
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 6,4,3,1};
        int max = maxProfit(prices);
        System.out.println(max);
    }

}
