
public class LowestCommonAncestorinBinarySearchTree_51 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(5);
        TreeNode b1 = new TreeNode(3);
        TreeNode b2 = new TreeNode(8);

        TreeNode c1 = new TreeNode(1);
        TreeNode c2 = new TreeNode(4);
        TreeNode c3 = new TreeNode(7);
        TreeNode c4 = new TreeNode(9);

        TreeNode d1 = new TreeNode(2);

        a1.left = b1; a1.right = b2;
        b1.left = c1; b1.right = c2;

        b2.left = c3; b2.right = c4;

        c1.right = d1;

        System.out.println(lowestCommonAncestor_2(a1, b1, c2).val);

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
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);

        if(p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p,q);

        return root;
    }


    /**
     * Method 2: 迭代
     * */
    public static TreeNode lowestCommonAncestor_2(TreeNode root, TreeNode p, TreeNode q) {
        // 从根节点开始遍历
       TreeNode cur = root;
       while(cur != null) {
           if(p.val < cur.val && q.val < cur.val) cur = cur.left;
           else if(p.val > cur.val && q.val > cur.val) cur = cur.right;
           else return cur;
       }
       return null;
    }
}
