package zfj.swordToOffer;

import java.util.Arrays;
import java.util.Stack;

public class DecodeString {
	public boolean isValid(char a, char b)
    {
        int x = (a - '0') * 10 + b - '0';
        return x >= 10 && x <= 26;
    }
	//(leetcode 91) Decode ways (medium)
	public int numDecodings(String s)
	{
		int n = s.length();
		char c[] = s.toCharArray();
		if(n == 0 || c[0] == '0')
			return 0;
		if(n == 1)
			return 1;
		int dp[] = new int[n];
		dp[0] = 1;
		if(c[1] == '0')
		{
			dp[0] = 0;
			dp[1] = isValid(c[0], c[1]) ? 1 : 0;
		}
		else
			dp[1] = isValid(c[0], c[1]) ? 2 : 1;
		for(int i = 2; i < n; i ++)
		{
			if(c[i] == '0')
			{
				dp[i - 1] = dp[i - 2];
				if(!isValid(c[i - 1], c[i]))
					return 0;
				dp[i] = dp[i - 1];
			}
			else
				dp[i] = isValid(c[i - 1], c[i]) ? dp[i - 2] + dp[i - 1] : dp[i - 1]; 
		}
		return dp[n - 1];
	}
	public boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }
    public String decodeString1(String s) {
        int n = s.length();
        int digit = 0;
        String res = "";
        for(int i = 0; i < n; i ++)
        {
            char c = s.charAt(i);
            if(isDigit(c))
                digit = digit * 10 + c - '0';
            else if(c == '[')
            {
                int np = 1;
                int j = i + 1;
                while(np != 0)
                {
                    if(s.charAt(j) == '[') np ++;
                    else if(s.charAt(j) == ']') np --;
                    j ++;
                }
                
                
                String t1 = decodeString1(s.substring(i + 1, j - 1));
                i = j - 1;
                for(j = 0; j < digit; j ++)
                    res += t1;
                digit = 0; 
            }
            else
                res += s.charAt(i);
        }
        return res;
    }
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        int n = s.length();
        for(int i = 0; i < n; i ++)
        {
            char c = s.charAt(i);
            if(c == ']')
            {
                String t = "";
                while(!stack.isEmpty())
                {
                    String top = stack.pop();
                    if(top.equals("["))
                        break;
                    t = top + t;
                }
                int cnt = 0;
                int e = 1;
                while(!stack.isEmpty())
                {
                    String top = stack.peek();
                    if(isDigit(top.charAt(0)))
                    {
                        cnt += Integer.valueOf(top) * e;
                        e *= 10;
                    }
                    else
                        break;
                    stack.pop();
                }
                String tmp = "";
                for(int j = 0; j < cnt; j ++)
                    tmp += t;
                stack.push(tmp);
            }
            else
                stack.push(String.valueOf(c));
        }
        String res = "";
        while(!stack.isEmpty())
            res += stack.pop();
        return res;
    }
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i < n; i ++)
            sum += nums[i];
        if(sum % 2 == 1)
            return false;
        Arrays.sort(nums);
        int target = sum / 2, left = 0;
        sum = 0;
        for(int i = 0; i < n; i ++)
        {
            sum += nums[i];
            while(sum > target)
            {
                sum -= nums[left];
                left ++;
            }
            if(sum == target) 
                return true;
        }
        return false;
    }
    public static void main(String[] args)
	{
    	DecodeString ds = new DecodeString();
		char c[] = {'1','0','0', '1'};
		int[] a = {19,33,38,60,81,49,13,61,50,73,60,82,73,29,65,62,53,29,53,86,16,83,52,67,41,53,18,48,32,35,51,72,22,22,76,97,68,88,64,19,76,66,45,29,95,24,95,29,95,76,65,35,24,85,95,87,64,97,75,88,88,65,43,79,6,5,70,51,73,87,76,68,56,57,69,77,22,27,29,12,55,58,18,30,66,53,53,81,94,76,28,41,77,17,60,32,62,62,88,61};
		System.out.println(ds.canPartition(a));
	}
}
