package zfj.swordToOffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LargestRectangle {
	//(leetcode 84)  Largest Rectangle in Histogram
	//ֱ��ͼ������������
	//��ջ����һ����������
	//����������ֵ���ڵ�ǰջ����Ԫ�ص�ֵ�����ʾ����Դ�ͳ������չʱ�ұ߽磬��չ�����ľ���ջ��Ԫ��
	//���ſ��������չ�ı߽磬��Ϊջ����������У��������һ����С��ջ����Ԫ��
	//��ʱ���ұ߽綼֪��
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
	//��ͳ��������չ����
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
	//���Խ���ǰ�Ķ�ά����ÿһ�����Ͽ���һ��ֱ��ͼ
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
	//��̬�滮
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
	//������е�������������
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
	//�ҵ�����������ʮ�ֺ�
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
