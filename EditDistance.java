package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.List;

public class EditDistance {
	//(leetcode 72)Edit distance
	//Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
	//三个操作：增删改
	//for example :(horse, ros)
	//     # r o s
	//   # 0 1 2 3
	//   h 1 1 2 3	
	//   o 2 2 1 2
	//   r 3 2 2 2
	//   s 4 3 3 2
	//   e 5 4 4 3
	public int minDistance(String s1, String s2)
	{
		int n1 = s1.length();
        int n2 = s2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];
        for(int i = 0; i <= n1; i ++)
            dp[i][0] = i;
        for(int i = 0; i <= n2; i ++)
            dp[0][i] = i;
        for(int i = 1; i <= n1; i ++)
        {
            for(int j = 1; j <= n2; j ++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
            }
        }
        return dp[n1][n2];
	}
	//(leetcode 161) one edit distance
	public boolean isOneEditDistance(String s, String t)
	{
		int ns = s.length();
		int nt = t.length();
		int d = Math.abs(ns - nt);
		if(d > 1 || s.equals(t))
			return false;
		for(int i = 0; i < Math.min(ns, nt); i ++)
		{
			if(s.charAt(i) != t.charAt(i))
			{
				if(ns == nt)
					return s.substring(i + 1).equals(t.substring(i + 1));
				else if(ns > nt)
					return s.substring(i + 1).equals(t.substring(i));
				else 
					return s.substring(i).equals(t.substring(i + 1));
			}
		}
		return true;
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
	
	public class TrieNode
	{
		TrieNode children[];
		boolean isWord;
		String word;
		public TrieNode() {
			// TODO Auto-generated constructor stub
			children = new TrieNode[26];
			isWord = true;
			word = "";
		}
	}
	public void insert(String word, TrieNode t)
	{
		for(int i = 0; i < word.length(); i ++)
		{
			int index = word.charAt(i) - 'a';
			if(t.children[index] == null)
				t.children[index] = new TrieNode();
			t = t.children[index];
		}
		t.isWord = true;
		t.word = word;
	}
	//(lintCode 623) K Edit Distance
	public List<String> kDistance(String[] words, String target, int k)
	{
		List<String> res = new ArrayList<>();
		int nw = words.length;
		TrieNode root = new TrieNode();
		for(String word : words)
			insert(word, root);
		int dp[] = new int[target.length() + 1];
		for(int i = 0; i < target.length() + 1; i ++)
			dp[i] = i;
		find(target, root, k, dp, res);
		return res;
	}
	
	public void find(String target, TrieNode t, int k, int dp[], List<String> res)
	{
		int nt = target.length();
		if(t.isWord && dp[nt] <= k)
			res.add(t.word);
		
		int cur_dp[] = new int[nt + 1];
		for(int i = 0; i < 26; i ++)
		{
			if(t.children[i] == null)
				continue;
			cur_dp[0] = dp[0] + 1;
			for(int j = 1; j <= nt; j ++)
			{
				if(target.charAt(j - 1) - 'a' == i)
					cur_dp[j] = dp[j - 1];
				else
					cur_dp[j] = Math.min(Math.min(dp[j], dp[j - 1]) , cur_dp[j - 1]) + 1;
			}
			find(target, t.children[i], k, cur_dp, res);
		}
	}
	
	public static void main(String[] args)
	{
		EditDistance edx = new EditDistance();
		System.out.println(edx.minDistance("horse", "ros"));
	}
}
