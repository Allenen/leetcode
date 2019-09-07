package zfj.swordToOffer;

public class LongestIncreasingSubsequenece {
	
	//(leetcode 300) Longest Increasing Subsequence
	public int lengthOfLIS(int[] nums)
	{
		int n = nums.length;
        if(n == 0)
            return 0;
        int[] dp = new int[n];
        int max = 0;
        for(int i = 0; i < n; i ++)
        {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j --)
            {
                if(nums[i] > nums[j])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            max = Math.max(dp[i], max);
        }
        return max;
	}

	//(leetcode 673) Number of Longest Increasing Subsequence
	public int findNumberIfLIS(int nums[])
	{
		int n = nums.length;
		if(n == 0)
			return 0;
		int maxLen = 0, cnt = 0;
		int[][] dp = new int[n][2];
		for(int i = 0; i < n; i ++)
		{
			dp[i][0] = dp[i][1] = 1;
			for(int j = i - 1; j >= 0; j --)
			{
				if(nums[i] > nums[j])
				{
					if(dp[i][0] > dp[j][0] + 1)
					{
						dp[i][0] = dp[j][0] + 1;
						dp[i][1] = dp[j][1]; 
					}
					else if(dp[i][0] == dp[j][0] + 1)
						dp[i][1] += dp[j][1];
				}
			}
			if(maxLen < dp[i][0])
			{
				maxLen = dp[i][0];
				cnt = dp[i][1];
			}
			else if(maxLen == dp[i][0])
				cnt += dp[i][1];
		}
		return cnt;
	}
	
	//(leetcode 334) increasing Triplet subsequence
	public boolean increasingTriplet(int nums[])
	{
		int n = nums.length;
		if(n < 3)
			return false;
		int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE;
		for(int a : nums)
		{
			if(m1 >= a)
				m1 = a;
			else if(m2 >= a)
				m2 = a;
			else
				return true;
		}
		return false;
	}
	
}
