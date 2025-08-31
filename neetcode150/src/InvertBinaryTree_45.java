import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class InvertBinaryTree_45 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode b1 = new TreeNode(2);
        TreeNode b2 = new TreeNode(3);

        TreeNode c1 = new TreeNode(4);
        TreeNode c2 = new TreeNode(5);
        TreeNode c3 = new TreeNode(6);
        TreeNode c4 = new TreeNode(7);
        a1.left = b1; a1.right = b2;
        b1.left = c1; b1.right = c2;
        b2.left = c3; b2.right = c4;

        a1 = invertTree_3(a1);
        printTree(a1);
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
    public static TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    private static void printTree(TreeNode root) {
        if(root == null) return;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while(!deque.isEmpty()) {
            TreeNode p = deque.poll();
            System.out.print(p.val+" ");
            if(p.left!=null) deque.offer(p.left);
            if(p.right != null) deque.offer(p.right);
        }
    }

    /**
     * Method 2: 迭代法BFS，遍历整棵树然后交换左右节点
     * */
    public static TreeNode invertTree_2(TreeNode root) {
        if(root == null) return null;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        while(!deque.isEmpty()) {
            TreeNode cur = deque.poll();
            // swap left and right
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;

            if(cur.left != null) deque.offer(cur.left);
            if(cur.right != null) deque.offer(cur.right);
        }
        return root;
    }

    /**
     * Method 3: DFS 迭代
     * */
    public static TreeNode invertTree_3(TreeNode root) {
        if(root == null) return null;
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;

            if(cur.left!=null) stack.push(cur.left);
            if(cur.right!=null) stack.push(cur.right);
        }
        return root;
    }

}
