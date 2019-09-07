package zfj.swordToOffer;

import java.util.Arrays;


//腾讯2019春招正式批
public class Tencent {
	
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
	//字符串中只要存在1与0相邻就会相互抵消，求最后剩余字符串长度
	public int xiaoXiaoLe(String s)
	{
		int n = s.length();
		int a[] = new int[2];
		for(int i = 0; i < n; i ++)
			a[s.charAt(i) - '0'] ++;
		int min = Math.min(a[0], a[1]);
		return n - 2 * min;
	}
	
	//勇士勇闯地下城
	//power[i]代表位置i处怪兽的武力值，cost[i]代表给怪兽贿赂的金币数让它做你的守卫直至闯关
	//求勇士最小需要多少金币
	public int minCoins(int[] power, int[] cost)
	{
		return dfs(power, cost, 0, 0);
	}
	public int dfs(int[] power, int[] cost, int loc, int guard)
	{
		int n = power.length;
		int res = 0;
		if(loc == n - 1)
			return guard >= power[loc] ? 0 : cost[loc]; 
		if(guard >= power[loc])
			res += Math.min(dfs(power, cost, loc + 1, guard), 
					cost[loc] + dfs(power, cost, loc + 1, guard + power[loc]));
		else
			res += cost[loc] + dfs(power, cost, loc + 1, guard + power[loc]); 
		return res;
	}
	public static void main(String[] args)
	{

		
		Tencent t1 = new Tencent();
		int a[] = {8,5,10};
		int b[] = {1,1,2};
		System.out.println(t1.minCoins(a,b));
	}
}
