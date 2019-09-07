package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicCalculator {
	
	//(leetcode 224) Basic Calculator
	//字符串中只含+-和空格，求表达式的值
	public int calculate(String s) {
		s = "(" + s + ")";
        Stack<Integer> opd = new Stack<>();
        Stack<Character> opr = new Stack<>();
        int n = s.length();
        int digit = -1;
        for(char c : s.toCharArray())
        {
            if(c == ' ') continue;
            if(c >= '0' && c <= '9')
            {
                digit = (digit == -1 ? 0 : opd.pop() * 10) + c - '0';
                opd.push(digit);
                continue;
            }
            if(c == '(')
                opr.push(c);
            else
            {
                
                if(!opr.isEmpty() && opr.peek() != '(')
                    opd.push(opr.pop() == '+' ? opd.pop() + opd.pop() : 0 - opd.pop() + opd.pop());
                if(c == ')')
                    opr.pop();
                else
                    opr.push(c);
            }
            digit = -1;   
        }
        return opd.pop();
    }

	//(leetcode 227) Basic Calculator2
	//字符串中只含+-*/和空格，求表达式的值
	public int calculate2(String s)
	{
		s += "+";
		int n = s.length();
		Stack<Integer> opd = new Stack<>();
		Stack<Character> opr = new Stack<>();
		int digit = -1;
		for(int i = 0; i < n; i ++)
		{
			char c = s.charAt(i);
            if(c == ' ')
				continue;
			if(c >= '0' && c <= '9')
			{
				digit = (digit == -1 ? 0 : opd.pop() * 10) + c - '0';
				opd.push(digit);
				continue;
			}
			
			if(c == '*' || c == '/') 
            {
                if(!opr.isEmpty() && (opr.peek() == '*' || opr.peek() == '/'))
                {
                    char pre = opr.pop();
                    int a = opd.pop();
                    int b = opd.pop();
                    opd.push(pre == '*' ? b * a : b / a);
                }
                opr.push(c);
            }
            else
            {
                while(!opr.isEmpty())
                {
                    char pre = opr.pop();
                    int a = opd.pop();
                    int b = opd.pop();
                    switch(pre)
                    {
                    case '+' : opd.push(b + a); break;
                    case '-' : opd.push(b - a); break;
                    case '*' : opd.push(b * a); break;
                    case '/' : opd.push(b / a); break;
                    }
                }
                opr.push(c);
            }
			digit = -1;
		}

		return opd.pop();
	}
	//(leetcode 227) Basic Calculator2
	//字符串中只含+-*/和空格，求表达式的值
	public int calculate2_NoStack(String s)
	{
		s += '+';
		int res = 0;
		int cur = 0, num = 0;
		char pre = '+';
		for(char c : s.toCharArray())
		{
			if(c == ' ') continue;
			if(c >= '0' && c <= '9')
				num = num * 10 + c - '0';
			else
			{
				switch (pre) {
				case '+':  cur += num; break;
				case '-':  cur -= num; break;
				case '*':  cur *= num; break;
				case '/':  cur /= num; break;
				}
				if(c == '+' || c == '-')
				{
					res += cur;
					cur = 0;
				}
				pre = c;
				num = 0;
			}
		}
		return res;
	}
	//(leetcode 772) Basic Calculator3
	//字符串中含有+-*/()以及空格号
	public int calculate3(String s)
	{
		s = "(" + s + ")";
		int n = s.length();
		Stack<Integer> opd = new Stack<>();
		Stack<Character> opr = new Stack<>();
		int digit = -1;
		for(int i = 0; i < n; i ++)
		{
			char c = s.charAt(i);
			if(c == ' ')
				continue;
			if(c >= '0' && c <= '9')
			{
				digit = (digit == -1 ? 0 : opd.pop() * 10) + c - '0';
				opd.push(digit);
				continue;
			}
			if(c == '(')
				opr.push(c);
			else if(c == '*' || c == '/')
			{
				if(!opr.isEmpty() && (opr.peek() == '*' || opr.peek() == '/'))
				{
					char pre = opr.pop();
					int a = opd.pop();
					int b = opd.pop();
					opd.push(pre == '*' ? b * a : b / a);
				}
				opr.push(c);
			}
			else 
			{
				while(!opr.isEmpty() && opr.peek() != '(')
				{
					char pre = opr.pop();
					int a = opd.pop();
                    int b = opd.pop();
                    switch(pre)
                    {
                        case '+' : opd.push(b + a); break;
                        case '-' : opd.push(b - a); break;
                        case '*' : opd.push(b * a); break;
                        case '/' : opd.push(b / a); break;
                    }
				}
				if(c == ')') opr.pop();
				else opr.push(c);
			}
			digit = -1;
		}
		return opd.pop();
	}
	//(leetcode 282) Expression Add Operators
	//给数字添加运算符使得表达式结果等于target
	public List<String> list = new ArrayList<>();
	public int target;
	public void divideAndConquer(String num, String res, long curRes, long preNum)
	{
		int n = num.length();
		if(curRes == target && n == 0)
		{
			list.add(res);
			return ;
		}
		for(int i = 1; i <= n; i ++)
		{
			String left = num.substring(0, i);
			if(left.length() > 1 && left.charAt(0) == '0')
				return ;
			long leftNum = Long.parseLong(left);
			String right = num.substring(i);
			if(res.length() != 0)
			{
				divideAndConquer(right, res + "*" + left, curRes - preNum + preNum * leftNum, preNum * leftNum);
				divideAndConquer(right, res + "+" + left, curRes + leftNum, leftNum);
				divideAndConquer(right, res + "-" + left, curRes - leftNum, -leftNum);
			}
			else
				divideAndConquer(right, left, leftNum, leftNum);
		}
	}
	public List<String> addOperators(String num, int t) {
		target = t;
		divideAndConquer(num, "", 0, 0);
		return list;
    }
	public static void main(String[] args)
	{
		BasicCalculator bc = new BasicCalculator();
		System.out.println(bc.calculate3("1+1"));
	}
}
