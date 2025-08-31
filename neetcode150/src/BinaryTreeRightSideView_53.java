import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeRightSideView_53 {
    public static void main(String[] args) {

    }

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

  /**
   * Method 1: BFS
   * 二叉树的右视图，层次遍历，判断是否为该层的最后一个节点
   * */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);

        while(!deque.isEmpty()) {
            int size = deque.size();
            for(int i=0;i<size;i++) {
                TreeNode p = deque.poll();
                if(p.left != null) deque.offer(p.left);
                if(p.right != null) deque.offer(p.right);
                if(i == size-1) ans.add(p.val);
            }
        }
        return ans;
    }
}
