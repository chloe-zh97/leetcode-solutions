public class BitwiseANDofNumbersRange_201 {
    public static void main(String[] args) {
        int left = 1;
        int right = 2147483647;
        System.out.println(rangeBitwiseAnd_2(left, right));
    }

    /**
     * Method 1: 寻找公共前缀
     * */
    public static int rangeBitwiseAnd(int left, int right) {
        int step = 0;

        while(left < right) {
            left >>= 1;
            right >>= 1;
            step++;
        }

        return left << step;
    }

    /**
     * Method 2: 抹去最右边的1
     * */
    public static int rangeBitwiseAnd_2(int left, int right) {
        while(left < right) {
            right = right & (right-1);
        }
        return right;
    }
}
