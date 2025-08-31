import java.util.Arrays;
import java.util.HashMap;

public class TwoIntegerSumII_11 {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4};
        int target = 3;
        System.out.println(Arrays.toString(twoSum_2(numbers, target)));
    }

    /**
     * Method 1: Sorted array, 双指针
     * O(n)/ O(1)
     * */
    public static int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int i = 0, j = n-1;
        int[] res = {-1, -1};

        while(i < j) {
            if(numbers[i] + numbers[j] == target) {
                res[0] = i+1;
                res[1] = j+1;
                break;
            } else if(numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    /**
     * Method 2: HashMap
     * val, index
     * */
    public static int[] twoSum_2(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = numbers.length;

        int[] res = new int[2];

        for(int i=0;i<n;i++) {
            if(map.containsKey(target-numbers[i])) {
                res[0] = map.get(target-numbers[i]) + 1;
                res[1] = i+1;
                break;
            } else {
                map.put(numbers[i], i);
            }
        }
        return res;
    }
}
