package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntervalList {

	public class Interval {
		int start;
	 	int end;
	 	Interval() { start = 0; end = 0; }
	 	Interval(int s, int e) { start = s; end = e; }
 	}
	//(leetcode 986) Interval List Intersections
	public Interval[] intervalIntersection(Interval[] a, Interval[] b)
	{
		int na = a.length;
		int nb = b.length;
		List<Interval> list = new ArrayList<>();
		int ka = 0, kb = 0;
		while(ka < na && kb < nb)
		{
			if(a[ka].end < b[kb].start)
			{
				ka ++;
				continue;
			}
			else if(b[kb].end < a[ka].start)
			{
				kb ++;
				continue;
			}
			list.add(new Interval(Math.max(a[ka].start, b[kb].start), Math.min(a[ka].end, b[kb].end)));
			if(a[ka].end <= b[kb].end)
				ka ++;
			else
				kb ++;
		}
		Interval[] res = new Interval[list.size()];
		list.toArray(res);
		return res;
	}
	
	//(leetcode 56) merge Interval
	public List<Interval> mergeInterval(List<Interval> intervals)
	{
		int n = intervals.size();
		List<Interval> list = new ArrayList<>();
		if(n == 0)
			return list;
		Collections.sort(list, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b)
			{
				return a.start - b.start;
			}
		});
		Interval cur = intervals.get(0);
		for(int i = 1; i < n; i ++)
		{
			int start = intervals.get(i).start;
			int end = intervals.get(i).end;
			if(start > cur.end)
			{
				list.add(cur);
				cur = intervals.get(i);
			}
			else if(end > cur.end)
				cur.end = end;
				
		}
		list.add(cur);
		return list;
	}
	//(leetcode 57). Insert Interval
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> list = new ArrayList<>();
		int n = intervals.size();
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        int i = 0;
        Interval mid = new Interval(newStart, newEnd);
        for( ; i < n; i ++)
        {
        	int start = intervals.get(i).start;
        	int end = intervals.get(i).end;
        	if(newStart < start)
        	{
        		mid.start = newStart;
        		break;
        	}
        	else if(newStart <= end)
        	{
        		mid.start = start;
        		break;
        	}
        	else {
				list.add(intervals.get(i));
			}
        }
        for( ; i < n; i ++)
        {
        	int start = intervals.get(i).start;
        	int end = intervals.get(i).end;
        	if(newEnd < start)
        	{
        		mid.end = newEnd;
        		break;
        	}
        	else if(newEnd <= end)
        	{
        		mid.end = end;
        		i ++;
        		break;
        	}
        }
        list.add(mid);
        for( ; i < n; i ++)
        	list.add(intervals.get(i));
		return list;
    }
	
	//活动安排问题，贪心算法
	public List<Interval> activityArrangement(Interval activity[])
	{
		List<Interval> list = new ArrayList<>();
		int n = activity.length;
		if(n == 0)
			return list;
		Arrays.sort(activity, new Comparator<Interval>() {

			@Override
			public int compare(Interval a, Interval b) {
				// TODO Auto-generated method stub
				return a.end - b.end;
			}
		});
		list.add(activity[0]);
		Interval pre = activity[0];
		for(int i = 1; i < n; i ++)
		{
			if(activity[i].start >= pre.end)
			{
				list.add(activity[i]);
				pre = activity[i];
			}
		}
		return list;
	}
	
	//(leetcode 97) Interleaving String
	//s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
	//可以将s1和s2展开为一个二维地图，判断能否从左上角走到右下角
	//当s1到达第i个元素，s2到达第j个元素
	//(1)、地图上往右走一步相当于匹配s2[j - 1]和s3[i + j - 1]
	//(2)、地图上往下走一步相当于匹配s1[i - 1]和s3[i + j - 1]
	public boolean isInterleave(String s1, String s2, String s3) {
		int n1 = s1.length();
		int n2 = s2.length();
		if(n1 + n2 != s3.length())
			return false;
		boolean dp[][] = new boolean[n1 + 1][n2 + 1];
		for(int i = 0; i <= n1; i ++)
			Arrays.fill(dp[i], false);
		dp[0][0] = true;
		for(int i = 1; i <= n1; i ++)
			dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
		for(int i = 1; i <= n2; i ++)
			dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
		for(int i = 1; i <= n1; i ++)
		{
			for(int j = 1; j <= n2; j ++)
				dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || 
				           (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
		}
        return dp[n1][n2];
    }
	
	//(leetcode 1024) video Stitching
	public int videoStitching(int clips[][], int T)
	{
		Arrays.sort(clips, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b)
            {
                if(a[0] != b[0])
                    return a[0] - b[0];
                return b[1] - a[1];
            }
        });
		List<Interval> list = new ArrayList<>();
		list.add(new Interval(clips[0][0], clips[0][1]));
		int k = 0;
		for(int i = 1; i < clips.length; i ++)
		{
			if(clips[i][0] > clips[i - 1][0] && clips[i][1] > list.get(k).end)
			{
				list.add(new Interval(clips[i][0], clips[i][1]));
				k ++;
			}
		}
		int n = list.size();
		if(list.get(0).start > 0 || list.get(n - 1).end < T)
        	return -1;
		int dp[] = new int[n];
		for(int i = 0; i < n; i ++)
        {
        	int s1 = list.get(i).start;
            int e1 = list.get(i).end;
        	dp[i] = i == 0 ? 1 : Integer.MAX_VALUE; 
        	for(int j = i - 1; j >= 0; j --)
        	{
        		int e2 = list.get(j).end;
        		if(s1 <= e2)
        			dp[i] = Math.min(dp[i], dp[j] + 1);
        	}
            if(e1 >= T)
                return dp[i] == Integer.MAX_VALUE ? -1 : dp[i];
        }
		return -1;
	}
	//(leetcode 1024) video Stitching
    public int videoStitching1(int[][] clips, int T) {
        Arrays.sort(clips, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b)
            {
                if(a[0] != b[0])
                    return a[0] - b[0];
                return b[1] - a[1];
            }
        });
        int n = clips.length;
        if(clips[0][0] > 0)
            return -1;
        int dp[] = new int[n];
        for(int i = 0; i < n; i ++)
        {
            dp[i] = i == 0 ? 1 : Integer.MAX_VALUE;
            for(int j = i - 1; j >= 0; j --)
            {
                if(dp[j] == -1)
                    continue;
                if(!(clips[i][0] > clips[j][0] && clips[i][1] > clips[j][1]))
                {
                    dp[i] = -1;
                    break;
                }
                if(clips[i][0] <= clips[j][1])
                    dp[i] = Math.min(dp[i], dp[j] + 1);
            }
            if(dp[i] != -1 && clips[i][1] >= T)
                return dp[i] == Integer.MAX_VALUE ? -1 : dp[i];
        }
        		
        return -1;
    }
	
	public static void main(String args[])
	{
		IntervalList il = new IntervalList();
		il.isInterleave("aabcc","dbbca","aadbbcbcac");
	}
}
