package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class N_QUEENS {
	public boolean isValid(char[][] visited, int i, int j)
	{
		int n = visited.length;
		for(int k = 0; k < i; k ++)
			if(visited[k][j] == 'Q')
				return false;
		int l = i, r = j;
		while(l >= 0 && r < n)
		{
			if(visited[l][r] == 'Q')
				return false;
			l --;
			r ++;
		}
		l = i;
		r = j;
		while(l >= 0 && r >= 0)
		{
			if(visited[l][r] == 'Q')
				return false;
			l --;
			r --;
		}
		return true;
	}
	public void backTracking(List<List<String>> ll, char[][] visited, int row)
	{
		int n = visited.length;
		if(row == n)
		{
			List<String> list = new ArrayList<>();
			for(int i = 0; i < n; i ++)
				list.add(String.valueOf(visited[i]));
			ll.add(list);
			return ;
		}
		for(int i = 0; i < n; i ++)
		{
			if(visited[row][i] == '.' && isValid(visited, row, i))
			{
				visited[row][i] = 'Q';
				backTracking(ll, visited, row + 1);
				visited[row][i] = '.'; 
			}
		}
	}
	//(leetcode 51) 
	//(leetcode 52) 
	public List<List<String>> solveNQueens(int n)
	{
		char[][] visited = new char[n][n];
		for(int i = 0; i < n; i ++)
			for(int j = 0; j < n; j ++)
				visited[i][j] = '.'; 
		List<List<String>> ll = new ArrayList<>();
		backTracking(ll, visited, 0);
		return ll;
	}
	
	
	
	public Set<Integer> set = new HashSet<>();
    public Map<Integer, Integer> mpRow = new HashMap<>();
    public Map<Integer, Integer> mpCol = new HashMap<>();
    public Map<Integer, Integer> mpRow_Col = new HashMap<>();
    public Map<Integer, Integer> mpCol_Row = new HashMap<>();
    public int n;
    public int[][] adj = {{0, 0}, {1,1}, {1,0}, {0,1}, {1,-1}, {-1, -1}, {-1, 0}, {0, -1}, {-1, 1}};
    public boolean isIlluminated(int i, int j)
    {
        if((mpRow.containsKey(i) && mpRow.get(i) > 0) 
           || (mpCol.containsKey(j) && mpCol.get(j) > 0) 
           || (mpRow_Col.containsKey(i - j) && mpRow_Col.get(i - j) > 0) 
           || (mpCol_Row.containsKey(i + j) && mpCol_Row.get(i + j) > 0) )
            return true;
        return false;
    }
    public void turnOff(int i, int j)
    {
        for(int k = 0; k < 9; k ++)
        {
            int x = i + adj[k][0];
            int y = j + adj[k][1];
            if(x >= 0 && x < n && y >= 0 && y < n && set.contains(x * n + y))
            {
                set.remove(x * n + y);
                mpRow.put(x, mpRow.getOrDefault(x, 1) - 1);
                mpCol.put(y, mpCol.getOrDefault(y, 1) - 1);
                mpRow_Col.put(x - y, mpRow_Col.getOrDefault(x - y, 1)- 1);
                mpCol_Row.put(x + y, mpCol_Row.getOrDefault(x + y, 1) - 1);
            }
        }
    }
    //(leetcode 1001) Grid Illumination
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        int nl = lamps.length;
        int nq = queries.length;
        n = N;
        int res[] = new int[nq];
        for(int i = 0; i < nl; i ++)
        {
            int x = lamps[i][0];
            int y = lamps[i][1];
            set.add(x * n + y);
            mpRow.put(x, mpRow.getOrDefault(x, 0) + 1);
            mpCol.put(y, mpCol.getOrDefault(y, 0) + 1);
            mpRow_Col.put(x - y, mpRow_Col.getOrDefault(x - y, 0) + 1);
            mpCol_Row.put(x + y, mpCol_Row.getOrDefault(x + y, 0) + 1);
            
        }
        for(int i = 0; i < nq; i ++)
        {
            int x = queries[i][0];
            int y = queries[i][1];
            res[i] = isIlluminated(x, y) ? 1 : 0;
            turnOff(x, y);
        }
        return res;
    }
	public static void main(String[] args)
	{
		char c[][] = {{'a', 'b'},{'a', 'b'}};
		String s = "";
		for(int i = 0; i < c.length; i ++)
			s += String.valueOf(c[i]);
		System.out.println(s);
	}
}
