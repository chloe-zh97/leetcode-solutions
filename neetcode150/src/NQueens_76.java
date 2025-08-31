import java.util.*;

public class NQueens_76 {
    public static void main(String[] args) {
        int n = 4;
        List<List<String>> ans = solveNQueens(n);
        for(List<String> a: ans)
            System.out.println(a);
    }

    private static List<List<String>> ans;

    public static List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        int[] positions = new int[n];
        backtracking(0, positions, n);

        return ans;
    }

    /**
     * 摆放第 index 个皇后
     * */
    private static void backtracking(int index, int[] positions, int N) {
       if(index >= N) {
           List<String> tmp = new ArrayList<>();

           // 摆完了
           char[] row = new char[N];
           for(int i=0;i<positions.length;i++) {
               Arrays.fill(row, '.');
               row[positions[i]] = 'Q';
               tmp.add(String.valueOf(row));
           }

           ans.add(new ArrayList<>(tmp));
           return;
       }

       for(int col = 0; col < N; col++) {
           // 第 index 个皇后是否可以放在 col 位置上
           if(canPut(index, col, positions)) {
               positions[index] = col;
               backtracking(index+1, positions, N);
               positions[index] = 0;
           }
       }

    }

    /**
     * 根据当前的 positions, (i, j) 能被放到当前 j 位置
     * */
    private static boolean canPut(int row, int col, int[] positions) {
        for(int i=0;i<row;i++) {
            if(positions[i] == col) return false;
            // 考虑斜率 k
            if(Math.abs(row-i) == Math.abs(col-positions[i])) return false;
        }
        return true;
    }


}
