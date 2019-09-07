package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import zfj.leetcode.ListNode;


//最长回文
public class LongPalindrommic {
	//(leetcode 409) longest palindrome
	//给定字符串，求能用字符串中的字符所能拼成的最长回文
	public int longestPalindrome(String s)
	{
		int n = s.length();
		int[] ascii = new int[128];
		for(int i = 0; i < n; i ++)
			ascii[s.charAt(i)] ++;
		int res = 0;
		int flag = 0;
		for(int i = 0; i < 128; i ++)
		{
			res += ascii[i] % 2 == 0 ? ascii[i] : (ascii[i] - 1);
			flag = ascii[i] % 2 == 0 ? flag : 1; 
		}
		return res + flag;
	}
	//(leetcode 5)Given a string s, find the longest palindromic substring in s
	public String longestPalindromeSubstring(String s)
	{
		int n = s.length();
		if(n == 0)
			return "";
		boolean dp[][] = new boolean[n][n];
		for(int i = 0; i < n; i ++)
			dp[i][i] = true;
		int l = 0, r = 0;
		for(int i = 1; i < n; i ++)
		{
			for(int j = i - 1; j >= 0; j --)
			{
				dp[j][i] = (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1]));
				if(dp[j][i] && r - l < i - j)
				{
					l = j;
					r = i;
				}
			}
		}
		return s.substring(l, r + 1);
	}
	
	//(leetcode 647)Given a string, your task is to count how many palindromic substrings in this string.
	//The substrings with different start indexes or end indexes are counted as different substrings 
	//even they consist of same characters.
	public int countPalindromicSubstrings(String s)
	{
		int n = s.length();
		boolean dp[][] = new boolean[n][n];
		for(int i = 0; i < n; i ++)
			dp[i][i] = true;
		int res = n;
		for(int i = 1; i < n; i ++)
		{
			for(int j = i - 1; j >= 0; j --)
			{
				dp[j][i] = s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1]);
				res += dp[j][i] ? 1 : 0; 
			}
		}
		return res;
	}
	
	//(leetcode 516)
	//Given a string s, find the longest palindromic subsequence's length in s.
	public int longestPalindromeSubseq(String s)
	{
		int n = s.length();
		int dp[][] = new int[n][n];
		for(int i = 1; i < n; i ++)
		{
			for(int j = i - 1; j >= 0; j --)
			{
				if(s.charAt(i) == s.charAt(j))
					dp[j][i] = dp[j + 1][i - 1] + 2;
				else
					dp[j][i] = Math.max(dp[j + 1][i], dp[j][i - 1]); 
			}
		}
		return dp[0][n - 1];
	}
	
	//(leetcode 132)
	//Given a string s, partition s such that every substring of the partition is a palindrome.
	//Return the minimum cuts needed for a palindrome partitioning of s.
	public int minCut(String s)
	{
		int n = s.length();
        boolean dp[][] = new boolean[n][n];
        int[] cut = new int[n];
        for(int i = 0; i < n; i ++)
            dp[i][i] = true;
        cut[0] = 0;
        for(int i = 1; i < n; i ++)
        {
        	cut[i] = cut[i - 1] + 1; 
        	for(int j = i - 1; j >= 0; j --)
        	{
        		if(s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1]))
        		{
        			dp[j][i] = true;
        			cut[i] = j - 1 < 0 ? 0 : Math.min(cut[i], cut[j - 1] + 1); 
        		}
        	}
        }
        return cut[n - 1];
	}
	public boolean isPalindrome(String s)
	{
		int n = s.length();
        for(int i = 0; i < n / 2; i ++)
    		if(s.charAt(i) != s.charAt(n - i - 1))
    			return false;
        return true;
	}
	//(leetcode336) Palindrome Pairs
	//Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
	//so that the concatenation of the two words, 
	//i.e. words[i] + words[j] is a palindrome.
	public List<List<Integer>> palindromePairs(String[] words) 
	{
		int n = words.length;
		List<List<Integer>> ll = new ArrayList<>();
		TreeSet<Integer> ts = new TreeSet<>();
		Map<String, Integer> mp = new HashMap<>();
		for(int i = 0; i < n; i ++)
		{
			mp.put(words[i], i);
			ts.add(words[i].length());
		}
		for(int i = 0; i < n; i ++)
		{
			String s = new StringBuilder(words[i]).reverse().toString();
			if(mp.containsKey(s) && mp.get(s) != i)
			{
				List<Integer> list = new ArrayList<>();
				list.add(i);
				list.add(mp.get(s));
				ll.add(list);
			}
			int len = s.length();
			Iterator<Integer> it = ts.iterator();
			while(it.hasNext())
			{
				int d = it.next();
				if(d >= len)
					break;
				if(isPalindrome(s.substring(d, len)) && mp.containsKey(s.substring(0, d)))
				{
					List<Integer> l = new ArrayList<>();
					l.add(mp.get(s.substring(0, d)));
					l.add(i);
					ll.add(l);
				}
				if(isPalindrome(s.substring(0, len - d)) && mp.containsKey(s.substring(len - d, len)))
				{
					List<Integer> l = new ArrayList<>();
					l.add(i);
					l.add(mp.get(s.substring(len - d, len)));
					ll.add(l);
				}
			}
		}
		return ll;
	}
	//(leetcode 680). Valid Palindrome II
	//Given a non-empty string s, you may delete at most one character. 
	//Judge whether you can make it a palindrome
	public boolean validPalindrome(String s) {
		int n = s.length();
		for(int i = 0; i < n / 2; i ++)
		{
			if(s.charAt(i) != s.charAt(n - 1 - i))
			{
				return isPalindrome(s.substring(i, n - 1 - i)) || isPalindrome(s.substring(i + 1, n - 1 - i + 1));
			}
		}
		return true;
	}
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	//(leetcode 234). Palindrome Linked List
	public boolean isPalindrome(ListNode head) {
		if(head == null)
			return true;
		ListNode fast, slow;
		fast = slow = head;
		while(fast.next != null && fast.next.next != null)
		{
			fast = fast.next.next;
			slow = slow.next;
		}
		ListNode p = slow.next;
		slow.next = null;
		ListNode newHead = new ListNode(0);
		while(p != null)
		{
			ListNode next = newHead.next;
			ListNode pnext = p.next;
			newHead.next = p;
			p.next = next;
			p = pnext;
		}
		p = newHead;
		while(p != null)
		{
			if(p.val != head.val)
				return false;
			p = p.next;
			head = head.next;
		}
		return true;
	}
	//添加最少的字符串，使得源字符串变为回文
	public int leastCharInsertedToMakePalindrom(String s, int l, int r)
	{
		if(l >= r)
			return 0;
		if(s.charAt(l) == s.charAt(r))
			return leastCharInsertedToMakePalindrom(s, l + 1, r - 1);
		return Math.min(leastCharInsertedToMakePalindrom(s, l + 1, r), leastCharInsertedToMakePalindrom(s, l, r - 1)) + 1;
	}
	//(leetcode 266) Palindrome Permutation
	//给定字符串，是否可以由这个字符串全排序后得到回文
	public boolean canPermutePalindrome(String s) {
		int n = s.length();
        int ascii[] = new int[127];
        for(char c : s.toCharArray())
            ascii[c] ++;
        int cnt = 0;
        for(int a : ascii)
            cnt += (a & 1);
        return cnt == 1 || cnt == 0;
	}
	public void PalindromePermutationBackTracking(List<String> list, String s, String r, int visited[], String single)
    {
        if(r.length() == s.length())
        {
            list.add(r + single + new StringBuilder(r).reverse().toString());
            return ;
        }
        for(int i = 0; i < s.length(); i ++)
        {
            if(visited[i] == 1 || (i > 0 && visited[i - 1] == 0 && s.charAt(i) == s.charAt(i - 1)))
                continue;
            visited[i] = 1;
            PalindromePermutationBackTracking(list, s, r + s.charAt(i), visited, single);
            visited[i] = 0;
        }
    }
	//(leetcode 277) Palindrome Permutation II'
	public List<String> generatePalindromes(String s) {
		int n = s.length();
        List<String> list = new ArrayList<>();
        int ascii[] = new int[127];
        for(char c : s.toCharArray())
            ascii[c] ++;
        int cnt = 0;
        for(int a : ascii)
            cnt += (a & 1);
        if(cnt > 1)
            return list;
        
        String single = "";
        StringBuilder r = new StringBuilder("");
        for(int i = 0; i < 127; i ++)
        {
            if((ascii[i] & 1) == 1)
                single = String.valueOf((char)i);
            for(int j = 0; j < ascii[i] / 2; j ++)
                r.append((char)i);
        }
        s = r.toString();
        PalindromePermutationBackTracking(list, s, "", new int[s.length()], single);
        return list;
	}
	
	public static void main(String[] args)
	{
		LongPalindrommic lp = new LongPalindrommic();

	}
}
