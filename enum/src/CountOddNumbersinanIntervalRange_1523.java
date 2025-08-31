public class CountOddNumbersinanIntervalRange_1523 {
    public static void main(String[] args) {
        int low = 4, high = 100078;
        System.out.println(countOdds(low, high));
        System.out.println(countOdds_2(low, high));
    }

    public static int countOdds(int low, int high) {
        int cnt = 0;
        for(int i=low;i<=high;i++) {
            cnt += i % 2;
        }
        return cnt;
    }

    /**
     * 分类讨论
     * */
    private static int countOdds_2(int low, int high) {
        if(low % 2 == 0 && high % 2 == 0) {
            // 都为偶数, 2,4, 相减为偶数
            return (high - low) / 2;

        } else if(low % 2 == 0 && high % 2 != 0) {
            // low 为偶数，high 为奇数, 2,5
            return (high - low)/2 + 1;
        } else if(low % 2 != 0 && high % 2 == 0) {
            // low 为奇数，high 为偶数 3,6
            return (high-low)/2+1;
        } else {
            // low 为奇数，high 为奇数, 5,9
            return (high-low)/2+1;
        }
    }
}
