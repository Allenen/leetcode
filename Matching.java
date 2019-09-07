package zfj.swordToOffer;

public class Matching {

	//(leetocde 10)regular expression matching
	//"."匹配任何字符除空格
	//"*"匹配前一个元素的任意次即可以为0次
	//".*"可以匹配任意字符串
	//".*"="....................."
	public boolean isMatch(String s, String p)
	{
		int ns = s.length();
		int np = p.length();
		boolean[][] dp = new boolean[ns + 1][np + 1];
		dp[0][0] = true;
		for(int i = 1; i < np; i ++)
			if(p.charAt(i) == '*')
				dp[0][i + 1] = dp[0][i - 1];
		for(int i = 1; i <= ns; i ++)
		{
			for(int j = 1; j <= np; j ++)
			{
				if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
					dp[i][j] = dp[i - 1][j - 1];
				else if(p.charAt(j - 1) == '*')
				{
					if(p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.')
						dp[i][j] = dp[i][j - 2];
					else
						dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j]; 
						
				}
			}
		}
		return dp[ns][np];
	}
	
	//(leetcode 44) wildCard matching
	//"?"匹配任意字符除空格外
	//"*"匹配任意长度字符串
	public boolean wildMatch(String s, String p)
	{
		int ns = s.length();
		int np = p.length();
		boolean[][] dp = new boolean[ns + 1][np + 1];
		dp[0][0] = true;
		for(int i = 0; i < np; i ++)
			if(p.charAt(i) == '*')
				dp[0][i + 1] = dp[0][i];
		for(int i = 1; i <= ns; i ++)
        {
            for(int j = 1; j <= np; j ++)
            {
            	if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
            		dp[i][j] = dp[i - 1][j - 1];
            	else if(p.charAt(j - 1) == '*')
            		dp[i][j] = dp[i][j - 1] || dp[i - 1][j]; 
            }
        }
		return dp[ns][np];
	}
}
