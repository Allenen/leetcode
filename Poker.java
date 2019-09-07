package zfj.swordToOffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Poker {
	//��������Ƿ񹹳�˳�ӣ�����0�����滻���κ���
	//����˼·�����ȼ�������г���0֮�⣬�Ƿ����ظ�����,���򷵻�false
	//������鹹��˳�ӵĻ�����С��min����0֮�⣩�������max�Ĳmax - min + 1 <= n
	//ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(1)
	public boolean isContinuous(int [] numbers) {
        int n = numbers.length;
        if(n < 5)
            return false;
        int a[] = new int[14];
        int max = 0, min = 14;
        for(int i = 0; i < n; i ++)
        {
            if(numbers[i] == 0)
                continue;
            a[numbers[i]] ++;
            max = Math.max(max, numbers[i]);
            min = Math.min(min, numbers[i]);
        }
        for(int i = 1; i < 14; i ++)
            if(a[i] > 1)
                return false;
        return max - min + 1 <= n;
    }
	//��������˿��ƵĻ�,ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(n)
	public boolean isContinuous2(int [] numbers) {
        int n = numbers.length;
        if(n < 5)
            return false;
        Map<Integer, Integer> mp = new HashMap<>();
        int max = 0, min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i ++)
        {
        	if(numbers[i] == 0)
        		continue;
        	if(mp.containsKey(numbers[i]))
        		return false;
        	mp.put(numbers[i], 1);
        	max = Math.max(max, numbers[i]);
            min = Math.min(min, numbers[i]);
        }
        return max - min + 1 <= n;
    }
	//�Ƚ���������ʱ�临�Ӷ�ΪO(nlogn)���ռ临�Ӷ�ΪO(1)
	public boolean isContinuous3(int [] numbers) {
        int n = numbers.length;
        if(n < 5)
            return false;
        Arrays.sort(numbers);
        int numOfZero = 0;
        int i = 0;
        while(numbers[i ++] == 0)
        	numOfZero ++;
        for(++ i; i < n; i ++)
        {
            int d = numbers[i] - numbers[i - 1];
            if(d == 0)
                return false;
            numOfZero -= d - 1;
        }
        return numOfZero >= 0;
    }
	public static void main(String[] args)
	{
		Poker poker = new Poker();
		int a[] = {0,3,1,6,4};
		poker.isContinuous3(a);
	}
}
