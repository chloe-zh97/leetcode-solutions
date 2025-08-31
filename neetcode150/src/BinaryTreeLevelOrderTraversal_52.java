import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeLevelOrderTraversal_52 {
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

        List<List<Integer>> ans = levelOrder(a1);
        for(List<Integer> row: ans)
            System.out.println(row);

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

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        while(!deque.isEmpty()) {
            int size = deque.size();

            List<Integer> row = new ArrayList<>();
            for(int i=0;i<size;i++) {
                TreeNode p = deque.poll();
                row.add(p.val);

                if(p.left != null) deque.offer(p.left);
                if(p.right != null) deque.offer(p.right);
            }
            ans.add(row);
        }
        return ans;
    }
}
