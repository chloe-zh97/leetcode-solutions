public class NQueensII_52 {

    public static void main(String[] args) {
        int n = 8;
        System.out.println(totalNQueens(n));
    }

    private static int res = 0;

    public static int totalNQueens(int n) {
        backtracking(0, n, new int[n]);
        return res;
    }

    private static void backtracking(int index, int n, int[] positions) {
        if(index == n) {
            // 找到一种方案
            res += 1;
            return;
        }

        // 第 index 个皇后可以放在哪个位置上
        for(int col=0;col<n;col++) {
            if(canPut(index, col, positions)) {
                positions[index] = col;
                backtracking(index+1, n, positions);
                positions[index] = -1;
            }
        }
    }

    private static boolean canPut(int row, int col, int[] positions) {
        for(int i=0;i<row;i++) {
            if(positions[i] == col) return false;
            if(Math.abs(row-i) == Math.abs(col-positions[i])) return false;
        }
        return true;
    }

}
