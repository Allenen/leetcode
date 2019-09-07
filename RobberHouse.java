package zfj.swordToOffer;


public class RobberHouse {
	//(leetcode 198) house robber
	public int rob(int nums[])
	{
		int n = nums.length;
		if(n == 0)
			return 0;
		int[][] dp = new int[n][2];
		dp[0][1] = nums[0];
		for(int i = 1; i < n; i ++)
		{
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
			dp[i][1] = dp[i - 1][0] + nums[i];
		}
		return Math.max(dp[n - 1][0], dp[n - 1][1]);
	}
	
	//(leetcode 213) house robber2 
	public int rob_circle(int nums[])
	{
		int n = nums.length;
        if(n == 0)
            return 0;
        if(n == 1)
            return nums[0];
        int[][] dp = new int[n][2];
        dp[0][1] = nums[0];
        for(int i = 1; i < n - 1; i ++)
        {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        int max = Math.max(dp[n - 2][0], dp[n - 2][1]);
        dp[0][1] = 0;
        for(int i = 1; i < n; i ++)
        {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), max);
	}


	public int[] rob_dfs(TreeNode root)
	{
		if(root == null)
			return new int[2];
		int l[] = rob_dfs(root.left);
		int r[] = rob_dfs(root.right);
		int res[] = new int[2];
		res[0] = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
		res[1] = root.val + l[0] + r[0];
		return res;
	}
	
	//(leetcode 337) house robber3
	public int rob_tree(TreeNode root)
	{
		int res[] = rob_dfs(root);
		return Math.max(res[0], res[1]);
	}
	
	//(leetcode 740) Delete And Earn
	public int deleteAndEarn(int nums[])
	{
		int n = nums.length;
		int[] cnt = new int[10001];
		int max = 0;
		for(int a : nums)
		{
			max = Math.max(max, a);
			cnt[a] ++;
		}
		int pick = 0, delete = 0;
		int res = 0;
		for(int i = 0; i <= max; i ++)
		{
			int prePick = pick, preDelete = delete;
			pick = Math.max(prePick, preDelete + i * cnt[i]);
			delete = Math.max(preDelete, prePick);
			res = Math.max(pick, delete);
		}
		return res;
	}
}
