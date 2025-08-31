import java.util.*;

public class SerializeandDeserializeBinaryTree_59 {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode b1 = new TreeNode(2);
        TreeNode b2 = new TreeNode(3);
        TreeNode c1 = new TreeNode(4);
        TreeNode c2 = new TreeNode(5);
        a1.left = b1; a1.right = b2;
        b2.left = c1; b2.right = c2;

        Codec_2 cc = new Codec_2();

        String s = cc.serialize(a1);
        System.out.println(s);

        // a1 = cc.deserialize("");

//        cc.inOrderTraverse(a1);
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
   * 1 2 3 # # 4 5 # # # #
   * */
    public static class Codec {

        private static int INVALID_VAL = Integer.MIN_VALUE;
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null) return "";

            StringBuilder sb = new StringBuilder();

            Deque<TreeNode> deque = new LinkedList<>();
            deque.offer(root);

            while(!deque.isEmpty()) {
                int size = deque.size();

                for(int i=0;i<size;i++) {
                    TreeNode p = deque.poll();
                    if(p.val == INVALID_VAL) {
                        sb.append("#").append(" ");
                        continue;
                    } else {
                        sb.append(p.val).append(" ");
                    }

                    if(p.left != null) {
                        deque.offer(p.left);
                    } else {
                        deque.offer(new TreeNode(INVALID_VAL));
                    }

                    if(p.right != null) {
                        deque.offer(p.right);
                    } else {
                        deque.offer(new TreeNode(INVALID_VAL));
                    }
                }
            }

            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null || data.trim().equals("")) return null;

            // 去头去尾，以空格分割
            String[] ds = data.trim().split("\\s+");

            Deque<TreeNode> nodeQueue = new LinkedList<>();
            // dataQueue 中不可能出现 null, 只有可能是数字或者 #
            Deque<String> dataQueue = new LinkedList<>();
            for(String s: ds)
                dataQueue.offer(s);

            int rootVal = Integer.parseInt(dataQueue.poll());
            TreeNode root = new TreeNode(rootVal);
            nodeQueue.offer(root);

            while(!nodeQueue.isEmpty()) {
                int size = nodeQueue.size();

                for(int i=0;i<size;i++) {
                    TreeNode p = nodeQueue.poll();

                    // 构造左右节点
                    String leftVal = dataQueue.poll();
                    if(!leftVal.equals("#")) {
                        p.left = new TreeNode(Integer.parseInt(leftVal));
                        nodeQueue.offer(p.left);
                    }

                    String rightVal = dataQueue.poll();
                    if(!rightVal.equals("#")) {
                        p.right = new TreeNode(Integer.parseInt(rightVal));
                        nodeQueue.offer(p.right);
                    }
                }
            }

            return root;
        }

//      public static void inOrderTraverse(TreeNode root) {
//          Stack<TreeNode> stack = new Stack<>();
//          TreeNode p = root;
//          while(p!=null || !stack.isEmpty()) {
//              // 确保第一个节点能够进来
//              if(p != null) {
//                  // 向左走到底
//                  stack.push(p);
//                  p = p.left;
//              } else {
//                  // 左边不能走了，pop 出根节点
//                  p = stack.pop();
//                  System.out.print(p.val+" ");
//                  p = p.right;
//              }
//          }
//      }

    }


    public static class Codec_2 {

        public String serialize(TreeNode root) {
            Stack<TreeNode> nodeStack = new Stack<>();

            if(root == null) return "";

            StringBuilder sb = new StringBuilder();
            // 中序遍历
            TreeNode p = root;
            while(p != null || !nodeStack.isEmpty()) {
                // 向左走到底
                if(p != null) {
                    sb.append(p.val).append(" ");
                    nodeStack.push(p);
                    p = p.left;
                } else {
                    // p 为 null
                    sb.append("#").append(" ");
                    p = nodeStack.pop();
                    p = p.right;
                }
            }

           return sb.toString();
        }

    }
}
