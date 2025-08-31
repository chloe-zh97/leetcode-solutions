public class MaximumPopulationYear_1854 {
    public static void main(String[] args) {
        int[][] logs = {
                {1950,1961},
                {1960,1971},
                {1970,1981}
        };
        System.out.println(maximumPopulation(logs));
    }

    public static int maximumPopulation(int[][] logs) {
        int n = logs.length;
        int[] d = new int[2055];
        // construct diff array
        for(int i=0;i<n;i++) {
            d[logs[i][0]]++;
            d[logs[i][1]]--;
        }

        int s = 0, maxS = Integer.MIN_VALUE, maxI = 0;
        for(int i=1950;i<d.length;i++) {
            s += d[i];
            if(s > maxS) {
                maxS = s;
                maxI = i;
            }
        }
        return maxI;
    }
}
