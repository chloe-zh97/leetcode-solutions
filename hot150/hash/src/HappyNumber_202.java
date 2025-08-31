import java.util.HashSet;

public class HappyNumber_202 {
    public static void main(String[] args) {
        System.out.println(isHappy_2(19));
    }

    /**
     * Method 1: 哈希表判断循环
     * */
    public static boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<>();
        int sum = n;
        while(!seen.contains(sum)) {
            seen.add(sum);
            int next = 0;
            while(sum > 0) {
                int d = sum % 10;
                next += d * d;
                sum /= 10;
            }
            if(next == 1) return true;
            sum = next;
        }
        return false;
    }

    /**
     * Method 2: 快慢指针判断循环
     * */

    public static boolean isHappy_2(int n) {
        int slow = n, fast = getNext(n);
        while(fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    private static int getNext(int n) {
        int next = 0;
        while(n > 0) {
            int d = n % 10;
            next += d * d;
            n = n / 10;
        }
        return next;
    }

}
