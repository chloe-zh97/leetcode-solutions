public class RemoveNodeFromEndofLinkedList_38 {

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;

        int n = 2;
        a1 = removeNthFromEnd_2(a1, n);
        while(a1 != null) {
            System.out.print(a1.val+" ");
            a1 = a1.next;
        }
    }

    /**
     * Method 1: 得到listNode 长度
     * */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int len = 0;
        ListNode p = dummy;
        while(p.next != null) {
            len++;
            p = p.next;
        }

        ListNode pre = dummy;
        p = head;
        for(int i=0;i<len-n;i++) {
            p = p.next;
            pre = pre.next;
        }

        pre.next = p.next;
        return dummy.next;
    }

    /**
     * Method 2: two pointers
     * 两个指针，一个left, 一个 right, right 先走 n 个节点
     * 然后让 left 开始走，直到 right 走到终点，left 就停在了 remove 前的一个节点
     * */
    public static ListNode removeNthFromEnd_2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode right = head;
        while(n > 0) {
            right = right.next;
            n--;
        }

        ListNode left = dummy;
        while(right != null) {
            left = left.next;
            right = right.next;
        }

        if(left.next != null) left.next = left.next.next;

        return dummy.next;
    }
}
