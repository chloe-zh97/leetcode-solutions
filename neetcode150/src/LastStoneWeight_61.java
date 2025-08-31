import java.util.PriorityQueue;

public class LastStoneWeight_61 {
    public static void main(String[] args) {
        int[] stones = {1,2};
        System.out.println(lastStoneWeight(stones));
    }

    /**
     * 最大堆
     * */
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a,b) -> b-a);

        for(int stone: stones)
            priorityQueue.offer(stone);

        while(priorityQueue.size() > 1) {
            int x = priorityQueue.poll();
            int y = priorityQueue.poll();

            int c = x-y;
            if(c != 0) priorityQueue.offer(c);
        }

        return priorityQueue.isEmpty() ? 0 :priorityQueue.peek();
    }
}
