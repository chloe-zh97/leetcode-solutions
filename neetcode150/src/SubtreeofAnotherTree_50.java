import java.util.Stack;

public class SubtreeofAnotherTree_50 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode b1 = new TreeNode(2);
        TreeNode b2 = new TreeNode(3);

        TreeNode c1 = new TreeNode(4);
        TreeNode c2 = new TreeNode(5);

        TreeNode d1 = new TreeNode(6);

        a1.left = b1; a1.right = b2;
        b1.left = c1; b1.right = c2;
        c1.left = d1;

        TreeNode aa1 = new TreeNode(2);
        TreeNode bb1 = new TreeNode(4);
        TreeNode bb2 = new TreeNode(5);
        aa1.left = bb1;
        aa1.right = bb2;

        System.out.println(isSubtree(a1, aa1));
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

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
       if(subRoot == null) return true;
       // subRoot 不为 null 但是 root 为 null
       if(root == null) return false;

       if(isSameTree(root, subRoot)) return true;

       // 不是同一棵树
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null) return true;
        if(root == null || subRoot == null || root.val != subRoot.val) return false;

        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
    }


}
