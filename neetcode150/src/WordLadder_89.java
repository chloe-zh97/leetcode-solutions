import java.util.*;

public class WordLadder_89 {
    public static void main(String[] args) {
        String beginWord = "cat";
        String endWord = "sag";
        String[] wordList = {"bat","bag","sag","dag","dot"};
        List<String> list = new ArrayList<>();
        Collections.addAll(list, wordList);
        System.out.println(ladderLength_3(beginWord, endWord, list));
    }

//    static class GNode {
//        String value;
//
//        List<Edge> edges;
//
//        public GNode(String _val) {
//            value = _val;
//            edges = new ArrayList<>();
//        }
//
//        public GNode(String _val, List<Edge> _edges) {
//            value = _val;
//            edges = _edges;
//        }
//    }
//
//    static class Edge {
//        GNode from;
//        GNode to;
//
//        public Edge(GNode _from, GNode _to) {
//            from = _from;
//            to = _to;
//        }
//
//        @Override
//        public String toString() {
//            return "from:"+from.value+" to:"+to.value;
//        }
//    }
//
//    static class Pair {
//        GNode node;
//        int path;
//
//        public Pair() {}
//
//        public Pair(GNode _node, int _path) {
//            node = _node;
//            path = _path;
//        }
//    }
//    /**
//     * Method 1: create map
//     * */
//    private static Map<String, GNode> nodeMap = new HashMap<>();
//
//    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        Set<String> wordSet = new HashSet<>(wordList);
//        // add begin and end word to list
//         wordSet.add(beginWord);
//
//        constructGraph(new ArrayList<>(wordSet));
//        // printGraph();
//        if(!nodeMap.containsKey(endWord)) return 0;
//
//        return bfs(nodeMap.get(beginWord), nodeMap.get(endWord));
//    }
//
//    /**
//     * Find the minimum path of from -> to
//     * */
//    private static int bfs(GNode from, GNode to) {
//        if(from.value.equals(to.value)) return 0;
//
//        int ans = 0;
//
//        HashSet<GNode> visit = new HashSet<>();
//
//        Deque<Pair> deque = new LinkedList<>();
//        deque.offer(new Pair(from, 1));
//        visit.add(from);
//
//        while(!deque.isEmpty()) {
//            Pair cur = deque.poll();
//            GNode curNode = cur.node;
//            int curPath = cur.path;
//
//            // System.out.println("node:"+curNode.value);
//
//            // check all neighbors
//            for(Edge edge: curNode.edges) {
//                GNode nei = edge.to;
//
//                // 找到了，直接返回
//                if(nei.value.equals(to.value)) {
//                    // System.out.println("nei:"+nei.value);
//                    return curPath+1;
//                }
//
//                if(!visit.contains(nei)) {
//                    // 没有访问过邻居节点
//                    deque.offer(new Pair(nei, curPath+1));
//                    visit.add(nei);
//                }
//            }
//        }
//
//        return ans;
//    }
//
//    private static void constructGraph(List<String> wordList) {
//        int n = wordList.size();
//
//        for(int i=0;i<n;i++) {
//            String val = wordList.get(i);
//            GNode node = nodeMap.getOrDefault(val, new GNode(val));
//            nodeMap.put(val, node);
//
//            for(int j=i+1;j<n;j++) {
//                String val2 = wordList.get(j);
//                GNode node2 = nodeMap.getOrDefault(val2, new GNode(val2));
//                nodeMap.put(val2, node2);
//
//                if(hasConnection(val, val2)) {
//                    // System.out.println("node="+node.value+" node2="+node2.value);
//                    // from node to node2
//                    Edge edge1 = new Edge(node, node2);
//                    // from node2 to node
//                    Edge edge2 = new Edge(node2, node);
//
//                    nodeMap.get(val).edges.add(edge1);
//                    nodeMap.get(val2).edges.add(edge2);
//                }
//            }
//        }
//    }
//
//    /**
//     * Check whether two words have connection
//     * */
//    private static boolean hasConnection(String word1, String word2) {
//        if(word1 == null || word2 == null || word1.length() != word2.length()) return false;
//
//        char[] chs1 = word1.toCharArray();
//        char[] chs2 = word2.toCharArray();
//
//        int diff = 0;
//        for(int i=0;i<chs1.length;i++) {
//            if(chs1[i] != chs2[i]) diff++;
//            if(diff > 1) return false;
//        }
//        return true;
//    }
//
//    /**
//     * 统计词频
//     * */
//    private static int[] countFreq(String word) {
//        char[] chs = word.toCharArray();
//        int[] cnt = new int[26];
//
//        for(int i=0;i<chs.length;i++)
//            cnt[chs[i]-'a']++;
//        return cnt;
//    }
//
//    private static void printGraph() {
//        for(Map.Entry<String, GNode> entry: nodeMap.entrySet()) {
//            GNode node = entry.getValue();
//
//            System.out.println("node:"+node.value);
//            for(Edge edge: node.edges) {
//                System.out.println(edge);
//            }
//        }
//    }

    /**
     * Optimize: 精简代码 单向 BFS
     * */
    public static int ladderLength_2(String beginWord, String endWord, List<String> wordList) {
        int m = beginWord.length();

        HashSet<String> wordSet = new HashSet<>(wordList);
        wordSet.add(beginWord);

        if(!wordSet.contains(endWord)) return 0;

        // pattern, 符合 pattern 的所有 words
        Map<String, List<String>> adj = new HashMap<>();

        // construct adj table
        for(String word: wordSet) {
            // 替换第 j 个元素
            for(int j=0;j<m;j++) {
                for(char c='a'; c<='z'; c++) {
                    if(word.charAt(j) == c) continue;

                    String pattern = word.substring(0, j) + "*" + word.substring(j+1, m);

                    List<String> neis = adj.getOrDefault(pattern, new ArrayList<>());
                    neis.add(word);
                    adj.put(pattern, neis);
                }
            }
        }

        HashSet<String> visit = new HashSet<>();
        Deque<String> deque = new LinkedList<>();

        visit.add(beginWord);
        deque.offer(beginWord);
        int ans = 1;

        while(!deque.isEmpty()) {
            int size = deque.size();

            for(int i=0;i<size;i++) {
                String curWord = deque.poll();

                // find curWord's all patterns
                for(int j=0;j<m;j++) {
                    String pattern = curWord.substring(0,j)+ "*" + curWord.substring(j+1, m);
                    // 含有这个 pattern
                    if(adj.containsKey(pattern)) {
                        // 属于这个 pattern 的所有 word
                        for(String nei: adj.get(pattern)) {
                            if(endWord.equals(nei)) return ans+1;

                            if(!visit.contains(nei)) {
                                deque.offer(nei);
                                visit.add(nei);
                            }
                        }
                    }
                }
            }

            ans++;
        }
        return 0;
    }

    /**
     * Method 2: 双向 BFS
     * 两个队列，两个HashMap
     * */
    public static int ladderLength_3(String beginWord, String endWord, List<String> wordList) {
        int m = beginWord.length();

        // 保存全部合理的 word
        HashSet<String> wordSet = new HashSet<>(wordList);
        wordSet.add(beginWord);
        if(!wordSet.contains(endWord)) return 0;

        Deque<String> beginQueue = new LinkedList<>();
        Deque<String> endQueue = new LinkedList<>();
        beginQueue.offer(beginWord);
        endQueue.offer(endWord);

        HashMap<String, Integer> beginMap = new HashMap<>();
        HashMap<String, Integer> endMap = new HashMap<>();
        beginMap.put(beginWord, 1);
        endMap.put(endWord, 1);

        // 二者有一个为空说明双方都不可能到对方那
        while(!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            // keep the beginQueue size smaller than endQueue
            if(beginQueue.size() > endQueue.size()) {
                // swap
                Deque<String> tmpQueue = beginQueue;
                beginQueue = endQueue;
                endQueue = tmpQueue;

                HashMap<String, Integer> tmpMap = beginMap;
                beginMap = endMap;
                endMap = tmpMap;
            }

            // expand from beginQueue
            int size = beginQueue.size();
            for(int i=0;i<size;i++) {
                String word = beginQueue.poll();
                int step = beginMap.get(word);

                // replace word at j
                for(int j=0;j<m;j++) {
                    for(char c='a';c<='z';c++) {
                        if(word.charAt(j) == c) continue;

                        String replaced = word.substring(0,j) + c + word.substring(j+1,m);
                        if(!wordSet.contains(replaced)) continue;


                        if(endMap.containsKey(replaced)) {
                            // 找到了一条路径
                            return step + endMap.get(replaced);
                        }

                        if(!beginMap.containsKey(replaced)) {
                            beginQueue.offer(replaced);
                            beginMap.put(replaced, step+1);
                        }
                    }
                }

            }
        }
        return 0;
    }

}
