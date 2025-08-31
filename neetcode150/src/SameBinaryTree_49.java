import java.util.*;

public class SameBinaryTree_49 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(4);
        TreeNode b1 = new TreeNode(7);
        a1.left = b1;

        TreeNode aa1 = new TreeNode(4);
        TreeNode bb1 = new TreeNode(7);
        a1.right = bb1;

        System.out.println(isSameTree_3(a1, aa1));

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
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;

        if(p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * Method 2: BFS 同时遍历两个 tree
     * */
    public static boolean isSameTree_2(TreeNode p, TreeNode q) {
        Deque<TreeNode> deque1 = new LinkedList<>();
        Deque<TreeNode> deque2 = new LinkedList<>();

        deque1.offer(p);
        deque2.offer(q);

        while (!deque1.isEmpty() && !deque2.isEmpty()) {
            int size = deque1.size();
            for (int i = 0; i < size; i++) {
                TreeNode p1 = deque1.poll();
                TreeNode p2 = deque2.poll();

                if (p1 == null && p2 == null) continue;
                if (p1 == null || p2 == null || p1.val != p2.val) return false;

                // check 通过，加入左右节点
                deque1.offer(p1.left);
                deque1.offer(p1.right);

                deque2.offer(p2.left);
                deque2.offer(p2.right);
            }
        }
        return true;
    }


    /**
     * Method 2: DFS 同时放两个节点到 stack 中
     * 先序遍历
     * */
    public static boolean isSameTree_3(TreeNode p, TreeNode q) {
        TreeNode p1 = p, p2 = q;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(p1);
        stack2.push(p2);

        while(!stack1.isEmpty()) {
            p1 = stack1.pop();
            p2 = stack2.pop();

            if(p1 == null && p2 == null) continue;
            if(p1 == null || p2 == null || p1.val != p2.val) return false;

            stack1.push(p1.right);
            stack1.push(p1.left);
            stack2.push(p2.right);
            stack2.push(p2.left);
        }
        return true;
    }


}
