package zfj.swordToOffer;

public class JumpGame {
	//(leetcode 55) Jump Game
	public boolean canJump(int[] nums)
	{
		int n = nums.length;
		int far = 0; // 最远能到的位置
		for(int i = 0; i < n; i ++)
		{
			if(far < i)
				return false;
			far = Math.max(far, i + nums[i]);
		}
		return true;
	}
	//(leetcode 45)
	public int jump(int[] nums) {
        int n = nums.length;
        int res = 0;
        int preFar = 0; // 上次最远能到的位置
        int curFar = 0;  // 本次最远能到的位置
        for(int i = 0; i < n - 1; i ++)
        {
        	if(i > curFar)
        		return -1;
        	if(i > preFar)
        	{
        		res ++;
        		preFar = curFar;
        	}
        	curFar = Math.max(curFar, i + nums[i]);
        }
        return res;
    }

	public static void main(String[] args)
	{
		JumpGame jp = new JumpGame();
		int a[] = {2,3,1,1,4};

	}
}
