public class MergeTripletstoFormTarget_124 {
    public static void main(String[] args) {
        int[][] triplets = {
                {2,5,3},
                {1,8,4},
                {1,7,5}
        };
        int[] target = {2,7,5};
        System.out.println(mergeTriplets(triplets, target));
    }

    /**
     * 在 triplets 中选两个数组合并，成为 target
     * 遍历每个 triple, 如果三元组有某一元不满足条件，continue
     * */
    public static boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean a = false, b = false, c = false;
        for(int[] t: triplets) {
            if(t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) continue;
            a = (a || (t[0] == target[0]));
            b = (b || (t[1] == target[1]));
            c = (c || (t[2] == target[2]));
        }
        return a && b && c;
    }
}
