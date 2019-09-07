package zfj.swordToOffer;

public class NimGame {
	/*尼姆博弈
	有三堆各若干的物品，两人轮流从某一堆取任意多的物品，规定每次至少取一个，最后取光者获胜。
	我们用(a, b, c)表示某种局势
	第一种奇异局势(0,0,0)是奇异局势，无论谁面对奇异局势，必然会输
	第二种奇异局势：(0, n, n)，只要拿走与对手一样多的物品，最后都会导致(0, 0, 0)
	第三种奇异局势：(a, b, c)，经过几轮游戏变为(0, n, n)
	我们注意到对于奇异局势：(a, b, c), a ^ b ^ c = 0
	*****那么获胜的方法就呼之欲出了，只要初始状态不是奇异局势，第一个人一定可以获胜，而获胜的方法就是把当前的局势转化为奇异局势
	我们知道a ^ a = 0，那么对于三元组，只要对其中任意两组异或，第三组减去异或结果，则必然是奇异局势
	如(a, b, c),其中 a <= b <= c, x = a ^ b,只要第三组保留x个，即我首次从第三组取(c - x)，形成奇异局势(a, b, a ^ b)
	
	*/
	
	//(leetcode 292). Nim Game
	//有一堆石头，共n个，每次最多可以拿走take个
	//两个人玩游戏，从你开始，如果最后一个石头你能拿走，则获胜
	//如果石头有n = take + 1,则不论你第一次拿多少个，最后一块都会被对手拿走，
	//如果石头有n = (take + 1) * s + t，则如果你第一次拿走s块，一定可以获胜
	public boolean canWinNim(int n, int take)
	{
		return n % 4 != 0;
	}
	
	//(leetcode 294) Flip Game
	//给定字符串只有+-，每次可以将++转变为--，两个人参与游戏，问开始游戏的人能否获胜
	public boolean canWinFlip(String s)
	{
		int n = s.length();
		for(int i = 1; i < n; i ++)
		{
			int c1 = s.charAt(i);
			int c2 = s.charAt(i - 1);
			if(c1 == '+' && c2 == '+' && !canWinFlip(s.substring(0, i - 1) + "--" + s.substring(i + 1)))
				return true;
		}
		return false;
	}
	
}
