package zfj.swordToOffer;

import java.util.ArrayList;
import java.util.List;

public class FindDuplcateInArray {
	//(leetcode 442) find all dulicaptes in array
	public List<Integer> findDuplicates(int nums[])
	{
		int n = nums.length;
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < n; i ++)
		{
			while(nums[i] != i + 1 && nums[i] != 0)
			{
				int tmp = nums[i];
				if(nums[tmp - 1] == tmp)
				{
					list.add(tmp);
					nums[i] = 0;
					break;
				}
				nums[i] = nums[tmp - 1];
				nums[tmp - 1] = tmp;
			}
		}
		return list;
	}
	//(leetcode 775) gobal and local inversions
	public boolean isIdealPermutation(int[] A) {
        int n = A.length;
        if(n == 0)
            return false;
        int min[] = new int[n];
        min[n - 1] = A[n - 1];
        for(int i = n - 2; i >= 0; i --)
            min[i] = Math.min(A[i], min[i + 1]);
        for(int i = 0; i < n - 1; i ++)
        {
            if(i + 2 < n && A[i] > min[i + 2])
            	return false;
        }
        return true;
    }
	//(leetcode 775) gobal and local inversions
	public boolean isIdealPermutation1(int[] A) {
		int n = A.length;
		if(n <= 2) return true;
		int max = -1;
		for(int i = 0; i < n - 2; i ++)
		{
			max = Math.max(max, A[i]);
			if(max > A[i + 2])
				return false;
		}
		return true;
	}
	public static void main(String[] args)
	{
		FindDuplcateInArray fd = new FindDuplcateInArray();
		int A[] = {2,0,1};
		System.out.println(fd.isIdealPermutation(A));
	}
}
