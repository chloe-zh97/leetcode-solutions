import java.util.PriorityQueue;

public class MergeKSortedLinkedLists_43 {

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(4);
        a1.next = a2;
        a2.next = a3;


        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(5);
        b1.next = b2;
        b2.next = b3;

        ListNode c1 = new ListNode(3);
        ListNode c2 = new ListNode(6);
        c1.next = c2;

        ListNode[] lists = {a1,b1,c1};
        ListNode a = mergeKLists_4(lists);
        while(a!=null) {
            System.out.print(a.val + " ");
            a = a.next;
        }
        System.out.println();
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if(n == 0) return null;
        if(n == 1) return lists[0];

        ListNode head = mergeTwoLists(lists[0], lists[1]);

        for(int i=2;i<n;i++)
            head = mergeTwoLists(head, lists[i]);

        return head;
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        while(l1!=null || l2!=null) {
            ListNode l;
            if(l1 == null || l2 == null) l = l1 == null ? l2 : l1;
            else l = l1.val <= l2.val ? l1: l2;

            p.next = l;
            p = p.next;

            l1 = l == l1 ? l1.next : l1;
            l2 = l == l2 ? l2.next : l2;
        }

        return dummy.next;
    }

    /**
     * Method 2: Priority Queue
     * O(n) / O(n)
     * */
    public static ListNode mergeKLists_2(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a,b) -> a.val - b.val);

        for(int i=0;i<lists.length;i++) {
            ListNode a = lists[i];
            while(a!=null) {
                queue.offer(a);
                a = a.next;
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while(!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            p.next = null;
        }
        return dummy.next;
    }

    /**
     * Method 3: 迭代，两两合并
     * */
    public static ListNode mergeKLists_3(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        int n = lists.length;
        // step = 1: 0,1,2,3
        // (0,1) (2,3)
        for(int step=1;step<n;step*=2) {
            for(int i=0;i+step<n;i+=2*step) {
                // 每组的开头应该是 i+step+step
                lists[i] = mergeTwoLists_2(lists[i], lists[i+step]);
            }
        }

        return lists[0];
    }

    private static ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if(l1 != null) p.next = l1;
        if(l2 != null) p.next = l2;
        return dummy.next;
    }

    /**
     * Method 4: 递归
     * */
    public static ListNode mergeKLists_4(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;

        return mergeInRange(lists, 0, lists.length-1);
    }

    /**
     * 合并 lists[left...right]
     * */
    private static ListNode mergeInRange(ListNode[] lists, int left, int right) {
        if(right < left) return null;
        if(left == right) return lists[left];

        int mid = left + right >> 1;
        ListNode l = mergeInRange(lists, left, mid);
        ListNode r = mergeInRange(lists, mid+1, right);
        return mergeTwoLists_2(l, r);
    }

}
