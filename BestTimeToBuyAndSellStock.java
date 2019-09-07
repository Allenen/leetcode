package zfj.swordToOffer;

import java.util.*;


public class BestTimeToBuyAndSellStock {
	//某日买入股票，某日卖出，只交易一次，求能赚的最大利润（Easy） 
	public int maxProfit(int[] prices)
	{
		int n = prices.length;
		int min = Integer.MAX_VALUE;
		int max = 0;
		for(int i = 0; i < n; i ++)
		{
			min = Math.min(min, prices[i]);
			max = Math.max(max, prices[i]- min);
		}
		return max;
	}
	//某日买入股票，某日卖出，可以交易任意次，求能赚的最大利润（Easy）
	public int maxProfit2(int[] prices)
	{
		int n = prices.length;
		int res = 0;
		for(int i = 1; i < n; i ++)
			if(prices[i] > prices[i - 1])
				res += prices[i] - prices[i - 1]; 
		return res;
	}
	//某日买入股票，某日卖出，最多可以交易两次，求能赚的最大利润（Hard）
	public int maxProfit3(int[] prices) {
        int n = prices.length;
        int first[] = new int[n];
        int second[] = new int[n];
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0; i < n; i ++)
        {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            first[i] = maxProfit;
        }
        int maxPrice = Integer.MIN_VALUE;
        maxProfit = 0;
        for(int i = n - 1; i >= 0; i --)
        {
            maxPrice = Math.max(maxPrice, prices[i]);
            maxProfit = Math.max(maxProfit, maxPrice - prices[i]);
            second[i] = maxProfit;
        }
        int res = 0;
        for(int i = 0; i < n; i ++)
            res = Math.max(res, first[i] + second[i]);
        return res;
    }
	//(leetcode 188)  可以交易k次（Hard）
	public int maxProfit4(int k, int[] prices) {
		int n = prices.length;
        if(n == 0)
            return 0;
        if(2 * k >= n)
            return maxProfit2(prices);
        int local[][] = new int[k + 1][n];
        int global[][] = new int[k + 1][n];
        for(int i = 1; i < k + 1; i ++)
        {
            for(int j = 1; j < n; j ++)
            {
                int d = prices[j] - prices[j - 1];
                local[i][j] = Math.max(local[i][j - 1] + d, global[i - 1][j - 1] + Math.max(d, 0));
                global[i][j] = Math.max(local[i][j], global[i][j - 1]);
            }
        }
        return global[k][n - 1];
    }
	public static void main(String[] args)
	{
		BestTimeToBuyAndSellStock bt = new BestTimeToBuyAndSellStock();
		int a[] = {3,2,6,5,0,3};
		bt.maxProfit4(2, a);
		String xString = "acv";
		System.out.println(xString.substring(0,3));
		Stack<String> s = new Stack<>();
		s.peek();
	}
}
