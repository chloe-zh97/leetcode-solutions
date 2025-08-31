import java.util.*;

public class AlienDictionary_94 {
    public static void main(String[] args) {
        String[] words = {"wrt","wrf","er","ett","rftt","te"};
        System.out.println(foreignDictionary_2(words));
    }

    /**
     * Method 1: DFS
     * 合理的排序，必须是无环
     * */
    public static String foreignDictionary(String[] words) {
        int n = words.length;
        Map<Character, Set<Character>> adj = new HashMap<>();
        for(String word: words) {
            char[] chs = word.toCharArray();
            for(char c: chs) {
                Set<Character> neis = new HashSet<>();
                adj.put(c, neis);
            }
        }

        // create adj table
        for(int i=0;i<n-1;i++) {
            String s1 = words[i], s2 = words[i+1];

            int len1 = s1.length(), len2 = s2.length();
            int minLen = Math.min(len1, len2);
            // 包含前缀
            if(len1 > len2 && s1.substring(0, minLen).equals(s2.substring(0, minLen))) return "";


            // 遍历每个字符，找到第一个不相同的字符
            int j=0;
            while(j < minLen && s1.charAt(j) == s2.charAt(j)) j++;

            if(j >= minLen) continue;
            // 退出循环后，s1[j] != s2[j]
            char first = s1.charAt(j), second = s2.charAt(j);

            // 建立边关系
            Set<Character> neis = adj.getOrDefault(first, new HashSet<>());
            neis.add(second);
            adj.put(first, neis);
        }

        // visit = 0, 没有被访问过，visit=1, 正在被 dfs 访问，visit = 2, 以及访问完了
        int[] visit = new int[128];
        LinkedList<Character> path = new LinkedList<>();

        for(char c: adj.keySet()) {
            if(dfs(adj, c, visit, path)) return "";
        }

        StringBuilder sb = new StringBuilder();
        for(char c: path)
            sb.append(c);

        return sb.toString();
    }

    /**
     * dfs return true, if cycle exist
     * DFS 判断是否有环，可以通过三色染色法，将 visit[] 数组的定义为0，1，2
     * */
    private static boolean dfs(Map<Character, Set<Character>> adj, char start, int[] visit, LinkedList<Character> path) {
        if(visit[start] == 1) {
            // 正在 dfs 访问的路径上，有环了
            return true;
        }

        if(visit[start] == 2) return false;

        visit[start] = 1;

        for(char nei: adj.get(start)) {
            boolean res = dfs(adj, nei, visit, path);
            if(res) return true;
        }

        // 访问完毕了
        visit[start] = 2;
        path.addFirst(start);
        return false;
    }

    /**
     * Method 2: 拓扑排序
     * 1.构建邻接表，维护入度表
     * 2.将所有入度为0的点入队列
     * 3.不断 poll, 删除这个节点所连的边
     * 4.统计是否能访问到所有的节点
     * */
    public static String foreignDictionary_2(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        // 每个节点的入度
        Map<Character, Integer> indegrees = new HashMap<>();

        // 每个出现在 words 中的字符都要建立 key
        for(String word: words) {
            char[] chs = word.toCharArray();
            for(char c: chs) {
                Set<Character> set = new HashSet<>();
                adj.put(c, set);
                indegrees.put(c, 0);
            }
        }

        // 遍历 words, 建立 mapping 规则, 两两对比
        for(int i=0;i<words.length-1;i++) {
            String w1 = words[i], w2 = words[i+1];
            int len1 = w1.length(), len2 = w2.length();
            int minLen = Math.min(len1, len2);

            // 前缀
            if(len1 > len2 && w1.substring(0, minLen).equals(w2.substring(0, minLen))) return "";

            for(int j=0;j<minLen;j++) {
                if(w1.charAt(j) != w2.charAt(j)) {
                    // 维护 adj table
                    char first = w1.charAt(j), second = w2.charAt(j);
                    adj.get(first).add(second);
                    break;
                }
            }
        }

        for(Map.Entry<Character, Set<Character>> entry: adj.entrySet()) {
            Set<Character> set = entry.getValue();
            for(char c: set)
                indegrees.merge(c, 1, Integer::sum);
        }


        // 到这里，indegree 也维护好了，将所有 indegree = 0 的点加入到队列
        Deque<Character> deque = new LinkedList<>();
        for(Map.Entry<Character, Integer> entry: indegrees.entrySet()) {
            if(entry.getValue() == 0) deque.offer(entry.getKey());
        }


        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            char cur = deque.poll();
            // 当前节点的入度 -1
            indegrees.merge(cur, -1, Integer::sum);
            sb.append(cur);

            // 将它所有入度=0 的邻居入队列
            for(char nei: adj.get(cur)) {
                indegrees.merge(nei, -1, Integer::sum);

                if(indegrees.get(nei) == 0) {
                    deque.offer(nei);
                }
            }
        }

        return sb.length() == adj.size() ? sb.toString() : "";
    }
}
