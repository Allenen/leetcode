package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import zfj._abstract.A;

public class Permutation {
	public List<List<Integer>> ll = new ArrayList<>();
	public int visited = 0;
	
	//(leetcode 46) permutations
	public List<List<Integer>> permute(int[] nums)
	{
		Arrays.sort(nums);
		visited = nums[0] - 1;
		bk_permute(nums, new ArrayList<>());
		return ll;
	}
	public void bk_permute(int[] nums, List<Integer> l)
	{
		if(l.size() == nums.length)
		{
			ll.add(l);
			return ;
		}
		for(int i = 0; i < nums.length; i ++)
		{
			if(nums[i] == visited)
				continue;
			List<Integer> ltmp = new ArrayList<>();
			ltmp.addAll(l);
			int tmp = nums[i];
			ltmp.add(nums[i]);
			nums[i] = visited;
			bk_permute(nums, ltmp);
			nums[i] = tmp; 
		}
	}
	//(leetcode 47) permutations2
	public List<List<Integer>> permuteUnique(int[] nums)
	{
		Arrays.sort(nums);
		visited = nums[0] - 1;
		bk_uniquePermute(nums, new ArrayList<>());
		return ll;
	}
	public void bk_uniquePermute(int[] nums, List<Integer> l)
	{
		if(l.size() == nums.length)
		{
			ll.add(l);
			return ;
		}
		for(int i = 0; i < nums.length; i ++)
		{
			if(nums[i] == visited || (i > 0 && nums[i] == nums[i - 1]))
				continue;
			 List<Integer> lt = new ArrayList<>();
	            lt.addAll(l);
	            lt.add(nums[i]);
	            int tmp = nums[i];
	            nums[i] = visited;
	            bk_uniquePermute(nums, lt);
	            nums[i] = tmp;
		}
	}

	//(leetcode 31) next permutatiom
    public void nextPermutation(int[] nums)
	{
		int n = nums.length;
		int index = -1;
		for(int i = n - 2; i >= 0; i --)
		{
			if(nums[i] < nums[i + 1])
			{
				index = i;
				break;
			}
		}
		if(index == -1)
		{
			Arrays.sort(nums);
			return ;
		}
		int j = n - 1;
		while(nums[j] <= nums[index])
			j --;
		int tmp = nums[index];
		nums[index] = nums[j];
		nums[j] = tmp;
		Arrays.sort(nums, index + 1, n);
	}
    
    //(leetcode 60) Permutation Sequence
    public String getPermutation(int n, int k)
    {
    	int a[] = new int[n];
    	int factor = 1;
    	for(int i = 1; i <= n; i ++)
    	{
    		a[i - 1] = i;
    		factor *= i;
    	}
    	int cnt = n;
    	String res = "";
    	k --;
    	while(cnt > 0)
    	{
    		factor /= cnt;
    		int index = k / factor;
    		res += String.valueOf(a[index]);
    		for(int i = index; i < n - 1; i ++)
    			a[i] = a[i + 1];
    		k %= factor;
    		cnt --;
    	}
    	return res;
    }
    
    //(leetcode 266) palindrome permutation
    //判断一个String的全排列能否构成回文
    public boolean canPermutePalindrome(String s)
    {
    	int n = s.length();
    	int[] ascii = new int[128];
    	for(int i = 0; i < n; i ++)
    		ascii[s.charAt(i)] ++;
    	int even = 0;
    	for(int a :ascii)
    		even += (a & 1);
    	return even == 0 || even == 1;
    }
    
    public void PalindromePermutationBackTracking(List<String> list, String s, String r, int visited[], String single)
    {
        if(r.length() == s.length())
        {
            list.add(r + single + new StringBuilder(r).reverse().toString());
            return ;
        }
        for(int i = 0; i < s.length(); i ++)
        {
            if(visited[i] == 1 || (i > 0 && visited[i - 1] == 0 && s.charAt(i) == s.charAt(i - 1)))
                continue;
            visited[i] = 1;
            PalindromePermutationBackTracking(list, s, r + s.charAt(i), visited, single);
            visited[i] = 0;
        }
    }
	//(leetcode 267) Palindrome Permutation II
	public List<String> generatePalindromes(String s) {
		int n = s.length();
        List<String> list = new ArrayList<>();
        int ascii[] = new int[127];
        for(char c : s.toCharArray())
            ascii[c] ++;
        int cnt = 0;
        for(int a : ascii)
            cnt += (a & 1);
        if(cnt > 1)
            return list;
        
        String single = "";
        StringBuilder r = new StringBuilder("");
        for(int i = 0; i < 127; i ++)
        {
            if((ascii[i] & 1) == 1)
                single = String.valueOf((char)i);
            for(int j = 0; j < ascii[i] / 2; j ++)
                r.append((char)i);
        }
        s = r.toString();
        PalindromePermutationBackTracking(list, s, "", new int[s.length()], single);
        return list;
	}
    
  //(leetcode 784) Letter Case Permutation
    public List<String> l = new ArrayList<>();
    public char changeCase(char c)
    {
        if(c >= 'a' && c <= 'z')
            return (char)(c - 'a' + 'A');
        return (char)(c - 'A' + 'a');
    }
    public void backTracking(char[] s, String t, int k)
    {
        int n = s.length;
        if(t.length() == n)
        {
            l.add(new String(t));
            return ;
        }
        if(s[k] >= '0' && s[k] <= '9')
        {
            backTracking(s, t + s[k], k + 1);
            return ;
        }
        backTracking(s, t + s[k], k + 1);
        backTracking(s, t + changeCase(s[k]), k + 1);
    }
    public List<String> letterCasePermutation(String S) {
        
        char s[] = S.toCharArray();
        backTracking(s, "", 0);
        return l;
    }
    
    //(leetcode 484) find Permutation
    public int[] findPermutation(String s)
    {
    	int n = s.length() + 1;
    	int nums[] = new int[n];
    	int loc = 1;
    	int indexOfI = -1;
    	while(loc <= n)
    	{
    		int i = indexOfI + 1;
    		while(i < n - 1 && s.charAt(i) != 'I') i ++;
    		int tmp = i;
    		nums[i] = loc ++;
    		while(-- i > indexOfI)
    			nums[i] =  loc ++;
    		indexOfI = tmp;
    	}
    	return nums;
    }
    //(leetcode 903). Valid Permutations for DI Sequence
    public int numPermsDISequence(String S) {
    	return 0;
    }
    
    //(leetcode 1053) previous permutation with one swap
    public int[] prePermOpt1(int[] a)
    {
    	int n = a.length;
    	int index = - 1;
    	for(int i = n - 2; i >= 0; i --)
    		if(a[i] > a[i + 1])
    			break;
    	if(index == - 1)
    		return a;
    	int j = n - 1;
    	while(a[j] >= a[index])
    		j --;
    	while(a[j] == a[j - 1])
    		j --;
    	int tmp = a[j];
    	a[j] = a[index];
    	a[index] = tmp;
    	return a;
    }
    public static void main(String[] args)
    {
    	Permutation p = new Permutation();
    	p.findPermutation("DDIIDD");
    	System.out.println(p.changeCase('I'));
    }
}
