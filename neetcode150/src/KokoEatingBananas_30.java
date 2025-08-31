import java.util.Arrays;

public class KokoEatingBananas_30 {
    public static void main(String[] args) {
        int[] piles = {25,10,23,4};
        int h = 4;
        System.out.println(minEatingSpeed(piles, h));
    }

    /**
     * Method 1: binary Search
     * 二分吃香蕉速率 k
     * 判断该速率是否能将香蕉在 h 小时内吃完
     * */
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = Arrays.stream(piles).max().getAsInt();
        while(left < right) {
            int mid = left + right >> 1;
            if(check(piles, mid, h)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static boolean check(int[] piles, int rate, int h) {
        int count = 0;
        for(int pile: piles) {
            // 向上取整数
            count += (int) Math.ceil(1.0 * pile / rate);
            if(count > h) return false;
        }
        return true;
    }
}
