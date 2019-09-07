package zfj.swordToOffer;

import java.util.LinkedList;
import java.util.Queue;


public class BFS {
	public int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public int bfs(int[][] grid, int i, int j, int buildings)
	{
		int m = grid.length;
        int n = grid[0].length;
		int d = 0;
        int builds = 0;
        int[][] visited = new int[m][n];
        visited[i][j] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(i * n + j);
        int level = 0;
        while(!q.isEmpty())
        {
        	level ++;
            int len = q.size();
            for(int k = 0; k < len; k ++)
            {
                int index = q.poll();
                int x = index / n;
                int y = index % n;
                for(int l = 0; l < 4; l ++)
                {
                    int a = x + dir[l][0];
                    int b = y + dir[l][1];
                    if(a >= 0 && a < m && b >= 0 && b < n && grid[a][b] != 2 && visited[a][b] == 0)
                    {
                        if(grid[a][b] == 1)
                        {
                        	d += level;
                        	builds ++;
                        }
                        else
                            q.add(a * n + b);
                        visited[a][b] = 1;
                    }
                }
            }
        }
        return builds == buildings ? d : Integer.MAX_VALUE;
	}
	//(leetcode 317) Shortest Distance from All Buildings 
    public int shortestDistance(int[][] grid) {
        // write your code here
        int m = grid.length;
        int n = grid[0].length;
        int buildings = 0;
        for(int i = 0; i < m; i ++)
        	for(int j = 0; j < n; j ++)
        			buildings += grid[i][j] == 1 ? 1 : 0;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < m; i ++)
        	for(int j = 0; j < n; j ++)
            	if(grid[i][j] == 0)
                	res = Math.min(res, bfs(grid, i, j, buildings));
        return res;
    }
    
	public static void main(String[] args) throws InterruptedException
	{
		BFS t1 = new BFS();
		//LinkedBlockingQueue<E>

		int[][] grid = {
				{1,1,1,1,1,0},
				{0,0,0,0,0,1},
				{0,1,1,0,0,1},
				{1,0,0,1,0,1},
				{1,0,1,0,0,1},
				{1,0,0,0,0,1},
				{0,1,1,1,1,0}
		};
		
		System.out.println(t1.shortestDistance(grid));
	}
}
