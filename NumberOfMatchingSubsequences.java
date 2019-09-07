package zfj.swordToOffer;

import java.util.Arrays;

public class NumberOfMatchingSubsequences {
	//(leetcode 392). Is Subsequence
	public boolean isSubsequence(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        int k = 0;
        for(int i = 0; i < nt; i ++)
        {
            if(k < ns && s.charAt(k) == t.charAt(i))
                k ++;
        }
        return k == ns;
    }
	//(leetcode 792). Number of Matching Subsequences
	public int numMatchingSubseq(String S, String[] words) {
        int nw = words.length;
        int ns = S.length();
        int dp[][] = new int[ns + 1][27];
        for(int i = 0; i <= ns; i ++)
            Arrays.fill(dp[i], -1);
        for(int i = ns - 1; i >= 0; i --)
            dp[0][S.charAt(i) - 'a'] = i + 1;
        for(int i = ns - 2; i >= 0; i --)
        {
            for(int j = 0; j < 27; j ++)
                dp[i + 1][j] = dp[i + 2][j];
            dp[i + 1][S.charAt(i + 1) - 'a'] = i + 2;
        }
        int res = 0;
        for(String t : words)
        {
            int nt = t.length(); 
            int pre = 0;
            boolean b = true;
            for(int j = 0; j < nt; j ++)
            {
                int next = dp[pre][t.charAt(j) - 'a'];
                if(next != -1)
                    pre = next;
                else
                    b = false;
            }
            res += b ? 1 : 0;
        }
        return res;
    }
}
