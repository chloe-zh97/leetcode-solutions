import java.util.*;

public class DesignTwitter_64 {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1,10);
        twitter.postTweet(2,20);

        System.out.println(twitter.getNewsFeed(1));
        System.out.println(twitter.getNewsFeed(2));

        twitter.follow(1,2);
        System.out.println(twitter.getNewsFeed(1));

    }

    /**
     * twitMap: userId, List<int[]> twits
     * 其中 int[] 存储的是 timestamp, twitId
     *
     * followMap: userId, Set<Integer> 存储这个 user 和他 follow 的人
     * */
    static class Twitter {

        private int timestamp;
        private Map<Integer, List<int[]>> twitMap;
        private Map<Integer, Set<Integer>> followMap;

        public Twitter() {
            timestamp = 0;
            twitMap = new HashMap<>();
            followMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            List<int[]> twitList = twitMap.getOrDefault(userId, new ArrayList<>());
            twitList.add(new int[] {timestamp++, tweetId});
            twitMap.put(userId, twitList);
        }

        public List<Integer> getNewsFeed(int userId) {
            // 获取这个 userId 的所有 follows
            Set<Integer> follows = followMap.getOrDefault(userId, new HashSet<>());
            // 把自己也加进来
            follows.add(userId);

            // maxHeap, 按照 timestamp 倒序排列
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0]-a[0]);

            for(int user: follows) {
                if(twitMap.containsKey(user)) {
                    // 这个 user 有 twits
                    List<int[]> twits = twitMap.get(user);
                    int index = twits.size()-1;

                    // 只是把这个用户的最后一条加入了优先队列
                    if(index > -1) {
                        int[] item = twits.get(index);
                        int time = item[0], twitId = item[1];
                        pq.offer(new int[]{time, twitId, user, index});
                    }
                }
            }

            List<Integer> ans = new ArrayList<>();
            while(ans.size() < 10 && !pq.isEmpty()) {
                int[] item = pq.poll();
                // 首先把这条记录的 twitId 拿到
                ans.add(item[1]);

                userId = item[2];
                // 把这个 user 的下一条记录插入到 priority queue
                int nextIndex = item[3]-1;

                if(nextIndex > -1) {
                   int[] nextItem = twitMap.get(userId).get(nextIndex);
                    pq.offer(new int[]{nextItem[0], nextItem[1], userId, nextIndex});
                }
            }

            return ans;
        }

        public void follow(int followerId, int followeeId) {
            // 维护 followMap
            Set<Integer> follows = followMap.getOrDefault(followerId, new HashSet<>());
            follows.add(followeeId);
            followMap.put(followerId, follows);
        }

        public void unfollow(int followerId, int followeeId) {
            if(followMap.containsKey(followerId)) {
                Set<Integer> follows = followMap.get(followerId);
                follows.remove(followeeId);
            }
        }
    }
}
