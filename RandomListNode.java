package zfj.swordToOffer;

public class RandomListNode {
	
	public int label;
	public RandomListNode next = null;
	public RandomListNode random = null;
	RandomListNode(int label) {
        this.label = label;
    }

	public RandomListNode Clone(RandomListNode head)
    {
        if(head == null)
            return null;
        RandomListNode newHead = new RandomListNode(-1), p = head;
        newHead.next = head;
        
        while(p != null)
        {
            RandomListNode node = new RandomListNode(p.label);
            RandomListNode next = p.next;
            p.next = node;
            node.next = next;
            p = next;
        }
        p = head;
        while(p != null)
        {
            if(p.random != null)
                p.next.random = p.random.next;
            p = p.next.next;
        }
        p = newHead;
        while(p.next != null)
        {
        	RandomListNode old = p.next;
            p.next = old.next;
            old.next = null;
            p = p.next;
        }
        RandomListNode res = newHead.next;
        newHead.next = null;
        return res;
    }
	
	public RandomListNode create() {
		RandomListNode r1 = new RandomListNode(1);
		RandomListNode r2 = new RandomListNode(2);
		RandomListNode r3 = new RandomListNode(3);
		RandomListNode r4 = new RandomListNode(4);
		r1.next = r2;
		r2.next = r3;
		r3.next = r4;
		r2.random = r1;
		r3.random = r4;
		return r1;
	}
	
	
	public static void main(String[] args)
	{
		RandomListNode test = new RandomListNode(0);
		RandomListNode head = test.create();
		test.Clone(head);
	}
}
