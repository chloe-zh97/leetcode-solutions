public class ReverseNodesinKGroup_44 {
    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        a1 = reverseKGroup_2(a1, 3);
        while(a1 != null) {
            System.out.print(a1.val+" ");
            a1 = a1.next;
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

    /**
     * Method 1: 0, 1,2, 3,4, 5,6
     * 0, 2,1, 3,4,5,6
     * */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while(true) {
            ListNode kthNode = getKthNode(groupPrev, k);
            if(kthNode == null) break;

            ListNode kthNext = kthNode.next;

            // reverse 的范围是 [groupPrev.next, kthNode]
            ListNode prev = kthNext;
            ListNode cur = groupPrev.next;

            printList(dummy.next);

            while(cur!=kthNext) {
                ListNode nxt = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nxt;
            }

            // groupPrev.next 是原来 group 的第一个元素
            // kth 是原来group的最后一个元素，现在想要它变成第一个
            ListNode originalFirstElement = groupPrev.next;
            // 更新这组的第一个元素
            groupPrev.next = kthNode;
            // 原来的第一个元素现在要变成这组的最后一个元素
            groupPrev = originalFirstElement;
        }
        return dummy.next;
    }

    /**
     * 获取 cur 这条链上的第 k 个节点
     * 1,2,3
     * */
    private static ListNode getKthNode(ListNode cur, int k) {
        while(cur != null && k > 0) {
            cur = cur.next;
            k--;
        }
        return cur;
    }

    private static void printList(ListNode a) {
        while(a!=null) {
            System.out.print(a.val+" ");
            a = a.next;
        }
        System.out.println();
    }

    /**
     * Method 2: 递归
     * return 条件：head 的长度小于 k
     * */
    public static ListNode reverseKGroup_2(ListNode head, int k) {
        ListNode p = head;
        for(int i=0;i<k;i++) {
            if(p == null) return head;
            p = p.next;
        }
        // 1，2，3，4
        // reverse 前 k 个节点
        ListNode pre = null, cur = head;
        for(int i=0;i<k;i++) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }

        // 原来的 head 是翻转过后的最后一个节点
        head.next = reverseKGroup_2(cur, k);
        // pre 是每组第一个节点
        return pre;
    }

}
