import java.util.ArrayDeque;
import java.util.Deque;

public class DiameterofBinaryTree_47 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode b1 = new TreeNode(2);
        TreeNode c1 = new TreeNode(3);
        TreeNode c2 = new TreeNode(4);
        TreeNode d1 = new TreeNode(5);
//        a1.right = b1;
//        b1.left = c1; b1.right = c2;
//        c1.left = d1;
        a1.left = b1;
        b1.left = c1;
        System.out.println(diameterOfBinaryTree_2(a1));
    }

    static class TreeNode {
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

    /**
     * Method 1: 递归，O(n^2)
     * */
    public static int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        while(!deque.isEmpty()) {
            TreeNode cur = deque.poll();
            int leftDepth = maxDepth(cur.left);
            int rightDepth = maxDepth(cur.right);
            ans = Math.max(ans, leftDepth+rightDepth);

            if(cur.left != null) deque.offer(cur.left);
            if(cur.right != null) deque.offer(cur.right);
        }
        return ans;
    }

    private static int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * Method 2: DFS
     * */
    static int res = 0;
    public static int diameterOfBinaryTree_2(TreeNode root) {
        if(root == null) return res;
        dfs(root);
        return res;
    }
    /**
     * 返回最大高度，同时更新 res
     */
    private static int dfs(TreeNode root) {
        if(root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        res = Math.max(res, left+right);
        return Math.max(left,right)+1;
    }
}
