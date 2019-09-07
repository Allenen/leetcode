package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.List;

public class DivideAndConquer {
	//(leetcode 282) expression and operators
	//给字符串加上“=、-、*”使得表达式的结果等于target
	//Input: num = "123", target = 6
	//Output: ["1+2+3", "1*2*3"] 
	public List<String> addOperators(String num, int target)
	{
		List<String> list = new ArrayList<>();
		addOperatorsDAQ(num, "", 0, 0, list, target);
		return list;
	}
	public void addOperatorsDAQ(String num, String res, long sum, long last, List<String> list, int target)
	{
		int n = num.length();
		if(n == 0 && sum == target)
		{
			list.add(res);
			return ;
		}
		for(int i = 1; i <= n; i ++)
		{
			String leftNum = num.substring(0, i);
			if(leftNum.length() > 1 && leftNum.charAt(0) == '0')
				return ;
			String rightNum = num.substring(i);
			long left = Long.parseLong(leftNum);
			if(res.length() == 0)
				addOperatorsDAQ(rightNum, leftNum, left, left, list, target);
			else
			{
				addOperatorsDAQ(rightNum, res + "*" + leftNum, sum - last + last * left, last * left, list, target);
				addOperatorsDAQ(rightNum, res + "+" + leftNum, sum + left, left, list, target);
				addOperatorsDAQ(rightNum, res + "-" + leftNum, sum - left, -left, list, target);
			}
		}
	}
}
