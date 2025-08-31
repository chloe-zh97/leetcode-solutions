import java.util.Stack;

public class KthSmallestIntegerinBST_56 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(2);
        TreeNode b1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(3);

        a1.left = b1;
        a1.right = b2;
        System.out.println(kthSmallest(a1, 1));
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
   * Method 1: 中序遍历
   * */
    public static int kthSmallest(TreeNode root, int k) {
        if(root == null) return -1;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        int cnt = 0;
        while(p != null || !stack.isEmpty()) {
            if(p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                cnt++;
                if(cnt == k) return p.val;

                p = p.right;
            }
        }
        return -1;
    }


}
