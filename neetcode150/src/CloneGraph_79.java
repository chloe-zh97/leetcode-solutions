import java.util.*;

public class CloneGraph_79 {
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {
        Node a1 = new Node(1);
        Node b1 = new Node(2);
        Node c1 = new Node(3);
        a1.neighbors = Arrays.asList(b1);
        b1.neighbors = Arrays.asList(a1,c1);
    }

    /**
     * Method 1: BFS
     * */
    public static Node cloneGraph(Node node) {
        if(node == null) return null;
        // 构造新的根节点
        Map<Node, Node> oldToNew = new HashMap<>();

        Node newNode = new Node(node.val);
        oldToNew.put(node, newNode);

        // 构造队列
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(node);

        while(!deque.isEmpty()) {
            Node p = deque.poll();

            // 拿到 p 的 neighbors, 构造这些新的邻居节点
            for(Node nei: p.neighbors) {
                if(!oldToNew.containsKey(nei)) {
                    oldToNew.put(nei, new Node(nei.val));
                    // 图有可能走回头路，以及创建过新的节点以前一定被加入到了队列中
                    // 避免重复加入
                    deque.offer(nei);
                }

                oldToNew.get(p).neighbors.add(oldToNew.get(nei));
            }
        }
        return oldToNew.get(node);
    }


    /**
     * Method 2: DFS
     * */
    private static Map<Node, Node> oldToNew = new HashMap<>();

    public static Node cloneGraph_2(Node node) {
        if(node == null) return null;

        if(oldToNew.containsKey(node)) return oldToNew.get(node);

        Node newNode = new Node(node.val);
        oldToNew.put(node, newNode);

        for(Node nei: node.neighbors) {
            Node copyNei = cloneGraph_2(nei);
            oldToNew.get(node).neighbors.add(copyNei);
        }
        return newNode;
    }



}
