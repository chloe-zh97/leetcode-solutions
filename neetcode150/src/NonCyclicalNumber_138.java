import java.util.HashSet;
import java.util.Set;

public class NonCyclicalNumber_138 {
    public static void main(String[] args) {
        int n = 19;
        System.out.println(isHappy(n));
    }
    public static boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while(n != 1) {
            n = digitalSquareSum(n);
            if(seen.contains(n)) return false;
            seen.add(n);
        }
        return true;
    }

    private static int digitalSquareSum(int n) {
        int sum = 0;
        while(n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }

    /**
     * Method 2: Two Pointers
     * */
    public static boolean isHappy_2(int n) {
        int slow = n, fast = digitalSquareSum(n);
        while(slow != fast) {
            slow = digitalSquareSum(slow);
            fast = digitalSquareSum(fast);
            fast = digitalSquareSum(fast);
        }

        return slow == 1;
    }
    
}
