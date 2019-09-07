package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zfj._abstract.A;

//最大字数组“和”或“乘积”
//如果数组都是positive，则可以使用slide Windows
//如果数组存在负数，考虑使用动态规划（局部最大最小值）
public class MaximumSubarry {

	//动态规划求最大子数组和
	public int maxAddSubArray(int[] nums)
	{
		int n = nums.length;
		if(n == 0)
			return 0;
		int local = nums[0];
		int global = nums[0];
		for(int i = 1; i < n; i ++)
		{
			local = Math.max(local + nums[i], nums[i]);
			global = Math.max(local, global);
		}
		return global;
	}
//	public List<Integer> MaxAddSubArray(int[] nums)
//	{
//		List<Integer> list = new ArrayList<>();
//		int n = nums.length;
//		if(n == 0)
//			return list;
//		int local = nums[0];
//		int global = nums[0];
//		int start = 0, len = 0;
//		for(int i = 1; i < n; i ++)
//		{
//			local += nums[i];
//			if(local < nums[i])
//			{
//				start = i;
//				local = nums[i];
//			}
//			if(global < local)
//			{
//				global = local;
//			}
//			local = Math.max(local + nums[i], nums[i]);
//			global = Math.max(local, global);
//		}
//		return list;
//	}
	
	//前缀法求最大子数组和
	public int maxAddSubArray1(int[] nums)
	{
		int n = nums.length;
		int prefix[] = new int[n + 1];
		for(int i = 1; i <= n; i ++)
			prefix[i] = prefix[i - 1] + nums[i - 1];
		int res = prefix[1];
		int min = 0;
		for(int i = 1; i <= n; i ++)
		{
			res = Math.max(res, prefix[i] - min);
			min = Math.min(min, prefix[i]);
		}
		return res;
	}
	
	//(leetcode 152) Maximum product subarray
	//动态规划求最大子数组乘积
	public int maxProductSubArray(int[] nums)
	{
		int n = nums.length;
		if(n == 0)
			return 0;
		int localMin = nums[0];
		int localMax = nums[0];
		int globalMax = nums[0];
		for(int i = 1; i < n; i ++)
		{
			int preLocalMin = localMin;
			localMin = Math.min(localMax * nums[i], Math.min(localMin * nums[i], nums[i]));
			localMax = Math.max(localMax * nums[i], Math.max(preLocalMin * nums[i], nums[i]));
			globalMax = Math.max(globalMax, localMax);
		}
		return globalMax;
	}
	//(leetcode 918) maximum sum circle subarray
	public int maxSubarrayCircular(int[] a)
	{
		int n = a.length;
		int localMax = a[0];
		int globalMax = a[0];
		int localMin = a[0];
		int globalMin = a[0];
		int sum = a[0];
		for(int i = 1; i < n; i ++)
		{
			localMax = Math.max(localMax + a[i], a[i]);
			globalMax = Math.max(localMax, globalMax);
			localMin = Math.min(localMin + a[i], a[i]);
			globalMin = Math.min(globalMin, localMin);
		}
		return globalMin == sum ? globalMax : Math.max(globalMax, sum - globalMin);
	}
	
	//(leetcode 713) subarray product less than k
	//0 <= k < 10^6
	//0 < nums[i] < 1000
	public int numSubarrayProductLessThanK(int[] nums, int k)
	{
		if(k <= 1) return 0;
		int n = nums.length;
		long cnt = 0;
		int left = 0;
		int product = 1;
		for(int i = 0; i < n; i ++)
		{
			product *= nums[i];
			if(product >= k)
			{
				long len = i - left;
				while(product >= k)
					product /= nums[left ++];
				long repeat = i - left;
				cnt += len * (len + 1) / 2 - repeat * (repeat + 1) / 2;
			}
		}
		long len = n - left;
		cnt += len * (len + 1) / 2;
		return (int)cnt;	
	}
	//(leetcode 713) subarray product less than k
	//5,2,6相当于从6开始，有(6),(6,2),(6,2,5)
	//slide window
	public int numSubarrayProductLessThanK1(int nums[], int k)
	{
		int n = nums.length;
		int res = 0 ;
		int left = 0;
		int product = 1;
		for(int i = 0; i < n; i ++)
		{
			product *= nums[i];
			while(product >= k)
				product /= nums[left ++];
			res += i - left + 1;
		}
		return res;
	}
	
	//(leetcode 325) maximum size subarray sum equals k
	//HashMap
	public int maxSubArrayLen(int nums[], int k)
	{
		Map<Integer, Integer> mp = new HashMap<>();
		mp.put(0,  0);
		int max = 0;
		int sum = 0;
		for(int i = 0; i < nums.length; i ++)
		{
			sum += nums[i];
			if(!mp.containsKey(sum))
				mp.put(sum, i);
			if(mp.containsKey(sum - k))
				max = Math.max(max, i + 1 - mp.get(sum - k));
		}
		return max;
	}
	//(leetcode 525) contiguous array
	//同(leetcode 325) HashMap
	//将数组中的0置换为-1，然后就是325的特殊情况k=0
	public int findMaxLength(int nums[])
	{
		Map<Integer, Integer> mp = new HashMap<>();
		mp.put(0, 0);
		int sum = 0, max = 0;
		for(int i = 0; i < nums.length; i ++)
		{
			sum += nums[i] == 0 ? -1 : 1;
			if(!mp.containsKey(sum))
				mp.put(sum, i + 1);
			else
				max = Math.max(max, mp.get(sum));
		}
		return max;
	}
	
	//(leetcode 209) minimum size subarray sum
	//slide window
	public int minSubArrayLen(int s, int[] nums) {
        int left = 0, sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i ++)
        {
            sum += nums[i];
            int l = left;
            while(sum >= s)
                sum -= nums[left ++];
            if(l != left)
                min = Math.min(min, i - (left - 1) + 1);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
	
}
