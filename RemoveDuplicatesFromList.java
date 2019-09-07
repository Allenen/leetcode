package zfj.swordToOffer;

import zfj.swordToOffer.SortList.ListNode;

public class RemoveDuplicatesFromList {
	public class ListNode {
		int val;
      	ListNode next;
      	ListNode(int x) { val = x; }
	}
	
	//Given a sorted linked list,
	//delete all duplicates such that each element appear only once.
	public ListNode deleteDuplicatesFromSortedList(ListNode head) 
	{
		if(head == null || head.next == null)
			return head;
		ListNode cur = head.next, pre = head;
		while(cur != null)
		{
			if(cur.val != pre.val)
			{
				pre.next = cur;
				pre = cur;
			}
			cur = cur.next;
		}
		pre.next = null;
		return head;
	}
	//Given a sorted linked list,
	//delete all nodes that have duplicate numbers, 
	//leaving only distinct numbers from the original list.
	public ListNode deleteDuplicatesFromSortedList2(ListNode head) 
	{
		if(head == null || head.next == null)
            return head;
        ListNode newHead = new ListNode(0), p = newHead;
        ListNode cur, pre;
        cur = pre = head;
        while(cur != null)
        {
            if(cur.val != pre.val)
            {
                if(pre.next == null || pre.next.val != pre.val)
                {
                    p.next = pre;
                    p = p.next;
                }
                pre = cur;
            }
            cur = cur.next;
        }
        p.next = pre.next == null ? pre : null;
        return newHead.next;
	}
	//Remove all elements from a linked list of integers that have value val.
	public ListNode removeElements(ListNode head, int val) 
	{
		if(head == null)
			return null;
		ListNode l = new ListNode(0), p = l;
		while(head != null)
		{
			if(head.val != val)
			{
				p.next = head;
				p = p.next;
			}
			head = head.next;
		}
		p.next = null;
		return l.next;
	}
	public ListNode createLists()
    {
    	ListNode l1 = new ListNode(4);
    	ListNode l2 = new ListNode(2);
    	ListNode l3 = new ListNode(1);
    	ListNode l4 = new ListNode(3);
    	l1.next = l2;
    	l2.next = l3;
    	l3.next = l4;
    	return l1;
    }
	public static void main(String[] args)
	{
		RemoveDuplicatesFromList rdfl = new RemoveDuplicatesFromList();
	}
}
