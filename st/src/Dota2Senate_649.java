import java.util.ArrayDeque;
import java.util.Deque;

public class Dota2Senate_649 {
    public static void main(String[] args) {
        String senate = "RDD";
        System.out.println(predictPartyVictory_2(senate));
    }

    /**
     * Method 1: 队列
     * */
    public static String predictPartyVictory(String senate) {
        Deque<Character> deque = new ArrayDeque<>();

        // 统计 R 和 D 的总数
        int totalR = 0, totalD = 0;
        for(char c: senate.toCharArray()) {
            deque.offerLast(c);
            if(c == 'R') totalR++;
            else totalD++;
        }

        int banR = 0, banD = 0;

        while(totalR != 0 && totalD != 0) {
            char c = deque.pollFirst();
            if(c == 'R') {
                if(banR <= 0) {
                    // 不能禁止这个R
                    deque.offerLast(c);
                    banD++;
                } else {
                    // 这个R要禁止
                    banR--;
                    totalR--;
                }
            } else {
                if(banD <= 0) {
                    // 无法禁止这个D
                    deque.offerLast(c);
                    banR++;
                } else {
                    banD--;
                    totalD--;
                }
            }
        }
        return totalR == 0 ? "Dire" : "Radiant";
    }

    /**
     * Method 2: 两个队列，存储的是各个议员的下标
     * */
    public static String predictPartyVictory_2(String senate) {
        Deque<Integer> queueR = new ArrayDeque<>();
        Deque<Integer> queueD = new ArrayDeque<>();

        int n = senate.length();
        // 扫描 senate 将下标放入队列中
        for(int i=0;i<n;i++) {
            char c = senate.charAt(i);
            if(c == 'R') queueR.offerLast(i);
            else queueD.offerLast(i);
        }

        while(!queueR.isEmpty() && !queueD.isEmpty()) {
            int indexR = queueR.pollFirst(), indexD = queueD.pollFirst();
            if(indexR < indexD) {
                // D 会被禁止, R 重新入队
                queueR.offerLast(indexR+n);
            } else {
                queueD.offerLast(indexD+n);
            }
        }

        return queueR.isEmpty() ? "Dire" : "Radiant";
    }
}
