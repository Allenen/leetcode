package zfj.swordToOffer;

import java.util.*;

public class Parentheses {
	//括号匹配
	public boolean match(char a, char b)
    {
        return (a == '(' && b == ')') || (a == '{' && b == '}') || (a == '[' && b == ']');
    }
	
	//(leetcode 20) valid parentheses
	public boolean isValid(String s)
    {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray())
        {
        	if(c == '}' || c == ']' || c == ')')
            {
                if(stack.isEmpty() || !match(stack.pop(), c))
                    return false;
            }
            else if(c == '(' || c == '[' || c == '{')
                stack.push(c);
        }
        return stack.isEmpty();
    }
	public List<String> removeInvalidParentheses(String s) {
        Queue<String> q = new LinkedList<>();
        List<String> list = new ArrayList<>();
        q.add(s);
        Map<String, Integer> mp = new HashMap<>();
        boolean found = false;
        while(!q.isEmpty())
        {
            String top = q.peek();
            q.poll();
            if(isValid(top))
            {
                list.add(top);
                found = true;
            }
            if(found)
                continue;
            for(int i = 0; i < top.length(); i ++)
            {
                char c = top.charAt(i);
                if(c != ')' && c != '(')
                    continue;
                String tmp = top.substring(0, i) + top.substring(i + 1);
                if(!mp.containsKey(tmp))
                {
                    mp.put(tmp, 1);
                    q.add(tmp);
                }
            }
        }
        return list;
    }
	public boolean backTracking(String s, int k, Stack<Character> stack)
    {
        int n = s.length();
        if( k == n)
            return stack.isEmpty();
        char c = s.charAt(k);
        if(c == '(')
            stack.push(c);
        else if(c == ')')
        {
            if(stack.isEmpty() || stack.pop() != '(')
                return false;
        }
        else if(c == '*')
        {
        	Stack<Character> stackTmp = new Stack<>();
            stackTmp.addAll(stack);
            //当前*表示空格符
            if(backTracking(s, k + 1, stackTmp))
                return true;
            //当前*表示"("
            stackTmp.clear();
            stackTmp.addAll(stack);
            stackTmp.push('(');
            if(backTracking(s, k + 1, stackTmp))
                return true;
            //当前*表示")"
            if(stack.isEmpty() || stack.pop() != '(')
                return false;
        }
        if(backTracking(s, k + 1, stack))
            return true;
        return false;
    }
	//(leetcode 678) valid parenthesis String
	//回溯法求解
    public boolean checkValidString_BK(String s) {
        int n = s.length();
        if(n == 0)
            return true;
        return backTracking(s, 0, new Stack<Character>());
    }
    //(leetcode 678) valid parenthesis String
    //max表示左边最多可能出现的左括号，min表示左边最少可能出现的左括号
    //当前字符是否为"(",min += c == '(' ? 1 : -1; 这里min可能小于0，所以min = Math.max(min,0)
    //当前字符是否为')',max += c != ')' ? 1 : -1; 这里max可能小于0，表示左括号小于右括号的个数，返回false
    public boolean checkValidString(String s) {
        int n = s.length();
        if(n == 0)
            return true;
        int max = 0, min = 0;
        for(char c : s.toCharArray())
        {
        	min += c == '(' ? 1 : -1;
        	max += c != ')' ? 1 : -1;
        	if(max < 0) break;
        	min = Math.max(min, 0);
        }
        return min == 0;
    }
    //(leetcode 1021). Remove Outermost Parentheses
    public String removeOuterParentheses_STACK(String S) {
        int n = S.length();
        Stack<String> s = new Stack<>();
        String res = "";
        for(int i = 0; i < n; i ++)
        {
            char c = S.charAt(i);
            if(c == ')')
            {
                String t = "";
                while(s.peek() != "(")
                    t = s.pop() + t;
                s.pop();
                if(s.isEmpty())
                    res += t;
                else
                    s.push("(" + t + ")");
            }
            else
                s.push("(");
        }
        return res;
    }
    //(leetcode 1021). Remove Outermost Parentheses
    public String removeOuterParentheses(String S)
    {
    	String res = "";
    	int flag = 0;
    	for(char c : S.toCharArray())
    	{
    		if(c == '(' && flag ++ > 0)
    			res += c;
    		if(c == ')' && flag -- > 0)
    			res += c;
    	}
    	return res;
    }
    //(leetcode 32). Longest Valid Parentheses
    public int longestValidParentheses(String s) {
        int n = s.length();
        int max = 0;
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < n; i ++)
        {
            char c = s.charAt(i);
            if(c == '(')
                stack.push("(");
            else
            {
                String t = "";
                while(!stack.isEmpty() && stack.peek() != "(")
                    t = stack.pop() + t;
                if(stack.isEmpty())
                    max = Math.max(max, t.length());
                else if(stack.pop() == "(")
                {
                    t = "(" + t + ")";
                    while(!stack.isEmpty() && stack.peek() != "(")
                        t = stack.pop() + t;
                    stack.push(t);
                }
            }
        }
        while(!stack.isEmpty())
        {
            String top = stack.pop();
            if(top != "(")
                max = Math.max(max, top.length());
        }
        return max;
    }
    
    //(leetcode 32) Longest Valid Parentheses
    public int longestValidParentheses_DP(String s) 
    {
    	int n = s.length();
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        boolean b[] = new boolean[n];
        for(int i = 0; i < n; i ++)
        {
        	char c = s.charAt(i);
        	if(c == '(')
        		stack.push(i);
        	else if(!stack.isEmpty())
        		b[i] = b[stack.pop()] = true; 
        }
        int len = 0;
        for(int i = 0; i < n; i ++)
        {
        	len = b[i] ? len + 1 : 0;
        	max = Math.max(max, len);
        }
        return max;
    }
    
    //(leetcode 921) Minimum Add to Make Parentheses Valid
    //贪心
    public int minAddToMakeValid(String S) {
    	int n = S.length();
    	Stack<Character> stack = new Stack<>();
    	for(char c : S.toCharArray())
    	{
    		if(c == '(' || stack.isEmpty())
    			stack.push(c);
    		else
    		{
    			if(stack.peek() == '(')
    				stack.pop();
    			else
    				stack.push(c);
    		}
    	}
    	return stack.size();
    }
    //(leetcode 22) Generate Parentheses
    public List<String> list = new ArrayList<>();
    public void backTracking(int n, String s, int left, int right)
    {
        if(right > left || left > n)
            return ;
        if(left == n && right == n)
        {
            list.add(s);
            return ;
        }
        backTracking(n, s + "(", left + 1, right);
        backTracking(n, s + ")", left, right + 1);
    }
    public List<String> generateParenthesis(int n) {
        backTracking(n, "", 0, 0);
        return list;
    }
    //(leetcode 856) socre Of parentheses
    public int scoreOfParentheses(String S)
    {
    	Stack<Integer> stack = new Stack<>();
    	for(char c : S.toCharArray())
    	{
    		if(c == '(')
    			stack.push(-1);
    		else
    		{
    			int cur = 0;
    			while(stack.peek() != -1)
    				cur += stack.pop();
    			stack.pop();
    			stack.push(cur == 0 ? 1 : 2 * cur);
    		}
    	}
    	int res = 0;
    	while(!stack.isEmpty())
    		res += stack.pop();
    	return res;
    }
    //(leetcode 241) Different Ways to Add Parentheses
    //分治法
    public List<Integer> diffWaysToCompute(String input) {
    	List<Integer> list = new ArrayList<>();
    	for(int i = 0; i < input.length(); i ++)
    	{
    		char c = input.charAt(i);
    		if(c == '+' || c == '-' || c == '*')
    		{
    			List<Integer> left = diffWaysToCompute(input.substring(0, i));
    			List<Integer> right = diffWaysToCompute(input.substring(i + 1));
    			for(int j = 0; j < left.size(); j ++)
    			{
    				for(int k = 0; k < right.size(); k ++)
    				{
    					if(c == '+')
    						list.add(left.get(j) + right.get(k));
    					else if(c == '-')
    						list.add(left.get(j) - right.get(k));
    					else if(c == '*')
    						list.add(left.get(j) * right.get(k));
    				}
    			}
    		}
    	}
    	if(list.size() == 0)
    		list.add(Integer.valueOf(input));
    	return list;
    }
    List<String> ls = new ArrayList<>();
    public void dfs(String s, char ch, int last)
    {
    	int cnt = 0;
    	for(int i = 0; i < s.length(); i ++)
    	{
    		char c = s.charAt(i);
    		if(c == '(' || c == ')')
    			cnt += c == ch ? 1 : -1; 
    		if(cnt <= 0) continue;
    		for(int j = last; j <= i; j ++)
    		{
    			if(s.charAt(j) == ch && (j == last || s.charAt(j - 1) != ch))
    				dfs(s.substring(0, j) + s.substring(j + 1), ch, j);
    		}
    		return ;
    	}
    	String t = "";
    	for(int i = s.length() - 1; i >= 0; i --)
    		t += s.charAt(i);
    	if(ch == ')')
    		dfs(t, '(', 0);
    	else
    		ls.add(s);
    }
    //(leetcode 301) remove invalid parentheses (hard)
    public List<String> removeInvalidParenthese(String s)
    {
    	
    	dfs(s, ')', 0);
    	return ls;
    }
    public static void main(String[] args)
    {
    	Parentheses px = new Parentheses();
    	px.generateParenthesis(3);
    }
}
