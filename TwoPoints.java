package zfj.swordToOffer;

public class TwoPoints {
	//(leetcode 11) Container with Most water
	public int maxArea(int[] heights)
	{
		int n = heights.length;
		int res = 0;
		int left = 0, right = n - 1;
		while(left < right)
		{
			if(heights[left] >= heights[right])
			{
				res = Math.max(res, (right - left) * heights[right]);
				right --;
			}
			else
			{
				res = Math.max(res, (right - left) * heights[left]);
				left ++;
			}
		}
		return res;
	}
	
	//(leetcode 42) Trapping Rain Water
	//当左边比右边高，则左边低于最左端的位置都能被填满
	//当右边比左边高，则右边低于最右端的位置都能被填满
	public int trap(int heights[])
	{
		int n = heights.length;
		int res = 0;
        int left = 0, right = n - 1;
        while(left < right)
        {
            if(heights[left] <= heights[right])
            {
                int l = left;
                while(l < right && heights[l] <= heights[left])
                    res += heights[left] - heights[l ++];
                left = l;
            }
            else
            {
                int r = right;
                while(left < r && heights[r] <= heights[right])
                    res += heights[right] - heights[r --];
                right = r;
            }
        }
		return res;
	}
	//(leetcode 407) Trapping Rain Water in 3D
	public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int res = 0;
        return res;
    }
	
	//(leetcode 755) Pour water
	public int[] pourWater(int heights[], int v, int k)
	{
		int left = k, right = k;
		int n = heights.length;
		while(left - 1 >= 0 && heights[left - 1] <= heights[left])
			left --;
		while(right + 1 < n && heights[right + 1] <= heights[right])
			right ++;
		for(int i = 0; i < v; i ++)
		{
			//当左边>=heights[k],pourRight
			if(heights[left] == heights[k])
			{
				//右边>=heights[k],pourIndexK
				if(heights[right] == heights[k])
				{
					heights[k] ++;
					while(left - 1 >= 0 && heights[left - 1] <= heights[left])
						left --;
					while(right + 1 < n && heights[right + 1] <= heights[right])
						right ++;
				}
				else
				{
					while(right > k && heights[right] == heights[right - 1])
						right --;
					heights[right] ++;
					while(right + 1 < n && heights[right + 1] <= heights[right])
						right ++;
				}
			}
			else
			{
				while(left < k && heights[left] == heights[left + 1])
					left ++;
				heights[left] ++;
				while(left - 1 >= 0 && heights[left - 1] <= heights[left])
					left --;
			}
				
		}
		return heights;
	}
	
	public static void main(String[] args)
	{
		TwoPoints tp = new TwoPoints();
		int heightMap[][] = {
				{12,13,1,12},
				{13,4,13,12},
				{13,8,10,12},
				{12,13,12,12},
				{13,13,13,13}
		};
		tp.trapRainWater(heightMap);
	}
}
