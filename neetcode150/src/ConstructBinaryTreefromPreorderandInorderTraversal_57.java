import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ConstructBinaryTreefromPreorderandInorderTraversal_57 {
    public static void main(String[] args) {
        int[] preorder = {1};
        int[] inorder = {1};
        TreeNode root = buildTree(preorder, inorder);

        List<List<Integer>> ans = levelOrder(root);
        for(List<Integer> list: ans)
            System.out.println(list);

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

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;

        int n = preorder.length;
        return buildTree(preorder, 0, n-1, inorder, 0, n-1);
    }

    private static TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart > preEnd) return null;

        // 在 inorder 中找 root 的位置，inorder 一定是有序的，二分查找
        int rootPosition = findRootPosition(inorder, inStart, inEnd, preorder[preStart]);

        // left 的范围 [inStart, rootPosition-1]
        int leftSize = rootPosition-inStart;

        TreeNode root = new TreeNode(preorder[preStart]);

        // 构造左子树
        root.left = buildTree(preorder, preStart+1, preStart+leftSize, inorder, inStart, rootPosition-1);
        root.right = buildTree(preorder, preStart+leftSize+1, preEnd, inorder, rootPosition+1, inEnd);
        return root;
    }

    private static int findRootPosition(int[] nums, int start, int end, int target) {
        for(int i=start;i<=end;i++)
            if(nums[i] == target) return i;
        return -1;
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
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
