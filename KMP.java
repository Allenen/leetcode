package zfj.swordToOffer;

public class KMP {
	//next[i]表示当前索引i不匹配后，下一轮匹配模式串的起始位置
	public int[] getNext(String s)
	{
		char p[] = s.toCharArray();
		int[] next = new int[s.length()]; 
		next[0] = -1;
		int j = 0;
		int k = -1;
		while(j < p.length - 1)
		{
			if(k == -1 || p[j] == p[k])
				next[++ j] = ++ k;
			else
				k = next[k];
		}
		
		return next;
	}
	public int kmp(String ts, String ps)
	{
		char[] t = ts.toCharArray();
		char[] p = ps.toCharArray();
		int i = 0; // 主串位置
		int j = 0; //模式串位置
		int[] next = getNext(ps);
		while(i < t.length && j < p.length)
		{
			if(j == -1 || t[i] == p[j])
			{
				i ++;
				j ++;
			}
			else {
				j = next[j];
			}
		}
		return j == p.length ? i - j : -1;
	}
	
	//(leetcode 214) shortest palindrome
	//在头部添加最少字符串使得其成为回文
	public String shortestPalindrome(String s)
	{
		String pre = s;
        int np = pre.length();
        s += " " + new StringBuilder(s).reverse().toString() + " ";
        int next[] = getNext(s);
        
        return new StringBuilder(pre.substring(next[s.length() - 1])).reverse().toString() + pre;
	}
	
	public static void main(String[] args)
	{
		String s = "ababababca";
		int index = s.indexOf("abc");
		KMP k = new KMP();
		int a[] = k.getNext("acdacf");
		System.out.println(k.kmp("ababababca", "abc"));
	}
}
