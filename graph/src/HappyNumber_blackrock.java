import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class HappyNumber_blackrock {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(isHappyNumber(Integer.parseInt(line)));
        }
    }


    private static boolean isHappyNumber(int num) {
        HashSet<Integer> seen = new HashSet<>();

        while(!seen.contains(num) && num != 1) {
            seen.add(num);
            num = countSum(num);
        }

        return num == 1;
    }

    private static int countSum(int num) {
        int sum = 0;
        while(num > 0) {
            int c = num % 10;
            sum += c*c;
            num /= 10;
        }
        return sum;
    }
}
