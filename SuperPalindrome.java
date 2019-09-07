package zfj.swordToOffer;

//(leetcode 906)
public class SuperPalindrome {
	public boolean isPalindrome(long x)
    {
        long r = 0;
        long tmp = x;
        while(x != 0)
        {
            r = r * 10 + x % 10;
            x /= 10;
        }
        return r == tmp;
    }
    public long getValue(char[]  c)
    {
        long r = 0;
        for(int i = 0; i < c.length; i ++)
            r = r * 10 + c[i] - '0';
        return r;
    }
    public long getPostPalindrome(long x)
    {
        char c[] = String.valueOf(x).toCharArray();
        int n = c.length;
        for(int i = 0; i < n / 2; i ++)
            c[n - 1 - i] = c[i];
        long y = getValue(c);
        if(y > x)
            return y;
        for(int i = n / 2; i < n; i ++)
        {
            int j = n - i - 1;
            int a = c[i] - '0';
            int b = c[j] - '0';
            if(a == 9 && b == 9)
            {
                c[i] = c[j] = '0';
                continue;
            }
            c[i] = c[j] = (char)(c[i] + 1);
            break;
        }
        if(c[0] == '0')
            return (long)Math.pow(10, n) + 1;
        return getValue(c);
    }
    public int superpalindromesInRange(String L, String R) {
        long l = Long.valueOf(L);
        long r = Long.valueOf(R);
        int res = 0;
        long i = (long)Math.sqrt(l);
        while(i * i <= r)
        {
            if(isPalindrome(i * i) && isPalindrome(i))
                res ++;
            i = getPostPalindrome(i);
        }
        return res;
    }
    public static void main(String[] args)
	{
		SuperPalindrome sp = new SuperPalindrome();
		char c[] = {'1','0','0', '1'};
		System.out.println(sp.superpalindromesInRange("4", "1000"));
	}
}
