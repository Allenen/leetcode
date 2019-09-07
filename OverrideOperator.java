package zfj.swordToOffer;

//利用位运算简化一些计算，如重写运算符+-*/
public class OverrideOperator {
	
	//不用加减乘除实现两个数的加法
	//两数相加在计算机中的计算方法是先计算两个数的相加没有进位的结果，然后计算进位结果
	public int myAdd(int a, int b)
	{
		while(b != 0)
		{
			int carry = (a & b) << 1;
			a ^= b; 
			b = carry;
		}
		return a;
	}
	
	//减法
	public int myMinus(int a, int b)
	{
		while(b != 0)
		{
			int d = a ^ b;
			int borrow = (b & d) << 1;
			a = d;
			b = borrow;
		}
		return a;
	}
	
	//乘法
	public int myMultiply(int a, int b)
	{
		int res = 0;
		for(int i = 0; i < 32; i ++)
		{
			int bit = b & (1 << i);
			res += bit == 0 ? 0 : (a << i);
		}
		return res;
	}
	
	//除法
	public int myDivide(int a, int b)
	{
		int flag = (a ^ b) < 0 ? -1 : 1;
		long res = 0;
		long x = Math.abs((long)a);
		long y = Math.abs((long)b);
		while(x >= y)
		{
			int cnt = 0;
			while(x >= y << cnt)
			{
				res += (1 << cnt);
				x -= y << cnt;
				cnt ++;
			}
		}
        res *= flag;
        if(res > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
		return res < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int)res;
	}
	
	//十进制转化为二进制
	public String decimalToBinary(int x)
	{
		String reString = "";
		for(int i = 0; i < 32; i ++)
		{
			int bit = x & (1 << i);
			reString = (bit == 0 ? "0" : "1") + reString;
		}
		System.out.println(reString);
		return reString;
	}
	//获取整数n的二进制中最后一个1
	public int getLastOne(int n)
	{
		/*计算机中数的运算都是以补码运算
		 * 正数的补码是其原码
		 *负数的 补码是其正数补码从左边起第一个不为0的位置之后开始，右边所有的位取反
		 * n与-n补码的区别，他们只有1位不同，即左边起第一个不为0的位
		 */
		return n & -n;
	}
	//找到最小的大于n的2的次幂数
	public int getFirstNumberGreaterThanN(int n)
	{
		n --;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return n + 1;
	}
	//k % (2^n) = k & (2^n - 1)
	public int modToAnd(int k, int n)
	{
		return k & (n - 1);
	}

	public static void main(String[] args)
	{
		OverrideOperator oo = new OverrideOperator();
		System.out.println(oo.myAdd(2, 2));
	}
}
