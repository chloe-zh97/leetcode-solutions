import java.util.HashMap;

public class CopyLinkedListwithRandomPointer_39 {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        Node a1 = new Node(3);

    }

    /**
     * Method 1: HashMap, two pass
     * O(n) / O(n)
     * */
    public static Node copyRandomList(Node head) {
        // old node, new node
        HashMap<Node, Node> map = new HashMap<>();

        Node p = head;
        while(p!=null) {
            // copy 的节点默认 next 和 random 都指向 null
            Node copy = new Node(p.val);
            // create mapping relationship
            map.put(p, copy);
            p = p.next;
        }

        p = head;
        while(p != null) {
            Node copy = map.get(p);
            copy.next = map.get(p.next);
            copy.random = map.get(p.random);
            p = p.next;
        }

        return map.get(head);
    }


    /**
     * 优化，一次遍历
     * */
    public static Node copyRandomList_2(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node p = head;

        while(p!=null) {
            if(!map.containsKey(p)) {
                // 如果 p 还没被 copy
                map.put(p, new Node(p.val));
            }

            if(p.next != null && !map.containsKey(p.next)) {
                map.put(p.next, new Node(p.next.val));
            }
            map.get(p).next = map.get(p.next);

            if(p.random != null && !map.containsKey(p.random)) {
                map.put(p.random, new Node(p.random.val));
            }
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }


    /**
     * 优化，不使用 hashmap
     * 将copy先复制在原链表的后面
     * */
    public static Node copyRandomList_3(Node head) {
        if(head == null) return head;
        Node l1 = head;
        while(l1!=null) {
            Node l2 = new Node(l1.val);
            l2.next = l1.next;
            l1.next = l2;
            l1 = l2.next;
        }

        // 1,1,2,2,3,3
        // 构造 random 指针
        l1 = head;
        while(l1!=null) {
            if(l1.random != null) {
                // copy 的 random
                l1.next.random = l1.random.next;
            }
            l1 = l1.next.next;
        }

        // 断链
        l1 = head;
        Node newHead = head.next, l2 = newHead;

        while(l1 != null) {
            l1.next = l2.next;
            if(l1.next != null) l2.next = l1.next.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        return newHead;
    }

}
