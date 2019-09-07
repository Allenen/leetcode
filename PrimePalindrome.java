package zfj.swordToOffer;

public class PrimePalindrome {
	public long getValue(char[]  c)
    {
        long r = 0;
        for(int i = 0; i < c.length; i ++)
            r = r * 10 + c[i] - '0';
        return r;
    }
    public long getPrePalindromic(long x)
    {
        char c[] = String.valueOf(x).toCharArray();
        int n = c.length;
        for(int i = 0; i < n / 2; i ++)
            c[n - 1 - i] = c[i];
        long y = getValue(c);
        if(y < x)
            return y;
        for(int i = n / 2; i < n; i ++)
        {
            int j = n - 1 - i;
            int a = c[i] - '0';
            int b = c[j] - '0';
            if(a == 0 && b == 0)
            {
                c[i] = c[j] = '9';
                continue;
            }
            c[i] = c[j] = (char)(c[i] - 1);
            break;
        }
        if(c[0] == '0')
            return (long)Math.pow(10, n - 1) - 1;
        return getValue(c);
    }
    public long getPostPalindromic(long x)
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
    public String nearestPalindromic(String n) {
        long cur = Long.valueOf(n);
        if(cur == 0)
            return "1";
        if(cur <= 10)
            return String.valueOf(cur - 1);

        long pre = getPrePalindromic(cur);
        long post = getPostPalindromic(cur);
        return cur - pre > post - cur ? String.valueOf(post) : String.valueOf(pre);
    }
    public static void main(String[] args)
	{
		PrimePalindrome pp = new PrimePalindrome();
		char c[] = {'1','0','0', '1'};
		System.out.println(pp.nearestPalindromic("121"));
	}
}
