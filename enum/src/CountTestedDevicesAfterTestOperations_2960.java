import java.util.Arrays;

public class CountTestedDevicesAfterTestOperations_2960 {
    public static void main(String[] args) {
        int[] batteryPercentages = {1,1,2,1,3};
        System.out.println(countTestedDevices_2(batteryPercentages));
    }

    /**
     * Method 1: 暴力法 15%
     * */
    public static int countTestedDevices(int[] batteryPercentages) {
        int n = batteryPercentages.length;
        int cnt = 0;
        for(int i=0;i<n;i++) {
            if(batteryPercentages[i] == 0) continue;

            batteryPercentages[i]++;
            cnt++;
            // [i+1,n-1]
            for(int j=i+1;j<n;j++) {
                batteryPercentages[j] = Math.max(0, --batteryPercentages[j]);
            }
        }
        return cnt;
    }

    /**
     * Method 2: 差分思想
     * */
    public static int countTestedDevices_2(int[] batteryPercentages) {
        int n = batteryPercentages.length;
        int dec = 0;
        for(int i=0;i<n;i++) {
            if(batteryPercentages[i] > dec) {
                dec++;
            }
        }
        return dec;
    }
}
