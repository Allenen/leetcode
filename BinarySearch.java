package zfj.swordToOffer;


public class BinarySearch {
	//���������������г��ֵĴ���
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
	//���ö��ַ�����target�����������г��ֵĴ���
	//˼�����ö��ֲ����ҵ�target�״γ��ֵĵط�
	//��ôtarget���ֵĴ����͵��ڣ�target+1���״γ��ֵ�λ�� - target�״γ��ֵ�λ��
	//��Ҫע����ǣ����targetû���������У����������1������ֵ�������鳤��2�����ص�λ�ò�����target
	public int getNumOfTarget(int a[], int target)
	{
		int n = a.length;
		int l = binarySearch(a, target);
		int r = binarySearch(a, target + 1);
		return (l == n || a[l] != target) ? 0 : r - l;
	}
	
	//(leetcode 4) Median of two sorted Arrays
	//��������������ϲ������λ��
	//Ҫ��ʱ�临�Ӷ�Ϊlog(m + n)
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
