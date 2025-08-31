import java.util.*;

public class ReconstructFlightPath_91 {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        List<String> ticket1 = new ArrayList<>(Arrays.asList("MUC","LHR"));
        List<String> ticket2 = new ArrayList<>(Arrays.asList("JFK","MUC"));
        List<String> ticket3 = new ArrayList<>(Arrays.asList("SFO","SJC"));
        List<String> ticket4 = new ArrayList<>(Arrays.asList("LHR","SFO"));
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);

        ans = findItinerary_4(tickets);
        System.out.println(ans);
    }


//    private static List<String> ans;
//    private static Map<Integer, String> idToNameMap;
//    private static Map<String, Integer> nameToIdMap;
//
//    // BUF: [HOU, JFK]
//    public static List<String> findItinerary(List<List<String>> tickets) {
//        int m = tickets.size();
//
//        tickets.sort((a,b) -> {
//            int cmp = a.get(0).compareTo(b.get(0));
//            if(cmp == 0) return a.get(1).compareTo(b.get(1));
//
//            return cmp;
//        });
//
//        // id to string
//        idToNameMap = new HashMap<>();
//        nameToIdMap = new HashMap<>();
//
//        int n = 0;
//        for(List<String> ticket:tickets) {
//            String src = ticket.get(0), dst = ticket.get(1);
//            if(!nameToIdMap.containsKey(src)) {
//                // source port not exist, create it
//                nameToIdMap.put(src, n);
//                idToNameMap.put(n, src);
//                n++;
//            }
//
//            if(!nameToIdMap.containsKey(dst)) {
//                nameToIdMap.put(dst, n);
//                idToNameMap.put(n, dst);
//                n++;
//            }
//        }
//
//        // create graph
//        List<Integer>[] adj = new ArrayList[n];
//        Arrays.setAll(adj, g -> new ArrayList<>());
//
//        for(List<String> ticket: tickets) {
//            String _src = ticket.get(0), _dst = ticket.get(1);
//            int src = nameToIdMap.get(_src), dst = nameToIdMap.get(_dst);
//            // src - > dst
//            adj[src].add(dst);
//        }
//
//        // print graph
////        printGraph(adj);
//
//        List<String> path = new ArrayList<>();
//        path.add("JFK");
//
//        dfs(adj, nameToIdMap.get("JFK"), m, path);
//
//        return ans;
//    }
//
//    /**
//     *
//     * */
//    private static boolean dfs(List<Integer>[] adj, int start ,int n, List<String> path) {
//
//        if(path.size() == n+1) {
//            ans = new ArrayList<>(path);
//            return true;
//        }
//
//        // 走到死胡同
//        if(adj[start].isEmpty()) return false;
//
//        // copy adj
//        List<Integer>[] tmp = copyAdj(adj);
//
//        // for all its to nodes
//        for(int i=0;i<tmp[start].size();i++) {
//            int _to = tmp[start].get(i);
//            String to = idToNameMap.get(_to);
//
//            path.add(to);
//            tmp[start].remove(i);
//
//            boolean res = dfs(tmp, _to, n, path);
//            if(res) return true;
//
//            // backtracking
//            path.remove(path.size()-1);
//            tmp[start].add(i, _to);
//
//        }
//        return false;
//    }
//
//
//    private static List<Integer>[] copyAdj(List<Integer>[] adj) {
//        List<Integer>[] copy = new ArrayList[adj.length];
//        Arrays.setAll(copy, g -> new ArrayList<>());
//
//        for(int i=0;i<adj.length;i++) {
//            List<Integer> after = new ArrayList<>(adj[i]);
//            copy[i] = after;
//        }
//        return copy;
//    }
//
//    private static void printGraph(List<Integer>[] adj) {
//        for(int i=0;i<adj.length;i++) {
//            String from = idToNameMap.get(i);
//
//            System.out.print("from:"+from+" to:");
//
//            for(int to: adj[i]) {
//                String _t = idToNameMap.get(to);
//                System.out.print(_t+" ");
//            }
//            System.out.println();
//        }
//    }

    /**
     * Method 1: Optimize DFS
     * */
    private static List<String> ans;

    public static List<String> findItinerary_2(List<List<String>> tickets) {
        int m = tickets.size();

        tickets.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                int cmp = o1.get(0).compareTo(o2.get(0));
                if(cmp == 0) return o1.get(1).compareTo(o2.get(1));
                return cmp;
            }
        });

        // 直接用 hashMap 可以存储邻接表
        Map<String, List<String>> adj = new HashMap<>();

        for(List<String> ticket: tickets) {
            String src = ticket.get(0), dst = ticket.get(1);

            List<String> row = adj.getOrDefault(src, new ArrayList<>());
            row.add(dst);

            adj.put(src, row);
        }

        List<String> path = new ArrayList<>();
        path.add("JFK");
        dfs(adj, "JFK", m, path);

        return ans;

    }

    /**
     * dfs from start port
     * */
    private static boolean dfs(Map<String, List<String>> adj, String start, int n, List<String> path) {
        if(path.size() == n + 1) {
            ans = new ArrayList<>(path);
            return true;
        }

        if(!adj.containsKey(start) || adj.get(start).isEmpty()) return false;

        List<String> neighbors = new ArrayList<>(adj.get(start));

        for(int i=0;i<neighbors.size();i++) {
            String nei = neighbors.get(i);

            path.add(nei);
            adj.get(start).remove(i);

            if(dfs(adj, nei, n, path)) return true;

            path.remove(path.size()-1);
            adj.get(start).add(i, nei);
        }
        return false;
    }


    /**
     * Method 2: 欧拉回路，一笔画问题
     * 该节点的所有节点访问完以后，把该节点加入到 path 中
     * */
    public static List<String> findItinerary_3(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> adj = new HashMap<>();

        for(List<String> ticket: tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            PriorityQueue<String> pq = adj.getOrDefault(src, new PriorityQueue<>());
            pq.offer(dst);
            adj.put(src, pq);
        }

        List<String> path = new ArrayList<>();

        dfs_3(adj, "JFK", path);

        Collections.reverse(path);

        return path;
    }

    private static void dfs_3(Map<String, PriorityQueue<String>> adj, String start, List<String> path) {
       PriorityQueue<String> neighbors = adj.get(start);

       while(neighbors != null && !neighbors.isEmpty()) {
           // 取到最小的 neighbor
           String nei = neighbors.poll();

           dfs_3(adj, nei, path);
       }

       path.add(start);
    }

    /**
     * Method 4: 欧拉路径，迭代版本
     * 不断将最小边入栈，如果栈顶的边没有其他邻边了，将这个元素 pop 出，放入路径中
     * */
    public static List<String> findItinerary_4(List<List<String>> tickets) {
        // construct adj table
        Map<String, PriorityQueue<String>> adj = new HashMap<>();
        for(List<String> ticket: tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            PriorityQueue<String> row = adj.getOrDefault(src, new PriorityQueue<>());
            row.add(dst);
            adj.put(src, row);
        }

        LinkedList<String> path = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while(!stack.isEmpty()) {
            String top = stack.peek();

            if(!adj.containsKey(top) || adj.get(top).isEmpty()) {
                // 没有邻边了, 到了死胡同, 加入到 path, 死胡同先加，那么应该后访问
                path.addFirst(stack.pop());
            } else {
                String nei = adj.get(top).poll();
                stack.push(nei);
            }
        }

        return path;
    }
}
