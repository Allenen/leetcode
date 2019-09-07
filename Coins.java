package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Coins {
	//腾讯2019春招正式批
	//给定一组硬币面值，每中硬币都有无数个，求可以由这些硬币组成（1-m）元钱最小需要几个硬币
	//贪心算法，sum为当前最少硬币面值和，遍历硬币面值，若是coins[i] > sum + 1,则表示[sum + 1, coins[i] - 1]无法表示
	public int coinCombination(int[] coins, int m)
	{
		Arrays.sort(coins);
		if(coins[0] != 1)
			return -1;
		int n = coins.length;
		int res = 1;
		int sum = 1;
		while(sum < m)
		{
			int i = 0;
			for( ; i < n; i ++)
				if(coins[i] > sum + 1)
					break;
			sum += i == 0 ? 0 : coins[i - 1];
			res ++;
		}
		return res;
	}
	//(leetcode 518). Coin Change2 
	//有面值为coins = {1,2,5,10}的硬币，组合为m有多少种方法
	public int coinCount(int m, int[] coins)
	{
		int n = coins.length;
		int dp[] =  new int[m + 1];
		dp[0] = 1;
		for(int i = 0; i < n; i ++)
		{
			for(int j = coins[i]; j <= m; j ++)
				dp[j] = dp[j] + dp[j - coins[i]];  
		}
		return dp[n];
	}
	//(leetcode322). Coin Change
	//fewest number of coins to make up the amount
	public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int dp[] = new int[amount + 1];
        dp[0] = 0;
        Arrays.sort(coins);
        for(int i = 1; i < amount + 1; i ++)
        {
        	dp[i] = Integer.MAX_VALUE;
            for(int j = 0; j < n && coins[j] <= i; j ++)
            {
                if(dp[i - coins[j]] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
            System.out.println(dp[i]);
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
	//(leetcode322). Coin Change 
	public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int dp[] = new int[amount + 1];
        Arrays.sort(coins);
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < n; i ++)
        {
            for(int j = coins[i]; j <= amount; j ++)
            {
                if(dp[j - coins[i]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
	//(leetcode 656) coin path
	//可以从coins[i]位置跳跃[i + 1, i + B]步，经过的位置要付钱coins[i]，求最少付多少钱
	public List<Integer> coinPath(int[] coins, int B)
	{
		int n = coins.length;
		List<Integer> list = new ArrayList<>();
		if(coins[n - 1] == -1)
			return list;
		int dp[] = new int[n];
		int pos[] = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		Arrays.fill(pos, -1);
		
		dp[n - 1] = coins[n - 1];
		for(int i = n - 2; i >= 0; i --)
		{
			if(coins[i] == -1) continue;
			for(int j = i + 1; j <= Math.min(i + B, n - 1); j ++)
			{
				if(dp[j] == Integer.MAX_VALUE) continue;
				if(dp[i] > coins[i] + dp[j])
				{
					dp[i] = coins[i] + dp[j];
					pos[i] = j; 
				}
			}
		}
		if(dp[0] == Integer.MAX_VALUE)
			return list;
		for(int cur = 0; cur != -1; cur = pos[cur])
		{
			list.add(cur + 1);
		}
		return list;
	}
	//(leetcode 656) coin path
	public List<Integer> coinPath2(int[] coins, int B)
	{
		int n = coins.length;
		List<Integer> list = new ArrayList<>();
		if(coins[n - 1] == -1)
			return list;
		int dp[] = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		int pos[] = new int[n];
		dp[n - 1] = coins[n - 1];
		int len[] = new int[n];
		for(int i = 0; i < n; i ++)
		{
			if(coins[i] == -1) continue;
			for(int j = Math.max(0, i - B); j < i; j ++)
			{
				if(dp[j] == Integer.MAX_VALUE) continue;
				int t = coins[i] + dp[j];
				if(t < dp[i] || (t == dp[i] && len[i] < len[j] + 1))
				{
					dp[i] = t;
					pos[i] = j;
					len[i] = len[j] + 1;  
				}
			}
		}
		if(dp[n - 1] == Integer.MAX_VALUE)
			return list;
		for(int cur = n - 1; cur != -1; cur = pos[cur])
			list.add(cur + 1);
		Collections.reverse(list);
		return list;
	}
	//(leetcode 983). Minimum Cost For Tickets
	public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dcs = new int [366];
        for (int i = 0; i < n; i++) {
            dcs[days[i]] = 1;
        }

        for (int i = 1; i < 366; i++) {
            if (0 == dcs[i]) {
                dcs[i] = dcs[i - 1];
            } else {
                dcs[i] = Math.min(costs[0] + dcs[i - 1], 
                                  Math.min(costs[1] + dcs[Math.max(0, i - 7)],
                                           costs[2] + dcs[Math.max(0, i - 30)]));
            }
        }
        
        return dcs[days[n - 1]];
    }
	public static void main(String[] args)
	{

		Coins t1 = new Coins();
		int a[] = {1,2,3,4,5};
		int b[] = {7,2,15};
		t1.coinPath(a, 2);
		System.out.println();
	}
}
