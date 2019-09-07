package zfj.swordToOffer;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
	
	//(leetcode 290) word pattern
	public boolean wordPattern(String pattern, String str)
	{
		
		Map<Character, String> dict1 = new HashMap<>();
		Map<String, Character> dict2 = new HashMap<>();
		String ss[] = str.split(" ");
		int ns = ss.length;
		int np = pattern.length();
		if(ns != np)
			return false;
		for(int i = 0; i < ns; i ++)
		{
			char c = pattern.charAt(i);
			if(dict1.containsKey(c))
			{
				if(!dict1.get(c).equals(ss[i]))
					return false;
			}
			else
			{
				if(dict2.containsKey(ss[i]))
					return false;
				dict1.put(c, ss[i]);
				dict2.put(ss[i], c);
			}
		}
		return true;
	}
	public boolean backTracking(String pattern, String str, int p, int s, Map<Character, String> dict1, Map<String, Character> dict2)
	{
		int np = pattern.length();
		int ns = str.length();
		if(p == np)
			return ns == s;
		char c = pattern.charAt(p);
		if(dict1.containsKey(c))
		{
			String t = dict1.get(c);
			if(s + t.length() > ns || !t.equals(str.substring(s, s + t.length())))
				return false;
			if(backTracking(pattern, str, p + 1, s + t.length(), dict1, dict2))
				return true;
		}
		else
		{
			for(int i = s; i < ns; i ++)
			{
				String t = str.substring(s, i + 1);
				if(dict2.containsKey(t))
					continue;
				dict1.put(c, t);
				dict2.put(t, c);
				if(backTracking(pattern, str, p + 1, i + 1, dict1, dict2))
					return true;
				dict1.remove(c);
				dict2.remove(t);
			}
		}
		return false;
	}
	//(leetcode 291) word pattern II
	public boolean wordPattern2(String pattern, String str)
	{
		return backTracking(pattern, str, 0, 0, new HashMap<Character, String>(), new HashMap<String, Character>());
	}
}
