package zfj.swordToOffer;

public class EggDrop {
	//m��������n�γ��ԣ�����ȷ���ü�����������¥��
	//dp[i][j]��ʾi����������j�ο���ȷ�������¥��
	//������x¥����һ�������������ܻ���Ҳ���ܲ���
	//���������ˣ�����Ҫ��ʣ�µ�i-1��������j-1�λ�����x¥��֮��Ѱ��������¥��
	//������û�ƣ�����Ҫ��ʣ�µ�i��������j-1�λ�����x¥��֮��Ѱ��������¥��
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
