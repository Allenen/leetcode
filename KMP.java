package zfj.swordToOffer;

public class KMP {
	//next[i]��ʾ��ǰ����i��ƥ�����һ��ƥ��ģʽ������ʼλ��
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
		int i = 0; // ����λ��
		int j = 0; //ģʽ��λ��
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
	//��ͷ����������ַ���ʹ�����Ϊ����
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
