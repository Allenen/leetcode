package zfj.swordToOffer;

public class LargetPalindromeProduct {
	public long getValue(char[]  c)
    {
        long r = 0;
        for(int i = 0; i < c.length; i ++)
            r = r * 10 + c[i] - '0';
        return r;
    }
    public long getPrePalindrome(long x)
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
    public int largestPalindrome(int n) {
        long a = (long)Math.pow(10, n) - 1;
        long b = (long)Math.pow(10, n - 1);
        long max = a * a;
        long min = b * b;
        int res = 0;
        max = isPalindrome(max) ? max : getPrePalindrome(max);
        while(max >= min)
        {
            for(long i = a; i >= b && max / i <= a; i --)
            {
                long j = max / i;
                if(j * i == max) return (int)(max % 1337);
            }
            max = getPrePalindrome(max);
        }
        return 1;
    }
    public static void main(String[] args)
	{
    	LargetPalindromeProduct lpp = new LargetPalindromeProduct();
		char c[] = {'1','0','0', '1'};
		System.out.println(lpp.largestPalindrome(2));
	}
}
