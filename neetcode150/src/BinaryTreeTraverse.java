import java.util.Stack;

public class BinaryTreeTraverse {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public static void main(String[] args) {
            TreeNode a1 = new TreeNode(1);
            TreeNode b1 = new TreeNode(2);
            TreeNode b2 = new TreeNode(3);
            TreeNode c1 = new TreeNode(4);
            a1.left = b1;
            a1.right = b2;
            b2.left = c1;
            preOrderTraverse(a1);
            System.out.println();
            preOrderTraverse_2(a1);
        }

        /**
         * Tree 的中序遍历
         * 往左走到底，走不了的话 pop, 改为往右
         * */
        private static void inOrderTraverse(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode p = root;
            while(p!=null || !stack.isEmpty()) {
                // 确保第一个节点能够进来
                if(p != null) {
                    // 向左走到底
                    stack.push(p);
                    p = p.left;
                } else {
                    // 左边不能走了，pop 出根节点
                    p = stack.pop();
                    System.out.print(p.val+" ");
                    p = p.right;
                }
            }
        }


        /**
         * Tree 的前序遍历 根 - 左 - 右
         * inOrder 和 preOrder 几乎一致，只是输出的时间不一样
         * */
        private static void preOrderTraverse(TreeNode root) {
            TreeNode p = root;
            Stack<TreeNode> stack = new Stack<>();
            while(p != null || !stack.isEmpty()) {
                if(p != null) {
                    System.out.print(p.val+" ");
                    stack.push(p);
                    p = p.left;
                } else {
                    p = stack.pop();
                    p = p.right;
                }
            }
        }

        private static void preOrderTraverse_2(TreeNode root) {
            TreeNode p = root;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(p);

            while(!stack.isEmpty()) {
                p = stack.pop();
                System.out.print(p.val+" ");

                if(p.right != null) stack.push(p.right);
                if(p.left != null) stack.push(p.left);
            }
            System.out.println();
        }


        /**
         * Tree 的后序遍历 左- 右 - 根
         * 两个 stack
         * */
        private static void postOrderTraverse(TreeNode root) {
            Stack<TreeNode> outputStack = new Stack<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()) {
                TreeNode p = stack.pop();
                outputStack.push(p);

                if(p.left != null) stack.push(p.left);
                if(p.right != null) stack.push(p.right);
            }

            while(!outputStack.isEmpty()) {
                System.out.print(outputStack.pop().val+" ");
            }
            System.out.println();
        }


        /**
         * 后序遍历，记录 pre 节点，如果 pre 的 right 是空，或者 pre 已经是 last 了
         * */
        private static void postOrderTraverse_2(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode p = root;
            TreeNode pre = null;
            while(p != null || !stack.isEmpty()) {
                if(p != null) {
                    // 向左走到底
                    stack.push(p);
                    p = p.left;
                } else {
                    // 根节点
                    p = stack.peek();
                    // p 什么时候能出栈？1.p 的右节点是空 or 2.右节点已经处理完了
                    if(p.right == null || pre == p.right) {
                        // 根节点没有右节点了，或者前一个节点已经是右节点了，需要 pop 出根节点
                        stack.pop();
                        System.out.print(p.val+" ");
                        pre = p;
                        p = null;
                    } else {
                        p = p.right;
                    }
                }
            }
        }
    }
}
