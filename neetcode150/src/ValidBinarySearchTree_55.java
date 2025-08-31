

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class ValidBinarySearchTree_55 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode b1 = new TreeNode(2);
        TreeNode b2 = new TreeNode(3);
        a1.left = b1; a1.right = b2;
        System.out.println(isValidBST_3(a1));

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
   * Method 1: 中序遍历是严格递增的
   * */
    public static boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        int pre = Integer.MIN_VALUE;

        while(p != null || !stack.isEmpty()) {
            if(p!=null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                if(p.val <= pre) return false;
                pre = p.val;

                p = p.right;
            }
        }
        return true;
    }

    /**
     * Method 2: 递归
     * */
    public static boolean isValidBST_2(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean check(TreeNode root, long left, long right) {
        if(root == null) return true;
        if(root.val <= left || root.val >= right) return false;
        return check(root.left, left, root.val) && check(root.right, root.val, right);
    }

    /**
     * Method 3: 每个节点不能超过的范围 BFS
     * */

    static class Pair {
        TreeNode node;
        // node.val 的范围 (min, max)
        Long min;
        Long max;

        public Pair() {}
        public Pair(TreeNode _node, long _min, long _max) {
            node = _node;
            min = _min;
            max = _max;
        }
    }

    public static boolean isValidBST_3(TreeNode root) {
        if(root == null) return true;

        Deque<Pair> deque = new LinkedList<>();
        deque.offer(new Pair(root, Long.MIN_VALUE, Long.MAX_VALUE));

        while(!deque.isEmpty()) {
            int size = deque.size();

            for(int i=0;i<size;i++) {
                Pair p = deque.poll();
                long left = p.min, right = p.max;

                // 出队列后 check
                if(p.node.val <= left || p.node.val >= right) return false;

                if(p.node.left != null) {
                    deque.offer(new Pair(p.node.left, left, p.node.val));
                }

                if(p.node.right != null) {
                    deque.offer(new Pair(p.node.right, p.node.val, right));
                }
            }
        }
        return true;
    }


}
