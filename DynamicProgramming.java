package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgramming {

	//(leetcode 368) largest divisible subset
	public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        if(n == 0)
            return list;
        int[] path = new int[n];
        int[] dp = new int[n];
        Arrays.fill(path, -1);
        Arrays.fill(dp, 1);
        int k = n - 1;
        Arrays.sort(nums);
        for(int i = n - 1; i >= 0; i --)
        {
            for(int j = n - 1; j > i; j --)
            {
                if(nums[j] % nums[i] != 0)
                    continue;
                if(dp[i] < dp[j] + 1)
                {
                    dp[i] = dp[j] + 1;
                    path[i] = j;
                }
            }
            k = dp[k] < dp[i] ? i : k;
        }
        while(path[k] != -1)
        {
            list.add(nums[k]);
            k = path[k];
        }
        list.add(nums[k]);
        return list;
    }
	public int min = Integer.MAX_VALUE;
    public int end, stop;
    public void backTracking(int[][] flights, int cost, int cur, int k, int[] visited)
    {
        if(k > stop || cost > min)
            return ;
        if(cur == end)
        {
            min = Math.min(min, cost);
            return ;
        }
        visited[cur] = 1;
        for(int pos[] : flights)
        {
            if(pos[0] == cur && visited[pos[1]] == 0)
                backTracking(flights, cost + pos[2], pos[1], k + 1, visited);
        }
        visited[cur] = 0;
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int nf = flights.length;
        end = dst;
        stop = K;
        backTracking(flights, 0, src, 0, new int[n]);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
	public static void main(String[] args)
	{
		DynamicProgramming DP = new DynamicProgramming();
		int nums[][] = {{0,1,100},{1,2,100},{0,2,500}};
		DP.findCheapestPrice(3, nums, 0, 2, 0);
	}
}
