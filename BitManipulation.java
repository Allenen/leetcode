package zfj.swordToOffer;

public class BitManipulation {
	//(leetcode 136). Single Number I
	public int singleNum1_1(int[] nums)
	{
		int res = 0;
		for(int num : nums)
			res ^= num;
		return res;
	}
	public int singleNum1_2(int[] nums)
	{
		int res = 0;
		for(int i = 0; i < 32; i ++)
		{
			int sum = 0;
			for(int num : nums)
				sum += (num >> i) & 1;
			res += (sum % 2 == 1) ? (1 << i) : 0;
		}
		return res;
	}
	//(leetcode 260). Single Number II
	public int singleNum2_1(int[] nums)
	{
		int res = 0;
		for(int i = 0; i < 32; i ++)
		{
			int sum = 0;
			for(int num : nums)
				sum += (num >> i) & 1;
			res += (sum % 3 == 1) ? (1 << i) : 0;
		}
		return res;
	}
	//(leetcode 260). Single Number III
	public int[] singleNum3(int nums[])
	{
		int res[] = new int[2];
		int AxorB = 0;
		for(int num : nums)
			AxorB ^= num;
		int dif = AxorB & -AxorB;
		for(int num : nums)
		{
			if((dif & num) == 0)
				res[0] ^= num;
			else
				res[1] ^= num;
		}
		return res;
	}
	
	
	public static void main(String[] args)
	{
		BitManipulation bm = new BitManipulation();
		int nums[] = {1,2,1,2,3,5};
		bm.singleNum3(nums);
	}
}
