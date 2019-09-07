package zfj.swordToOffer;

public class Joseph {
	//约瑟夫（joseph）环问题
	//n个数（0 ~ n）构成一个环，从0开始计数，每次剔除第m-1个数，并从被剔除的下一个数开始计数，
	//最后剩下的那个数是？
	//当n = 10, m = 4
	// 0 1 2 3 4 5 6 7 8 9
	// 6 7 8 - 0 1 2 3 4 5
	// 2 3 4 - 5 6 7 - 0 1
	// 6 - 0 - 1 2 3 - 4 5
	// 2 - 3 - 4 5 - - 0 1
	// 4 - - - 0 1 - - 2 3
	// 0 - - - 1 2 - - 3 -
	// 0 - - - 1 2 - - - -
	// - - - - 0 1 - - - -
	// - - - - 0 - - - - -
	public int josephCircle(int n, int m)
	{
		if(n == 0)
			return -1;
		if(n == 1)
			return 0;
		return (josephCircle(n - 1, m) + m) % n;
	}
	public static void main(String[] args)
	{
		int n = 2;
		boolean  b = n == 1;
		b |= n == 2;
		Joseph joseph = new Joseph();
		int last = joseph.josephCircle(10, 4);
		System.out.println(last);
	}
}
