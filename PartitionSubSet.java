package zfj.swordToOffer;

import java.util.Arrays;

public class PartitionSubSet {
	//(leetcode 416) 
	//Given a non-empty array containing only positive integers
	//find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
	public boolean canPartition(int[] nums)
	{
		int sum = 0;
        for(int num : nums)
            sum += num;
        if(sum % 2 == 1)
            return false;
        int target = sum / 2;
        boolean dp[] = new boolean[target + 1];
        dp[0] = true;
        for(int num : nums)
        {
        	for(int i = target; i >= num; i --)
        		dp[i] = dp[i] || dp[i - num];  
        }
        return dp[target];
	}
	
	
    public boolean dfs(int[] nums, int k, int sum, int start, int target, int[] visited)
    {
        if(k == 1) return true;
        if(sum > target) return false;
        if(sum == target) return dfs(nums, k - 1, 0, 0, target, visited);
        int n = nums.length;
        for(int i = start; i < n; i ++)
        {
            if(visited[i] == 1)
                continue;
            visited[i] = 1;
            if(dfs(nums, k, sum + nums[i], i + 1, target, visited))
                return true;
            visited[i] = 0;
        }
        return false;
    }
    //(leetcode 698)
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num : nums) sum += num;
        if(sum % k != 0)
            return false;
        Arrays.sort(nums);
        int [] visited = new int[nums.length];
        int target = sum / k;
        return dfs(nums, k, 0, 0, target, visited);
    }
}
