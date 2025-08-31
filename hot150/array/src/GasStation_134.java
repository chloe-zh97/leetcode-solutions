public class GasStation_134 {
    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    /**
     * Method 1: 暴力法，超时
     * */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for(int i=0;i<n;i++) {
            if(gas[i] < cost[i]) continue;
            if(check(gas, cost, i)) return i;
        }
        return -1;
    }

    private static boolean check(int[] gas, int[] cost, int start) {
        int n = gas.length;
        int cnt = 0, remain = 0;

        for(int i=start;cnt < n;i=(i+1)%n) {
            remain += gas[i]-cost[i];
            if(remain < 0) return false;
            cnt ++;
        }
        return cnt == n;
    }


    /**
     * Method 2: 贪心，亏空最多的站点放最后走
     * */
    public static int canCompleteCircuit_2(int[] gas, int[] cost) {
        int n = gas.length;
        int totalGas = 0, minGas = 0, minPos = -1;

        for(int i=0;i<n;i++) {
            totalGas += gas[i]-cost[i];
            if(totalGas < minGas) {
                minGas = totalGas;
                minPos = i;
            }
        }
        return totalGas < 0 ? -1 : (minPos+1) % n;
    }

}
