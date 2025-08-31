public class BinaryTreeMaximumPathSum_58 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(-15);
        TreeNode b1 = new TreeNode(10);
        TreeNode b2 = new TreeNode(20);

        TreeNode c1 = new TreeNode(15);
        TreeNode c2 = new TreeNode(5);

        TreeNode d1 = new TreeNode(-5);

        a1.left = b1; a1.right = b2;
        b2.left = c1; b2.right = c2;
        c1.left = d1;

        System.out.println(maxPathSum(a1));
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

    private static int res = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        if(root == null) return res;

        res = root.val;

        dfs(root);

        return res;
    }

    /**
     * DFS 返回经过 root 节点但不 split 的最大值
     * */
    private static int dfs(TreeNode root) {
        if(root == null) return 0;

        // root 不为 null
        // 防止负数
        int leftMax = Math.max(dfs(root.left), 0);
        int rightMax = Math.max(dfs(root.right), 0);

        // 动态维护 res
        res = Math.max(res, leftMax+rightMax+root.val);
        // System.out.println("root="+root.val+" leftMax="+ leftMax + " rightMax="+rightMax + " res="+res);

        // 返回不 split 的最大值
        return Math.max(leftMax,rightMax)+root.val;
    }
}
