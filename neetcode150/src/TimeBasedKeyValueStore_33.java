import java.util.*;

public class TimeBasedKeyValueStore_33 {
    public static void main(String[] args) {
        TimeMap tm = new TimeMap();
        tm.set("test", "one", 10);
        tm.set("test", "two", 20);
        tm.set("test", "three", 30);

        System.out.println(tm.get("test",15));
        System.out.println(tm.get("test",25));

        Map<String, Deque<Node>> map = tm.map;
        Deque<Node> test = tm.map.get("test");
        for(Node v: test)
            System.out.println(v.value + " "+ v.timestamp);

        System.out.println(tm.get("test",35));
    }

    /**
     * Method 1: Map<String, Deque<Node>>
     *     (a, (timestamp, val))
     * */

    static class Node {
        public int timestamp;
        public String value;

        public Node() {}

        public Node(int t, String v) {
            this.timestamp = t;
            this.value = v;
        }
    }

    /**
     * Method 2: Binary Search
     * */
    static class TimeMap_2 {
        Map<String, List<Node>> map;

        public TimeMap_2() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            List<Node> group = map.getOrDefault(key, new ArrayList<>());
            group.add(new Node(timestamp, value));
            map.put(key, group);
        }

        public String get(String key, int timestamp) {
            if(!map.containsKey(key)) return "";
            List<Node> group = map.get(key);

            // 在 group 中找 <= timestamp 的位置
            int left = 0, right = group.size()-1;
            while(left < right) {
                int mid = left + right + 1>> 1;
                if(group.get(mid).timestamp <= timestamp) left = mid;
                else right = mid - 1;
            }

            return group.get(left).timestamp <= timestamp ? group.get(left).value: "";
        }
    }

    /**
     * Method 1: 单调栈
     * */
    static class TimeMap {

        public Map<String, Deque<Node>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            Deque<Node> deque = map.getOrDefault(key, new ArrayDeque<>());

            // set 严格按照递增顺序
            Node cur = new Node(timestamp, value);
            deque.offerLast(cur);

            map.put(key, deque);
        }

        public String get(String key, int timestamp) {
            Deque<Node> deque = map.getOrDefault(key, new ArrayDeque<>());
            Deque<Node> cache = new ArrayDeque<>();

            while(!deque.isEmpty() && deque.peekLast().timestamp > timestamp) {
                // System.out.println("time:"+timestamp+" peek="+deque.peekLast().timestamp);
                cache.offerLast(deque.pollLast());
            }

            String ans = deque.isEmpty() ? "": deque.peekLast().value;

            // 把 cache 加回来
            while(!cache.isEmpty()) {
                deque.offerLast(cache.pollLast());
            }
            return ans;
        }
    }


    /**
     * Method 3: TreeMap
     * */
    static class TimeMap_3 {
        private Map<String, TreeMap<Integer, String>> map;

        public TimeMap_3() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> group = map.getOrDefault(key, new TreeMap<>());
            group.put(timestamp, value);
            map.put(key, group);
        }

        public String get(String key, int timestamp) {
            if(!map.containsKey(key)) return "";
            TreeMap<Integer, String> group = map.get(key);

            Map.Entry<Integer, String> entry = group.floorEntry(timestamp);

           return entry == null ? "" : entry.getValue();
        }
    }
}
