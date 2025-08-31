import java.util.Arrays;

public class Candy_135 {
    public static void main(String[] args) {
        int[] ratings = {1,3,4,5,2};
        System.out.println(candy(ratings));
    }

    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candy = new int[n];
        Arrays.fill(candy, 1);

        // 从左往右
        for(int i=1;i<n;i++) {
            if(ratings[i] > ratings[i-1]) candy[i] = candy[i-1]+1;
        }

        for(int i=n-2;i>=0;i--) {
            if(ratings[i] > ratings[i+1] && candy[i] < candy[i+1]) candy[i] = candy[i+1]+1;
        }

        int ans = 0;
        for(int c: candy)
            ans += c;
        return ans;
    }
}
