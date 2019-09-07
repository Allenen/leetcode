package zfj.swordToOffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LargestRectangle {
	//(leetcode 84)  Largest Rectangle in Histogram
	//直方图里的最大矩阵面积
	//用栈保存一个递增序列
	//当遍历到的值大于当前栈顶的元素的值，则表示如果以传统方法扩展时右边界，扩展的中心就是栈顶元素
	//接着考虑左边扩展的边界，因为栈保存递增序列，所以左边一定是小于栈顶的元素
	//这时左右边界都知道
	public int largestRectangleArea(int[] heights) {
		int n = heights.length;
        if(n == 0)
            return 0;
        int maxArea = 0;
        Stack<Integer> index = new Stack<>();
        int i = 0;
        while(i < n)
        {
        	if(index.isEmpty() || heights[i] > heights[index.peek()])
        		index.push(i ++);
        	else
        		maxArea = Math.max(maxArea, heights[index.pop()] * (index.isEmpty() ? i : i - index.peek() - 1));
        }
        while(!index.isEmpty())
        	maxArea = Math.max(maxArea, heights[index.pop()] * (index.isEmpty() ? i : i - index.peek() - 1));
        return maxArea;
	}
	//(leetcode 84)  Largest Rectangle in Histogram
	//传统的两边扩展方法
	public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        if(n == 0)
            return 0;
        int maxArea = 0;
        for(int i = 0; i < n; i ++)
        {
            int l = i - 1, r = i + 1;
            while(l >= 0 && heights[l] >= heights[i])
                l --;
            while(r < n && heights[r] >= heights[i])
                r ++;
            int len = r - l - 1;
            maxArea = Math.max(maxArea, heights[i] * len);
        }
        return maxArea;
    }
	
	//(leetcode 85) maximal rectangle
	//可以将当前的二维矩阵每一行以上看做一个直方图
	public int maximalRectangle(char[][] matrix)
	{
		int m = matrix.length;
		if(m == 0) return 0;
		int n = matrix[0].length;
		if(n == 0) return 0;
		int maxArea = 0;
		int heights[][] = new int[m][n];
		for(int i = 0; i < n; i ++)
			heights[0][i] = matrix[0][i] - '0';
		for(int i = 1; i < m; i ++)
		{
			for(int j = 0; j < n; j ++)
			{
				heights[i][j] = matrix[i][j] == '0' ? 0 : heights[i - 1][j] + 1;   
			}
		}
		for(int i = 0; i < m; i ++)
			maxArea = Math.max(maxArea, largestRectangleArea(heights[i]));
		return maxArea;
	}
	//(leetcode 85) maximal rectangle
	//动态规划
	public int maximalRectangle_DP(char[][] matrix)
	{
		int m = matrix.length;
		if(m == 0) return 0;
		int n = matrix[0].length;
		if(n == 0) return 0;
		int maxArea = 0;
		int heights[][] = new int[m][n];
		for(int i = 0; i < n; i ++)
			heights[0][i] = matrix[0][i] - '0';
		for(int i = 1; i < m; i ++)
			for(int j = 0; j < n; j ++)
				heights[i][j] = matrix[i][j] == '0' ? 0 : heights[i - 1][j] + 1; 
		int left[] = new int[n];
		int right[] = new int[n];
		int height[] = new int[n];
		Arrays.fill(right, n);
		for(int i = 0; i < m; i ++)
		{
			int curLeft = 0, curRight = n;
			for(int j = 0; j < n; j ++)
			{
				height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
				left[j] = matrix[i][j] == '1' ? Math.max(left[j], curLeft) : 0;
				curLeft = matrix[i][j] == '0' ? j + 1 : curLeft; 
			}
			for(int j = n - 1; j >= 0; j --)
			{
				right[j] = matrix[i][j] == '1' ? Math.min(right[j], curRight) : n;
				curRight = matrix[i][j] == '0' ? j : curRight; 
			}
			for(int j = 0; j < n; j ++)
				maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
		}
		return maxArea;
	}
	
	//(leetcode 221) Maximal Square
	//求矩阵中的最大正方形面积
	public int maximalSquare(char[][] matrix) {
        int maxArea = 0;
        int m = matrix.length;
        if(m == 0) return 0;
		int n = matrix[0].length;
		if(n == 0) return 0;
        int dp[][] = new int[m + 1][n + 1];
        int height[] = new int[n + 1];
        int left[] = new int[n + 1];
        for(int i = 1; i <= m; i ++)
        {
            for(int j = 1; j <= n; j ++)
            {
            	height[j] = matrix[i - 1][j - 1] == '1' ? height[j] + 1 : 0;
                left[j] = matrix[i - 1][j - 1] == '1' ? left[j - 1] + 1 : 0;
                if(matrix[i - 1][j - 1] == '1')
                    dp[i][j] = Math.min(Math.min(left[j], height[j]), dp[i - 1][j - 1] + 1);
                else
                    dp[i][j] = 0;
                maxArea = Math.max(maxArea, dp[i][j] * dp[i][j]);
            }
        }
        return maxArea;
    }
	//(leetcode 764). Largest Plus Sign
	//找到数组中最大的十字号
	public int orderOfLargestPlusSign(int N, int[][] mines) {
        int n = mines.length;
        int dp[][] = new int[N][N];
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i ++)
            set.add(mines[i][0] * N + mines[i][1]);
        int res = 0;
        for(int i = 0; i < N; i ++)
        {
            int cnt = 0;
            for(int j = 0; j < N; j ++)
            {
                cnt = set.contains(i * N + j) ? 0 : cnt + 1;
                dp[i][j] = cnt;
            }
            cnt = 0;
            for(int j = N - 1; j >= 0; j --)
            {
                cnt = set.contains(i * N + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
            }
        }
        for(int j = 0; j < N; j ++)
        {
            int cnt = 0;
            for(int i = 0; i < N; i ++)
            {
                cnt = set.contains(i * N + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
            }
            cnt = 0;
            for(int i = N - 1; i >= 0; i --)
            {
                cnt = set.contains(i * N + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
	public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        if(m == 0 || dungeon[0].length == 0)
            return 0;
        int n = dungeon[0].length;
        int dp[][] = new int[m + 1][n + 1];
        for(int i = 0; i < m + 1; i ++)
        	Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[m - 1][n] = dp[m][n - 1] = 1;
        for(int i = m - 1; i >= 0; i --)
        {
            for(int j = n - 1; j >= 0; j --)
            {
                int val = Math.min(dp[i+1][j], dp[i][j+1])-dungeon[i][j];
                dp[i][j] = Math.max(val, 1);
            }
            System.out.println();
        }
        return dp[0][0];
    }
	public static void main(String args[])
	{
		LargestRectangle lr = new LargestRectangle();
		char a[][] = {{'1','0','1','1','0','1'},
				      {'1','1','1','1','1','1'},
		              {'0','1','1','0','1','1'},
		              {'1','1','1','0','1','0'},
		              {'0','1','1','1','1','1'},
		              {'1','1','0','1','1','1'}};
		int b[][] = {{-2,-3,3},{-5,-10,1}, {10,30,-5}};
		lr.calculateMinimumHP(b);
	}
}
