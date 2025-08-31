public class LinkedListCycleDetection_37 {
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
        a1.next = a1;
//        a2.next = a3;
//        a3.next = a4;
//        a4.next = a2;
        System.out.println(hasCycle(a1));
    }

    /**
     * Method 1: 快慢指针，是否有环存在
     * */
    public static boolean hasCycle(ListNode head) {
        if(head == null) return false;

        // 初始位置在同一个地方
        ListNode slow = head, fast = head;
        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
}
