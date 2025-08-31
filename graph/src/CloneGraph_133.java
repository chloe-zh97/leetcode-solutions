import java.util.*;

public class CloneGraph_133 {

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

    }

    /**
     * Method 1: BFS
     * */
    public static Node cloneGraph(Node node) {
        if(node == null) return null;

        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(node);
        Node newNode = new Node(node.val);

        Map<Node, Node> oldToNew = new HashMap<>();
        oldToNew.put(node, newNode);

        while(!deque.isEmpty()) {
            Node p = deque.poll();
            // 创建邻居节点
            for(Node nei: p.neighbors) {
                if(!oldToNew.containsKey(nei)) {
                    oldToNew.put(nei, new Node(nei.val));
                    deque.offer(nei);
                }
                oldToNew.get(p).neighbors.add(oldToNew.get(nei));
            }
        }
        // create a newNode
        return oldToNew.get(node);
    }

    /**
     * Method 2: DFS
     */
    private static Map<Node, Node> oldToNew = new HashMap<>();

    public static Node cloneGraph_2(Node node) {
        if(node == null) return null;

        if(oldToNew.containsKey(node)) return oldToNew.get(node);

        // 没有创建过对应的 node, 创建一个新的
        Node newNode = new Node(node.val);
        oldToNew.put(node, newNode);

        // 处理 neighbors
        for(Node nei: node.neighbors) {
            Node newNei = cloneGraph_2(nei);
            // 加入到邻居列表
            newNode.neighbors.add(newNei);
        }
        return oldToNew.get(node);
    }
}
