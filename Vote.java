package zfj.swordToOffer;

public class Vote {
	public int majorityElement(int[] nums) 
    {
        int n = nums.length;
        int cnt = 1;
        int candidate = -1;
        for(int i = 1; i < n; i ++)
        {
        	if(cnt == 0)
            {
                candidate = nums[i];
                cnt ++;
            }
            else if(nums[i] == candidate)
                cnt ++;
            else
                cnt --;
        }
        cnt = 0;
        for(int i = 0; i < n; i ++)
        	if(nums[i] == candidate)
        		cnt ++;
        return cnt >= n / 2 ? candidate : -1;
    }
	//(leetcode 747). Largest Number At Least Twice of Others
	public int dominantIndex(int[] nums) {
        int n = nums.length;
        int max1 = nums[0];
        int max2 = 0;
        int indexOfMax = 0;
        for(int i = 1; i < n; i ++)
        {
            if(max1 < nums[i])
            {
                int tmp = max1;
                max1 = nums[i];
                max2 = tmp;
                indexOfMax = i;
            }
            else if(max1 == nums[i])
                max2 = max1;
            else
                max2 = Math.max(max2, nums[i]);
        }
        return max1 >= max2 * 2 ? indexOfMax : -1;
    }
	//(leetcode 414). Third Maximum Number
	public int thirdMax(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        if(n == 1)
            return nums[0];
        if(n == 2)
            return Math.max(nums[0], nums[1]);
        long max1 = nums[0];
        long max2 = (long)Integer.MIN_VALUE - 1;
        long max3 = max2 - 1;
        for(int i = 1; i < n; i ++)
        {
            if(max1 < nums[i])
            {
                long tmp1 = max1;
                long tmp2 = max2;
                max1 = nums[i];
                max2 = tmp1;
                max3 = tmp2;
            }
            else if(max1 > nums[i])
            {
                if(max2 < nums[i])
                {
                    long tmp = max2;
                    max2 = nums[i];
                    max3 = tmp;
                }
                else if(max2 > nums[i])
                    max3 = Math.max(max3, nums[i]);
            }
        }
        return max3 < Integer.MIN_VALUE ? (int)max1 : (int)max3;
    }
	public static void main(String[] args)
	{
		Vote v = new Vote();
		int nums[] = {2, 2, 2, 1};
		int most = v.thirdMax(nums);
		System.out.println(most);
	}
}
