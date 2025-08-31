import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class CountGoodNodesinBinaryTree_54 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(2);
        TreeNode b1 = new TreeNode(4);
        TreeNode c1 = new TreeNode(10);
        TreeNode c2 = new TreeNode(8);
        TreeNode d1 = new TreeNode(4);

        a1.right = b1;
        b1.left = c1; b1.right = c2;
        c2.left = d1;

        System.out.println(goodNodes(a1));
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

 static class Pair {
        TreeNode node;
        int maxVal;

        public Pair() {}

     public Pair(TreeNode n, int v) {
            this.node = n;
            this.maxVal = v;
     }
 }

    /**
     * 二叉树的层序遍历
     * */
    public static int goodNodes(TreeNode root) {
       if(root == null) return 0;
        Deque<Pair> deque = new LinkedList<>();
        deque.offer(new Pair(root, root.val));

        int ans = 1;
        while(!deque.isEmpty()) {
            int size = deque.size();

            for(int i=0;i<size;i++) {
                Pair cur = deque.poll();
                TreeNode p = cur.node;

                if(p.left != null) {
                    deque.offer(new Pair(p.left, Math.max(p.left.val, cur.maxVal)));
                    if(p.left.val >= cur.maxVal) ans++;
                }

                if(p.right != null) {
                    deque.offer(new Pair(p.right, Math.max(p.right.val, cur.maxVal)));
                    if(p.right.val >= cur.maxVal) ans++;
                }
            }
        }
        return ans;
    }

    public static int goodNodes_2(TreeNode root) {
        if(root == null) return 0;
        Deque<Pair> deque = new LinkedList<>();
        deque.offer(new Pair(root, Integer.MIN_VALUE));

        int ans = 0;
        while(!deque.isEmpty()) {
            int size = deque.size();

            for(int i=0;i<size;i++) {
                Pair cur = deque.poll();
                if(cur.node.val >= cur.maxVal) ans++;

                TreeNode p = cur.node;

                if(p.left != null) {
                    deque.offer(new Pair(p.left, Math.max(p.left.val, cur.maxVal)));
                }

                if(p.right != null) {
                    deque.offer(new Pair(p.right, Math.max(p.right.val, cur.maxVal)));
                }
            }
        }
        return ans;
    }

}
