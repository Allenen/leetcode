package zfj.swordToOffer;

//����λ�����һЩ���㣬����д�����+-*/
public class OverrideOperator {
	
	//���üӼ��˳�ʵ���������ļӷ�
	//��������ڼ�����еļ��㷽�����ȼ��������������û�н�λ�Ľ����Ȼ������λ���
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
	
	//����
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
	
	//�˷�
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
	
	//����
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
	
	//ʮ����ת��Ϊ������
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
	//��ȡ����n�Ķ����������һ��1
	public int getLastOne(int n)
	{
		/*��������������㶼���Բ�������
		 * �����Ĳ�������ԭ��
		 *������ �����������������������һ����Ϊ0��λ��֮��ʼ���ұ����е�λȡ��
		 * n��-n�������������ֻ��1λ��ͬ����������һ����Ϊ0��λ
		 */
		return n & -n;
	}
	//�ҵ���С�Ĵ���n��2�Ĵ�����
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
