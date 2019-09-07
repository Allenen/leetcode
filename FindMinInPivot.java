package zfj.swordToOffer;

public class FindMinInPivot {
	
	public int binarySearch(int[] a, int start, int end, int target)
    {
        int l = start, r = end - 1;
        while(l <= r)
        {
            int m = (l + r) / 2;
            if(a[m] > target)
                r = m - 1;
            else if(a[m] < target)
                l = m + 1;
            else
                return m;
        }
        return -1;
    }
	
	//leetcode(153). Find Minimum in Rotated Sorted Array
	public int findMinNum(int nums[])
	{
		int n = nums.length;
        int l = 0, r = n - 1;
        while(l < r)
        {
            int m = l + (r - l) / 2;
            if(nums[m] < nums[0])
                r = m;
            else
                l = m + 1;
        }
        return nums[l] > nums[0] ? nums[0] : nums[l]; 
	}
	
	//leetocde(154). Find Minimum in Rotated Sorted Array II
	public int findMin(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return -1;
        if(nums[0] == nums[n - 1])
        {
            for(int i = 1; i < n; i ++)
                if(nums[i] < nums[i - 1])
                    return nums[i];
            return nums[0];
        }
        return findMinNum(nums);
    }
	
	//leetcode(33). Search in Rotated Sorted Array
	public int search(int[] nums, int target) {
		int n = nums.length;
        if(n == 0)
            return -1;
        int l = 0, r = n - 1;
        while(l < r)
        {
            int m = l + (r - l) / 2;
            if(nums[m] < nums[0])
                r = m;
            else
                l = m + 1;
        }
        int pivot = nums[l] >= nums[0] ? 0 : l;
        if(pivot == 0)
            return binarySearch(nums, 0, n, target);
        return target >= nums[0] ? binarySearch(nums, 0, pivot, target) 
            : binarySearch(nums, pivot, n, target);
	}
	
	public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        int res = 1, cnt = 1;
        int[][] dp = new int[n][2];
        dp[0][0] = dp[0][1] = 1;
        for(int i = 1; i < n; i ++)
        {
        	dp[i][0] = dp[i][1] = 1;
            for(int j = i - 1; j >= 0; j --)
            {
                if(nums[i] > nums[j])
                {
                    if(dp[i][0] < dp[j][0] + 1)
                    {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                    }
                    else if(dp[i][0] == dp[j][0] + 1)
                        dp[i][1] += dp[j][1];
                }
            }
            if(res < dp[i][0])
            {
                res = dp[i][0];
                cnt = dp[i][1];
            }
            else if(res == dp[i][0])
                cnt += dp[i][1];
        }
        return cnt;
    }
	
	public static void main(String args[])
	{
		FindMinInPivot fmp = new FindMinInPivot();
		int a[] = {2,2,2,2,2};
		fmp.findNumberOfLIS(a);
	}
	
}
