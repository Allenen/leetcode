package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

	//(leetcode 139) word Break (medium)
	public boolean wordBreak(String s, List<String> wordDict)
	{
		Set<String> dict = new HashSet<>();
		for(String word : wordDict)
			dict.add(word);
		int n = s.length();
		boolean dp[] = new boolean[n + 1];
		dp[0] = true;
		for(int i = 1; i <= n; i ++)
		{
			for(int j = i - 1; j >= 0; j --)
			{
				if(dp[j] && dict.contains(s.substring(j, i)))
				{
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}
	public List<String> rebuild(String s, int end, boolean[] dp, Set<String> dict)
	{
		List<String> list = new ArrayList<>();
		if(!dp[end])
			return list;
		for(int i = end - 1; i > 0; i --)
		{
			String tail = s.substring(i, end);
			if(dp[i] && dict.contains(tail))
			{
				List<String> body = rebuild(s, i, dp, dict);
				for(String b : body)
					list.add(b + " " + tail);
			}
		}
		if(dict.contains(s.substring(0, end)))
			list.add(s.substring(0, end));
		return list;
	}
	//(leetcode 140) word break II (hard)
	public List<String> wordBreakII(String s, List<String> wordDict)
	{
		Set<String> dict = new HashSet<>();
		for(String word : wordDict)
			dict.add(word);
		int n = s.length();
		boolean dp[] = new boolean[n + 1];
		dp[0] = true;
		for(int i = 1; i <= n; i ++)
		{
			for(int j = i - 1; j >= 0; j --)
			{
				if(dp[j] && dict.contains(s.substring(j, i)))
				{
					dp[i] = true;
					break;
				}
			}
		}
		return rebuild(s, n, dp, dict);
	}
}
