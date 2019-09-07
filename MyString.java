package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyString {
	public String msString;
	//(leetcode 6). ZigZag Conversion
	public String convert(String s, int numRows) {
        int len = s.length();
        if(len <= numRows)
            return s;
        int maxd = 2 * (numRows - 1);
        String res = "";
        for(int i = 0; i < numRows; i ++)
        {
            String cur = "";
            int j = i;
            int d1 = (i == 0 || i == numRows - 1) ? maxd : maxd - 2 * i;
            int d2 = (i == 0 || i == numRows - 1) ? maxd : 2 * i;
            int[] d = {d1, d2};
            int index = 0;
            while(j < len)
            {
                cur += s.charAt(j);
                j += d[index];
                index ^= 1;
            }
            res += cur;
        }
        return res;
    }
	//(leetcode 151) reverse words in a string
	public String reverseWords(String s)
	{
		String ss[] = s.split(" +");
		List<String> list = Arrays.asList(ss);
		Collections.reverse(list);
		return String.join(" ", ss);
	}
	//(leetcode 151) reverse words in a string
	public String ReverseWords(String s)
	{
		s = s.trim();
		if(s.equals("")) return s;
		s += " ";
		int n = s.length();
		int i = 0, left = 0;
		while(i < n && s.charAt(i) != ' ')
			i ++;
		String res = s.substring(left, i);
		for(++ i, left = i; i < n; i ++)
		{
			char c = s.charAt(i);
			if(c == ' ')
			{
				if(s.charAt(i - 1) != ' ')
					res = s.substring(left, i) + " " + res;
				left = i + 1;
			}
		}
		return res;	
	}
	//(leetcode 8). String to Integer (atoi)
	public int myAtoi(String s)
	{
		s = s.trim();
		s += " ";
		int n = s.length();
		char c[] = s.toCharArray();
		if(!(c[0] == '+' || c[0] == '-' || Character.isDigit(c[0])))
			return 0;
		int sign = c[0] == '-' ? -1 : 1;
		long res = 0;
		int i = Character.isDigit(c[0]) ? 0 : 1;
		for( ; i < n; i ++)
		{
			if(!Character.isDigit(c[i]))
				break;
			res = res * 10 + c[i] - '0'; 
			if(res > Integer.MAX_VALUE)
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		return (int)res * sign;
	}
	//(leetcode 12) Integer to Roman
	public String ss[] = {"I", "V", "X", "L", "C", "D", "M"};
    public String v1[] = {"", "M", "MM", "MMM"};
    public String v2[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    public String v3[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    public String v4[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    public String intToRoman(int num) {
        return v1[num / 1000] + v2[(num % 1000) / 100] + v3[(num % 100) / 10] + v4[num % 10];
    }
    //(leetcode 13) Roman to Integer
    public int rule(char c)
    {
    	int r = 0;
    	switch(c)
    	{
    	case 'I' : return 1;
    	case 'V' : return 5; 
    	case 'X' : return 10; 
    	case 'L' : return 50;
    	case 'C' : return 100;
    	case 'D' : return 500;
    	case 'M' : return 1000;
    	}
    	return r;
    }
    public int romanToInteger(String s)
    {
    	int n = s.length();
    	if(n == 0)
    		return 0;
    	int res = rule(s.charAt(n - 1));
    	int pre = res;
    	for(int i = n - 2; i >= 0; i ++)
    	{
    		int cur = rule(s.charAt(i));
    		res = cur >= pre ? res + cur : res - cur;
    		pre = cur;
    	}
    	return res;
    }
    //(leetcode 14). Longest Common Prefix
    public String longestCommonPrefix(String[] s)
    {
    	int n = s.length;
    	if(n == 0)
    		return "";
    	String res = s[0];
    	for(int i = 1; i < n; i ++)
    	{
    		int m = s[i].length();
    		int j = 0;
    		while(j < Math.max(m, res.length()) && s[i].charAt(j) == res.charAt(j))
    			j ++;
    		res = res.substring(0, j);
    	}
    	return res;
    }
    //(leetcode 17) Letter Combinations of a phone number
    public List<String> ls = new ArrayList<>();
    public String sn[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public void backTracking(String digits, int k, String s)
    {
        int n = digits.length();
        if(k == n)
        {
            ls.add(s);
            return ;
        }
        int index = digits.charAt(k) - '0';
        for(int j = 0; j < sn[index].length(); j ++)
            backTracking(digits, k + 1, s + sn[index].charAt(j));
    }
    public List<String> letterCombinations(String digits) {
        if(digits.equals("")) return ls;
        backTracking(digits, 0, "");
        return ls;
    }
    
    //(leetcode 28)Implement strStr() or indexOf
    public int[] getNext(String p)
    {
        char s[] = p.toCharArray();
        int[] next = new int[p.length()];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while(j < p.length() - 1)
        {
            if(k == -1 || s[j] == s[k])
                next[++ j] = ++ k;
            else
                k = next[k];
        }
        return next;
    }
    public int strStr(String haystack, String needle) {
        if(needle.equals(""))
            return 0;
        int next[] = getNext(needle);
        int i = 0, j = 0;
        while(i < haystack.length() && j < needle.length())
        {
            if(j == -1 || haystack.charAt(i) == needle.charAt(j))
            {
                i ++;
                j ++;
            }
            else
                j = next[j];
        }
        return j == needle.length() ? i - j : -1;
    }
    //(leetcode 459). Repeated Substring Pattern
    public boolean repeatedSubstringPattern(String s)
    {
    	int n = s.length();
        if(n == 0)
            return false;
        int[] next = new int[n + 1];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while(j < n)
        {
            if(k == -1 || s.charAt(j) == s.charAt(k))
                next[++ j] = ++ k;
            else
                k = next[k];
        }
        return next[n] > 0 && next[n] % (n - next[n]) == 0;
    }
    //(leetcode 459). Repeated Substring Pattern
    public boolean repeatedSubstringPattern_N2(String s)
    {
    	int n = s.length();
    	for(int d = 1; d <= n / 2; d ++)
    	{
    		int flag = 0;
    		if(n % d == 1)
    			continue;
    		for(int i = 0; i < n - d; i ++)
    		{
    			if(s.charAt(i) != s.charAt(i + d))
    			{
    				flag = 1;
    				break;
    			}
    		}
    		if(flag == 0)
    			return true;
    	}
    	return false;
    }
    //(leetcode 686) Repeated String Match
    public int repeatedStringMatch(String A, String B) {
    	int na = A.length();
        int nb = B.length();
        
        String s = "";
        if(na >= nb)
        {
            if(A.indexOf(B) != -1)
                return 1;
            return (A + A).indexOf(B) == -1 ? -1 : 2;
        }
        int index = B.indexOf(A);
        if(index == -1)
            return (A + A).indexOf(B) == -1 ? -1 : 2;
        s = (index == 0 ? "" : A) + B.substring(index, index + (nb - index) - (nb - index) % na)
            + ((nb - index) % na == 0 ? "" : A);
        if(s.indexOf(B) == -1)
        	return -1;
        for(int i = 0; i < s.length(); i += na)
        {
        	String t = s.substring(i, i + na);
            if(!t.equals(A))
                return -1;
        }
        return s.length() / na;
    }
    //最长重复子串
    public String getLongsubstring(String p)
	{
    	int n = p.length();
    	if(n == 0)
    		return "";
    	for(int d = n / 2; d >= 1; d --)
    	{
    		for(int i = 0; i <= n - 2 * d; i ++)
    		{
    			String s1 = p.substring(i, i + d);
    			String s2 = p.substring(i + d);
    			if(s2.indexOf(s1) != -1)
    				return s1;
    		}
    	}
		return "";
	} 
    //最长重复子串(字符串为一个环)
    public String getLongsubstring_ring(String p)
    {
    	int n = p.length();
    	for(int d = n / 2; d >= 1; d --)
    	{
    		for(int i = 0; i < n; i ++)
    		{
    			String s1 = i + d <= n ? p.substring(i, i + d) : p.substring(i) + p.substring(0, d + i - n);
    			String s2 = i + d < n ? p.substring(i + d) + p.substring(0, i) : p.substring(2 * d + i - n, i);
    			for(int j = 0; j < s2.length(); j ++)
    			{
    				String s3 = s2.substring(j) + s2.substring(0, j);
    				StringBuilder sb = new StringBuilder(s3);
    				sb.reverse();
    				if(s3.indexOf(s1) != -1 || sb.toString().indexOf(s1) != -1)
    					return s1;
    			}
    		}
    	}
    	return "";
    }
    //(leetcode 3)Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s)
    {
    	int n = s.length();
    	int max = 0;
    	int index[] = new int[n];
    	Arrays.fill(index, -1);
    	int len = 0;
    	for(int i = 0; i < n; i ++)
    	{
    		char c = s.charAt(i);
    		if(i - len <= index[c])
    		{
    			max = Math.max(max, len);
    			len = i - index[i];
    		}
    		else
    			len ++;
    		index[c] = i; 
    	}
    	max = Math.max(max, len);
    	return max;
    }
	public static void main(String[] args)
	{
		MyString ms = new MyString();
		System.out.println(ms.msString);
		String s1;
		System.out.println(ms.getLongsubstring_ring("aabcxycb"));
		System.out.println(new String("").indexOf(""));
	}
}
