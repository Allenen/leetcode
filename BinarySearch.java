package zfj.swordToOffer;


public class BinarySearch {
	//数组在排序数组中出现的次数
	public int GetNumberOfK(int a[], int k)
	{
		int n = a.length;
        int res = 0;
        int i = 0;
        while(i < n && a[i] != k)
            i ++;
        while(i < n && a[i] == k)
        {
            i ++;
            res ++;
        }
        return res;
	}
	public int binarySearch(int[] a, int target)
	{
		int n = a.length;
		int l = 0, r = n;
		while(l < r)
		{
			int m = l + (r - l) / 2;
			if(a[m] >= target)
				r = m;
			else
				l = m + 1;
		}
		return l;
	}
	//利用二分法查找target在有序数组中出现的次数
	//思想是用二分查找找到target首次出现的地方
	//那么target出现的次数就等于（target+1）首次出现的位置 - target首次出现的位置
	//需要注意的是，如果target没有在数组中，两种情况，1、返回值等于数组长度2、返回的位置并不是target
	public int getNumOfTarget(int a[], int target)
	{
		int n = a.length;
		int l = binarySearch(a, target);
		int r = binarySearch(a, target + 1);
		return (l == n || a[l] != target) ? 0 : r - l;
	}
	
	//(leetcode 4) Median of two sorted Arrays
	//求两个有序数组合并后的中位数
	//要求时间复杂度为log(m + n)
	public double findMedianSortedArrays(int nums1[], int nums2[])
	{
		int m = nums1.length;
		int n = nums2.length;
		int left = (m + n + 1) / 2;
		int right = (m + n + 2) / 2; 
		return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
	}
	public int findKth(int[] nums1, int i, int[] nums2, Integer j, int k)
	{
		if(i >= nums1.length)
			return nums2[j + k - 1];
		if(j >= nums2.length)
			return nums1[i + k - 1];
		if(k == 1)
			return Math.min(nums1[i], nums2[j]);
		int mid1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
		int mid2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
		if(mid1 < mid2)
			return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
		else 
			return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
	}
	
}
