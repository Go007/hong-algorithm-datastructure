package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-08-26 16:49
 * Description:
 * 122. 买卖股票的最佳时机 II
 */
public class Solution122 {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }

        int maxProfit = 0;
        for (int i=1;i<prices.length;i++){
            if (prices[i] > prices[i-1]){
                maxProfit += prices[i] - prices[i-1];
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        int maxProfit = maxProfit(prices);
        System.out.println(maxProfit);
    }

}
