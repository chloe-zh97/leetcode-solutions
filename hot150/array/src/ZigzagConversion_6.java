import java.util.Arrays;

public class ZigzagConversion_6 {
    public static void main(String[] args) {
        String s = "Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.";
        System.out.println(convert(s, 2));
    }

    public static String convert(String s, int numRows) {
        if(numRows == 1) return s;
        char[] chs = s.toCharArray();
        int n = s.length();
        // 两个方向
        int[][] dirs = {
                {1,0},
                {-1,1}
        };

        char[][] board = new char[numRows][n];
        for(int i=0;i<board.length;i++)
            Arrays.fill(board[i], '#');

        int i=0, j=0, k=0, dir=-1;

        while(k < n) {
            char ch = chs[k++];
            board[i][j] = ch;

            if(i == 0) dir = 0;
            else if(i == numRows-1) dir = 1;

            i += dirs[dir][0];
            j += dirs[dir][1];
        }

        StringBuilder sb = new StringBuilder();
        // 遍历 board
        for(i=0;i<board.length;i++) {
            for(j=0;j<board[0].length;j++)
                if(board[i][j]!='#') sb.append(board[i][j]);
        }

        return sb.toString();
    }
}
