import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class MaximumDepthofBinaryTree_46 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode b1 = new TreeNode(2);
        TreeNode b2 = new TreeNode(3);
        TreeNode c1 = new TreeNode(4);
        a1.left = b1;
        a1.right = b2;
        b2.left = c1;
        System.out.println(maxDepth_3(null));
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
     * Method 1: 递归
     * */
    public static int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1+Math.max(left, right);
    }

    /**
     * Method 2: dfs
     * */
    static class Pair {
        TreeNode node;
        int level;

        public Pair(TreeNode n, int l) {
            node = n;
            level = l;
        }
    }
    public static int maxDepth_2(TreeNode root) {
        if(root == null) return 0;
        Stack<Pair> stack = new Stack<>();
        Pair p = new Pair(root, 1);
        stack.push(p);

        int ans = 0;
        while(!stack.isEmpty()) {
            Pair cur = stack.pop();
            int curLevel = cur.level;
            ans = Math.max(ans, curLevel);

            if(cur.node.left != null) {
                stack.push(new Pair(cur.node.left, curLevel+1));
            }
            if(cur.node.right != null) {
                stack.push(new Pair(cur.node.right, curLevel+1));
            }
        }
        return ans;
    }

    /**
     * Method 3: BFS
     * */
    public static int maxDepth_3(TreeNode root) {
        if(root == null) return 0;
        Pair p = new Pair(root, 1);
        Deque<Pair> deque = new ArrayDeque<>();
        deque.offer(p);
        int ans = 0;
        while(!deque.isEmpty()) {
            Pair cur = deque.poll();
            TreeNode curNode = cur.node;
            int curLevel = cur.level;
            ans = Math.max(ans, curLevel);
            if(curNode.left != null) deque.offer(new Pair(curNode.left, curLevel+1));
            if(curNode.right != null) deque.offer(new Pair(curNode.right, curLevel+1));
        }
        return ans;
    }

}
