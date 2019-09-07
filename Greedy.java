package zfj.swordToOffer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Greedy {
	//(leetcide 1094) car pooling
	//trips[0],trips[1],trips[2]表示在trips[1]处接trips[0]个人，在trips[2]处将这些人放下
	public boolean carPooling(int trips[][], int capacity)
	{
		int n = trips.length;
		int drop[] = new int[1001];
		int pickUp[] = new int[1001];
		for(int i = 0; i < n; i ++)
		{
			pickUp[trips[i][1]] += trips[i][0];
			drop[trips[i][2]] += trips[i][0];
		}
		int left = capacity;
		for(int i = 0; i < 1001; i ++)
		{
			left = left + drop[i] - pickUp[i];
			if(left < 0)
				return false;
		}
		return true;
	}
	
	//(leetcode 406) Queue Reconstruction by height
	public int[][] reconstructQueue(int[][] people)
	{
		int n = people.length;
		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b)
			{
				if(a[0] != b[0])
					return b[0] - a[0];
				return a[1] - b[1];
			}
		});
		
		List<int[]> list = new LinkedList<int[]>();
		for(int[] a : people)
			list.add(a[1], a);
		list.toArray(people);
		return people;
	}
}
