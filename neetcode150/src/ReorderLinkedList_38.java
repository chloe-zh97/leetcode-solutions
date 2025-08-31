import java.util.ArrayDeque;
import java.util.Deque;

public class ReorderLinkedList_38 {

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(6);
        ListNode a4 = new ListNode(8);
//        ListNode a5 = new ListNode(10);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
//        a4.next = a5;
        reorderList_2(a1);
        while(a1 != null) {
            System.out.print(a1.val+" ");
            a1 = a1.next;
        }
        System.out.println();
    }

    /**
     * Method 1: Deque
     * */
    public static void reorderList(ListNode head) {
        ListNode p = head;
        Deque<ListNode> deque = new ArrayDeque<>();
        // 将 head 加入 deque 中
        while(p!=null) {
            deque.offerLast(p);
            p = p.next;
        }

        boolean fromFirst = true;
        p = head;


        while(!deque.isEmpty()) {
            // 出来的节点马上断链
            p.next = fromFirst ? deque.pollFirst() : deque.pollLast();
            p = p.next;
            p.next = null;

            // 改变方向
            fromFirst = !fromFirst;
        }
    }

    /**
     * Method 2: 分割成一半正序，一半逆序，然后重新插入
     * 0,1,2,3,4,5,6
     * */
    public static void reorderList_2(ListNode head) {
        // 分割一半list
        ListNode slow = head, fast = head.next;
        while(fast!=null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 断链
        ListNode newHead = slow.next;
        slow.next = null;
        newHead = reverse(newHead);

        // merge head 和 newHead
        ListNode p = head;
        ListNode q = newHead;

        while(q!=null) {
            ListNode nxt = q.next;
            q.next = p.next;
            p.next = q;

            q = nxt;
            p = p.next.next;
        }
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while(cur!=null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
