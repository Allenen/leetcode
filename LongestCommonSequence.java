package zfj.swordToOffer;

public class LongestCommonSequence {


	//最长公共子串 substring
	public String longestCommonSubstring(String s1, String s2)
	{
		int n1 = s1.length();
		int n2 = s2.length();
		int[][] dp = new int[n1 + 1][n2 + 1];
		int max = 0;
		int end = 0;
		for(int i = 1; i <= n1; i ++)
		{
			for(int j = 1; j <= n2; j ++)
			{
				if(s1.charAt(i - 1) == s2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = 0;
				if(max < dp[i][j])
				{
					max = dp[i][j];
					end = i;
				}
			}
		}
		return s1.substring(end - max, end);	
	}
	
	//最长公共子序列 subsequence
	public String longestCommonSubsequence(String s1, String s2)
	{
		int n1 = s1.length();
		int n2 = s2.length();
		int[][] dp = new int[n1 + 1][n2 + 1];
		for(int i = 1; i <= n1; i ++)
		{
			for(int j = 1; j <= n2; j ++)
			{
				if(s1.charAt(i - 1) == s2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); 
			}
		}
		String LCS = "";
		int i = n1, j = n2;
		while(i > 0 && j > 0)
		{
			if(s1.charAt(i - 1) == s2.charAt(j - 1))
			{
				LCS += s1.charAt(i - 1);
				i --;
				j --;
			}
			else
			{
				if(dp[i][j - 1] > dp[i - 1][j])
					j --;
				else
					i --;
			}	
		}
		return new StringBuilder(LCS).reverse().toString();
	}
	//(leetcode 583)
	//Given two words word1 and word2
	//find the minimum number of steps required to make word1 and word2 the same
	//where in each step you can delete one character in either string.
	public int DeleteOperationforTwoStrings(String word1, String word2)
	{
		int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for(int i = 1; i <= n1; i ++)
        {
            for(int j = 1; j <= n2; j ++)
            {
                dp[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? 
                    dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return n1 + n2 - 2 * dp[n1][n2];
	}
	
	
	//(leetcode 712)
	//Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
	public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];
        for(int i = 1; i <= n1; i ++)
        {
            for(int j = 1; j <= n2; j ++)
            {
                dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ?
                    dp[i - 1][j - 1] + s1.charAt(i - 1) : 
                    Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int sum = 0;
        for(int i = 0; i < n1; i ++)
            sum += s1.charAt(i);
        for(int i = 0; i < n2; i ++)
            sum += s2.charAt(i);
        return sum - 2 * dp[n1][n2];
    }
	
	//(leetcode 1092) shortest common supersequence
	public String shortestCommonSupersequence(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];
        for(int i = 1; i <= n1; i ++)
        {
            for(int j = 1; j <= n2; j ++)
            {
                if(str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        String LCS = "";
        int i = n1, j = n2;
        while(i != 0 && j != 0)
        {
            if(str1.charAt(i - 1) == str2.charAt(j - 1))
            {
                LCS += str1.charAt(i - 1);
                i --;
                j --;
            }
            else if(dp[i - 1][j] < dp[i][j - 1])
                j --;
            else if(dp[i - 1][j] >= dp[i][j - 1])
                i --;
        }
        LCS = new StringBuilder(LCS).reverse().toString();
        int n = LCS.length();
        String[] ss = new String[n + 1];
        int k = 0, left = 0;
        for(i = 0; i < n1; i ++)
        {
            if(k < n && LCS.charAt(k) == str1.charAt(i))
            {
                ss[k ++] = str1.substring(left, i);
                left = i + 1;
            }
        }
        ss[k] = str1.substring(left, n1);
        String res = "";
        k = 0;
        left = 0;
        for(i = 0; i < n2; i ++)
        {
            if(k < n && LCS.charAt(k) == str2.charAt(i))
            {
                res += ss[k ++] + str2.substring(left, i + 1);
                left = i + 1;
            }
        }
        return res;
    }
	
	public static void main(String args[])
	{
		LongestCommonSequence LCS = new LongestCommonSequence();
		String str1 = "sseexaybacm", str2 = "erexaysdc";
		String res = LCS.longestCommonSubsequence(str1, str2);
		System.out.println(res);
	}
}
