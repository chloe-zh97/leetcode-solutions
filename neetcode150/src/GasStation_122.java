public class GasStation_122 {
    public static void main(String[] args) {
        int[] gas = {2,3,4};
        int[] cost = {3,4,5};
        System.out.println(canCompleteCircuit_3(gas, cost));
    }

    /**
     * Method 1: 直接模拟
     * */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
       int n = gas.length;

       // 遍历每个可能的 i 作为起点
        for(int i=0;i<n;i++) {
            // 从 i 位置开出去还剩多少
            int rest = gas[i]-cost[i];
            int index = (i+1) % n;

            // 当前 index 不是到达起点时，rest 必须要有剩余
            while(rest > 0 && index != i) {
                // 离开 index 这个位置，还剩多少汽油
                rest += gas[index] - cost[index];
                index = (index+1) % n;
            }

            if(rest >= 0 && index == i) return i;
        }
        return -1;

    }


    /**
     * Method 2: greedy
     * */
    public static int canCompleteCircuit_2(int[] gas, int[] cost) {
        int n = gas.length;
        int total = 0, cur = 0, res = 0;
        for(int i=0;i<n;i++) {
            total += gas[i] - cost[i];
            cur += gas[i] - cost[i];

            // i 位置到不了就去 i+1 位置
            if(cur < 0) {
                cur = 0;
                res = i+1;
            }
        }
        return total >= 0 ? res: -1;
    }


    /**
     * 找到欠油量最多的 index, 下一个位置就是起点
     * */
    public static int canCompleteCircuit_3(int[] gas, int[] cost) {
        int n = gas.length;
        int total = 0;

        int min = 0, ans = 0;
        for(int i=0;i<n;i++) {
            total += gas[i] - cost[i];
            if(total < min) {
                min = total;
                ans = i+1;
            }
        }
        return total >= 0 ? ans : -1;
    }
}
