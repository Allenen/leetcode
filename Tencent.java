package zfj.swordToOffer;

import java.util.Arrays;


//��Ѷ2019������ʽ��
public class Tencent {
	
	//����һ��Ӳ����ֵ��ÿ��Ӳ�Ҷ��������������������ЩӲ����ɣ�1-m��ԪǮ��С��Ҫ����Ӳ��
	//̰���㷨��sumΪ��ǰ����Ӳ����ֵ�ͣ�����Ӳ����ֵ������coins[i] > sum + 1,���ʾ[sum + 1, coins[i] - 1]�޷���ʾ
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
	//�ַ�����ֻҪ����1��0���ھͻ��໥�����������ʣ���ַ�������
	public int xiaoXiaoLe(String s)
	{
		int n = s.length();
		int a[] = new int[2];
		for(int i = 0; i < n; i ++)
			a[s.charAt(i) - '0'] ++;
		int min = Math.min(a[0], a[1]);
		return n - 2 * min;
	}
	
	//��ʿ�´����³�
	//power[i]����λ��i�����޵�����ֵ��cost[i]��������޻�¸�Ľ�����������������ֱ������
	//����ʿ��С��Ҫ���ٽ��
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
