package zfj.swordToOffer;

import zfj.sort.QuickSort.ListNode;

//归并排序，时间复杂度O(nlogn)，空间复杂度O(1)
//对于数组而言归并排序的时间复杂度是O(n)
public class SortList {
	public class ListNode {
		int val;
      	ListNode next;
      	ListNode(int x) { val = x; }
	}
	public ListNode merge(ListNode l, ListNode r)
    {
        ListNode p = new ListNode(0), h = p;
        while(l != null && r != null)
        {
            if(l.val > r.val)
            {
                p.next = r;
                r = r.next;
            }
            else
            {
                p.next = l;
                l = l.next;
            }
            p = p.next;
        }
        p.next = l == null ? r : l;
        return h.next;
    }
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode fast, slow;
        fast = slow = head;
        while(fast.next != null && fast.next.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null;
        return merge(sortList(head), sortList(fast));
    }
    public ListNode createLists()
    {
    	ListNode l1 = new ListNode(1);
    	ListNode l2 = new ListNode(1);
    	ListNode l3 = new ListNode(1);
    	ListNode l4 = new ListNode(1);
    	l1.next = l2;
    	l2.next = l3;
    	l3.next = l4;
    	return l1;
    }
    public ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead == null)
            return null;
        ListNode head = new ListNode(pHead.val - 1);
        head.next = pHead;
        ListNode l = head, pre = head;
        while(l.next != null)
        {
            if(l.next.val == l.val)
            {
                int same = l.val;
                while(l != null && l.val == same)
                    l = l.next;
                pre.next = l;
            }
            else
            {
                pre = l;
                l = l.next;
            }
        }
        return head.next;
    }
    
    //链表的快速排序
    public ListNode partitionList(ListNode start, ListNode end)
	{
		ListNode p1 = start, p2 = start.next;
		while(p2 != end)
		{
			if(p2.val < start.val)
			{
				p1 = p1.next;
				int tmp = p1.val;
				p1.val = p2.val;
				p2.val = tmp;
			}
			p2 = p2.next;
		}
		int tmp = start.val;
		start.val = p1.val;
		p1.val = tmp;
		return p1;
	}
	public void quickSortList(ListNode start, ListNode end)
	{
		if(start == null || start == end)
			return ;
		ListNode p = partitionList(start, end);
		
		quickSortList(start, p);
		quickSortList(p.next, end);
	}
    public static void main(String[] args)
    {
    	SortList sl = new SortList();
    	ListNode head = sl.createLists();
    	sl.deleteDuplication(head);
    }
}
