import java.util.PriorityQueue;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class MergekSortedLists_23 {
    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(5);

        a1.next = a2; a2.next = a3;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(4);
        b1.next = b2; b2.next = b3;

        ListNode c1 = new ListNode(2);
        ListNode c2 = new ListNode(6);
        c1.next = c2;

        ListNode[] lists = {a1, b1, c1};
        a1 = mergeKLists_2(lists);

        while(a1!=null) {
            System.out.print(a1.val+" ");
            a1 = a1.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        for(int i=0;i<lists.length;i++) {
            // get head
            ListNode p = lists[i];
            while(p != null) {
                pq.offer(p);
                // get next node
                ListNode q = p.next;
                p.next = null;
                p = q;
            }
        }

        ListNode head = new ListNode(0);
        ListNode p = head;

        while(!pq.isEmpty()) {
            p.next = pq.poll();
            p = p.next;
        }
        return head.next;
    }

    /**
     * Method 2: 最小堆,将头节点加入优先队列
     * */
    public static ListNode mergeKLists_2(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        // 头节点加入
        for(ListNode a: lists) {
            if(a != null) pq.offer(a);
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while(!pq.isEmpty()) {
            ListNode p = pq.poll();
            if(p.next != null) pq.offer(p.next);

            cur.next = p;
            cur = cur.next;
            p.next = null;
        }
        return dummy.next;
    }
}
