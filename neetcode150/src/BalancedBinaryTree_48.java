import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BalancedBinaryTree_48 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode b1 = new TreeNode(2);
        TreeNode b2 = new TreeNode(3);
        TreeNode c1 = new TreeNode(4);
        TreeNode d1 = new TreeNode(5);

        a1.left = b1; a1.right = b2;
        b2.left = c1; c1.left = d1;

        System.out.println(isBalanced_3(a1));
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

    static boolean res = true;
    public static boolean isBalanced(TreeNode root) {
        dfs(root);
        return res;
    }

    /**
     * 返回最大高度
     * */
    private static int dfs(TreeNode root) {
        if(root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 发现不一致
        if(Math.abs(left-right) > 1) res = false;
        return Math.max(left, right)+1;
    }

    /**
     * Method 2: 优化，用 int[] gobal variable 存储 isBalanced, height 两个值
     * */
    public static boolean isBalanced_2(TreeNode root) {
        return dfs_2(root)[0] == 1;
    }

    /**
     * dfs 返回每个节点的 isBalanced 和 height
     * */
    private static int[] dfs_2(TreeNode root) {
        if(root == null) return new int[] {1, 0};

        int[] left = dfs_2(root.left);
        int[] right = dfs_2(root.right);

        // 左树和右树都要平衡并且高度差 < 2
        int isBalanced = left[0]*right[0] == 1 && Math.abs(left[1]-right[1]) < 2 ? 1: 0;
        int height = Math.max(left[1], right[1])+1;

        return new int[]{isBalanced, height};
    }



    /**
     * Method 2: 迭代法，后序遍历
     * */
    public static boolean isBalanced_3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root, pre = null;
        Map<TreeNode, Integer> depthMap = new HashMap<>();

        while(p!=null || !stack.isEmpty()) {
            // 向左走到底
            if(p != null) {
                stack.push(p);
                p = p.left;
            } else {
                // 获取栈顶元素
                p = stack.peek();
                // 什么时候需要出栈
                if(p.right == null || pre == p.right) {
                    stack.pop();

                    int leftHeight = depthMap.getOrDefault(p.left, 0);
                    int rightHeight = depthMap.getOrDefault(p.right, 0);

                    if(Math.abs(rightHeight-leftHeight) > 1) return false;
                    depthMap.put(p, Math.max(leftHeight, rightHeight)+1);

                    pre = p;
                    p = null;
                } else {
                    p = p.right;
                }
            }
        }
        return true;
    }
}
