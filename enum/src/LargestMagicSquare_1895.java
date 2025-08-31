public class LargestMagicSquare_1895 {
    public static void main(String[] args) {
        int[][] grid = {};
        System.out.println(largestMagicSquare(grid));
    }

    public static int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // preRows[i+1][j+1]: grid[i][0...j]的和
        int[][] preRows = new int[m+1][n+1];
        for(int i=1;i<preRows.length;i++) {
            for(int j=1;j<preRows[0].length;j++) {
                // fix row
                preRows[i][j] = preRows[i][j-1] + grid[i-1][j-1];
            }
        }

        int[][] preCols = new int[n+1][m+1];
        for(int j=1;j<preCols.length;j++) {
            for(int i=1;i<preCols[0].length;i++) {
                // fix col
                preCols[j][i] = preCols[j][i-1] + grid[i-1][j-1];
            }
        }

        // 遍历每一个可能的 k
        for(int k=Math.min(m,n);k>0;k--) {
            // 枚举可能的起点
            for(int i=0;i+k<=m;i++) {
                for(int j=0;j+k<=n;j++) {
                    // System.out.println("i="+i+" j="+j+" k="+k);
                    // (i,j) 左上起点，(i,j+k-1) 右上，(i+k-1, j) 左下，(i+k-1,j+k-1) 右下
                    int sum1 = checkRows(preRows, i,j,k);
                    int sum2 = checkCols(preCols, i,j,k);
                    if(sum1 != -1 && sum1 == sum2 && checkDialog(grid, i,j,k,sum1))
                        return k;
                }
            }
        }
        return 1;
    }

    private static boolean checkDialog(int[][] grid, int x, int y, int k, int target) {
        // (x,y) -> (x+k,y+k)
        int i = x, j = y;
        int sum = 0;

        int t = 0;
        while(t < k) {
            sum += grid[i][j];
            i++;
            j++;
            t++;
        }

        if(sum != target) return false;
        sum = 0;

        i = x; j = y + k - 1;
        t = 0;
        while(t < k) {
            sum += grid[i][j];
            i++;
            j--;
            t++;
        }

        return sum == target;
    }

    private static int checkCols(int[][] preCols, int x, int y, int k) {
        // (x, y) 为起点
        int target = preCols[y+1][x+k] - preCols[y+1][x];

        for(int j=y+1;j<y+k;j++) {
            int temp = preCols[j+1][x+k] - preCols[j+1][x];
            if(temp != target) return -1;
        }
        return target;
    }

    private static int checkRows(int[][] preRows, int x, int y, int k) {
        // (x, y) 为起点
        int target = preRows[x+1][y+k] - preRows[x+1][y];
        for(int i=x+1;i<x+k;i++) {
            int temp = preRows[i+1][y+k] - preRows[i+1][y];
            if(target != temp) return -1;
        }
        return target;
    }

//    private static void printMatrix(int[][] nums) {
//        int m = nums.length, n = nums[0].length;
//        for(int i=0;i<m;i++) {
//            for(int j=0;j<n;j++) {
//                System.out.print(nums[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
}
