public class MergeTwoSortedLinkedLists_36 {
    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }}
    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(4);
        a1.next = a2; a2.next = a3;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(5);
        b1.next = b2; b2.next = b3;

        ListNode p = mergeTwoLists_2(a1, b1);
        while(p!=null) {
            System.out.print(p.val+" ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Method 1: two pointers
     * */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(-1);
        ListNode p1 = list1, p2 = list2;

        // 新链表的扫描指针
        ListNode p = dummy;

        while(p1 != null && p2 != null) {
            ListNode nxt;
            if(p1.val <= p2.val) {
                nxt = p1.next;
                p1.next = null;
                p.next = p1;
                p1 = nxt;
            } else {
                nxt = p2.next;
                p2.next = null;

                p.next = p2;
                p2 = nxt;
            }
            p = p.next;
        }

        if(p1 != null) p.next = p1;
        if(p2 != null) p.next = p2;

        return dummy.next;
    }

    /**
     * Method 2: 递归
     * */
    public static ListNode mergeTwoLists_2(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        if(list1.val <= list2.val) {
            list1.next = mergeTwoLists_2(list1.next, list2);
        } else {
            list2.next = mergeTwoLists_2(list1, list2.next);
        }
        return list1.val <= list2.val ? list1 : list2;
    }
}
