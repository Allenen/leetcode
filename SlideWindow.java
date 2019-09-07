package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import zfj._abstract.A;

//滑动窗口问题
public class SlideWindow {
	//（牛客）给定数组，在滑动窗口size内找到最大值
	//例如：{2,3,4,2,6,2,5,1}， size = 3
	//结果：{4,4,6,6,6,5}
	public List<Integer> maxInWindows(int[] nums, int size)
	{
		List<Integer> list = new ArrayList<>();
		int n = nums.length;
		if(size == 0 || size > n)
			return list;
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		for(int i = 0; i < size; i ++)
			pq.add(nums[i]);
		for(int i = size; i < n; i ++)
		{
			list.add(pq.peek());
			pq.remove(nums[i - size]);
			pq.add(nums[i]);
		}
		list.add(pq.peek());
		return list;
	}
	
	//（leetcode3）Longest Substring Without Repeating Characters
	public int lengthOfLongestSubstring(String s) {
		int n = s.length();
		int max = 0;
		int[] ascii = new int[128];
		Arrays.fill(ascii, -1);
		int len = 0;
		for(int i = 0; i < n; i ++)
		{
			char c = s.charAt(i);
			if(i - len <= ascii[c])
			{
				max = Math.max(max, len);
				len = i - ascii[c];
			}
			else
				len ++;
			ascii[c] = i; 
		}
		max = Math.max(max, len);
		return max;
	}
	
	//（leetcode76）Minimum Window Substring
	//Input: S = "ADOBECODEBANC", T = "ABC"
	//Output: "BANC"
	//包含字符串T的S的最小子串
	public String minWindow(String s, String t) {
		int ns = s.length();
		int nt = t.length();
		int ascii[] = new int[128];
		for(int i = 0; i < nt; i ++)
			ascii[t.charAt(i)] ++;
		String res = "";
		int min = Integer.MAX_VALUE;
		int cnt = 0, left = 0;
		for(int i = 0; i < ns; i ++)
		{
			char c = s.charAt(i);
			if(-- ascii[c] >= 0)
				cnt ++;
			while(cnt == nt)
			{
				if(i - left + 1 < min)
				{
					min = i - left + 1;
					res = s.substring(left, i + 1);
				}
				if(++ ascii[s.charAt(left)] > 0)
					cnt --;
				left ++;
			}
		}
		return res;
	}
	//（leetcode978）Longest Turbulent Subarray
	public int maxTurbulenceSize(int[] A) {
		int n = A.length;
		if(n == 0)
			return 0;
		int max = 1;
		for(int i = 1; i < n; i ++)
		{
			if(A[i] == A[i - 1])
				continue;
			int left = i - 1;
			int f = A[i] > A[i - 1] ? 1 : -1; 
			while(i + 1 < n && (A[i + 1] - A[i]) * f < 0)
			{
				f = -f;
				i ++;
			}
			max = Math.max(max, i - left + 1);
		}
        return max;
    }
	//（leetcode424）Longest Repeating Character Replacement
	//最多可以替换k个字符，求最长重复子串
	public int characterReplacement(String s, int k) {
		int n = s.length();
		int alph[] = new int[26];
		int max = 0, left = 0, res = 0;
		for(int i = 0; i < n; i ++)
		{
			char c = s.charAt(i);
			max = Math.max(max, ++ alph[c - 'A']);
			while(i - left + 1 - max > k)
			{
				alph[s.charAt(left) - 'A'] --;
				left ++;
			}
			res = Math.max(res, i - left + 1);
		}
		return res;
	}
	//（leetcode1004）Max Consecutive Ones III
	//Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
	//Return the length of the longest (contiguous) subarray that contains only 1s. 
	public int longestOnes(int[] A, int K) {
		int n = A.length;
		int zero = 0, left = 0, res = 0;
		for(int i = 0; i < n; i ++)
		{
			if(A[i] == 0)
				zero ++;
			while(zero > K)
			{
				if(A[left] == 0)
					zero --;
				left ++;
			}
			res = Math.max(res, i - left + 1);
		}
		return res;
	
	}
	//(leetcode 30) Substring with Concatenation of All Words
	public List<Integer> findSubstring(String s, String[] words) {
        int ns = s.length();
        int nw = words.length;
        List<Integer> list = new ArrayList<>();
        if(nw == 0 || ns == 0)
            return list;
        int len = words[0].length();
        Map<String, Integer> mp1 = new HashMap<>();
        for(int i = 0; i < nw; i ++)
            mp1.put(words[i], mp1.getOrDefault(words[i], 0) + 1);
        for(int i = 0; i < len; i ++)
        {
        	int left = i, cnt = 0;
        	Map<String, Integer> mp2 = new HashMap<>();
        	for(int j = i; j <= ns - len; j += len)
        	{
        		String t = s.substring(j, j + len);
        		if(mp1.containsKey(t))
        		{
        			mp2.put(t, mp2.getOrDefault(t, 0) + 1);
            		if(mp2.get(t) <= mp1.get(t))
            			cnt ++;
            		while(mp2.get(t) > mp1.get(t))
            		{
            			String t1 = s.substring(left, left + len);
            			mp2.put(t1, mp2.getOrDefault(t1, 0) - 1);
            			if(mp2.get(t1) < mp1.get(t1))
            				cnt --;
            			left += len;
            		}
            		if(cnt == nw)
            		{
            			list.add(left);
            			String t1 = s.substring(left, left + len);
            			mp2.put(t1, mp2.getOrDefault(t1, 0) - 1);
            			cnt --;
            			left += len;
            		}
        		}
        		else
        		{
        			mp2.clear();
        			cnt = 0;
        			left = j + len;
        		}
        		
        	}
        }
        return list;
    }
	//(leetcode 992) subarrays with k different integers
	//子数组中有k个不同的数字
	public int subarrayWithKDistinct(int[] nums, int k)
	{
		int n = nums.length;
		int res = 0;
		int m[] = new int[n + 1];
		int left = 0, cnt = 0, prefix = 0;
		for(int i = 0; i < n; i ++)
		{
			if(m[nums[i]] ++ == 0)
				cnt ++;
			if(cnt > k)
			{
				m[nums[left ++]] --;
				cnt --;
				prefix = 0;
			}
			while(m[nums[left]] > 1)
			{
				prefix ++;
				m[nums[left ++]] --;
			}
			if(cnt == k)
				res += prefix + 1;
		}
		return res;
	}
	//(leetcode 159) Longest substring with at most two distinct characters
	public int lengthOfLongestSubstringTwoDistinct(String s)
	{
		int n = s.length();
		int ascii[] = new int[128];
		int max = 1, left = 0, cnt = 0;
		for(int i = 0; i < n; i ++)
		{
			if(ascii[s.charAt(i)] ++ == 0)
				cnt ++;
			if(cnt > 2)
			{
				max = Math.max(max, i - left);
				while(-- ascii[s.charAt(left ++)] > 0);
				cnt --;
			}
		}
		max = Math.max(max, n - left);
		return max;
	}
	//(leetcode 340)Longest Substring with At Most K Distinct Characters
	public int lengthOfLongestSubstringKDistinct(String s, int k)
	{
		int n = s.length();
		int ascii[] = new int[128];
		int left = 0, max = 1, cnt = 0;
		for(int i = 0; i < n; i ++)
		{
			if(ascii[s.charAt(i)] ++ == 0)
				cnt ++;
			if(cnt > k)
			{
				max = Math.max(max, i - left);
				while(ascii[s.charAt(left)] > 0)
				{
					if(-- ascii[s.charAt(left ++)] == 0)
						break;
				}
			}
		}
		return 0;
	}
	public static void main(String[] args)
	{
		SlideWindow sw = new SlideWindow();
		Scanner scanner = new Scanner(System.in);
		String tmp;
		while(true)
		{
			tmp = scanner.nextLine();
			System.out.println(sw.lengthOfLongestSubstringTwoDistinct(tmp));
		}
//		String words[] = {"foo","foo","bar"};
//		sw.findSubstring("barfoofooxthefoobarfooman", words);
	}
}
