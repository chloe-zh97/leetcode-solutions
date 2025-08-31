import java.util.Arrays;

public class CorporateFlightBookings_1109 {
    public static void main(String[] args) {
        int[][] bookings = {
                {1,2,10},
                {2,2,15}
        };
        int n = 2;
        int[] ans = corpFlightBookings(bookings, n);
        System.out.println(Arrays.toString(ans));
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int m = bookings.length;
        // construct diff array
        int[] d = new int[n+2];
        for(int i=0;i<m;i++) {
            int from = bookings[i][0], to = bookings[i][1], c = bookings[i][2];
            d[from] += c;
            d[to+1] -= c;
        }

        // 还原
        int[] ans = new int[n+2];
        int s = 0;
        for(int i=0;i<d.length;i++) {
            s += d[i];
            ans[i] = s;
        }
        return Arrays.copyOfRange(ans, 1, ans.length-1);
    }
}
