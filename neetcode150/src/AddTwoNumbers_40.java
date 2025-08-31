public class AddTwoNumbers_40 {
    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
    public static void main(String[] args) {
        ListNode a1 = new ListNode(9);
        ListNode a2 = new ListNode(9);
        ListNode a3 = new ListNode(9);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(9);
        ListNode b2 = new ListNode(5);
        ListNode b3 = new ListNode(6);
//        b1.next = b2;
//        b2.next = b3;

        a1 = addTwoNumbers_3(a1, b1);
        while(a1!=null) {
            System.out.print(a1.val+" ");
            a1 = a1.next;
        }
        System.out.println();
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        int carry = 0;
        while(l1 != null && l2 != null) {
            int tmp = carry + l1.val + l2.val;
            int v = tmp % 10;
            carry = tmp / 10;

            // 修改l1的值
            l1.val = v;
            p.next = l1;

            l1 = l1.next;
            l2 = l2.next;

            p = p.next;
            p.next = null;
        }

        while(l1 != null || l2 != null) {
            ListNode l = l1 == null ? l2 : l1;

            int tmp = carry + l.val;
            int v = tmp % 10;
            carry = tmp/10;
            l.val = v;

            p.next = l;
            p = p.next;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }

//        while(l1!=null) {
//            int tmp = carry + l1.val;
//            int v = tmp % 10;
//            carry = tmp/10;
//            l1.val = v;
//            // 把l1链接到p上
//            p.next = l1;
//            l1 = l1.next;
//            p = p.next;
//        }
//
//        while(l2!=null) {
//            int tmp = carry + l2.val;
//            int v = tmp % 10;
//            carry = tmp/10;
//            l2.val = v;
//
//            p.next = l2;
//            l2 = l2.next;
//            p = p.next;
//        }
        // 最后处理一次进位
        if(carry>0) p.next = new ListNode(1);

        return dummy.next;
    }


    public static ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    /**
     * ListNode l1 和 l2 相加，返回相加后的节点
     * */
    private static ListNode add(ListNode l1, ListNode l2, int carry) {
        if(l1 == null && l2 == null && carry == 0) return null;

        int v1 = l1 == null ? 0 : l1.val;
        int v2 = l2 == null ? 0 : l2.val;

        int v = (v1 + v2 + carry) % 10;
        int newCarry = (v1 + v2 + carry) / 10;

        // l1.next 和 l2.next 相加
        ListNode nxt = add(
                l1 == null ? l1: l1.next,
                l2 == null ? l2: l2.next,
                newCarry
        );

        return new ListNode(v, nxt);
    }

    public static ListNode addTwoNumbers_3(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        int carry = 0;

        while(l1 != null || l2 != null || carry!=0) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int v = (v1 + v2 + carry) % 10;
            carry = (v1 + v2 + carry) / 10;

            p.next = new ListNode(v);
            p = p.next;

            l1 = l1 == null ? l1: l1.next;
            l2 = l2 == null ? l2: l2.next;
        }
        return dummy.next;
    }
}
