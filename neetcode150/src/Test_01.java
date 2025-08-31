import java.util.*;
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }
public class Test_01 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(-10);
        TreeNode b1 = new TreeNode(9);
        TreeNode b2 = new TreeNode(20);
        a1.left = b1;
        a1.right = b2;

        TreeNode c1 = new TreeNode(-6);
        TreeNode c2 = new TreeNode(18);
        TreeNode c3 = new TreeNode(15);
        b1.left = c1;
        b1.right = c2;
        b2.left = c3;

        TreeNode d1 = new TreeNode(30);
        TreeNode d2 = new TreeNode(11);
        c2.left = d1;
        c2.right = d2;

//        TreeNode a1 = new TreeNode(-2);
//        TreeNode b1 = new TreeNode(-1);
//        a1.left = b1;

       // System.out.println(maxPathSum_2(a1));

        Set<int[]> set = new HashSet<>();
        set.add(new int[]{1,1});
        System.out.println(set.contains(new int[]{1,1}));

        List<Integer> ans = new ArrayList<>();

    }



    /**
     * Method 1: dfs
     * */
    private static int maxVal = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        int res = dfs(root, false);
        return maxVal < 0 ? maxVal : res;
    }
    private static int dfs(TreeNode root, boolean canUp) {
        if(root == null) return 0;
        maxVal = Math.max(maxVal, root.val);
        // 不选
        int res = 0;
        if(canUp) {
            // root 还能往上走
            // 选 root, 那么 root.left 和 root.right 都要能往上
            int left = dfs(root.left, true);
            int right = dfs(root.right, true);
            // left 或者 right 取更大的一边
            res = Math.max(Math.max(left, right) + root.val, res);
        } else {
            // root 不能往上走了
            int left = dfs(root.left, false);
            int right = dfs(root.right, false);
            res = Math.max(Math.max(left, right), res);

            left = dfs(root.left, true);
            right = dfs(root.right, true);
            res = Math.max(res, left+right+root.val);
        }
        return res;
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        // init
        Arrays.setAll(adj, g -> new ArrayList());

        int[] indegrees = new int[numCourses];

        for(int[] edge: prerequisites) {
            int from = edge[1], to = edge[0];
            adj[from].add(to);
            indegrees[to]++;
        }

        Deque<Integer> deque = new LinkedList<>();
        for(int i=0;i<indegrees.length;i++){
            if(indegrees[i] == 0) deque.offerLast(i);
        }

        List<Integer> ans = new LinkedList<>();

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            indegrees[cur]--;
            ans.add(cur);

            for(int to: adj[cur]) {
                indegrees[to]--;
                if(indegrees[to] == 0)
                    deque.offerLast(to);
            }
        }

        if(ans.size() != numCourses) return new int[]{};

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    

}

