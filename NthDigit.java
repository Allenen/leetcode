package zfj.swordToOffer;

public class NthDigit {
	//(leetcode 400) Nth Digit
	public int findNthDigit(int n)
	{
		if(n < 10)
			return n;
		long e = 1, k = 1;
		long sum = 1, m = 9 * e * k;
		while(n / m != 0)
		{
			sum += m;
			k ++;
			e *= 10;
			m = 9 * e * k;
		}
		long left = n - sum;
		long num = e + left / k;
		return String.valueOf(num).charAt((int)(left % k)) - '0';
	}
}
