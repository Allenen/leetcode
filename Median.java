package zfj.swordToOffer;

import java.util.PriorityQueue;

public class Median {
	
	//给定无序数组找出中位数
	public double getMedian(int a[])
	{
		int n = a.length;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i <= n / 2; i ++)
			pq.add(a[i]);
		for(int i = n / 2 + 1; i < n; i ++)
		{
			if(pq.peek() < a[i])
			{
				pq.add(a[i]);
				pq.poll();
			}
		}
		return (n & 1) == 0 ? (pq.poll() + pq.poll()) / 2.0 : pq.poll();
	}
}
