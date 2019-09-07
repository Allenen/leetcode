package zfj.swordToOffer;

public class EggDrop {
	//m个鸡蛋，n次尝试，可以确定让鸡蛋破碎的最高楼层
	//dp[i][j]表示i个鸡蛋尝试j次可以确定的最高楼层
	//假设在x楼扔下一个鸡蛋，他可能会破也可能不破
	//若鸡蛋破了，则需要用剩下的i-1个鸡蛋和j-1次机会在x楼层之下寻找这个最高楼层
	//若鸡蛋没破，则需要用剩下的i个鸡蛋和j-1次机会在x楼层之上寻找这个最高楼层
	public int eggDrop(int cnt, int time)
	{
		int dp[][] = new int[cnt + 1][time + 1];
		for(int i = 0; i <= time; i ++)
			dp[1][time] =  time;
		for(int i = 2; i <= cnt; i ++)
			for(int j = 1; j <= time; j ++)
				dp[i][j] = dp[i - 1][j - 1] + 1 + dp[i][j - 1];
		return dp[cnt][time];
	}
	//(leetcode 887) super egg drop
	public int superEggDrop(int k, int n)
	{
		int[][] dp = new int[k + 1][n + 1];
		for(int i = 0; i <= n; i ++)
			dp[1][i] = i;
		for(int i = 2; i <= k; i ++)
		{
			int x = 1;
			for(int j = 1; j <= n; j ++)
			{
				while(x < j && dp[i][j - x] > dp[i - 1][x])
					x ++;
				dp[i][j] = 1 + dp[i][j - x]; 
			}
		}
		return dp[k][n];
	}
}
