package zfj.swordToOffer;

//求数组中逆序对
public class InversePair {
	public int merge(int[] num, int l, int m, int r)
	{
		int tmp[] = new int[r - l + 1];
		int i = l, j = m + 1, k = 0;
		int res = 0;
		while(i <= m && j <= r)
		{
			if(num[i] > num[j])
			{
				res += m - i + 1;
				tmp[k ++] = num[j ++];
			}
			else
				tmp[k ++] = num[i ++];
		}
		while(i <= m)
			tmp[k ++] = num[i ++];
		while(j <= r)
			tmp[k ++] = num[j ++];
		for(int p = 0; p < k; p ++)
			num[l + p] = tmp[p];
		return res;
	}
	public int mergeSort(int[] num, int l, int r)
	{
		if(l >= r)
			return 0;
		int m = l + (r - l) / 2;
		int x = mergeSort(num, l, m);
		int y = mergeSort(num, m + 1, r);
		return x + y + merge(num, l, m, r);
	}
	public int InversePairs(int[] nums) {
		return 0;
	}
}
