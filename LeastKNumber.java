package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LeastKNumber {
	public ArrayList<Integer> list = new ArrayList<Integer>();
    public int partition(int[] a, int l, int r)
    {
        int x = a[l];
        int i = l, j = r;
        while(i < j)
        {
        	while(i < j && a[j] >= x)
        		j --;
        	a[i] = a[j];
        	while(i < j && a[i] <= x)
        		i ++;
        	a[j] = a[i]; 
        }
        a[i] = x;
        return i;
    }
    public void quickLocation(int[] a, int l, int r, int target)
    {
        if(l >= r)
            return ;
        int p = partition(a, l, r) + 1;
        if(p == target)
            return ;
        if(p > target)
            quickLocation(a, l , p - 2, target);
        else
            quickLocation(a, p, r, target - p);
    }
    //利用快速排序机制，时间复杂度为O(k*log(n)),前K个最小数是无序的
    public ArrayList<Integer> GetLeastNumbers_quicksort(int [] input, int k) {
        if(k > input.length)
            return list;
        quickLocation(input, 0, input.length - 1, k);
        for(int i = 0; i < k; i ++)
            list.add(input[i]);
        return list;
    }
    //利用容器PriorityQueue，时间复杂度为O(k*log(n))，前K个最小数是有序的
    public ArrayList<Integer> GetLeastNumbers_priorityqueue(int [] input, int k) {
        if(k > input.length)
            return list;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < input.length; i ++)
            pq.add(input[i]);
        for(int i = 0; i < k; i ++)
            list.add(pq.poll());
        return list;
    }
    public void adjust(int a[], int k, int n)
    {
    	int top = a[k];
    	int i = 2 * k + 1;
    	while(i < n)
    	{
    		if(i + 1 < n && a[i] > a[i + 1])
    			i ++;
    		if(a[i] >= top)
    			break;
    		a[k] = a[i];
    		k = i;
    		i = 2 * k + 1;
    	}
    	a[k] = top; 
    }
    //利用堆排序机制，原理同容器PriorityQueue，因为PriorityQueue就是由堆排序实现的
    public ArrayList<Integer> GetLeastNumbers_Heap(int [] input, int k) {
        if(k > input.length)
            return list;
        int n = input.length;
        for(int i = n - 1; i >= 0; i --)
        	adjust(input, i, n);
        for(int i = n - 1, j = 0; i >= 0 && j < k; i --, j ++)
        {
        	list.add(input[0]);
        	int tmp = input[0];
        	input[0] = input[i];
        	input[i] = tmp;
        	adjust(input, 0, i);
        }
        return list;
    }
    
    public static void main(String[] args)
    {
    	int a[] = {7,6,3,2,19,8,6,12,32,10,4,1,5,3};
    	int b[] = {2,3,2,3,2,3,1,2,3,4,1,2,3};
    	LeastKNumber lkn = new LeastKNumber();
    	List<Integer> list = lkn.GetLeastNumbers_quicksort(b, 5);
    	for(int i : list)
    		System.out.print(i + " ");
    	System.out.println();
    }
}
