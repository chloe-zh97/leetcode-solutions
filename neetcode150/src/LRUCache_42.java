import java.util.*;

public class LRUCache_42 {
    public static void main(String[] args) {
        LRUCache_2 lru = new LRUCache_2(3);
        lru.put(1,1);
        lru.put(2,2);
        lru.put(3,3);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));
        System.out.println(lru.get(4));

        lru.put(4,4);
        System.out.println(lru.get(1));

        System.out.println(lru.get(2));
        System.out.println(lru.get(3));

        System.out.println(lru.get(4));
        System.out.println(lru.get(2));

        lru.put(1,8);
        lru.put(3,7);

        System.out.println(lru.get(1));

    }

    /**
     * 3,2,1,4
     * 1) 执行 get 操作，如果 map 中有值，直接返回 1 对应的 value 的值，
     * 修改队列中的数变为：3,2,4,1
     * 删除原来 1 所在的节点，在末尾增加 1
     *
     * 如果不删，变为 3,2,-1,4,1
     * map 中存储 int[]: (val, index)
     *
     * 2) 如果 map 中没有值，返回 -1
     * 3) 执行 put 操作，在 map 中加入值，在队列末尾添加新元素
     * 如果满了，队首元素出队
     * */
    static class LRUCache {
        private List<Integer> cache;
        private int size;
        private int capacity;

        // store (key, (val, index))
        private Map<Integer, int[]> map;

        public LRUCache(int capacity) {
            cache = new ArrayList<>();
            size = 0;
            map = new HashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            if(map.containsKey(key)) {
                // 当前值存在, size 不更新
                int[] item = map.get(key);
                int val = item[0], index = item[1];

                // 将原来的位置标记为 -1
                cache.set(index, -1);
                // 重新加入到 cache 的末尾
                cache.add(key);

                int newId = cache.size()-1;
                map.put(key, new int[]{val, newId});
            }
            return -1;
        }

        public void put(int key, int value) {
            if(size >= capacity) {
                // 先要 pop 出元素
                while(!cache.isEmpty() && cache.get(0) == -1) {
                    cache.remove(0);
                }

                // 退出循环后，cache 的第一个元素不为 -1 了，这是真正需要 delete 的元素
                int deleteKey = cache.remove(0);
                size--;

                // 从 map 中删除
                map.remove(deleteKey);
            }

            // 到这里  size < capacity, 将需要加入的元素重新加入
            if(map.containsKey(key)) {
                // cache 中有 key
                int oldId = map.get(key)[1];
                // 将原来的先删除
                cache.set(oldId, -1);

                // 更新 map 和 cache
                cache.add(key);
                map.put(key, new int[]{value, cache.size()-1});
            } else {
                cache.add(key);
                map.put(key, new int[]{value, cache.size()-1});
                size++;
            }
        }
    }

    static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node() {}

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    static class LRUCache_2 {

        private Map<Integer, Node> map;
        private int capacity;
        // 哨兵节点
        private Node head;
        private Node tail;

        public LRUCache_2(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;

            this.head = new Node(0,0);
            this.tail = new Node(0,0);

            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        public int get(int key) {
            if(map.containsKey(key)) {
                // map 中包含了该节点, delete 原来的节点，append 到最后
                Node p = map.get(key);
                remove(p);
                // 将摘除下来的 p 放到 tail 的前面
                insert(p);
                return p.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(!map.containsKey(key)) {
                // cache 中不包含
                if(map.size()==capacity) {
                    map.remove(head.next.key);
                    remove(head.next);
                }
                Node p = new Node(key,value);
                insert(p);
                map.put(key, p);
            } else {
                Node p = map.get(key);
                p.val = value;
                remove(p);
                insert(p);
                map.put(key, p);
            }
        }

        /**
         * 在 tail 前插入 p 节点
         * */
        private void insert(Node p) {
            p.next = tail;
            tail.prev.next = p;
            p.prev = tail.prev;
            tail.prev = p;
        }

        /**
         * 移除节点 p
         * */
        private void remove(Node p) {
            p.prev.next = p.next;
            p.next.prev = p.prev;

            p.prev = null;
            p.next = null;
        }
    }

}
