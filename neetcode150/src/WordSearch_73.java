public class WordSearch_73 {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'D'},
                {'S', 'A', 'A', 'T'},
                {'A', 'C', 'A', 'E'}
        };
        String word = "ACAA";
        System.out.println(exist(board, word));
    }


//    private static boolean[][] visit;
//    private static int m;
//    private static int n;
//    private static boolean res = false;
//    private static int[][] dirs = {
//            {-1, 0},
//            {0,1},
//            {1,0},
//            {0,-1}
//    };
//
//    public static boolean exist(char[][] board, String word) {
//        m = board.length;
//        n = board[0].length;
//
//        visit = new boolean[m][n];
//
//        for(int i=0;i<m;i++) {
//            for(int j=0;j<n;j++) {
//                if(board[i][j] == word.charAt(0)) backtracking(board, i, j, new StringBuilder(), word);
//            }
//        }
//        return res;
//    }
//
//    private static void backtracking(char[][] board, int i, int j, StringBuilder sb, String word) {
//        // 等于 word 了
//        if(sb.toString().equals(word)) {
//            res = true;
//            return;
//        }
//
//        // 越界
//        if(i < 0 || i >= m || j < 0 || j >= n) return;
//
//        // 访问过了
//        if(visit[i][j]) return;
//
//        if(sb.length() > word.length()) return;
//
//        // 遍历这个点
//        visit[i][j] = true;
//        sb.append(board[i][j]);
//
//        for(int k=0;k<dirs.length && !res;k++) {
//            backtracking(board, i+dirs[k][0], j+dirs[k][1], sb, word);
//        }
//
//        sb.deleteCharAt(sb.length()-1);
//        visit[i][j] = false;
//    }

    /**
     * Method 2: optimize, 原地修改
     * */
    private static int m;
    private static int n;
    private static boolean res = false;

    public static boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++)
                backtracking(board, word, 0, i, j);
        }
        return res;
    }

    /**
     * backtracking, i 表示遍历到了 word 中的哪个 position
     * */
    private static void backtracking(char[][] board, String word, int i, int x, int y) {
        System.out.println("x="+x+" y="+y);
        // terminate condition
        if(word.length() == i || res) {
            res = true;
            return;
        }

        // not in area
        if(x < 0 || x >= m || y < 0 || y >= n) return;

        if(board[x][y] != word.charAt(i)) return;

        // tag it to visit
        board[x][y] = '#';

        backtracking(board, word, i+1, x+1, y);
        backtracking(board, word, i+1, x-1, y);
        backtracking(board, word, i+1, x, y-1);
        backtracking(board, word, i+1, x, y+1);

        // 还原现场
        board[x][y] = word.charAt(i);
    }
}
