package zfj.swordToOffer;

public class Lists {
	
	//剑指offer，寻找链表中的环起点
	public ListNode EntryNodeOfRing(ListNode head)
	{
		ListNode fast = head, slow = head;
		while(fast != null && fast.next != null)
		{
			fast = fast.next.next;
			slow = slow.next;
		}
		if(fast == null || fast.next == null)
			return null;
		fast = head;
		while(fast != slow)
		{
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}

	//(leetcode 160) Intersection of Two Linked Lists
	//剑指offer，寻找两个链表的公共节点
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode la = headA, lb = headB;
        while(la != lb)
        {
        	la = la == null ? headB : la.next;
        	lb = lb == null ? headA : lb.next;
        }
        return la;
    }
	//(leetcode 61) Rotate List
	public ListNode rotateRight(ListNode head, int k) {
		if(head == null || head.next == null)
            return head;
        int n = 0;
        ListNode l = head;
        while(l != null)
        {
        	l = l.next;
        	n ++;
        }
        k = n - k % n;
        if(k == 0 || k == n)
        	return head;
        l = head;
        while(k > 1)
        {
        	k --;
        	l = l.next;
        }
        ListNode newHead = l.next;
        l.next = null;
        l = newHead;
        while(l.next != null)
        	l = l.next;
        l.next = head;
        return newHead;
    }
	//(leetcode 725) Split Linked List in Parts
	public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] ls = new ListNode[k];
        if(root == null)
            return ls;
        int n = 0;
        ListNode l = root;
        while(l != null)
        {
            n ++;
            l = l.next;
        }
        int d = n / k;
        int mod = n % k;
        l = root;
        for(int i = 0; i < k; i ++)
        {
        	if(l == null)
        		break;
            int len = i < mod ? d + 1 : d;
            ls[i] = l;
            while(len > 1)
            {
                len --;
                l = l.next;
            }
            ListNode tmp = l.next;
            l.next = null;
            l = tmp;
        }
        return ls;
    }
	public int getLengthOfLists(ListNode head)
	{
		if(head == null)
			return 0;
		ListNode fast, slow;
		fast = slow = head;
		int n = 0;
		while(fast.next != null && fast.next.next != null)
		{
			n ++;
			fast = fast.next.next;
			slow = slow.next;
		}
		return fast.next == null ? 2 * n + 1 : 2 * (n + 1);
	}
	public ListNode reverseList(ListNode head)
    {
        if(head == null || head.next == null)
            return head;
        ListNode newHead = new ListNode(0);
        while(head != null)
        {
        	ListNode next = newHead.next;
            ListNode next2 = head.next;
            newHead.next = head;
            head.next = next;
            head = next2;
        }
        return newHead.next;
    }
	//(leetcode 445) add two numbers
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rl1 = reverseList(l1);
        ListNode rl2 = reverseList(l2);
        int c = 0;
        ListNode l = new ListNode(0);
        ListNode res = l;
        while(rl1 != null && rl2 != null)
        {
            int x = rl1.val + rl2.val + c;
            l.next = new ListNode(x % 10);
            c = x / 10;
            rl1 = rl1.next;
            rl2 = rl2.next;
            l = l.next;
        }
        while(rl1 != null)
        {
            int x = rl1.val + c;
            l.next = new ListNode(x % 10);
            c = x / 10;
            rl1 = rl1.next;
            l = l.next;
        }
        while(rl2 != null)
        {
            int x = rl2.val + c;
            l.next = new ListNode(x % 10);
            c = x / 10;
            rl2 = rl2.next;
            l = l.next;
        }
        if(c == 1)
            l.next = new ListNode(1);
        return reverseList(res.next);
    }
	public int addTwoLists(ListNode l1, ListNode l2, int k)
    {
        if(l1.next == null)
        {
            int x = l1.val + l2.val;
            l1.val = x % 10;
            return x / 10;
        }
        int x = l1.val;
        if(k > 0)
        {
            x += addTwoLists(l1.next, l2, k - 1);
            l1.val = x % 10;
            return x / 10;
        }
        x += l2.val + addTwoLists(l1.next, l2.next, 0);
        l1.val = x % 10;
        return x / 10;
    }
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		if(l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        int n1 = getLengthOfLists(l1), n2 = getLengthOfLists(l2);
        ListNode r1 = n1 > n2 ? l1 : l2;
        ListNode r2 = n1 > n2 ? l2 : l1;
        int k = Math.abs(n1 - n2);
        int c = addTwoLists(r1, r2, k);
        if(c == 1)
        {
            ListNode newHead = new ListNode(1);
            newHead.next = r1;
            return newHead;
        }
        return r1;
	}
	public static void main(String[] args)
	{
		Lists list = new Lists();
		ListNode l1 = new ListNode(8);
		ListNode l2 = new ListNode(9);
		ListNode l3 = new ListNode(9);
		ListNode r1 = new ListNode(2);
		l1.next = l2;
		l2.next = l3;
		list.addTwoNumbers2(l1, r1);
//		ListNode l1 = list.new ListNode(1);
//		ListNode l2 = list.new ListNode(2);
//		ListNode l3 = list.new ListNode(3);
//		ListNode l4 = list.new ListNode(4);
//		ListNode l5 = list.new ListNode(5);
//		ListNode l6 = list.new ListNode(6);
//		ListNode l7 = list.new ListNode(7);
//		ListNode l8 = list.new ListNode(8);
//		ListNode l9 = list.new ListNode(9);
//		ListNode l10 = list.new ListNode(10);
//		l1.next = l2;
//		l2.next = l3;
//		l3.next = l4;
//		l4.next = l5;
//		l5.next = l6;
//		l6.next = l7;
//		l7.next = l8;
//		l8.next = l9;
//		l9.next = l10;
//		ListNode newhead = list.reverseList(l1);
//		list.splitListToParts(l1, 3);
//		list.rotateRight(l1, 2);
	}
}
