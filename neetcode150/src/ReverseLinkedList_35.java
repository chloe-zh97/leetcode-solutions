
public class ReverseLinkedList_35 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(0);
        ListNode a2 = new ListNode(1);
        ListNode a3 = new ListNode(2);
        ListNode a4 = new ListNode(3);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        ListNode a = reverseList_4(a1);
        while(a!=null) {
            System.out.print(a.val + " ");
            a = a.next;
        }
        System.out.println();

    }

    /**
     * Method 1: 迭代法
     * */
    public static ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode p = head;
        while(p != null) {
            ListNode nxt = p.next;
            p.next = dummy.next;
            dummy.next = p;
            p = nxt;
        }
        return dummy.next;
    }

    /**
     * Method 2: 递归
     * */
    public static ListNode reverseList_2(ListNode head) {
        if(head  == null || head.next == null) return head;

        ListNode head2 = reverseList_2(head.next);
        head.next = null;

        // 1, 3,2
        // p 挪到已翻转的尾部
        ListNode p = head2;
        while(p.next != null) {
            p = p.next;
        }
        p.next = head;

        return head2;
    }

    /**
     * 优化
     * */
    public static ListNode reverseList_3(ListNode head) {
        if(head == null) return head;
        ListNode newHead = head;

        if(head.next != null) {
            newHead = reverseList_3(head.next);
            // 1->2<-3
            head.next.next = head;
            head.next = null;
        }
        return newHead;
    }

    /**
     * 优化：迭代，pre, cur 双指针
     * */
    public static ListNode reverseList_4(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while(cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
