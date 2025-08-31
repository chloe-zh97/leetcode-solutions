import java.util.HashMap;

public class MinimumConsecutiveCardstoPickUp_2260 {
    public static void main(String[] args) {
        int[] cards= {1,0,5,3};
        System.out.println(minimumCardPickup(cards));
    }

    public static int minimumCardPickup(int[] cards) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for(int i=0;i<cards.length;i++) {
            if(map.containsKey(cards[i])) {
                ans = Math.min(ans, i-map.get(cards[i]));
            }
            map.put(cards[i], i);
        }
        return ans == Integer.MAX_VALUE ? -1:ans+1;
    }
}
